package mx.com.tcs.Annotations.business;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.com.tcs.Annotations.design.Strategy;
import mx.com.tcs.Annotations.design.StrategyFactory;
import mx.com.tcs.Annotations.messenger.PubsubOutboundGateway;
import mx.com.tcs.Annotations.model.Employee;
import mx.com.tcs.Annotations.repository.EmployeeRepository;
import mx.com.tcs.Annotations.utils.Constants;
import mx.com.tcs.Annotations.utils.ProfileEmployee;

/**
 * The Class EmployeeServiceImpl.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	/** The strategy factory. */
	@Autowired
	private StrategyFactory strategyFactory;

	/** The repository. */
	@Autowired
	private EmployeeRepository repository;

	/** The pubsub. */
	@Autowired
	private PubsubOutboundGateway pubsub;

	/** The user. */
	@Value("${tcs.api.security.user}")
	private String USER;

	/**
	 * Creates the.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	@Override
	public Employee create(final Employee employee) throws IllegalArgumentException {

		Strategy strategy = strategyFactory.get(employee.getProfile());
		strategy.setupProfile(employee);
		employee.setId(UUID.randomUUID());
		employee.setStatus(Constants.CREATED);
		this.sendNotification(Constants.CREATED, employee.getId());
		return repository.save(employee);
	}

	/**
	 * Gets the employee.
	 *
	 * @param id the id
	 * @return the employee
	 */
	@Override
	public Employee getEmployee(final UUID id) {
		Optional<Employee> employee = repository.findById(id);
		return employee.isPresent() ? employee.get() : null;
	}

	/**
	 * Gets the by profile.
	 *
	 * @param profile the profile
	 * @return the by profile
	 */
	@Override
	public List<Employee> getByProfile(final ProfileEmployee profile) {

		List<Employee> availables = repository.findByProfile(profile);
		if (!availables.isEmpty()) {
			return availables.stream().filter(e -> !e.getStatus().equals(Constants.REMOVED))
					.collect(Collectors.toList());
		}
		return availables;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Employee> getAll() {
		List<Employee> availables = repository.findAll();
		if (!availables.isEmpty()) {
			return availables.stream().filter(e -> !e.getStatus().equals(Constants.REMOVED))
					.collect(Collectors.toList());
		}
		return availables;
	}

	/**
	 * Update.
	 *
	 * @param employee the employee
	 * @return the employee
	 */
	@Override
	public Employee update(final Employee employee) throws IllegalArgumentException {
		var employeeDb = this.getEmployee(employee.getId());
		if (employeeDb == null) {
			return null;
		}
		Strategy strategy = strategyFactory.get(employee.getProfile());
		strategy.setupProfile(employee);
		employeeDb.setName(employee.getName());
		employeeDb.setProfile(employee.getProfile());
		employeeDb.setCourses(employee.getCourses());
		employeeDb.setStatus(Constants.UPDATED);
		this.sendNotification(Constants.UPDATED, employee.getId());
		return repository.save(employeeDb);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the employee
	 */
	@Override
	public Employee delete(final UUID id) {
		var employeeDb = getEmployee(id);
		if (employeeDb == null) {
			return null;
		}
		employeeDb.setStatus(Constants.REMOVED);
		this.sendNotification(Constants.REMOVED, id);
		return repository.save(employeeDb);
	}

	/**
	 * Send notification.
	 *
	 * @param operation the operation
	 * @param id        the id
	 */
	private void sendNotification(final String operation, UUID id) {
		StringBuilder sb = new StringBuilder("User ");
		sb.append(operation).append(" by ").append(USER).append(" {").append(id).append("}");
		pubsub.sendToPubSub(sb.toString());
	}

}

package mx.com.tcs.Annotations.design;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import mx.com.tcs.Annotations.utils.ProfileEmployee;

/**
 * A factory for creating Strategy objects.
 */
@Component
public class StrategyFactory {

	/** The strategy. */
	Map<ProfileEmployee, Strategy> strategy = new EnumMap<>(ProfileEmployee.class);

	/** The manager. */
	@Autowired
	@Qualifier("manager")
	private Strategy manager;

	/** The leader. */
	@Autowired
	@Qualifier("leader")
	private Strategy leader;

	/** The programmer. */
	@Autowired
	@Qualifier("programmer")
	private Strategy programmer;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		strategy.put(ProfileEmployee.MANAGER, manager);
		strategy.put(ProfileEmployee.LEADER, leader);
		strategy.put(ProfileEmployee.PROGRAMMER, programmer);
	}

	/**
	 * Gets the.
	 *
	 * @param profile the profile
	 * @return the strategy
	 */
	public Strategy get(ProfileEmployee profile) throws IllegalArgumentException {

		if (profile == null || !strategy.containsKey(profile)) {
			throw new IllegalArgumentException("Invalid argument");
		}

		return strategy.get(profile);
	}

}

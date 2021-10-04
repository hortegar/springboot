package mx.com.tcs.Annotations.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import mx.com.tcs.Annotations.model.Employee;
import mx.com.tcs.Annotations.utils.ProfileEmployee;

/**
 * The Interface EmployeeRepository.
 */
@Repository
public interface EmployeeRepository extends CassandraRepository<Employee, UUID> {

	/**
	 * Find by profile.
	 *
	 * @param profile the profile
	 * @return the list
	 */
	@AllowFiltering
	List<Employee> findByProfile(ProfileEmployee profile);

}

package mx.com.tcs.Annotations.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Table;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import lombok.Data;
import mx.com.tcs.Annotations.utils.ProfileEmployee;

/**
 * The Class Employee.
 */
@Table

/**
 * Instantiates a new employee.
 */
@Data
public class Employee {

	/** The id. */
	@PrimaryKey
	private UUID id;

	/** The name. */
	private String name;

	/** The profile. */
	private ProfileEmployee profile;

	/** The courses. */
	@CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
	private List<String> courses;

	/** The status. */
	private String status;

}

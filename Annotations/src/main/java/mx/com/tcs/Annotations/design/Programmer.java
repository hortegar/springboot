package mx.com.tcs.Annotations.design;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.tcs.Annotations.model.Employee;
import mx.com.tcs.Annotations.utils.Constants;

/**
 * The Class Programmer.
 */
@Service("programmer")
public class Programmer implements Strategy {

	/**
	 * Sets the up profile.
	 *
	 * @param employee the new up profile
	 */
	@Override
	public void setupProfile(Employee employee) {
		List<String> courses = new ArrayList<>();
		courses.add(Constants.JAVA);
		courses.add(Constants.SPRING_BOOT);
		courses.add(Constants.CASSANDRA);
		employee.setCourses(courses);
	}

}

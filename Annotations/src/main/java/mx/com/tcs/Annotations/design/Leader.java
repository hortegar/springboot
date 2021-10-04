package mx.com.tcs.Annotations.design;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.tcs.Annotations.model.Employee;
import mx.com.tcs.Annotations.utils.Constants;

/**
 * The Class Leader.
 */
@Service("leader")
public class Leader implements Strategy {

	/**
	 * Sets the up profile.
	 *
	 * @param employee the new up profile
	 */
	@Override
	public void setupProfile(Employee employee) {
		List<String> courses = new ArrayList<>();
		courses.add(Constants.SECURITY);
		courses.add(Constants.PROJECT_MANAGER);
		courses.add(Constants.CASSANDRA);
		employee.setCourses(courses);
	}

}

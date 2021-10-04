package mx.com.tcs.Annotations.design;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.tcs.Annotations.model.Employee;
import mx.com.tcs.Annotations.utils.Constants;

/**
 * The Class Manager.
 */
@Service("manager")
public class Manager implements Strategy {

	/**
	 * Sets the up profile.
	 *
	 * @param employee the new up profile
	 */
	@Override
	public void setupProfile(Employee employee) {
		List<String> courses = new ArrayList<>();
		courses.add(Constants.PROJECT_MANAGER);
		courses.add(Constants.MANAGEMENT);
		courses.add(Constants.LEADERSHIP);
		employee.setCourses(courses);
	}

}

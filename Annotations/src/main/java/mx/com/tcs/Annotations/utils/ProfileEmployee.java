package mx.com.tcs.Annotations.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The Enum ProfileEmployee.
 */
public enum ProfileEmployee {

	/** The manager. */
	MANAGER,
	/** The leader. */
	LEADER,
	/** The programmer. */
	PROGRAMMER;

	private static Map<String, ProfileEmployee> namesMap = new HashMap<String, ProfileEmployee>(3);

	static {
		namesMap.put("MANAGER", MANAGER);
		namesMap.put("LEADER", LEADER);
		namesMap.put("PROGRAMMER", PROGRAMMER);
	}

	@JsonCreator
	public static ProfileEmployee forValue(String value) {
		return namesMap.get(value);
	}

	@JsonValue
	public String toValue() {
		for (Entry<String, ProfileEmployee> entry : namesMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey();
		}

		return this.toString();
	}

}

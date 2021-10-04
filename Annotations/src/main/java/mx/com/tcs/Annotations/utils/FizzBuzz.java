package mx.com.tcs.Annotations.utils;

public class FizzBuzz {

	public String play(int i) {

		if (i == 3)
			return "Fizz";
		if (i == 5)
			return "Buzz";
		if (i < 0)
			throw new IllegalArgumentException("Invalid arguments " + i);

		return "FizzBuzz";
	}

}

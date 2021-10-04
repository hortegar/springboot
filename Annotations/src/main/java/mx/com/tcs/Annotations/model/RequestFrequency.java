package mx.com.tcs.Annotations.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFrequency {

	private List<Integer> vector;
	private int find;

}

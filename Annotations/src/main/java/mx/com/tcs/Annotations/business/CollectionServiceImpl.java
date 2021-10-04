package mx.com.tcs.Annotations.business;

import org.springframework.stereotype.Service;

import mx.com.tcs.Annotations.model.RequestFrequency;

@Service
public class CollectionServiceImpl implements CollectionService {

	@Override
	public String revert(String str) {

		StringBuilder sb = new StringBuilder(str.length());

		for (int i = str.length() - 1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}

		return sb.toString();
	}

	@Override
	public int findFrequency(RequestFrequency frequency) {

		var temp = 0;
		for (Integer element : frequency.getVector()) {
			if (element == frequency.getFind()) {
				temp++;
			}
		}
		return temp;
	}

}

/**
 * Created by ionut-adrian.ene on 5/29/2017.
 */
public class Main {

	public static boolean validatePermGen(String input) {
		boolean answer = true;
		if (input.length() < 4 || input.charAt(0)!='a')
			answer = false;
		for (int l = 0; l < 4; l++) {
			if (input.charAt(l) == input.charAt(l + 1))
				answer = false;
		}
		return answer;
	}

	public static void permGen(char[] s, int i, int k, char[] buff) {
		if (i < k) {
			for (int j = 0; j < s.length; j++) {

				buff[i] = s[j];
				permGen(s, i + 1, k, buff);
			}
		} else
			if(validatePermGen(String.valueOf(buff))){

			System.out.println(String.valueOf(buff));
		}

	}

	public static void main(String[] args) {
		char[] database = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
		char[] buff = new char[database.length];
		int k = 4;
		for (int i = 1; i <= k; i++) {
			permGen(database, 0, i, buff);
		}

	}


}

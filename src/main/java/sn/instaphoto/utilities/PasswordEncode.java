package sn.instaphoto.utilities;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
public class PasswordEncode {
	
	public static String encode(String input) {
		String output = Base64.getEncoder().encodeToString(input.getBytes());
		return output;
	}

	public static String decode(String input) {
		byte[] decodedBytes = Base64.getDecoder().decode(input);
		String output = new String(decodedBytes);
		return output;
	}

	
	public static void main(String[] args) {
		System.err.println(decode("UGFzc2VyJDEyMw=="));
	}

	

}

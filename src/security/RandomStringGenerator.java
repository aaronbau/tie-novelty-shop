package security;

import java.security.SecureRandom;

public class RandomStringGenerator {
	private final String ALPHANUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private SecureRandom random;
	
	public RandomStringGenerator()
	{
		random = new SecureRandom();
	}
	
	public String generateRandomString(int length)
	{
		StringBuilder randString = new StringBuilder();
		
		for(int i = 0; i < length; i++)
			randString.append(ALPHANUM.charAt((int) (random.nextFloat() * ALPHANUM.length())));
		
		return randString.toString();
	}
}
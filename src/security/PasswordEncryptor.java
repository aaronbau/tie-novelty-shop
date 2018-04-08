package security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordEncryptor {
	private final int KEY_LENGTH = 512;
	private final int ITERATION = 10000;
		
	public String encryptPassword(String password, String salt) 
	{		
		PBEKeySpec pbe = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATION, KEY_LENGTH);
		SecretKeyFactory skf = null;
		byte[] encryptedPass = null;
		
		try
		{
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			encryptedPass = skf.generateSecret(pbe).getEncoded();
		}
		catch(NoSuchAlgorithmException nsae)
		{
			System.out.println(nsae);
		}
		catch(InvalidKeySpecException ikse)
		{
			System.out.println(ikse);
		}
		
		Base64.Encoder b64Encoder = Base64.getEncoder();
		
//		System.out.println("Password - " + password);
//		System.out.println("Salt - " + salt);
//		System.out.println("Encrypted Password - " + b64Encoder.encodeToString(encryptedPass));
		
		return b64Encoder.encodeToString(encryptedPass);
	}
	
	public boolean checkPassword(String inputPassword, String storedPassword, String salt)
	{
		String encryptedPass = encryptPassword(inputPassword, salt);
		
		return encryptedPass.equals(storedPassword);
	}
}

package security;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

public class PasswordEstimator {

	Zxcvbn zxcvbn;
	
	public PasswordEstimator()
	{
		zxcvbn = new Zxcvbn();
	}
	
	public int estimatePassword(String password)
	{
		if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{10,}$"))
		{
			return -1;
		}
		else
		{
			Strength strength = zxcvbn.measure(password);
			return strength.getScore();
		}
	}
}

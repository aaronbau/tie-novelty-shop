let usernameIsValid_S = false;
let passwordIsValid_S = false;
let emailIsValid_S = false;

function checkSignupValid()
{
	console.log(usernameIsValid_S + " " + passwordIsValid_S + " " + emailIsValid_S);
	if(usernameIsValid_S && emailIsValid_S && passwordIsValid_S)
	{
		$('#signup-submit').prop('disabled', false);
	}
}

$(document).ready(function()
{	
	$('#signup-username').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'Signup',
			data : {
				username : this.value
			}
		}).done(function(data)
		{
			let jsonData = JSON.parse(data);
			if("username" in jsonData)
			{
				if(jsonData.username == "yes")
				{
					usernameIsValid_S = true;
					$(".error#signup-username-error").html("");
					checkSignupValid();
				}
				else
				{
					console.log(jsonData.username);
					$(".error#signup-username-error").html(jsonData.username);
				}
			}
		});
	});

	$('#signup-email').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'Signup',
			data : {
				email : this.value
			}
		}).done(function(data)
		{
			let jsonData = JSON.parse(data);
			if("email" in jsonData)
			{
				console.log(jsonData.email);
				if(jsonData.email == "yes")
				{
					emailIsValid_S = true;
					$(".error#signup-email-error").html("");
					checkSignupValid();
				}
				else
				{
					console.log(jsonData.email);
					$(".error#signup-email-error").html(jsonData.email);
				}
			}
		});
	});
	
	$('#signup-password').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'Signup',
			data : {
				password : this.value
			}
		}).done(function(data)
		{
			console.log(data);
			let jsonData = JSON.parse(data);
			if("password" in jsonData)
			{
				if(jsonData.password == "yes")
				{
					passwordIsValid_S = true;
					checkSignupValid();
				}
				
				console.log(jsonData.strength);
				
				switch(jsonData.strength)
				{
					case -1:
						$(".error#signup-password-error").html("Password must contain a lower-case letter, an UPPERCASE letter and a digit; minimum of 10 characters");
						$(".error#signup-password-error").css('color', '#ff5555');
						break;
					case 0:
						$(".error#signup-password-error").html("Password Strength: Weak");
						$(".error#signup-password-error").css('color', '#FFC655');
						break;
					case 1:
						$(".error#signup-password-error").html("Password Strength: Fair");
						$(".error#signup-password-error").css('color', '#BFBF11');
						break;
					case 2:
						$(".error#signup-password-error").html("Password Strength: Good");
						$(".error#signup-password-error").css('color', '#44CC44');
						break;
					case 3:
						$(".error#signup-password-error").html("Password Strength: Very Good");
						$(".error#signup-password-error").css('color', '#4564AB');
						break;
					case 4:
						$(".error#signup-password-error").html("Password Strength: Excellent");
						$(".error#signup-password-error").css('color', '#843FAA');
						break;
				}		
			}
		});
	});
});

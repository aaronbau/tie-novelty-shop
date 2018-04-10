var usernameIsValid = false;
var passwordIsValid = false;
var emailIsValid = false;

function checkValid()
{
	console.log(usernameIsValid + " " + passwordIsValid + " " + emailIsValid);
	if(usernameIsValid && emailIsValid && passwordIsValid)
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
					usernameIsValid = true;
					$(".error#username").html("");
					checkValid();
				}
				else
				{
					console.log(jsonData.username);
					$(".error#username").html(jsonData.username);
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
					emailIsValid = true;
					$(".error#email").html("");
					checkValid();
				}
				else
				{
					console.log(jsonData.email);
					$(".error#email").html(jsonData.email);
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
					passwordIsValid = true;
					checkValid();
				}
				
				console.log(jsonData.strength);
				
				switch(jsonData.strength)
				{
					case -1:
						$(".error#password").html("Password must contain a lower-case letterUPPERCASE letter and a digit; minimum of 10 characters");
						$(".error#password").css('color', '#ff5555');
						break;
					case 0:
						$(".error#password").html("Password Strength: Weak");
						$(".error#password").css('color', '#FFC655');
						break;
					case 1:
						$(".error#password").html("Password Strength: Fair");
						$(".error#password").css('color', '#BFBF11');
						break;
					case 2:
						$(".error#password").html("Password Strength: Good");
						$(".error#password").css('color', '#44CC44');
						break;
					case 3:
						$(".error#password").html("Password Strength: Very Good");
						$(".error#password").css('color', '#4564AB');
						break;
					case 4:
						$(".error#password").html("Password Strength: Tough");
						$(".error#password").css('color', '#843FAA');
						break;
				}		
			}
		});
	});
});

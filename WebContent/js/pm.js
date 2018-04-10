let usernameIsValid_P = false;
let passwordIsValid_P = false;
let emailIsValid_P = false;

function checkManagerValid()
{
	console.log(usernameIsValid_P + " " + passwordIsValid_P + " " + emailIsValid_P);
	if(usernameIsValid_P && emailIsValid_P && passwordIsValid_P)
	{
		$('#pm-submit').prop('disabled', false);
	}
}

$(document).ready(function()
{	
	$('#pm-username').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'CreateProductManager',
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
					usernameIsValid_P = true;
					$(".error#pm-username-error").html("");
					checkManagerValid();
				}
				else
				{
					console.log(jsonData.username);
					$(".error#pm-username-error").html(jsonData.username);
				}
			}
		});
	});

	$('#pm-email').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'CreateProductManager',
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
					emailIsValid_P = true;
					$(".error#pm-email-error").html("");
					checkManagerValid();
				}
				else
				{
					console.log(jsonData.email);
					$(".error#pm-email-error").html(jsonData.email);
				}
			}
		});
	});
	
	$('#pm-password').blur(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'CreateProductManager',
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
					passwordIsValid_P = true;
					checkManagerValid();
				}
				
				console.log(jsonData.strength);
				
				switch(jsonData.strength)
				{
					case -1:
						$(".error#pm-password-error").html("Password must contain a lower-case letter, an UPPERCASE letter and a digit; minimum of 10 characters");
						$(".error#pm-password-error").css('color', '#ff5555');
						break;
					case 0:
						$(".error#pm-password-error").html("Password Strength: Weak");
						$(".error#pm-password-error").css('color', '#FFC655');
						break;
					case 1:
						$(".error#pm-password-error").html("Password Strength: Fair");
						$(".error#pm-password-error").css('color', '#BFBF11');
						break;
					case 2:
						$(".error#pm-password-error").html("Password Strength: Good");
						$(".error#pm-password-error").css('color', '#44CC44');
						break;
					case 3:
						$(".error#pm-password-error").html("Password Strength: Very Good");
						$(".error#pm-password-error").css('color', '#4564AB');
						break;
					case 4:
						$(".error#pm-password-error").html("Password Strength: Excellent");
						$(".error#pm-password-error").css('color', '#843FAA');
						break;
				}		
			}
		});
	});
});

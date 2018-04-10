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
			let jsonData = JSON.parse(data);
			if("password" in jsonData)
			{
				if(jsonData.password == "yes")
				{
					passwordIsValid = true;
					$(".error#password").html("");
					checkValid();
				}
				else
				{
					console.log(jsonData.password);
					$(".error#password").html(jsonData.password);
				}
			}
		});
	});
});

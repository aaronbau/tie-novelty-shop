$(document).ready(function()
{	
	$('#login-button').click(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'Login',
			data : $('#login-form').serialize()

		}).done(function(data)
		{
			let jsonData = JSON.parse(data);
			if("invalid" in jsonData)
			{
				console.log(jsonData.invalid);
				$(".error#login").html(jsonData.invalid);
			}
			else if("href" in jsonData)
			{
				console.log(jsonData.href);
				window.location.href = jsonData.href;
			}
		});
	});
	
});

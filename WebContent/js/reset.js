$(document).ready(function()
{	
	$('#reset').click(function()
	{
		$.ajax
		({
			type : 'POST',
			url : 'ResetPassword',
			data : {
				username : $('#reset-username').val()
			}
		}).done(function(data)
		{
			let jsonData = JSON.parse(data);
			if("success" in jsonData)
			{
				$("#success").removeClass("hidden");
			}
			else if("username" in jsonData)
			{
				$(".error#reset-username").html(jsonData.username);
			}
		});
	})
});
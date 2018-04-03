window.onload = function()
{
	$('#signup-button').click(function()
			{
				console.log("ey work");
				$.ajax
				({
					type : 'POST',
					url : 'Signup',
					data : $('#signup').serialize()
				}).done(function(data)
				{
//					let json = JSON.parse(data);
					console.log(data.type);
				});
			});
}


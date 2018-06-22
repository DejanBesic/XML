$.ajax({
    	url: "../admin/test",
		type: "GET",
		headers: {
			Authorization :"Bearer "+localStorage.getItem("token")
		},
        success: function (data) {},
		error: function(xhr, ajaxOptions, thrownError){
			if(xhr.status == '401'){
				window.location.href = "../AdminLogin.html";
			}
		}
	});
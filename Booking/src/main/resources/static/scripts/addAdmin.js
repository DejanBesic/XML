$(document).ready(function() {
	defaultElements();
});
var token;

function defaultElements(){
	var url = window.location.href;
	var str = url.split("key=");
	if(str.length < 2){
		window.location.href = "../AdminLogin.html";
		return;
	}
	token = str[1];
	var title = "Add Agent";
	document.title = title;
	$('#header').html(title);
}

function addAgent(){
	var data = new Object();
	data.name = $("#name").val();
	data.lastName = $("#lastName").val();
	data.address = $("#address").val();
	data.pmb = $("#pmb").val();
	data.email = $("#email").val();
	
	$.ajax({
    	url: "../admin/addAgent",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	window.location.href = "../AddAgent.html?key="+token;
        },
		error: function(xhr, ajaxOptions, thrownError){
			$("#error").css("visibility", "visible");

		}
	}); 
}

function returnt(){
	window.location.href = "../AdminHome.html?key="+token;
}
	
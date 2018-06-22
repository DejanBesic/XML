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
	var title = "Home";
	document.title = title;
	$('#header').html(title);
	
	$.ajax({
		url: "../user/getUserRole",
		success: function(data){
			console.log(data);
			if(data==3){
				$('#forAdm').append('<button onClick="addnewRequisite()">Add New Requisite</button>');
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}
 

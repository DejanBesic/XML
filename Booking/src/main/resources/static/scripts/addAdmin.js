$(document).ready(function() {
	defaultElements();
});
var token;

function defaultElements(){
	token = localStorage.getItem("token");
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
        	window.location.href = "../AddAgent.html";
        },
		error: function(xhr, ajaxOptions, thrownError){
			$("#error").css("visibility", "visible");

		}
	}); 
}

function returnt(){
	window.location.href = "../AdminHome.html";
}
	
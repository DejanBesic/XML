$(document).ready(function() {
	defaultElements();
});
var token;

function defaultElements(){
	token = localStorage.getItem("token");
	var title = "Users";
	document.title = title;
	$('#header').html(title);
	
	$.ajax({
		url: "../admin/getUsers",
		headers: {
			Authorization :"Bearer "+token
		},
		success: function(data){
			$('#centralPart').html("");
//			console.log(data);
			for(var i =0; i< data.length; i++){
				createUserElement(data[i]);
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function createUserElement(data){
	var str ="";

	str += '<tr class="tr'+data.id+'"><td>' + data.name+' '+data.lastName + '</td><td>'
	+data.email+'</td><td>'
	+'<button class="button approve" onClick="approveUser(' + data.id + ')">Activate</button>'
	+'</td>'
	+'<td>'
	+'<button class="button delete" onClick="blockUser(' + data.id + ')">Block</button>'
	+'</td></tr>';
	
	$('#centralPart').append(str);
}

function approveUser(id){
	var data = new Object();
	data.id = id;
	
	$.ajax({
    	url: "../admin/approveUser",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	var del = '.tr'+id;
        	$(del).remove();
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	}); 
}

function blockUser(id){
	var data = new Object();
	data.id = id;
	
	$.ajax({
    	url: "../admin/blockUser",
		data: JSON.stringify(data),
		type: "POST",
		contentType: "application/json",
		dataType: "json",
		headers: {
			Authorization :"Bearer "+token
		},
        success: function (data) {
        	var del = '.tr'+id;
        	$(del).remove();
        },
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});
}

function addAgent(){
	window.location.href = "../AddAgent.html";
}

function changePassword(){
	window.location.href = "../ChangePassword.html";
}

function manageComments(){
	window.location.href = "../AdminHome.html";
}

function manageCodebooks(){
	window.location.href = "../CodeBooks.html";
}

function logout(){
	$.ajax({
    	url: "../api/admin/logout",
		type: "GET",
        success: function (data) {
        	localStorage.setItem("token", "");
        	window.location.href = "../AdminLogin.html";	
        }
	});
}
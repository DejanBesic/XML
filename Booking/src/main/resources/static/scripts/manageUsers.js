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
	window.location.href = "../AddAgent.html?key="+token;
}
 
function manageComments(){
	window.location.href = "../AdminHome.html?key="+token;
}
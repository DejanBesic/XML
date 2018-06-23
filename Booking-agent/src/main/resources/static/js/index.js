$(document).ready(function(){
    
	showMyFacilities();
	showMessages();
	
	$(document).on('submit', '#newForm', function(e) {
		e.preventDefault();
		createFacility();
	});
});



function showMyFacilities(){
	$.ajax({
			url: '../api/facility/getFacilities',
			headers:{
				"authorization": localStorage.getItem("token")
			},
        	success: function(data){
        		showFacilities(data);
        	}
    	});
	
	return false;
}


function showFacilities(data){
	var el = document.getElementById("facilitiesContainer");
	
	var i;
	for(i=0; i<data.length; i++){
		el.innerHTML+='<div class="w3-panel w3-indigo w3-round-xlarge">' 
			+ '<h3>' + data[i].name + '</h3>' 
			+ '<p>' + data[i].description + '</p>'
			+ '<p>Category: ' + data[i].category + '</p>'
			+ '</div>';
	}
}

function createFacility(){
	if (document.getElementById('image').files[0]!= undefined){

    	var data = new FormData();
		var lista = [];
		var i;
		for(i=0; i<$('#image')[0].files.length; i++){
			//lista.push($('#image')[0].files[i]);
			data.append("images", $('#image')[0].files[i]);
		}
		
        data.append("name", $("#newName").val());
        data.append("description",$("#newDescription").val());
        data.append("location",$("#newLocationSelect").find(":selected").val());
        data.append("category",parseInt($("#newCategorySelect").find(":selected").val()));
        data.append("address",$("#newAddress").val());
        data.append("numberOfPeople",parseInt($("#newNumberOfPeople").val()));
    	//data.append("images", lista);
    	data.append("wifi", $("#newWifi").is(":checked"));
    	data.append("bathroom", $("#newBathroom").is(":checked"));
    	data.append("kitchen", $("#newKitchen").is(":checked"));
    	data.append("tv", $("#newTv").is(":checked"));
    	data.append("halfBoard", $("#newHalf").is(":checked"));
    	data.append("fullBoard", $("#newFull").is(":checked"));
    	data.append("breakfast", $("#newBreakfast").is(":checked"));
    	data.append("parkingLot", $("#newParking").is(":checked"));

    	data.append("app1", parseInt($("#newPrice1").val()));
    	data.append("app2", parseInt($("#newPrice2").val()));
    	data.append("app3", parseInt($("#newPrice3").val()));
    	data.append("app4", parseInt($("#newPrice4").val()));
    	
    	$.ajax({
        	url: "../api/facility/addNewFacility",
        	 type: "POST",
             enctype: 'multipart/form-data',
             data: data,
            processData: false, //prevent jQuery from automatically transforming the data into a query string
            contentType: false,
            cache: false,
            headers:{
				"authorization": localStorage.getItem("token")
			},
            success: function (data) {
            	
            },
            error: function (e) {
            	alert("Enter all fields properly.")
                console.log(e);
            }
        });
    }
}

function showMessages(){
	$.ajax({
		url: '../api/facility/getMessages',
		headers:{
			"authorization": localStorage.getItem("token")
		},
    	success: function(data){
    		$('#msgTable').html("");
    		createMessageHead();
    		for(var i =0; i< data.length; i++){
    			createMessageElement(data[i]);
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			console.log(thrownError);

		}
	});

return false;
}

function createMessageHead(){
	var str ="";
	str +='<tr><th>Message:</th><th>Username:</th></tr>'
		+'<tr>';
	
	$('#msgTable').append(str);
}

function createMessageElement(data){
	var str ="";
	str +='<tr><td>'+data.message+'</td><td>'+ data.senderUsername +'</td></tr>'
		+'<tr>'
		+'<td><input id="in'+data.id+'" type="text" class="form-control"></td>'
		+'<td><button class="button" onClick="sendResponse(' + data.id + ')">Send</button></td>'
		+'</tr>';
	
	$('#msgTable').append(str);
}



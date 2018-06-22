$(document).ready(function(){
    
	showMyFacilities();
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
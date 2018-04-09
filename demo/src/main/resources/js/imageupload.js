/**
 * 
 */
$(document).ready(function(){
	
$('.submit_action').hide();
	
var downloadURL='';
	console.log($(".imgData"));
	var config = {
			apiKey: "AIzaSyBy3zISB1yjUHnMISseNCG3hW1LB_ZWgzs",
		    authDomain: "starterzone-3cc26.firebaseapp.com",
		    databaseURL: "https://starterzone-3cc26.firebaseio.com/",
		    projectId: "starterzone-3cc26",
		    storageBucket: "starterzone-3cc26.appspot.com",
		    messagingSenderId: "276297128242"
		  };
  firebase.initializeApp(config);
  console.log("Done");
  var storage = firebase.storage();
  var storageRef = storage.ref();
  console.log("Done");
  $(".uploadButton").click(function(e){
		e.preventDefault();
		console.log("Done");
		var data = new FormData();
		jQuery.each(jQuery('.imgData')[0].files, function(i, file) {
		    data.append('img-'+i, file);
		});
		console.log($(".imgData") + "Done");
		var uploadTask=storageRef.child('images/'+$(".imgData")[0].files[0].name).put($('.imgData')[0].files[0]);
		uploadTask.on(firebase.storage.TaskEvent.STATE_CHANGED, // or 'state_changed'
				  function(snapshot) {
				    // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
				    var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
				    console.log('Upload is ' + progress + '% done');
				    switch (snapshot.state) {
				      case firebase.storage.TaskState.PAUSED: // or 'paused'
				        console.log('Upload is paused');
				        break;
				      case firebase.storage.TaskState.RUNNING: // or 'running'
				        console.log('Upload is running');
				        break;
				    }
				  }, function(error) {
				  switch (error.code) {
				    case 'storage/unauthorized':
				      // User doesn't have permission to access the object
				      break;

				    case 'storage/canceled':
				      // User canceled the upload
				      break;
				      
				    case 'storage/unknown':
				      // Unknown error occurred, inspect error.serverResponse
				      break;
				  }
				}, function() {
				  // Upload completed successfully, now we can get the download URL
				  downloadURL = uploadTask.snapshot.downloadURL;
				  console.log("Download URL" + downloadURL);
				  $(".img-upload").attr("src",downloadURL);
				  $(".imgURL").val(downloadURL);
				  console.log("Image URL" + $(".imgURL"));
				  $(".submit_action").click();
				});
	})
})
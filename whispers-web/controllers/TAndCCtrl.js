whispersApp.controller('TAndCCtrl',['$scope','$rootScope','$location','transport','mySettings','notify',function($scope,$rootScope,$location,transport,mySettings,notify){		 
	$rootScope.validadmin = true;	
	$rootScope.validUser = true;
	$rootScope.valid = true;
	$rootScope.showNav = true;
		
	if(localStorage.getItem('displayName') != "null"){
		$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('displayName') +"</b></p>");				
	}else{
		$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('loginUser') +"</b></p>");	
	}
	//$rootScope.rForm = false;
	$scope.initLoad=function(){		 
		$('.userManagement').attr('id','');
		$('.sceneManagement').attr('id','');
		$('.winner').attr('id','');
		$('.sponsorCard').attr('id','');
		$('.feedback').attr('id','');
		$('.tc').attr('id','');
		$('.reports').attr('id','');
		$('.playWriters').attr('id','');
		$('.dramaturgs').attr('id','');
		$('.users').attr('id','');
		
		$('#textName').text("T&C");
			
		$('.removeHtml').html("");
		$('.tc').attr('id','active');		
		 
		$scope.requestData = transport.request(mySettings.remoteServerUrl+'web/getFileContent.anka');		
		 
		$scope.requestData.then(function(data){
			var xyz = "";
			xyz = data.data;							
			$scope.text = xyz.toString();
			//$('#cke_15').attr('class','cke_button cke_button__source cke_button_on');
				},function(data){
					$scope.isLoading=false;
				});
	},
	 
	$scope.initLoad();		

	$scope.getData = function(){	

		var flag = true;	

		if($scope.text == ""){
			notify("error","Please enter text");
			var flag = false;
		}	
		if(flag){
			var data = CKEDITOR.instances.editor1.getData();
			
			$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/addContentToFile.anka',data);		 				
			$scope.requestData.then(function(data){
				$scope.jsonData = data.data;							
				var flag = $scope.jsonData[0].success;
				if(flag){
					notify("success","T&C Submitted Successfully");
				}
				$scope.isLoading=false;
			},function(data){
				notify("error","Error in request");
				$scope.isLoading=false;
			});		 
		}
	}	 
}]);
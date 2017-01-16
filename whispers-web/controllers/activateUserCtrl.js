whispersApp.controller('activateUserCtrl',['$scope','$rootScope','$location','$filter','ngTableParams','transport','ngDialog','notify','mySettings',function($scope,$rootScope,$location,$filter,ngTableParams,transport,ngDialog,notify,mySettings){		
	$rootScope.valid = true;
	$rootScope.showNav = true;	    
	$scope.user = {};
	$scope.initLoad=function(){
		
		var str = document.URL;
		var lastIndex = str.lastIndexOf("/")
		var str1 = str.substring(0, lastIndex);
		
		$('#homeLink').attr('href',str1);
			
		var vars = [], hash;
		var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');	   
		for(var i = 0; i < hashes.length; i++)
		{
			hash = hashes[i].split('=');
	        
			if('id1' == hash[0]){
				$scope.user.id = hash[1]; 	        	
			}else if('id2' == hash[0]){
				$scope.user.status = hash[1];	        	
			}       
	    }    	    	   
	    	
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/activateUser.anka',$scope.user);
		
		$scope.requestData.then(function(data){
			$scope.jsonData = data.data;			
			
			var flag = $scope.jsonData[0].success;
			if(flag){				
			}else{
				$('#mainMsg').text("Warning");
				$('#subMsg').text("You have already activated this account.");
			}									
		},function(data){
			notify("error","Error in request");			
		});
		
	};
		
	$scope.initLoad();
	
	/*$scope.getUrlVars = function(){
		var vars = [], hash;
		var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	    
		for(var i = 0; i < hashes.length; i++)
		{
			hash = hashes[i].split('=');
			
			if('id' == hash[0]){
				$scope.user.id = hash[1]; 	        
			}else if('roleId' == hash[0]){
				$scope.user.roleId = hash[1];	        
			}else if('email' == hash[0]){
				$scope.user.email = hash[1];
			}	       	       
		}    
		return hashes;	
	};*/	
}]);


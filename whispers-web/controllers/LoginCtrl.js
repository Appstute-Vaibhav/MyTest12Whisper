whispersApp.controller('LoginCtrl',['$scope','$rootScope', '$http','$location','transport','notify','mySettings',
                                    function($scope,$rootScope,$http,$location,transport,notify,mySettings){	
		
	$scope.loginData=[];		
	$rootScope.roleId = 2;		
	$rootScope.id = null;	
	$rootScope.showNav = false;	
	$rootScope.valid = false;
	
	$scope.loginUser=function(loginForm){					
		if(loginForm.$valid){
			var flag = true;	
			if($scope.flag == true){
				if($('#combo').val() == 'null'){
					notify("error","Please select role");
					flag = false;
				}
			}
			
			if(flag){
				$scope.user.roleId = $('#combo').val();	
				$scope.result = transport.request(mySettings.remoteServerUrl+'app/authenticateUser.anka',$scope.user);							
				$scope.result.then(function(data){
					$scope.jsonData=data.data;								
					var flag = $scope.jsonData[0].success;
					if(flag){					
						localStorage.setItem("loginUser",$scope.jsonData[0].response.user.firstName);
						localStorage.setItem("displayName",$scope.jsonData[0].response.user.displayName);
						localStorage.setItem("userId",$scope.jsonData[0].response.user.id);
						localStorage.setItem("roleId",$scope.jsonData[0].response.user.roleId);
						
						if(localStorage.getItem('displayName') != "null"){
							$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('displayName') +"</b></p>");			
						}else{
							$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('loginUser') +"</b></p>");			
						}
						
						$scope.userId = $scope.jsonData[0].response.user.id;
						$scope.role = $scope.jsonData[0].response.user.roleId;
												
						if($scope.role == 1){				
							$scope.role = "Admin";
							$location.path('/adminDashboard');			
							$rootScope.validadmin=true;			
							$rootScope.valid=true;			
							$rootScope.validUser = true;
							localStorage.setItem("roleId",1);
						}else if($scope.role == 2){						
							$scope.role = "Playwriter";
							$location.path('/manageScenes');	    		
							$rootScope.validPlayWriter=true;				
							$rootScope.valid=true;				
							$rootScope.validUser = true;	
							localStorage.setItem("roleId",2);
						}else if($scope.role == 3){	
							$scope.role = "Dramaturg";
							$location.path('/users');				
							$rootScope.validDramaturg=true;				
							$rootScope.valid=true;			
							$rootScope.validUser = true;	
							localStorage.setItem("roleId",3);
						}else{
							notify("error","");
						}
						localStorage.setItem("role",$scope.role);					
					}else{
						$scope.msg = $scope.jsonData[0].messages.message;
						notify("error",$scope.msg);
					}
					localStorage.setItem('userId',$scope.userId);
					
					$scope.processingRequest=false;				
				},function(data){notify("error","Login Failed..!!!")});				
			}
		}
		else{		
			if (loginForm.loginPassword.$error.required) {
				notify("error","Please enter password");			
			}
			if (loginForm.username.$error.required) {
				notify("error","Please enter email Id");			
			}						  
		}	
	}
	
	$scope.validUsername = function(){
		$('#combo').html("");
		if($('#username').val() != ''){
			var user = {'userName':$('#username').val()};
			$scope.result = transport.request(mySettings.remoteServerUrl+'web/validUserName.anka',user);							
			$scope.result.then(function(data){
				$scope.jsonData=data.data;				    				
				var arr1 = $scope.jsonData[0].response.user;				
				var arr = [];
				for(var a = 0 ; a < arr1.length ;a++){
					var obj1 = arr1[a];
					if(obj1.roleId != 4 && obj1.status != 0){
						arr.push(obj1);
					}					
				}
				if(arr.length > 1){
					$scope.flag = true;
					$('#showDropDown').show();
					$('#combo').html("<option value='null'>Please Select Role</option>");
					for(var i = 0 ; i < arr.length ; i++){
						var obj = arr[i];					
						if(obj.roleId == 1 && obj.status == 1){
							$('#combo').append("<option value='1'>Admin</option>");							
						}else if(obj.roleId == 2 && obj.status == 1){
							$('#combo').append("<option value='2'>Playwriter</option>");														
						}else if(obj.roleId == 3 && obj.status == 1){
							$('#combo').append("<option value='3'>Dramaturg</option>");														
						}											
					}
				}else if(arr.length == 1){
						$scope.flag = false;
						$('#showDropDown').hide();
						for(var i = 0 ; i < arr.length ; i++){
							var obj = arr[i];
							var obj = arr[i];					
							if(obj.roleId == 1 && obj.status == 1){
								$('#combo').html("<option value='1'>Admin</option>");							
							}else if(obj.roleId == 2 && obj.status == 1){
								$('#combo').html("<option value='2'>Playwriter</option>");														
							}else if(obj.roleId == 3 && obj.status == 1){
								$('#combo').html("<option value='3'>Dramaturg</option>");														
							}
						}
				}else if(arr1.length == 1){
					$scope.flag = false;
					$('#showDropDown').hide();
					$('#combo').html("<option value='0'>Please Select Role</option>");					
				}else{
					$scope.flag = false;
					$('#showDropDown').hide();
					$('#combo').html("<option value='-1'>Please Select Role</option>");
				}						
			},function(data){
				notify("error","error occurred..!!");
			});        					
		}				
	}		
	
		
	$scope.clear=function(){		
		$scope.user.userName=null;
		$scope.user.password=null;		
		$scope.user.roleId=null;				
	}		
}]);

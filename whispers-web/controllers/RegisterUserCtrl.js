whispersApp.controller('RegisterUserCtrl',['$scope','$rootScope','$location','$http','ngDialog','transport','notify','mySettings',function($scope,$rootScope,$location,$http,ngDialog,transport,notify,mySettings){
		 
	$scope.showCounter=1;	 	 
	
	if(localStorage.getItem('role') == 'Admin'){
		$scope.showBack = false;		 
	}else{		 
		$scope.showBack = true;
		$rootScope.hideNav = true;
		$rootScope.showNav = true;
	}
	$rootScope.valid=true;
	 	 	 	
	$scope.initLoad=function(){		 
		$('.removeHtml').html("");
		$scope.showToRegister = true;
		$('#changeTitle').text('Playwriter');
		if(localStorage.getItem('displayName') != "null"){
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('displayName') +"</b></p>");			
		}else{
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('loginUser') +"</b></p>");			
		}
	},
     	
	$scope.initLoad();	  	

	$scope.registerUser = function(registerForm){        
    	var flag = true;
    	if($('#password').val() != $('#confirmPass').val()){
    		flag = false;
    		notify("error","Password and confirm password are not matched");
    	}   
    	     	
    	if ($('#firstName').val() == '' || (registerForm.firstName.$dirty && registerForm.firstName.$invalid)) {
    		flag = false;
    		$('#firstName').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			registerForm.firstName.$error.required = registerForm.firstName.$dirty = registerForm.firstName.$invalid = true;
		}else{
			$('#firstName').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			registerForm.firstName.$error.required = registerForm.firstName.$dirty = registerForm.firstName.$invalid = false;
		}
		if ($('#lastName').val() == '' || (registerForm.lastName.$dirty && registerForm.lastName.$invalid)) {
			$('#lastName').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');			
			registerForm.lastName.$error.required = registerForm.lastName.$dirty = registerForm.lastName.$invalid = true;
		}else{
			$('#lastName').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			registerForm.lastName.$error.required = registerForm.lastName.$dirty = registerForm.lastName.$invalid = false;
		}
		    			
		if($('#dateOfBirth').val() == '') {
			$('#dateOfBirth').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			flag = false;        		
			$scope.pickDate = true;    				
			$('#pickDate').show();
		}else{
			$('#dateOfBirth').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			$('#pickDate').hide();
			$scope.pickDate = false;
		}
		
		if($('#mobileNumber').val().length < 10 || $('#mobileNumber').val().length > 12 || $('#mobileNumber').val() == '' || (registerForm.mobileNumber.$dirty && registerForm.mobileNumber.$invalid)) {
			$('#mobileNumber').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			flag = false;        		
			registerForm.mobileNumber.$error.required = registerForm.mobileNumber.$dirty = registerForm.mobileNumber.$invalid = true;
		}else{    		
			$('#mobileNumber').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			registerForm.mobileNumber.$error.required = registerForm.mobileNumber.$dirty = registerForm.mobileNumber.$invalid = false;
		}
		    			    			
		if($('#gender').val() == '' || (registerForm.gender.$dirty && registerForm.gender.$invalid)) {
			$('#gender').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			flag = false;        	
			registerForm.gender.$error.required = registerForm.gender.$dirty = registerForm.gender.$invalid = true;
		}else{
			$('#gender').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			registerForm.gender.$error.required = registerForm.gender.$dirty = registerForm.gender.$invalid = false;
		}
		
		if($('#email').val() == '' || (registerForm.email.$dirty && registerForm.email.$invalid)) {
			$('#email').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			flag = false;        		
			registerForm.email.$error.required = registerForm.email.$dirty = registerForm.email.$invalid = true;
		}else{
			$('#email').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			registerForm.email.$error.required = registerForm.email.$dirty = registerForm.email.$invalid = false;
		}
		
		/*if($('#userName').val() == '') {
			$('#userName').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			flag = false;
    		
			registerForm.userName.$error.required = registerForm.userName.$dirty = registerForm.userName.$invalid = true;
		}else{
			$('#userName').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			registerForm.userName.$error.required = registerForm.userName.$dirty = registerForm.userName.$invalid = false;
		}*/    			    		
		
		if($('#password').val() == '') {
			$('#password').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			flag = false;
    		
			registerForm.password.$error.required = registerForm.password.$dirty = registerForm.password.$invalid = true;
		}
		else{
			if( $('#password').val().length < 3){
				registerForm.password.$error.minlength = true;
			}else{
				$('#password').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
				registerForm.password.$error.minlength = registerForm.password.$error.required = registerForm.password.$dirty = registerForm.password.$invalid = false;
			}				
		}			
		if($('#confirmPass').val() == '') {
			$('#confirmPass').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			flag = false;        		
			registerForm.confirmPass.$error.required = registerForm.confirmPass.$dirty = registerForm.confirmPass.$invalid = true;
		}else{
			if( $('#confirmPass').val().length < 3){
				registerForm.confirmPass.$error.minlength = true;
			}else{
				$('#confirmPass').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
				registerForm.confirmPass.$error.minlength = registerForm.confirmPass.$error.required = registerForm.confirmPass.$dirty = registerForm.confirmPass.$invalid = false;					
			}				
		}								
		
		if(flag){
			var flag1 = false;
			if($scope.flag == false && $scope.user.id > 0){
				flag1 = confirm('Do you want to update existing record?');
			}else{
				flag1 = true;
			}
			
			if(flag1){
				$scope.isLoading = true;
				$('#saveBtn').hide();
        		
        		$scope.user.password = $scope.password;
        		$scope.user.userName = $scope.user.email;        		
        		if(localStorage.getItem('userId') == null || localStorage.getItem('userId') == "undefined"){        			        			
        			$scope.user.modifiedBy = 1;
            		$scope.user.createdBy = 1;            		            		
        		}else{
        			$scope.user.modifiedBy = localStorage.getItem('userId');
            		$scope.user.createdBy = localStorage.getItem('userId');        		            		
        		}	 	        			        	
        		if($scope.flag ==  false){        			
        			$scope.user.roleId = $rootScope.roleId;
        			$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/updateUserRecord.anka',$scope.user);        			
        		}else if($scope.flag == true){
        			/*var role = null;
        			if($rootScope.roleId == 2){
        				role = $scope.roleName + ',' + 'Playwriter';
        			}else{
        				role = $scope.roleName + ',' + 'Dramaturg';
        			}*/
        			/*var roleArray = role.split(",");
        			roleArray.sort();
        			var roleNm = roleArray.join();
        			$scope.user.address1 = roleNm;*/
        			var str = document.URL;
        			var lastIndex = str.lastIndexOf("/")
        			var str1 = str.substring(0, lastIndex);
        			$scope.user.address2 = str1;	        	
        		       			
        			/*if($scope.user.roleId == 2 || $scope.user.roleId == 3 || $scope.user.roleId == 5 || $scope.user.roleId == 6 || $scope.user.roleId == 7 || $scope.user.roleId == 8 || $scope.user.roleId == 9 || $scope.user.roleId == 11 || $scope.user.roleId == 13 || $scope.user.roleId == 14 || $scope.user.roleId == 15){
        				$scope.user.status = 4;
        			}else if($scope.user.roleId == 1 && $rootScope.roleId == 2){
        				$scope.user.status = 6;
        			}else if($scope.user.roleId == 1 && $rootScope.roleId == 3){
        				$scope.user.status = 8;
        			}else if($scope.user.roleId == 4 && $rootScope.roleId == 2){
        				$scope.user.status = 10;
        			}else if($scope.user.roleId == 4 && $rootScope.roleId == 3){
        				$scope.user.status = 12;
        			}else{
        				$scope.user.status = 1;
        			}*/
        			$scope.user.roleId = $rootScope.roleId;        			
        			$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/overrideUserRecord.anka',$scope.user);	        			
        		}else{
        			$scope.user.roleId = $rootScope.roleId;
        			$scope.requestData=transport.request(mySettings.remoteServerUrl+'app/registerUser.anka',$scope.user);
        		}
        				 				
				$scope.requestData.then(function(data){
					$scope.jsonData=data.data;
					var data=$scope.jsonData[0].success;
					var msg = $scope.jsonData[0].messages.message;
					if(data){						
						if($scope.flag == false){
							notify("success","User updated successfully");	
						}
						else if($scope.flag == true){
							notify("success","Please check your email for activation of user");
						}else{
							notify("success","User saved successfully");
						}						
						if($rootScope.roleId == 2 && localStorage.getItem('roleId') == 1){							
							$location.path('/abc');								
							setTimeout(function(){
								$location.path('/adminDashboard');
								$scope.isLoading = false;
							},500);
						}else if($rootScope.roleId == 3 && localStorage.getItem('roleId') == 1){
							$location.path('/abc');								
							setTimeout(function(){
								$location.path('/dramaturg');
								$scope.isLoading = false;
							},500);
						}else{	
							$location.path('/abc');																						
							setTimeout(function(){
								$location.path('/');
								$scope.isLoading = false;
								location.reload();
							},1000);							
						}						
					}else{
						notify("error",msg);
						$('#saveBtn').show();
						$scope.isLoading = false;
					}
				});											
        	}else{
        		$scope.reset(false);
        	}
		}									
	}
        
	
	
	$scope.validUsername = function(){
		if($('#email').val() != ''){
			var user = {'userName':$('#email').val()};		
			$scope.result = transport.request(mySettings.remoteServerUrl+'web/validUserName.anka',user);	
				
			$scope.result.then(function(data){
				$scope.jsonData=data.data;				    				
				var flag = $scope.jsonData[0].success;
				if(flag){								
				}
				else{
					var arr = $scope.jsonData[0].response.user;				
					var flag = true;					
					for(var i = 0 ; i< arr.length ; i++){
						var obj = arr[i];
						if($rootScope.roleId == obj.roleId){
							flag = false;							
						}
					}
					if(!flag){
						notify("success","You are already exist user");
						$('#email').val("");
					}else{
						var msg = confirm($scope.jsonData[0].messages.message);						
						if(msg){
							$scope.flag = true;
							var arr = $scope.jsonData[0].response.user;				
							for(var i = 0 ; i< arr.length ; i++){
								$scope.user = arr[i];								
							}
							var d = new Date($scope.user.dateOfBirth);							
							var date = d.getFullYear()+ '-' + (d.getMonth()+1) + '-' + d.getDate() ;                    	  
							$scope.user.dateOfBirth = date;
							$scope.user.password = undefined;
						}else{
							notify("error","Please enter another email address");
						}
					}
					
				}    							
			},function(data){
				notify("error","error occurred..!!");
			});        	
		}			
	}
	
	$scope.showList = function(){       
		setTimeout(function(){
			$('#resetForm').click();				
		},500);			
		$rootScope.showToRegister = false;        	
	}
                
	$scope.reset = function(flag){       
		$scope.registerForm.$setPristine();

		if(flag){
			$('#pickDate').hide();
			$('#firstName').val('');
			$('#lastName').val('');
			$('#dateOfBirth').val('');
			$('#mobileNumber').val('');
			$('#email').val('');
			$('#gender').val('');
			$('#userName').val('');        
			$('#password').val('');
			$('#confirmPass').val('');
		}        	        	
	}	 	 
}]);

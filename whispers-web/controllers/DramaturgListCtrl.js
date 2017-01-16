whispersApp.controller('DramaturgListCtrl',['$scope','$rootScope','$location','$filter','ngTableParams','transport','ngDialog','notify','mySettings',function($scope,$rootScope,$location,$filter,ngTableParams,transport,ngDialog,notify,mySettings){	
	
	$scope.checkedRow=[];
	$scope.showButton=false;
	$rootScope.roleId = 3;
	$rootScope.validadmin = true;
	$rootScope.validUser = true;
	$rootScope.valid = true;
	$rootScope.showNav = true;
	
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

		$('.removeHtml').html("");
		$('.userManagement').attr('id','active');
		$('.dramaturgs').attr('id','active');
			
		$rootScope.showToRegister = false;
		$scope.isLoading = true;
		if(localStorage.getItem('userId') == null){			
			$location.path('/login');
			setTimeout(function(){				
				location.reload();
			},500);
		}
		$('#textName').text("Dramaturg");
		if(localStorage.getItem('displayName') != "null"){	
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('displayName') +"</b></p>");			
		}else{
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('loginUser') +"</b></p>");			
		}                       
		var  data = {roleId : $rootScope.roleId};
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/fetchAllUserRecords.anka',data);		 				
		$scope.requestData.then(function(data){
			$scope.jsonData=data.data;
			var arr = [];
			var arrPush = [];
			arr = $scope.jsonData[0].response.users;
			for(var i = 0 ; i < arr.length ; i++){
				var obj = arr[i];
				if(obj.roleId == 3){
					arrPush.push(obj);
				}
			}
			
			$scope.renderTable(arrPush);	
			$scope.isLoading = false;					
		},function(data){
			notify("error","Error in request");
			$scope.isLoading = false;	
		});						
	}
	$scope.initLoad();   	  
	
	$scope.addUserByAdmin = function(){
		$pristine = true;	
		$scope.isLoading = true;
		$('#changeTitle').text('Add Dramaturg');
		$scope.showCancel = true;
		$scope.showBack = false;
		$scope.hideReset = false;
		$('#asd').hide();
		$rootScope.showToRegister=true;
		$scope.user = {};     
		$scope.editable = false;
		$scope.confirmPass = '';      		
		$scope.isLoading = false;
	},
		 
	$scope.renderTable = function(records){
		var data = records;
			
		if(data.length == 0){
			$scope.emptiness = true;
		}else{
			$scope.tableParams = new ngTableParams({
				page: 1,            // show first page
				count: 50,          // count per page
				filter: {
					firstName: ''       // initial filter
				},
				sorting: {
					firstName: 'asc'     // initial sorting
				}
			}, {
				total: data.length, // length of data
				getData: function($defer, params) {
					// use build-in angular filter
					var filteredData = params.filter() ?
							$filter('filter')(data, params.filter()) :
								data;
							var orderedData = params.sorting() ?
									$filter('orderBy')(filteredData, params.orderBy()) :
										data;

									params.total(orderedData.length);// set total for recalc pagination
									$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
				}
			});
		}			
	}	
	 
	$scope.editTableRow=function(rowId){
		$pristine = true;
		$scope.isLoading = true;
		$('#changeTitle').text('Edit Dramaturg');
		$scope.showCancel = true;
		$scope.hideReset = true;
		$scope.flag = false;
		var data = {
				roleId:$rootScope.roleId,
				id:rowId
		}
		$rootScope.showToRegister=true;			 
		$scope.user = {};
		//Send Request to get data for pSchema 
		$scope.editDataRequest=transport.request(mySettings.remoteServerUrl+'web/fetchUserRecord.anka',data);
		$scope.editDataRequest.then(function(data){                            
			$rootScope.editUserRoleJsonData=data.data;
			$scope.user = $rootScope.editUserRoleJsonData[0].response.user;                           
			
			var d = new Date($scope.user.dateOfBirth);	

			var date = d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate();
                    	   
			$scope.user.dateOfBirth = date;
			$scope.editable = true;
			$scope.password = $scope.user.password;
			$scope.confirmPass = $scope.user.password;
			$scope.isLoading = false;
		},function(data){
			notify("error","error occurred..!!");
			$scope.isLoading = false;
		});			 
	}	

	
	$scope.close=function(){
		ngDialog.close();
	}
		
	$scope.deleteUserRoleTableRow=function(rowId,bit){		
		var val = confirm("Are you sure?");
	
		if(val){
			$scope.isLoading = true;
			var data = {
					roleId:3,
					id:rowId,
					status:bit,
					//modifiedBy:localStorage.getItem('userId'),
					//createdBy:localStorage.getItem('userId')
			}
			$scope.bit = val;	
			var arrayData = [];
			arrayData.push(data);
			$scope.deleteDataRequest=transport.request(mySettings.remoteServerUrl+'web/deleteUserRecords.anka',arrayData);
			$scope.deleteDataRequest.then(function(data){
				$scope.jsonData=data.data;
				var flag = $scope.jsonData[0].success;
				var msg = $scope.jsonData[0].messages.message		    	 
				if(flag){
					$location.path('/abc');
					if(bit == 0 || bit == 3 || bit == 5){
						notify("success","User Blocked successfully");		    			 
					}
					if(bit == 1 || bit == 4){
						notify("success","User Unblocked successfully");
					}		    		 
					$scope.renderTable($scope.jsonData[0].response.users);	
					setTimeout(function(){
						$location.path('/dramaturg');
						$scope.isLoading = false;
					},500);
				}else{
					notify("error",msg);
					$scope.isLoading = false;
				}	    	 
			},function(){notify("error","Error in request");
			$scope.isLoading = false;
			});				
		}	                        	
	}      
}]);
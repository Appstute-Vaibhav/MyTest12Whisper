whispersApp.controller('UserListCtrl',['$scope','$rootScope','$location','$filter','ngTableParams','transport','ngDialog','notify','mySettings',function($scope,$rootScope,$location,$filter,ngTableParams,transport,ngDialog,notify,mySettings){		
	$scope.checkedRow=[];
	$rootScope.showNav = true;
	$rootScope.valid = true;
	$scope.dataList = [];
	$scope.value= null;
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
		$('.users').attr('id','active');
			
		$('#textName').text("Users");		
			
		$scope.isLoading=true;	
		if(localStorage.getItem('userId') == null){			
			$location.path('/login');
			setTimeout(function(){				
				location.reload();
			},500);
		}
			
		if(localStorage.getItem('displayName') != "null"){
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('displayName') +"</b></p>");			
		}else{
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('loginUser') +"</b></p>");			
		}
		
		if(localStorage.getItem('roleId') == 1 || localStorage.getItem('roleId') == 2){
			$scope.showDate = true;
			$scope.showRate = true;
			$scope.showEmail = false;							
		}
		else{				
			$scope.showDate = false;
			$scope.showRate = false;
			$scope.showEmail = true;				
		}
			
		var data = {roleId:localStorage.getItem('roleId'),id:localStorage.getItem('userId')};

		$scope.requestData = transport.request(mySettings.remoteServerUrl+'web/fetchAllUser12WhispersRecords.anka',data);		 				
						
		$scope.requestData.then(function(data){							
			$scope.jsonData=data.data;
			var data1 = $scope.jsonData[0].response.user12Whispers;
			var data = [];
			if(data1.length == 0){
				$scope.emptiness = true;
			}else{														
				//var data1 = [{'sceneId':'1','sceneTitle':'abc','user':'asd'},{'sceneId':'1','sceneTitle':'abc','user':'qaz'},{'sceneId':'1','sceneTitle':'abc','user':'azx'},{'sceneId':'1','sceneTitle':'abc','user':'qwerty'},{'sceneId':'1','sceneTitle':'abc','user':'asdasd'},{'sceneId':'1','sceneTitle':'abc','user':'123'},{'sceneId':'2','sceneTitle':'xyz','user':'sdas'},{'sceneId':'2','sceneTitle':'xyz','user':'aasd'},{'sceneId':'2','sceneTitle':'xyz','user':'dfsdfsd'},{'sceneId':'2','sceneTitle':'xyz','user':'sdasdas'},{'sceneId':'2','sceneTitle':'xyz','user':'sdsadsa'},{'sceneId':'2','sceneTitle':'xyz','user':'wewsdsd'}];					
				var whispers = [];
				//var dataCollection = [];
				var scene = null;					
				var j = 0;					
				for(var i = 0;i<data1.length;i++){
					var obj = data1[i];
					var d = new Date(obj.dateWhispersSubmitted);
					var date = d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate();		   
					switch (new Date(d.getFullYear() , (d.getMonth()) , d.getDate(), 0 , 0, 0).getDay()) {
					case 0:
						$scope.day = "Sunday";
						break;
					case 1:
						$scope.day = "Monday";
						break;
					case 2:
						$scope.day = "Tuesday";
						break;
					case 3:
						$scope.day = "Wednesday";
						break;
					case 4:
						$scope.day = "Thursday";
						break;
					case 5:
						$scope.day = "Friday";
						break;
					case 6:
						$scope.day = "Saturday";
						break;
					}						
					if(scene != obj.sceneId){							
						j++;
						$scope.collection = {};							
						$scope.collection.sceneTitle = obj.sceneTitle;
						$scope.collection.address1 = $scope.day;
						$scope.collection.dateWhispersSubmitted = date;
						$scope.collection.sceneId = obj.sceneId; 
						whispers[j] = new Array();
						var object = {};
						object.firstName = obj.firstName;
						object.lastName = obj.lastName
						object.email = obj.email;
						object.id = obj.id;
						object.rating = obj.rating; 
						whispers[j].push(object);
						scene = obj.sceneId;	
						$scope.collection.set = whispers[j];
						data.push($scope.collection);
					}else if(scene == obj.sceneId){
						var object = {};
						object.firstName = obj.firstName;
						object.lastName = obj.lastName
						object.email = obj.email;
						object.id = obj.id;
						object.rating = obj.rating;
						whispers[j].push(object);													
						$scope.collection.set = whispers[j];							
					}						
				}														
					
				$scope.dataList = $scope.jsonData[0].response.user12Whispers;	
				$scope.tableParams = new ngTableParams({
					page: 1,            // show first page
					count: 50,          // count per page
					filter: {
						sceneTitle: ''       // initial filter
					},
					sorting: {
						sceneTitle: 'asc'     // initial sorting
					}
				},							    
				{
					total: data.length, // length of data
					getData: function($defer, params) {
						// use build-in angular filter
						var filteredData = params.filter() ?
								$filter('filter')(data, params.filter()) :
									data;									
								var orderedData = params.sorting() ?
										$filter('orderBy')(filteredData, params.orderBy()) :
											data;
										params.total(orderedData.length); // set total for recalc pagination
										$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
					}
				});					
			}				
		},function(data){
			notify("error","Error in request");				
		})
		$scope.isLoading=false;
	}
	$scope.initLoad();
	
	$scope.newValue = function(value) {
		$scope.rating = value;
	}
	
	$scope.ratingUser = function(){			
		if($scope.rating == undefined){
			notify("error","Please select rating");
		}else{
			var flag = confirm("Are you sure?");
			if(flag){
				var data = {
						id:$scope.userId,
						rating:$scope.rating,
						submittedById:localStorage.getItem('userId'),
						modifiedBy:localStorage.getItem('userId'),
						createdBy:localStorage.getItem('userId')
				}				
				$scope.editDataRequest=transport.request(mySettings.remoteServerUrl+'web/addWhispersRatingRecord.anka',data);				
				$scope.editDataRequest.then(function(data){				
					$scope.jsonData=data.data;
					var flag = $scope.jsonData[0].success;
					var msg = $scope.jsonData[0].messages.message
					if(flag){
						ngDialog.close();
						$location.path('/abc');
						notify("success","Rate submitted successfully");
						setTimeout(function(){
							$location.path('/users');
						},500);
				    	 }else{
				    		 notify("error",msg);
				    	 }							
				},function(data){
					notify("error","Error in request");
				});					
			}						
		}			
		$scope.rating = undefined;
	}	
	
	$scope.getOriginalWhispers = function(id){
		ngDialog.open({
			template:'releases/master/partials/getOriginalScene.html',
			scope:$scope 	
		}) 	
		var data = {id:id};
		$scope.scene = {};    
		
		$scope.showRates = true;
		$scope.editDataRequest=transport.request(mySettings.remoteServerUrl+'web/fetchSceneRecord.anka',data);
		$scope.editDataRequest.then(function(data){            		
			$scope.jsonData=data.data;
			$scope.scene = $scope.jsonData[0].response.scene;       	  		
			$scope.scene.sceneDescription = JSON.parse($scope.scene.sceneDescription);
			$rootScope.editUserRoleJsonData=[$scope.scene];            			              				            	       	                     	               	  
		},function(data){
			notify("error","error occurred..!!");
		});	    	 
	}	     
	
	$scope.editTableRow=function(id){	 
		$scope.rating = undefined;
		$scope.isLoading=true;	
		$scope.userId = id;	    	
		if(localStorage.getItem('roleId')== 1 || localStorage.getItem('roleId')== 2){
			$scope.showRates = true;
		}else{
			$scope.showRates = false;
		}	    	 
		for(var i = 0 ; i < $scope.dataList.length ; i++){            		  
			var json = $scope.dataList[i];            		  
			if(json.id == id){            			  
				$scope.value = json.rating;	    			 
				if($scope.value == 0){
					$scope.showSubmit = true;
				}	    			 
				$rootScope.editUserRoleJsonData=[json];	    			 
				ngDialog.open({
					template:'releases/master/partials/get12Whispers.html',	    				 
					scope:$scope	
				})            			  
			}
		}        
		if($scope.value > 0){
			$scope.radioDis = true;
		}else{
			$scope.radioDis = false;
		}	    	 
		$scope.isLoading=false;	
	}
              
	$scope.close=function(){
		ngDialog.close();	
	}	 
}]);

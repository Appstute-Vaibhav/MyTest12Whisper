whispersApp.controller('CommentsCtrl',['$scope','$rootScope','$location','$filter','ngTableParams','transport','ngDialog','notify','mySettings',function($scope,$rootScope,$location,$filter,ngTableParams,transport,ngDialog,notify,mySettings){	
	$rootScope.validadmin = true;	
	$rootScope.validUser = true;
	$rootScope.valid = true;
	$rootScope.showNav = true;
	$scope.checkedRow=[];	
	$scope.initLoad=function(){
		if(localStorage.getItem('userId') == null){			
			$location.path('/login');
			setTimeout(function(){				
				location.reload();
			},500);
		}
		if(localStorage.getItem('displayName') != null){
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('displayName') +"</b></p>");			
		}else{
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('loginUser') +"</b></p>");			
		}
		$scope.processingRequest=true;
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/fetchAllSceneRecords.anka');		 				
		
		$scope.requestData.then(function(data){		 					
			$scope.jsonData=data.data;
			$scope.renderTable($scope.jsonData[0].response.scenes);
			$scope.processingRequest=false;						
		},function(data){
			notify("error","Error in request");	
		});
	}
	$scope.initLoad();
  
	$scope.close=function(){
		ngDialog.close();
	}
	
	$scope.renderTable = function(dataList){	
		var data1 = dataList;						
		if(data1.length == 0){
			$scope.emptiness = true;	
		}else{
			$scope.data = [];
			for(var i = 0;i<data1.length;i++){
				var obj = data1[i];														
				var d = new Date(obj.dateCommentSubmitted);
				var date = d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate();		                    	   
				obj.dateCommentSubmitted = date;								
				$scope.data.push(obj);
			}
		
			$scope.tableParams = new ngTableParams({
				page: 1,            // show first page
		        count: 50,          // count per page
		        filter: {
		        	dateCommentSubmitted: ''       // initial filter
		        },
		        sorting: {
		        	dateCommentSubmitted: '1'     // initial sorting
		        }
			},
		    
			{
				total: $scope.data.length, // length of data
				getData: function($defer, params) {
					// use build-in angular filter
					var filteredData = params.filter() ?
							$filter('filter')($scope.data, params.filter()) :
								$scope.data;
							var orderedData = params.sorting() ?
									$filter('orderBy')(filteredData, params.orderBy()) :
										$scope.data;
									params.total(orderedData.length); // set total for recalc pagination
									$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
				}
			});
		}		
	}

	$scope.deleteComments=function(id){
		var val = confirm("Are you sure?");
		if(val){			
			var data = [{id : id}];
			$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/deleteSceneCommentsRecords.anka',data);
			$scope.requestData.then(function(data){				
				$scope.jsonData=data.data;
				$scope.renderTable($scope.jsonData[0].response.sceneComments);
				var flag = $scope.jsonData[0].success;
				var msg = $scope.jsonData[0].messages.message
				if(flag){
					notify("success","record deleted successfully");		    		 
				}else{
					notify("error",msg);
				}	
				$scope.processingRequest=false;			
			},function(data){
				notify("error","Error in request");	
			});
		}    		
	}       
}]);
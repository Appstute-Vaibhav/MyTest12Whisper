whispersApp.controller('UserReportCtrl',['$scope','$rootScope','$filter','ngTableParams','ngDialog','transport','notify','mySettings',function($scope,$rootScope,$filter,ngTableParams,ngDialog,transport,notify,mySettings){		 	
	$rootScope.validadmin = true;	
	$rootScope.validUser = true;
	$rootScope.valid = true;
	$rootScope.showNav = true;
		
	if(localStorage.getItem('displayName') != "null"){
		$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('displayName') +"</b></p>");			
	}else{	
		$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('loginUser') +"</b></p>");		
	}	
	
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
			
		$('#textName').text("User Report");
			
		$('.removeHtml').html("");
		$('.reports').attr('id','active');
		 
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/fetchSceneUserReport.anka');
		$scope.requestData.then(function(data){							
			$scope.jsonData=data.data;
			var data1 = $scope.jsonData[0].response.scenes;
			
			if(data1.length == 0){						
				$scope.emptiness = true;
			}else{	
				var data = [];
				var scenes = [];
				var scene = null;					
				var j = 0;					
				for(var i = 0;i<data1.length;i++){
					var obj = data1[i];
					var d = new Date(obj.dateSubmitted);
					var date = d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate();		                    	   												
					if(scene != obj.id){							
						j++;
						$scope.collection = {};							
						$scope.collection.sceneTitle = obj.sceneTitle;								
						$scope.collection.dateSubmitted = date;								
						scenes[j] = new Array();
						var object = {};
						object.submittedByName = obj.submittedByName;
						scenes[j].push(object);
						scene = obj.id;	
						$scope.collection.set = scenes[j];
						data.push($scope.collection);
					}else if(scene == obj.id){
						var object = {};
						object.submittedByName = obj.submittedByName;								
						scenes[j].push(object);													
						$scope.collection.set = scenes[j];							
					}						
				}													
					
				$scope.tableParams = new ngTableParams({
					page: 1,            // show first page
					count: 50,          // count per page
					filter: {
						sceneTitle:''       // initial filter
					},
					sorting: {
						sceneTitle:'asc'     // initial sorting
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
	},	
	
	$scope.initLoad();	
}]);

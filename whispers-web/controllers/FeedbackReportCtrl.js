whispersApp.controller('FeedbackReportCtrl',['$scope','$rootScope','$filter','ngTableParams','ngDialog','transport','notify','mySettings',function($scope,$rootScope,$filter,ngTableParams,ngDialog,transport,notify,mySettings){		 
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
			
		$('#textName').text("Feedback Report");
			
		$('.removeHtml').html("");
		$('.reports').attr('id','active');
			
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/fetchUserFeedbackReport.anka');
		$scope.requestData.then(function(data){							
			$scope.jsonData=data.data;
			var data1 = $scope.jsonData[0].response.scenes;
						
			if(data1.length == 0){						
				$scope.emptiness = true;
			}else{	
				var data = [];
				var scenes = [];
				var options = [];
				var scene = null;	
				var question = null;	
				for(var i = 0;i<data1.length;i++){						
					var obj = data1[i];
					var d = new Date(obj.publishDate);
					var date = d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate();		                    	   												
					
					if(scene != obj.sceneTitle){							
						scene = obj.sceneTitle;
						var k = 0;												
						var scene1 = scene;
						var sceneArray = [];	
						$scope.collection = {};							
						$scope.collection.sceneTitle = obj.sceneTitle;								
						$scope.collection.dateWhispersSubmitted = date;																								
					
						for(var j = 0;j<data1.length;j++){
							var obj1 = data1[j];								
							if(scene1 == obj1.sceneTitle){																	
								if(question != obj1.timePlace){																	
										$scope.object = {};
										k++;
										$scope.object.question = obj1.timePlace;
										var opt = {};										
										opt.option = obj1.notificationText
										opt.average = obj1.authorName;
										options[k] = new Array();
										options[k].push(opt);										
										$scope.object.set = options[k];
										scenes[k] = new Array();
										scenes[k].push($scope.object);
										question = obj1.timePlace;
										sceneArray.push(scenes[k]);
										//$scope.collection.set = scenes[k];
										//data.push($scope.collection);						
									}else if(question == obj1.timePlace){																														
										var opt = {};										
										opt.option = obj1.notificationText										
										opt.average = obj1.authorName;
										options[k].push(opt);
									}
							}								
						}						
						
						$scope.collection.set = sceneArray;												
						data.push($scope.collection);
												
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

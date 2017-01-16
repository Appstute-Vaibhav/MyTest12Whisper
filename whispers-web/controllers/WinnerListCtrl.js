whispersApp.controller('WinnerListCtrl',['$scope','$rootScope','$location','$filter','ngTableParams','transport','ngDialog','notify','mySettings',function($scope,$rootScope,$location,$filter,ngTableParams,transport,ngDialog,notify,mySettings){		
	
	$scope.checkedRow=[];
	$rootScope.showNav = true;
	$scope.dataList = [];
	$rootScope.valid = true;
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
		$('.winner').attr('id','active');
		
		$('#textName').text("Winner");
		
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
		$scope.processingRequest=true;
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'app/fetchAllWinnerRecords.anka');		 				
		$scope.requestData.then(function(data){
			$scope.jsonData=data.data;							
			$scope.renderTable($scope.jsonData[0].response.winners);							
		},function(data){							
			notify("error","Error in request");
			$scope.isLoading=false;		
		});
		var d = new Date();
		
		if(new Date(d.getFullYear() , (d.getMonth()) , d.getDate(), 0 , 0, 0).getDay() == 0){
			$('#publishWinner1').hide();
			$('#publishWinner2').show();
			getCurrentWeekWinner();
		}else{
			$('#publishWinner2').hide();
			$('#publishWinner1').show();			
		}		
	}
	$scope.initLoad();
	
	$scope.addImage = function () {
		$scope.images = [];
		var reader = new FileReader(),
		$img = $("#img")[0],
		index = $scope.images.length;
		
		reader.onload = function (e) {
			if ($scope.images.indexOf(e.target.result) > -1) return;
			///$scope.sponsorCard.cardImage = e.target.result;			        
			var id = document.getElementById("img").getAttribute("value");			        			       
			
			var data = {id:id, sceneImage:e.target.result, sceneUploadType:"Replaced",modifiedBy:localStorage.getItem('userId'),createdBy:localStorage.getItem('userId')};
			
			$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/updateSceneRecord.anka',data);
			$scope.requestData.then(function(data){
				$scope.jsonData=data.data;
				var data=$scope.jsonData[0].success;
				var msg = $scope.jsonData[0].messages.message;
				if(data){														
					notify("success","image uploaded successfully");
				}else{
					notify("error",msg);
				}																				
			},function(data){
				notify("error","error occurred..!!");
			}); 	    		 	
			$scope.images.push(e.target.result);
			if (!$scope.$$phase) $scope.$apply();
			$("#imagePreview" + index).attr('src','');
			$("#imagePreview" + index).attr('src', e.target.result);
		}
		reader.readAsDataURL($img.files[0]);
	};

	$scope.editTableRow=function(id){
		for(var i = 0 ; i < $scope.dataList.length ; i++){            		  
			var json = $scope.dataList[i];            		  
			if(json.id == id){            		
				$rootScope.editUserRoleJsonData=[json];            			              				  
				ngDialog.open({
					template:'releases/master/partials/get12Whispers.html',
					className: 'ngdialog-theme-plain',
					scope:$scope 	
				})
			}
		}
	}
              
	function getCurrentWeekWinner(){
		var data = {id:localStorage.getItem('userId')};
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/fetchCurrentWeekWinnerRecord.anka',data);		 				
		$scope.requestData.then(function(data){
			$scope.jsonData=data.data;							
			$scope.renderTable($scope.jsonData[0].response.winners);		
			//notify("success","Winners published successfully!");					
		},function(data){							
			notify("error","Error in request");
			//$scope.isLoading=false;
		});            	
	}
	
	$scope.publishCurrentWeekWinner = function(){
		notify("success","Current week's winners published successfully!");
	}
	
	$scope.renderTable = function(data1){      		
		if(data1.length == 0 ){
			$scope.emptiness = true;
			$scope.isLoading=false;
		}else{
			$scope.emptiness = false;					
			var scene = null;
			var whispers = [];
			var j = 0;					
			var data = [];
			for(var i = 0;i<data1.length;i++){
				var obj = data1[i];
				var d = new Date(obj.dateScenePublished);
				var s = new Date(obj.startDate);
				var e = new Date(obj.endDate);
				var date =  d.getDate() + '/' + (d.getMonth()+1) + '/' + d.getFullYear();
				var startDate = s.getDate() + '/' + (s.getMonth()+1) + '/' + s.getFullYear();
				var endDate =  e.getDate() + '/' + (e.getMonth()+1) + '/' + e.getFullYear();
				obj.dateScenePublished = date;
				obj.startDate = startDate;
				obj.endDate = endDate;
				if(scene != obj.weekId){							
					j++;
					$scope.collection = {};							
					$scope.collection.weekId = obj.weekId;
					$scope.collection.startDate = obj.startDate;
					$scope.collection.endDate = obj.endDate;
					
					whispers[j] = new Array();
					var object = {};
					object.dateScenePublished = obj.dateScenePublished;
					object.winnerName = obj.winnerName
					object.sceneTitle = obj.sceneTitle;
					object.id = obj.id;
					whispers[j].push(object);
					scene = obj.weekId;	
					$scope.collection.set = whispers[j];
					data.push($scope.collection);
				}else if(scene == obj.weekId){
					var object = {};
					object.dateScenePublished = obj.dateScenePublished;
					object.winnerName = obj.winnerName
					object.sceneTitle = obj.sceneTitle;
					object.id = obj.id;
					whispers[j].push(object);													
					$scope.collection.set = whispers[j];							
				}							
			}
			
			$scope.tableParams = new ngTableParams({
				page: 1,            // show first page
				count: 50,          // count per page
				filter: {
					weekId: ''       // initial filter
				},
				sorting: {
					weekId: 'asc'     // initial sorting
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
			$scope.isLoading=false;							
		}					
	}
}]);

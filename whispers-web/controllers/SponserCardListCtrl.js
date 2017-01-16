whispersApp.controller('SponserCardListCtrl',['$scope','$rootScope','$location','$filter','ngTableParams','transport','ngDialog','notify','mySettings',function($scope,$rootScope,$location,$filter,ngTableParams,transport,ngDialog,notify,mySettings){	
	
	$rootScope.showNav = true;
	$rootScope.sponsorCardForm = false;
	$scope.checkedRow=[];		
	//$scope.sponsorCard = {};
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
		$('.sponsorCard').attr('id','active');
		
		$('#textName').text("Sponsor Card");
			
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
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/fetchAllSponsorCardRecords.anka');		 				
		$scope.requestData.then(function(data){
			$scope.jsonData=data.data;							
			$scope.renderTable($scope.jsonData[0].response.sponsorCards);
			$scope.isLoading=false;						
		},function(data){
			notify("error","Error in request");
			$scope.isLoading=false;
		});
	}
	$scope.initLoad();
  			
	$scope.saveSponsorCard=function(sponsorForm){	    	 
		var flag = true;
		if($('#name').val() == '' || (sponsorForm.name.$dirty && sponsorForm.name.$invalid)){	    		 
			flag = false;
			sponsorForm.name.$error.required = sponsorForm.name.$dirty = sponsorForm.name.$invalid = true;
			$('#name').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
		}else{
			sponsorForm.name.$error.required = sponsorForm.name.$dirty = sponsorForm.name.$invalid = false;
			$('#name').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
		}
		if($('#webUrl').val() == '' || (sponsorForm.webUrl.$dirty && sponsorForm.webUrl.$invalid)){
			flag = false;
			sponsorForm.webUrl.$error.required = sponsorForm.webUrl.$dirty = sponsorForm.webUrl.$invalid = true;
			$('#webUrl').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
		}else{
			sponsorForm.webUrl.$error.required = sponsorForm.webUrl.$dirty = sponsorForm.webUrl.$invalid = false;
			$('#webUrl').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
		}
		if($('#description').val() == '' || (sponsorForm.description.$dirty && sponsorForm.description.$invalid)){
			flag = false;
			sponsorForm.description.$error.required = sponsorForm.description.$dirty = sponsorForm.description.$invalid = true;
		}else{    			 
			sponsorForm.description.$error.required = sponsorForm.description.$dirty = sponsorForm.description.$invalid = false;
		}
		if($('#dateUploaded').val() == '') {
			$scope.pickDate = true;    				
			$('#pickDate').show();
			flag = false;
			$('#dateUploaded').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
		}else{
			$scope.pickDate = false;
			$('#pickDate').hide();

			$('#dateUploaded').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			var dt = $('#dateUploaded').val() +'T07:00:00';

			var date1 = new Date(dt);
			
			var day1 = date1.getDay();
			if(day1 == 6 || day1 == 0){
				alert("You can not add sponsor card on this date");
				$('#dateUploaded').val('');
				flag = false;
			}
			else{
				for(var i = 0;i<$scope.dataList.length;i++){
					var obj = $scope.dataList[i];					
					if(obj.dateUploaded != null){
					var d1 = new Date(obj.dateUploaded);
					var utcString = d1.toUTCString();
				
 					var dispDate = new Date(utcString.replace(' GMT',''));
					
					var dispMonth = dispDate.getMonth() + 1;

					var day = dispDate.getDate();				
					if(dispMonth.toString().length == 1){
						dispMonth = '0' + dispMonth;
					}
					if((dispDate.getDate()).toString().length == 1){
						day = '0' + dispDate.getDate();
					}
					
					var date1 = dispDate.getFullYear() + '-' + dispMonth + '-' + day;
						if(date1 == $('#dateUploaded').val()){					
							alert("Please select different date");
							flag = false;
							$('#dateUploaded').val('');
						}																											
					}
				}
			}		
		}
		if(flag){	
			$scope.isLoading=true;
			$('#btnSave').hide();
			$scope.sponsorCard.modifiedBy = localStorage.getItem('userId');
			$scope.sponsorCard.createdBy = localStorage.getItem('userId');
			if($scope.cardId > 0){
				$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/updateSponsorCardRecord.anka',$scope.sponsorCard);	    			
			}else{
				$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/addSponsorCardRecord.anka',$scope.sponsorCard);
			}	    		 
			$scope.requestData.then(function(data){
				$scope.jsonData=data.data;
				var data=$scope.jsonData[0].success;
				var msg = $scope.jsonData[0].messages.message;
				if(data){							
					if($scope.cardId > 0){
						notify("success","Sponsor card updated successfully");
					}else{
						notify("success","Sponsor card saved successfully");
					}							
					$scope.reset(sponsorForm);
					$location.path('/abc');
					setTimeout(function(){					
						$location.path('/sponsorcard');
						$scope.isLoading=false;
					},500);						
				}else{
					$('#btnSave').show();							
					notify("error",msg);
					$scope.isLoading=false;
				}						
			});
		}
	}

	$scope.addSponsorCard = function(){
		$pristine = true;			
		$scope.cardId = 0;
		$scope.isLoading=true;
		$scope.showCancel = true;          
		$rootScope.sponsorCardForm = true;	
		$scope.hideReset = false;         
		$scope.sponsorCard = {};
		$scope.isLoading=false;
		$('#sName').text('Add Sponsor Card');	
		$('#img').val('');
		$("#imagePreview0").attr('src','');			
	},
		 
	$scope.addImage = function () {
		var flag = true;
		var ext = $('#img').val().split('.').pop().toLowerCase();
		if($.inArray(ext, ['png','jpg','jpeg']) == -1) {
			$('#cardErrorMsg').fadeIn().text('Invalid file selection.').fadeOut(3000);    		 	    	     
			$('#img').val("");
			flag = false;
		}
		
		if(flag){
			$scope.images = [];
			var reader = new FileReader(),
			$img = $("#img")[0],
			index = $scope.images.length;	

			reader.onload = function (e) {
				if ($scope.images.indexOf(e.target.result) > -1) return;
				$scope.sponsorCard.cardImage = e.target.result;
				$scope.images.push(e.target.result);
				if (!$scope.$$phase) $scope.$apply();
				$("#imagePreview" + index).attr('src','');
				$("#imagePreview" + index).attr('src', e.target.result);
			}
			reader.readAsDataURL($img.files[0]);
		}			 	
	};
			     
	$scope.showList = function(){				
		setTimeout(function(){
			$('#resetForm').click();				
		},500);
		$rootScope.sponsorCardForm = false;
	}
	
	$scope.reset = function(){
		$scope.sponsorForm.$setPristine();
		$('#name').val('');
		$('#webUrl').val('');
		$('#description').val('');				
		$('#dateUploaded').val('');
		$('#img').val('');
		$("#imagePreview0").attr('src','');
		$('#pickDate').hide();
	}
			
	$scope.editTableRow=function(id){
		$pristine = true;
				
		$scope.isLoading=true;
		$rootScope.sponsorCardForm = true;      
		$scope.showCancel = true;   
		$scope.hideReset = true;
		$scope.sponsorCard = {};
		$scope.sCard = {};	

		var data = {id : id};            	  
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/fetchSponsorCardRecord.anka',data);
		$scope.requestData.then(function(data){
			$scope.jsonData=data.data;
			$scope.sponsorCard = $scope.jsonData[0].response.sponsorCard;
			$scope.cardId = $scope.jsonData[0].response.sponsorCard.id;
			var d = new Date($scope.sponsorCard.dateUploaded);                	   	
			var date = d.getFullYear()+ '-' + (d.getMonth()+1) + '-' + d.getDate() ;                 	   
			$scope.sponsorCard.dateUploaded = date;                 	           
			$("#imagePreview0").attr('src',$scope.sponsorCard.cardImage);
			$scope.isLoading=false;
		},function(data){
			notify("error","error occurred..!!");
			$scope.isLoading=false;
		}); 
		$('#sName').text('Edit Sponsor Card');	    		 	
	}	
	$scope.close=function(){
		ngDialog.close();
	}

	$scope.renderTable = function(records){
		$scope.dataList = records;
		var data1=records;		  												
		var data = [];
		if(data1.length == 0){
			$scope.emptiness = true;
		}else{
			for(var i = 0;i<data1.length;i++){
				var obj = data1[i];														
				var d1 = new Date(obj.dateUploaded);
					var utcString = d1.toUTCString();
				
 					var dispDate = new Date(utcString.replace(' GMT',''));
					
					var dispMonth = dispDate.getMonth() + 1;

					var day = dispDate.getDate();				
					if(dispMonth.toString().length == 1){
						dispMonth = '0' + dispMonth;
					}
					if((dispDate.getDate()).toString().length == 1){
						day = '0' + dispDate.getDate();
					}
					
					var date1 = dispDate.getFullYear() + '-' + dispMonth + '-' + day;
										
				obj.dateUploaded = date1;								
				
								
				data.push(obj);			
			}	
								
			$scope.tableParams = new ngTableParams({
				page: 1,            // show first page
				count: 50,          // count per page
				filter: {
					dateUploaded : ''       // initial filter
				},
				sorting: {
					dateUploaded : 'asc'     // initial sorting
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

									params.total(orderedData.length); // set total for recalc pagination
									$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
				}
			});
		}	
	}
	
	$scope.deleteSponsorCard=function(id){
		var val = confirm("Are you sure?");
	
		if(val){
			$scope.isLoading=true;
			var data = {id:id,modifiedBy:localStorage.getItem('userId'),createdBy:localStorage.getItem('userId')};			
			var arrayData = [];
			arrayData.push(data);
			
			$scope.deleteDataRequest=transport.request(mySettings.remoteServerUrl+'web/deleteSponsorCardRecords.anka',arrayData);
	           
			$scope.deleteDataRequest.then(function(data){	    	 
				$scope.checkedRow=[];	    	 
				$scope.jsonData=data.data;
				$scope.renderTable($scope.jsonData[0].response.sponsorCards);
				var flag = $scope.jsonData[0].success;
				var msg = $scope.jsonData[0].messages.message
				if(flag){
					$location.path('/abc');
					notify("success","Record deleted successfully");	    		 	    							
					setTimeout(function(){
						$location.path('/sponsorcard');
						$scope.isLoading=false;
					},500);
				}else{
					notify("error",msg);
					$scope.isLoading=false;
				}	    	 					    	
			},
			function(){
				notify("error","error occurred..!!");
				$scope.isLoading=false;
			});    
		}
	}
}]);


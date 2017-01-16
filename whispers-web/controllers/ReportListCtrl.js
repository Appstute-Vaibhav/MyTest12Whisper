whispersApp.controller('ReportListCtrl',['$scope','$rootScope','$location',function($scope,$rootScope,$location){		 		
	$rootScope.validadmin = true;		
	$rootScope.validUser = true;
	$rootScope.valid = true;
	$rootScope.showNav = true;
			
	if(localStorage.getItem('displayName') != "null"){
		$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('displayName') +"</b></p>");			
	}else{
		$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('loginUser') +"</b></p>");	
	}
	//$rootScope.rForm = false;
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
		
		$('#textName').text("Reports");
		
		$('.removeHtml').html("");
		$('.reports').attr('id','active');		
	 },
	 $scope.initLoad();
	
	 $scope.getReport = function(reportForm){
		 var flag = true;		 
		 if($('#report').val() == '' || (reportForm.report.$dirty && reportForm.report.$invalid)) {
			 $('#report').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			 flag = false;     		
			 reportForm.report.$error.required = reportForm.report.$dirty = reportForm.report.$invalid = true;
		 }else{
			 $('#report').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			 reportForm.report.$error.required = reportForm.report.$dirty = reportForm.report.$invalid = false;
		 }
		 if($('#type').val() == '' || (reportForm.type.$dirty && reportForm.type.$invalid)) {
			 $('#type').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			 flag = false;  		
			 reportForm.type.$error.required = reportForm.type.$dirty = reportForm.type.$invalid = true;
		 }else{
			 $('#type').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			 reportForm.type.$error.required = reportForm.type.$dirty = reportForm.type.$invalid = false;
		 }		 		
		 
		 if(flag){			
			 //$rootScope.rForm = true;
			 if($('#type').val() == 'user'){				 
				 $location.path('/userReport');
			 }else{								
				 $location.path('/feedbackReport');
			 }		
		 }
	 }
	 
	 $scope.reset = function(){       
		 $scope.reportForm.$setPristine();
		 $('#report').val("");
		 $('#type').val("");         	             	       
	 }	
}]);
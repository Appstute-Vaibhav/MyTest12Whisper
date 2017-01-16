whispersApp.controller('MainCtrl',['$scope','$rootScope','$location','notify','growl','$timeout','transport','mySettings','$confirm',
                                   function($scope,$rootScope,$location,notify,growl, $timeout, transport, mySettings, $confirm){	
	
	$scope.clock = "loading clock..."; // initialise the time variable
    $scope.tickInterval = 1000 //ms

    var tick = function () {
        $scope.clock = Date.now() // get the current time
        $timeout(tick, $scope.tickInterval); // reset the timer
    }

    // Start the timer
    $timeout(tick, $scope.tickInterval);

	
	$scope.isActive = function (viewLocation) {
		var active = (viewLocation === $location.path());
		return active;	
	};
		
	$scope.keyboardLanguages={en:"ENGLISH",mr:"MARATHI"};
	
	$scope.keyboardLanguage="ENGLISH";
	
	$scope.updateDayLightSavingMode = function(){
		var inputData = {dayLightSavingOn:$scope.switchStatus};
		
		$scope.switchStatus = !$scope.switchStatus;
		
		$confirm({text: 'Are you sure you want to change Day Light Saving Mode ?', title: 'Day Light Saving Mode ', ok: 'Yes', cancel: 'No'})
        .then(function() {
    		$scope.result = transport.request(mySettings.remoteServerUrl+'web/updateDayLightSavingSettings.anka',inputData);							
    		$scope.result.then(function(data){
    			$scope.switchStatus = !$scope.switchStatus;
    			if(data.data[0].success){
    				if($scope.switchStatus){
    					notify("success","Day Light Saving mode activated successfully..!!!");
    				}else {
    					notify("success","Day Light Saving mode de-activated successfully..!!!")	
    				}
    			}else {
    				notify("error","Day Light Saving mode modification failed..!!! Please contact your technical support..!!");
    			}
    		},
    		function(data){			
    			notify("error","Day Light Saving mode modification failed..!!! Please contact your technical support..!!")	
    		});	
        });
	}
	
	$scope.getDayLightSavingMode = function(){
		
		$scope.result = transport.request(mySettings.remoteServerUrl+'web/fetchDayLightSavingSettings.anka');							
		$scope.result.then(function(data){
			if(data.data[0].success){
				$scope.switchStatus = JSON.parse(data.data[0].response.dayLightSaving.dayLightSavingOn);
			}
		},
		function(data){			
			notify("error","Day Light Saving mode modification failed..!!! Please contact your technical support..!!")	
		});	
	}
	
	$scope.changeKeyBoardLang=function(lang){
		pramukhIME.setLanguage(lang, 'pramukhindic');
		$scope.keyboardLanguage=lang;
	}
			
	$scope.logOut=function(){
		
		$rootScope.valid=false;
		
		$rootScope.validadmin=false;
		 
		$rootScope.validDramaturg=false;
		 
		$rootScope.validPlayWriter=false;
		 		 
		localStorage.clear();
		 
		$location.path("/");
		
		localStorage.setItem("loginUser",null);
		localStorage.setItem("displayName",null);
		localStorage.setItem("userId",null);
		localStorage.setItem("roleId",null);
	      	   
	}
	
    $scope.getDayLightSavingMode();
}]);	 
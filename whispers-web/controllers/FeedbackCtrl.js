whispersApp.controller('FeedbackCtrl',['$scope','$rootScope','$location','$filter','ngTableParams','ngDialog','transport','notify','mySettings',function($scope,$rootScope,$location,$filter,ngTableParams,ngDialog,transport,notify,mySettings){		 	
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
		$('.feedback').attr('id','active');					
		
		$('#textName').text("Feedback");
		
		$scope.isLoading = true;
		if(localStorage.getItem('userId') == null){			
			$location.path('/login');
			setTimeout(function(){				
				location.reload();
			},500);
		}
		$rootScope.showToFeedback=false;
		if(localStorage.getItem('displayName') != "null"){
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('displayName') +"</b></p>");			
		}else{
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('loginUser') +"</b></p>");			
		}			
		
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'app/fetchAllFeedbackQuestionRecords.anka');		 				
		$scope.requestData.then(function(data){
			$scope.jsonData = data.data;							
			$scope.renderTable($scope.jsonData[0].response.feedbackQuestions);						
			$scope.isLoading = false;
		},function(data){
			$scope.isLoading = false;
			notify("error","Error in request");	
		});
	}
		
	$scope.initLoad();
		
	$scope.renderTable = function(records){		
		for(var a = 0 ; a < records.length; a++){
			var obj = records[a];							
			if(obj.parentQuestionId == 0){					
				obj.mainQuestion = obj.question;
				obj.question = '-';
			}else{					
				for(var b = 0 ; b < records.length; b++){
					var model = records[b];
					if(model.id == obj.parentQuestionId){								
						obj.mainQuestion = model.mainQuestion;							
					}
				}
			}
		}
		
		var data = records;		
		
		if(data.length == 0){
			$scope.emptiness = true;
		}
		else{
			$scope.tableParams = new ngTableParams({
				page: 1,            // show first page
				count: 50,          // count per page
				filter: {
					question: ''       // initial filter
				},
				sorting: {
					question: 'asc'     // initial sorting
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
		
	$scope.addFeedbackByAdmin = function(){				
		$pristine = true;					
		$scope.isLoading = true;
		$scope.showCancel = true;			
		$rootScope.showToFeedback=true;
		$scope.feedback = {};  
		$scope.isLoading = false;
		$scope.subQuestions = [];
		$scope.subOptions = [];    		
		$scope.subQuestion = [];    		
		$scope.optArray = [];
		$scope.question = "";
		$scope.mainQuestionOptions = [];
		$scope.data = [];
		$scope.count = 0;
		$scope.questionId = null;
		$scope.subQuestionId = null;
		$("#sName").text("Add Question");
	}
					
	$scope.subQuestions = [];
	$scope.subOptions = [];
		
	$scope.subQuestion = [];
		
	$scope.optArray = [];		
	$scope.data = [];
	$scope.count = 0;
	$scope.id = 1;
	
	$scope.addField = function(){		
		if($scope.question == ""){
			notify("error","Please enter question");
		}else{
			$scope.mainQuestionOptions.push({id:$scope.id++,optionLable:"",optionValue:""});
		}							
	};	    	   
	
	$scope.addSubQuestion = function(){
		var flag1 = false;
		var flag2 = false;		
		for(var k = 0 ; k < $scope.mainQuestionOptions.length ; k++ ){
			var obj = $scope.mainQuestionOptions[k];
								
			if(obj.id == 1 && obj.optionValue != ''){
				flag1 = true;
			}
			if(obj.id == 2 && obj.optionValue != ''){
				flag2 = true;
			}				
		}
	    	
		if($scope.question != ''){	    		
			if((flag1 == false && flag2 == false) || (flag1 == true && flag2 == true)){
				if($scope.count == 0){	    				
					$scope.subOptions[$scope.count] = new Array();
					$scope.subQuestions.push({text:""});
					$scope.count++;			    					    	
				}else{
					if($scope.subOptions[$scope.count - 1].length < 2){	   
						for(var j = 0 ; j < $scope.subOptions[$scope.count - 1].length ; j++ ){
							var object = $scope.subOptions[$scope.count - 1];	    						
							if($scope.subQuestion[$scope.count - 1] == ''){
								notify("error","please enter sub-question");	    						
							}else{
								if(object.optionValue == '' && j < 2){
									notify("error","please enter option "+(j+1));	    							
								}	    						
							}
						}
					}else{
						$scope.subOptions[$scope.count] = new Array();
						$scope.subQuestions.push({text:""});
						$scope.count++;
					}
				}
			}else{
				notify("error","Please enter second option of main question");	    			
			}		    		
		}
		else{
			notify("error","Please enter question");	    		
		}		
	};
	
	$scope.checkEmpty = function(index){	    	
		if($scope.subQuestion[index] == undefined){
			$scope.subOptions[index]= new Array();
			$scope.data[index] = null;	    			    	
		}
	};
	    
	$scope.addOption = function(index,option){	    	
		$scope.subOptions[index].push({});
		if($scope.subQuestion[index] != ""){	    		
			$scope.data[index] = option;		   		    	
		}else{
			notify("error","Please enter question");	    		
			$scope.subOptions[index] = new Array();
			$scope.data[index] = null;
		}	    	
	};
	    
	$scope.addNewOption = function(){	    	
		$scope.optionArray.push({});		
	};
	    	    	    	    
	$scope.close=function(){
		ngDialog.close();
	} 
	    		
	$scope.feedbackQuestion = function(feedbackForm){
		var data = [];
		var flag = true;
		
		if($scope.mainQuestionOptions.length > 0 ){
			if($scope.mainQuestionOptions[0] != undefined && $scope.mainQuestionOptions[1] != undefined){
				var arr = new Array();
				for(i = 1 ;i <= $scope.mainQuestionOptions.length ; i++){								        		        		       
					var obj = $scope.mainQuestionOptions[i-1];
					if(obj.optionValue != ''){
						arr.push({id:i,optionLabel:'',optionValue:obj.optionValue});
					}								        				        		
				}	        		
				var val = {id:$scope.questionId,parentQuestionId :null,question:$scope.question,feedbackQuestionOptions:arr,modifiedBy:localStorage.getItem('userId'),createdBy:localStorage.getItem('userId')};
				data.push(val);					
			}else{
				flag = false;
				notify("error","Please enter atleast two options for main-question");								
			}
		}else{
			if($scope.question == ""){
				flag =  false;
				notify("error","Please enter question");					
			}else if($scope.optArray.length == 0 && $scope.subQuestion.length == 0){
				flag =  false;
				notify("error","Please enter atleast two options or add sub-questions");					
			}else if($scope.optArray.length > 0 && $scope.optArray[0][0] == undefined){
				flag = false;
				notify("error","Please enter second option");					
			}else if($scope.question != undefined){
				var val = {id:$scope.questionId,parentQuestionId :null,question:$scope.question,feedbackQuestionOptions:[],modifiedBy:localStorage.getItem('userId'),createdBy:localStorage.getItem('userId')};
				data.push(val);
			}						
		}
			
		for(var i = 0 ; i < $scope.subQuestion.length ; i++){				
			if($scope.subQuestion[i] != undefined){					
				if($scope.data[i] != null){
					if($scope.data[i][0] != undefined && $scope.data[i][1] != undefined){
						var prop;
						var propCount = 0;
						
						for (prop in $scope.data[i]) {
							propCount++;
						}
						var arr = new Array();
						for(j = 1 ;j <= propCount ; j++){								        		        		       
							if($scope.data[i][j-1] != ""){
								arr.push({id:j,optionLabel:'',optionValue:$scope.data[i][j-1]});
							}										        				        		
						}
					
						if((i + 1) == 1){
							var val = {id : $scope.subQuestionId,parentQuestionId :$scope.questionId ,question:$scope.subQuestion[i],feedbackQuestionOptions:arr,modifiedBy:localStorage.getItem('userId'),createdBy:localStorage.getItem('userId')};								
						}else{
							var val = {id : null,parentQuestionId :$scope.questionId ,question:$scope.subQuestion[i],feedbackQuestionOptions:arr,modifiedBy:localStorage.getItem('userId'),createdBy:localStorage.getItem('userId')};								
						}
						data.push(val);
					}
					else{
						notify("error","Please enter atleast two options for sub-question " +(i + 1));							
						flag = false;
					}
				}
				else{
					notify("error","Please enter atleast two options for sub-question " +(i + 1));						
					flag = false;
				}					
			}else{
				notify("error","Please enter options");					
			}
		}
		if(flag){
			if($scope.questionId != null){
				$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/updateFeedbackQuestionRecord.anka',data);
			}else{
				$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/addFeedbackQuestionRecord.anka',data);
			}				
			$scope.requestData.then(function(data){
				$scope.jsonData=data.data;
				var flag=$scope.jsonData[0].success;
				var msg = $scope.jsonData[0].messages.message;
				
				if(flag){						
					if($scope.questionId > 0){
						notify("success","Question updated successfully");
					}else{
						notify("success","Question saved successfully");
					}						
					$scope.reset(feedbackForm);
					$location.path('/abc');
					setTimeout(function(){
						$location.path('/feedback');
						$scope.isLoading = false;
					},500);	
				}else{
					$('#btnSave').show();
					$scope.isLoading = false;
					notify("error",msg);										
				}
			});
		}
	}
		
	$scope.showList = function(){
		setTimeout(function(){
			$('#resetB').click();				
		},500);
		$rootScope.showToFeedback=false;			        	
	}
                
	$scope.reset = function(){
		$scope.feedbackForm.$setPristine();
		
		$scope.subQuestions = [];
		$scope.subOptions = [];
			
		$scope.subQuestion = [];
		$scope.mainQuestionOptions = [];
			
		$scope.optArray = [];		
		$scope.data = [];
		$scope.count = 0;
		$scope.id = 1;
		
		$scope.question = "";
	}
               
	$scope.editTableRow=function(id,parentQuestionId){        	    
		$pristine = true;
	
		$rootScope.showToFeedback=true;
		$scope.hideReset = true;
		$scope.showCancel = true;		

		var data = {
				id:id,
				parentQuestionId:parentQuestionId
		};
		//Send Request to get data for pSchema 
		$scope.editDataRequest=transport.request(mySettings.remoteServerUrl+'web/fetchFeedbackQuestionRecord.anka',data);
	
		$scope.editDataRequest.then(function(data){  
			
			$scope.editUserRoleJsonData=data.data;
    			
			$scope.feedback = $scope.editUserRoleJsonData[0].response.feedbackQuestion[0];
			$scope.question = $scope.feedback.question;
			$scope.questionId = $scope.feedback.id;    		
			$scope.mainQuestionOptions = [];    			
			for(var i = 0 ; i < $scope.feedback.feedbackQuestionOptions.length; i++){
				var obj = $scope.feedback.feedbackQuestionOptions[i];    						
				$scope.mainQuestionOptions.push(obj)    				
			}
			
    			
			if($scope.editUserRoleJsonData[0].response.feedbackQuestion[1] != undefined){
				$scope.feedback1 = $scope.editUserRoleJsonData[0].response.feedbackQuestion[1];    			
				
				$scope.subQuestionId = $scope.feedback1.id;    		
        			
				setTimeout(function(){
					$('#first').click();
					$('#subQues1').val($scope.feedback1.question).change();
					for(var i = 0 ; i < $scope.feedback1.feedbackQuestionOptions.length; i++){
						var obj = $scope.feedback1.feedbackQuestionOptions[i];        				
						$('#second').click();
						$('#subOpt'+(i + 1)).val(obj.optionValue).change();    					
					}
				},500);
			}
    			    		    			
		},function(data){notify("error","Error in request");    	
		});   			
		$("#sName").text("Edit Question");
	};
        
	$scope.deleteFeedback = function(id){
		var flag = confirm('Are you sure?');
		
		if(flag){
			$scope.isLoading = false;
			var data = [{id:id,modifiedBy:localStorage.getItem('userId'),createdBy:localStorage.getItem('userId')}]; 
			$scope.editDataRequest=transport.request(mySettings.remoteServerUrl+'web/deleteFeedbackQuestionRecords.anka',data);
            	
			$scope.editDataRequest.then(function(data){
				$scope.jsonData=data.data;
				var data=$scope.jsonData[0].success;
				var msg = $scope.jsonData[0].messages.message;				
				if(data){						
					notify("success","Question deleted successfully");    					
					$location.path('/abc');	
					$scope.renderTable($scope.jsonData[0].response.feedbackQuestions);
					setTimeout(function(){
						$location.path('/feedback');
						$scope.isLoading = false;
					},500);
				}else{
					notify("error",msg);		
					$scope.isLoading = false;
				}																
			});
		}        	
	}        
}]);
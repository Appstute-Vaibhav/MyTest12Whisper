whispersApp.controller('SceneListCtrl',['$scope','$rootScope','$location','$filter','ngTableParams','transport','ngDialog','notify','mySettings',function($scope,$rootScope,$location,$filter,ngTableParams,transport,ngDialog,notify,mySettings){		
	$rootScope.showNav = true;
	$scope.checkedRow=[];
	$scope.count=1;
	$scope.no = 2;	
	$rootScope.form = false;
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
		
		$('#textName').text("Scene");
		
		$('.sceneManagement').attr('id','active');		
		$('.removeHtml').html("");
		$scope.isLoading=true;	
		if(localStorage.getItem('userId') == null){			
			$location.path('/login');
			setTimeout(function(){				
				location.reload();
			},500);
		}
		if(localStorage.getItem('role') == 'Admin'){
			$scope.toAdmin = false;
			$scope.toPlayWriter = true;
		}else{			
			$scope.toAdmin = true;
			$scope.toPlayWriter = false;
		}		
		$('#sName').text("Add Scene");
		
		if(localStorage.getItem('displayName') != "null"){			
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('displayName') +"</b></p>");			
		}else{			
			$('#logUser').html("<p><b>Welcome "+ localStorage.getItem('loginUser') +"</b></p>");			
		}                        
		var data = {roleId:localStorage.getItem('roleId'),id:localStorage.getItem('userId')};
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/fetchAllSceneRecords.anka',data);		 				
						
		$scope.requestData.then(function(data){
			$scope.jsonData=data.data;
			$scope.renderTable($scope.jsonData[0].response.scenes); 							
			$scope.isLoading=false;
		},function(data){
			notify("error","Error in request");
			$scope.isLoading=false;
		});							
	}		 																
	$scope.initLoad();
   
	$scope.submitToPublish = function(){		
		
		var flag = true;
		
		if($('#dateOfPublish').val() == ''){
			notify("error","Please select date");
		}else{
			var dt = $('#dateOfPublish').val() +'T07:00:00';
			console.log("dt == "+dt);
			var date1 = new Date(dt);
			console.log("date1 == "+date1);
			var day1 = date1.getDay();
			console.log("day1 == "+day1);

			if(day1 == 6 || day1 == 0){
				notify("error","You can not publish scene on this date");
				$('#dateOfPublish').val('');
			}else{
				for(var i = 0;i<$scope.dataList.length;i++){
					var obj = $scope.dataList[i];					
					if(obj.publishDate != null){							
						if(obj.publishDate == $('#dateOfPublish').val()){					
							notify("error","Please select different date");
							flag = false;
							$('#dateOfPublish').val('');
						}																											
					}
				}
				
				if(flag){
					var data = {
							id:$scope.sceneIdToPublish,
							scenePublished:0,
							modifiedBy:localStorage.getItem('userId'),
							publishDate:$('#dateOfPublish').val()
					};
					$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/updateSceneRecord.anka',data);
					$scope.requestData.then(function(data){
						$scope.jsonData=data.data;
						var data=$scope.jsonData[0].success;
						var msg = $scope.jsonData[0].messages.message;
						if(data){
							ngDialog.close();
							$location.path('/abc');
							notify("success","Scene will publish on "+$('#dateOfPublish').val());
							setTimeout(function(){
								$location.path('/manageScenes');
							},500);													
						}else{
							notify("error",msg);
						}
					},function(data){
						notify("error","Error in request");	
					});
				}				
			}
		}					
	}
		
	$scope.publishScene = function(id){
		
		if($('#'+id).is(':checked') == false){
			var flag = confirm("You want to remove publish date?");
			if(flag){
				var data = {
						id:id,						
						modifiedBy:localStorage.getItem('userId'),
						publishDate:null
				}; 	
					  
				$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/updateSceneRecord.anka',data);
				$scope.requestData.then(function(data){
					$scope.jsonData=data.data;
					var data=$scope.jsonData[0].success;
					var msg = $scope.jsonData[0].messages.message;
					if(data){
						$location.path('/abc');
						notify("success","publish date removed successfully");
						setTimeout(function(){
							$location.path('/manageScenes');
						},500);													
					}else{
						notify("error",msg);
					}
				},function(data){
					notify("error","Error in request");	
				});			 
			}else{
				$('#'+id).click();
			}	
		}else{
			$scope.sceneIdToPublish = id;
			ngDialog.open({
				template:'releases/master/partials/ScenePublishDialogTemplate.html',
				scope:$scope 	
			})
			$('#'+id).click();
		}
		
	}
		 
	$scope.getComments = function(id){
		$scope.sceneIdIs = id;
		ngDialog.open({
			template:'releases/master/partials/CommentsTemplate.html',
			scope:$scope 	
		})
		var data = {sceneId:id};			
		$scope.requestData=transport.request(mySettings.remoteServerUrl+'app/getComments.anka',data);
		$scope.requestData.then(function(data){
			$scope.jsonData=data.data;
			$scope.sceneComments = $scope.jsonData[0].response.sceneComments;
			
			if($scope.sceneComments.length == 0){
				$scope.emptiness2 = true;
			}else{
				$scope.emptiness2 = false;	
				$scope.tableParams = new ngTableParams({
					page: 1,            // show first page
					count: $scope.sceneComments.length,// count per page
					/*filter: {
					        	comment: ''       // initial filter
					        },
					        sorting: {
					        	comment: 'asc'     // initial sorting
					        }*/
				},					  
				{
					total: $scope.sceneComments.length,
					//counts:[],// length of data
					getData: function($defer, params) {
						// use build-in angular filter
						/*var filteredData = params.filter() ?
					                    $filter('filter')($scope.sceneComments, params.filter()) :
					                    $scope.sceneComments;*/
						/*    var orderedData = params.sorting() ?
					                    $filter('orderBy')($scope.sceneComments, params.orderBy()) :
					                    $scope.sceneComments;*/

						//params.total(orderedData.length); // set total for recalc pagination
						///$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
					}
				});
			}				 
		},function(data){
			notify("error","Error in request");	
		});
	},
	
	$scope.deleteComment = function(id){
		var data = [{id:id,modifiedBy:localStorage.getItem('userId'), 
			createdBy:localStorage.getItem('userId')}];	
		var flag = confirm('Are you sure?');
		if(flag){
			$scope.isLoading=true;
			$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/deleteSceneCommentsRecords.anka',data);
			var data = {sceneId:$scope.sceneIdIs};			
			$scope.requestData=transport.request(mySettings.remoteServerUrl+'app/getComments.anka',data);				 
			
			$scope.requestData.then(function(data){
				$scope.jsonData=data.data;
				$scope.sceneComments = $scope.jsonData[0].response.sceneComments;
				$scope.tableParams = new ngTableParams({
					page: 1,            // show first page
					count: $scope.sceneComments.length,// count per page
					/*filter: {
					        	comment: ''       // initial filter
					        },
					        sorting: {
					        	comment: 'asc'     // initial sorting
					        }*/
				},					    
				{
					total: $scope.sceneComments.length,
					//counts:[],// length of data
					getData: function($defer, params) {
						// use build-in angular filter
						/*var filteredData = params.filter() ?
					                    $filter('filter')($scope.sceneComments, params.filter()) :
					                    $scope.sceneComments;*/
						/*var orderedData = params.sorting() ?
					                    $filter('orderBy')($scope.sceneComments, params.orderBy()) :
					                    $scope.sceneComments;*/

						//params.total(orderedData.length); // set total for recalc pagination
						///$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
					}
				});
				$scope.isLoading=false;
			},function(data){
				notify("error","Error in request");
				$scope.isLoading=false;
			});
		}
	}	
		 
	$scope.renderTable = function(dataList){		
		$scope.dataList = dataList;
		var data1 = dataList;						
		var data = [];
		if(data1.length == 0){
			$scope.emptiness = true;
		}else{
			for(var i = 0;i<data1.length;i++){
				var obj = data1[i];		
				
				if(obj.dateSubmitted != null){
					var d = new Date(obj.dateSubmitted);
					
					var date = d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate();
					obj.dateSubmitted = date;						
				}else{
					obj.dateSubmitted = '-';
				}
				if(obj.publishDate != null){
					var today = new Date();
					var dd = today.getDate();
					var mm = today.getMonth()+1; //January is 0!	

					var yyyy = today.getFullYear();
																			
					var d1 = new Date(obj.publishDate);
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
					
					var date2 = yyyy+ '-' + mm + '-' + dd;
							
					if(date1 == date2){
						$scope.gotPublished = true;
					}else{
						$scope.gotPublished = false;
					}																				
					obj.publishDate = date1;											
				}else{
					obj.publishDate = '-';													
				}
				if(localStorage.getItem('roleId') == 2){
					$scope.gotPublished = true;
				}
				if(localStorage.getItem('role') == "Admin"){
					if(obj.isSubmitted == 1){
						data.push(obj);
					}					
				}else{
					data.push(obj);
				}
				
			}				
			$scope.tableParams = new ngTableParams({
				page: 1,            // show first page
				count: 50,          // count per page
				filter: {
					dateSubmitted: ''       // initial filter
				},
				sorting: {
					dateSubmitted: 'asc'     // initial sorting
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
	}
		 		 
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
				$scope.scene.sceneImage = e.target.result;
				$scope.images.push(e.target.result);
				if (!$scope.$$phase) $scope.$apply();
				
				$("#imagePreview" + index).attr('src', '');
				$("#imagePreview" + index).attr('src', e.target.result);	

				//$scope.uploadImage(index);
			}
			reader.readAsDataURL($img.files[0]); 
		}				
	};
	
		
	
	$scope.saveScene=function(sceneForm){
		$scope.totalWhispers = 0;	
		var flag = true;	    	 
		if($('#title').val() == '' || (sceneForm.title.$dirty && sceneForm.title.$invalid)){
			$('#title').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			flag = false;
			sceneForm.title.$error.required = sceneForm.title.$dirty = sceneForm.title.$invalid = true;
		}else{
			$('#title').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			sceneForm.title.$error.required = sceneForm.title.$dirty = sceneForm.title.$invalid = false;
		}
		if($('#timeAndPlace').val() == '' || (sceneForm.timeAndPlace.$dirty && sceneForm.timeAndPlace.$invalid)){
			$('#timeAndPlace').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			flag = false;
			sceneForm.timeAndPlace.$error.required = sceneForm.timeAndPlace.$dirty = sceneForm.timeAndPlace.$invalid = true;
		}else{
			$('#timeAndPlace').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			sceneForm.timeAndPlace.$error.required = sceneForm.timeAndPlace.$dirty = sceneForm.timeAndPlace.$invalid = false;
		}
		if($('#whisper1').val() == '' || (sceneForm.whisper1.$dirty && sceneForm.whisper1.$invalid)){
			$('#whhisper1').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required');
			flag = false;
			sceneForm.whisper1.$error.required = sceneForm.whisper1.$dirty = sceneForm.whisper1.$invalid = true;
		}else{
			$('#whisper1').attr('class','form-control ng-valid-pattern ng-touched ng-dirty ng-valid-parse ng-valid ng-valid-required');
			sceneForm.whisper1.$error.required = sceneForm.whisper1.$dirty = sceneForm.whisper1.$invalid = false;
		}
    		 
		if(flag){
			$scope.isLoading=true;	
			$scope.hideSave = true;
			$scope.str = null;	
			
			if($scope.sceneDesc.whisper1 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character1 != undefined?$scope.sceneDesc.character1:"",'whisper':$("#stageDirection1").is(':checked')?"":$scope.sceneDesc.whisper1,'stageDirection':$scope.stageDirection[1]?$scope.sceneDesc.whisper1:""};		    		
				var x = JSON.stringify($scope.str);
				$scope.totalWhispers = 1;
			}			  				
			if($scope.sceneDesc.whisper2 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character2 != undefined?$scope.sceneDesc.character2:"",'whisper':$("#stageDirection2").is(':checked')?"":$scope.sceneDesc.whisper2,'stageDirection':$scope.stageDirection[2]?$scope.sceneDesc.whisper2:""};
				x = x + "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 2;
			}
			if($scope.sceneDesc.whisper3 != undefined){					
				$scope.str = {'character':$scope.sceneDesc.character3 != undefined?$scope.sceneDesc.character3:"",'whisper':$("#stageDirection3").is(':checked')?"":$scope.sceneDesc.whisper3,'stageDirection':$scope.stageDirection[3]?$scope.sceneDesc.whisper3:""};		    				
				x = x + "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 3;
			}
			if($scope.sceneDesc.whisper4 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character4 != undefined?$scope.sceneDesc.character4:"",'whisper':$("#stageDirection4").is(':checked')?"":$scope.sceneDesc.whisper4,'stageDirection':$scope.stageDirection[4]?$scope.sceneDesc.whisper4:""};
				x = x + "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 4;
			}
			if($scope.sceneDesc.whisper5 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character5 != undefined?$scope.sceneDesc.character5:"",'whisper':$("#stageDirection5").is(':checked')?"":$scope.sceneDesc.whisper5,'stageDirection':$scope.stageDirection[5]?$scope.sceneDesc.whisper5:""};
				x = x + "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 5;
			}
			if($scope.sceneDesc.whisper6 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character6 != undefined?$scope.sceneDesc.character6:"",'whisper':$("#stageDirection6").is(':checked')?"":$scope.sceneDesc.whisper6,'stageDirection':$scope.stageDirection[6]?$scope.sceneDesc.whisper6:""};
				x = x + "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 6;
			}
			if($scope.sceneDesc.whisper7 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character7 != undefined?$scope.sceneDesc.character7:"",'whisper':$("#stageDirection7").is(':checked')?"":$scope.sceneDesc.whisper7,'stageDirection':$scope.stageDirection[7]?$scope.sceneDesc.whisper7:""};
				x = x + "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 7;
			}
			if($scope.sceneDesc.whisper8 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character8 != undefined?$scope.sceneDesc.character8:"",'whisper':$("#stageDirection8").is(':checked')?"":$scope.sceneDesc.whisper8,'stageDirection':$scope.stageDirection[8]?$scope.sceneDesc.whisper8:""};
				x = x + "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 8;
			}
			if($scope.sceneDesc.whisper9 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character9 != undefined?$scope.sceneDesc.character9:"",'whisper':$("#stageDirection9").is(':checked')?"":$scope.sceneDesc.whisper9,'stageDirection':$scope.stageDirection[9]?$scope.sceneDesc.whisper9:""};
				x = x +  "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 9;
			}
			if($scope.sceneDesc.whisper10 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character10 != undefined?$scope.sceneDesc.character10:"",'whisper':$("#stageDirection10").is(':checked')?"":$scope.sceneDesc.whisper10,'stageDirection':$scope.stageDirection[10]?$scope.sceneDesc.whisper10:""};
				x = x + "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 10;
			}
			if($scope.sceneDesc.whisper11 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character11 != undefined?$scope.sceneDesc.character11:"",'whisper':$("#stageDirection11").is(':checked')?"":$scope.sceneDesc.whisper11,'stageDirection':$scope.stageDirection[11]?$scope.sceneDesc.whisper11:""};
				x = x + "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 11;
			}
			if($scope.sceneDesc.whisper12 != undefined){
				$scope.str = {'character':$scope.sceneDesc.character12 != undefined?$scope.sceneDesc.character12:"",'whisper':$("#stageDirection12").is(':checked')?"":$scope.sceneDesc.whisper12,'stageDirection':$scope.stageDirection[12]?$scope.sceneDesc.whisper12:""};
				x = x + "," + JSON.stringify($scope.str);
				$scope.totalWhispers = 12;
			}
						
			var  obj = "[" + x + "]" ;	
		    var errorMsg = "Please enter whisper";
			    
			if($('#whisper2').val() == "" && $scope.totalWhispers > 2){
				errorMsg = errorMsg + "2,";
			}
			if($('#whisper3').val() == "" && $scope.totalWhispers > 3){
				errorMsg = errorMsg + " 3,";
			}
			if($('#whisper4').val() == "" && $scope.totalWhispers > 4){
				errorMsg = errorMsg + " 4,";
			}
			if($('#whisper5').val() == "" && $scope.totalWhispers > 5){
				errorMsg = errorMsg + " 5,";
			}
			if($('#whisper6').val() == "" && $scope.totalWhispers > 6){
				errorMsg = errorMsg + " 6,";
			}
			if($('#whisper7').val() == "" && $scope.totalWhispers > 7){
				errorMsg = errorMsg + " 7,";
			}
			if($('#whisper8').val() == "" && $scope.totalWhispers > 8){
				errorMsg = errorMsg + " 8,";
			}
			if($('#whisper9').val() == "" && $scope.totalWhispers > 9){
				errorMsg = errorMsg + " 9,";
			}
			if($('#whisper10').val() == "" && $scope.totalWhispers > 10){
				errorMsg = errorMsg + " 10,";
			}
			if($('#whisper11').val() == "" && $scope.totalWhispers > 11){
				errorMsg = errorMsg + " 11,";
			}
			if($('#whisper12').val() == "" && $scope.totalWhispers == 12){
				errorMsg = errorMsg + " 12";
			}
			var	flag2 = true;
			if(errorMsg != "Please enter whisper"){
				notify("error",errorMsg);
				flag2 = false;
			}
			if(flag2){
				$scope.isLoading=true;
				$scope.hideSave = true;
				$scope.scene.sceneDescription = obj;		    	 		    	 
			    
				$scope.scene.sceneUploadType = "Original";
				$scope.scene.modifiedBy = localStorage.getItem('userId');
				$scope.scene.createdBy = localStorage.getItem('userId');
				$scope.scene.submittedBy = localStorage.getItem('userId');
				$scope.scene.allowComments = 0;
				$scope.scene.isSubmitted = 0;
			
				if($scope.sceneId > 0){
					$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/updateSceneRecord.anka',$scope.scene);
				}else{
					$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/addSceneRecord.anka',$scope.scene);
				}
			    			    	
				$scope.requestData.then(function(data){
					$scope.jsonData=data.data;
					var flag = $scope.jsonData[0].success;
					var msg = $scope.jsonData[0].messages.message
					if(flag){
						if($scope.sceneId > 0){
							notify("success","Scene updated successfully");
						}else{
							notify("success","Scene saved successfully");
						}				    		 
						$scope.reset(sceneForm);		    	 				 			 
						$location.path('/abc');
						setTimeout(function(){
							$location.path('/manageScenes');
							$scope.isLoading=false;
						},500);											
					}else{						
						notify("error",msg);
						$scope.isLoading=false;
					}											
				},function(data){
					notify("error","Error in request");
							
					$scope.isLoading=false;
				});
			}		
			$scope.hideSave = false;			
		}	    	 			   		    	 
	}
	
	$scope.reset = function(){
		$scope.sceneForm.$setPristine();
		//$scope.scene = {};
		//$scope.sceneDesc = {};
		//$scope.stageDirection = [];
		$("#imagePreview0").attr('src','');			
		$('#img').val('');
		$('#title').val('');
		$('#authorName').val('');	    	 
		$('#timeAndPlace').val('');
		$('#notification').val('');
		$('#publishDate').val('');
		for(var no=1 ;no <= 12;no++){
			$('#whisp'+no).hide();
			$('#whisper'+no).val('');
			$('#character'+no).val('');
		}
	}	
	    
	$scope.addScene = function(){
		$pristine = true;			
		$scope.hideSave = false;
		$scope.showCancel = true;     
		$scope.hideReset = false;
		$scope.fileControl = true;
		$rootScope.form = true;
		$scope.hideImg = false;
		$scope.scene={};	    	
		$scope.sceneDesc = {};
		$scope.stageDirection = [];	    	 
		$scope.sceneDesc.whisper1 = undefined;
		$scope.sceneDesc.whisper2 = undefined;
		$scope.sceneDesc.whisper3 = undefined;
		$scope.sceneDesc.whisper4 = undefined;
		$scope.sceneDesc.whisper5 = undefined;
		$scope.sceneDesc.whisper6 = undefined;
		$scope.sceneDesc.whisper7 = undefined;
		$scope.sceneDesc.whisper8 = undefined;
		$scope.sceneDesc.whisper9 = undefined;
		$scope.sceneDesc.whisper10 = undefined;
		$scope.sceneDesc.whisper11 = undefined;
		$scope.sceneDesc.whisper12 = undefined;	  
		$('.stageDirection').attr('checked',false);
		$('#sName').text("Add Scene");
		$('#hideButton').html("<button class='btn btn-info' onclick='nextWhisper(2);'>Add The Next Whisper</button>");
		//$('#title').attr('class','form-control width-250 ng-pristine ng-invalid ng-invalid-required ng-valid-maxlength ng-touched');
		//$('#timeAndPlace').attr('class','form-control width-250 ng-pristine ng-invalid ng-invalid-required ng-valid-maxlength ng-touched');
		//$('#whisper1').attr('class','form-control width-250 ng-pristine ng-invalid ng-invalid-required ng-valid-maxlength ng-touched');			    	 	    	 	
	}
	
	/*$scope.nextWhisper = function(no){
		$scope.totalWhipsers = no;
		if(no == 12){
			$('#hideButton').html("");							
		}else{
			$('#hideButton').html("<button class='btn btn-info' onclick='nextWhisper("+(no + 1)+");'>Add The Next Whisper</button>");	
		}
		
		$('#whisp'+no).show();
	}*/
	
	$scope.showList = function(){	    	 
		/*$scope.scene = {};
		$scope.sceneDesc = {};
		$scope.stageDirection = [];
		$("#imagePreview0").attr('src','');			
		$('#img').val('');
		$('#title').val('');
		$('#authorName').val('');	    	 
		$('#timeAndPlace').val('');
		$('#notification').val('');
		$('#publishDate').val('');
		for(var no=1 ;no <= 12;no++){
			$('#whisp'+no).hide();
			$('#whisper'+no).val('');
			$('#character'+no).val('');
		}*/
		setTimeout(function(){
			$('#resetForm').click();	
		},100);		
		$rootScope.form=false;
	}
	     	    
	$scope.editTableRow=function(id,num){
		$scope.sceneDesc={};
		$rootScope.form = true;
		$scope.showCancel = true;          
		$scope.hideReset = true;
		$scope.isLoading=true;
		$scope.stageDirection = [];            	  
		if(num == 1){
			$scope.hideSave = false;
			$scope.hideImg = false;	
			$('#sName').text("Review and Edit");
		}else{
			$scope.hideSave = true;
			$scope.hideImg = true;
			$('#sName').text("View Scene");
			$('#imgPreview').css('margin-left','200px')
		}
		
		var data = {id:id};
		$scope.scene = {};
		
		$scope.editDataRequest=transport.request(mySettings.remoteServerUrl+'web/fetchSceneRecord.anka',data);
		$scope.editDataRequest.then(function(data){            		
			$rootScope.editUserRoleJsonData=data.data;
			$scope.scene = $rootScope.editUserRoleJsonData[0].response.scene;
			$scope.sceneId = $scope.scene.id; 
			/*var d = new Date($scope.scene.publishDate);
            	   var date =  d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate();            	   
            	   $scope.scene.publishDate = date;*/
			$("#imagePreview0").attr('src',$scope.scene.sceneImage);

			var elements = jQuery.parseJSON( $scope.scene.sceneDescription );
            	  
			for(var j = 0; j < elements.length; j++){
				var i = j;
				var obj = elements[j];
				$('#whisp'+ ++i).show();
				$('#character' + i).val(obj.character);
				var val = obj.whisper != "" ? obj.whisper : obj.stageDirection;
				$('#whisper'+ i).val(val);
				
				if(i == 1){
					$scope.sceneDesc.character1 = obj.character;
					$scope.sceneDesc.whisper1 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
					$scope.stageDirection[1] = obj.stageDirection != "" ? true : false;
				}else   
					if(i == 2){
						$scope.sceneDesc.character2 = obj.character;
						$scope.sceneDesc.whisper2 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
						$scope.stageDirection[2] = obj.stageDirection != "" ? true : false;
					}else   
						if(i == 3){
							$scope.sceneDesc.character3 = obj.character;
							$scope.sceneDesc.whisper3 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
							$scope.stageDirection[3] = obj.stageDirection != "" ? true : false;
						}else   
							if(i == 4){
								$scope.sceneDesc.character4 = obj.character;
								$scope.sceneDesc.whisper4 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
								$scope.stageDirection[4] = obj.stageDirection != "" ? true : false;
							}else   
								if(i == 5){
									$scope.sceneDesc.character5 = obj.character;
									$scope.sceneDesc.whisper5 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
									$scope.stageDirection[5] = obj.stageDirection != "" ? true : false;
								}else   
									if(i == 6){
										$scope.sceneDesc.character6 = obj.character;
										$scope.sceneDesc.whisper6 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
										$scope.stageDirection[6] = obj.stageDirection != "" ? true : false;
									}else   
										if(i == 7){
											$scope.sceneDesc.character7 = obj.character;
											$scope.sceneDesc.whisper7 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
											$scope.stageDirection[7] = obj.stageDirection != "" ? true : false;
										}else   
											if(i == 8){
												$scope.sceneDesc.character8 = obj.character;
												$scope.sceneDesc.whisper8 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
												$scope.stageDirection[8] = obj.stageDirection != "" ? true : false;
											}else   
												if(i == 9){
													$scope.sceneDesc.character9 = obj.character;
													$scope.sceneDesc.whisper9 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
													$scope.stageDirection[9] = obj.stageDirection != "" ? true : false;
												}else   
													if(i == 10){
														$scope.sceneDesc.character10 = obj.character;
														$scope.sceneDesc.whisper10 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
														$scope.stageDirection[10] = obj.stageDirection != "" ? true : false;
													}else   
														if(i == 11){
															$scope.sceneDesc.character11 = obj.character;
															$scope.sceneDesc.whisper11 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
															$scope.stageDirection[11] = obj.stageDirection != "" ? true : false;
														}else   
															if(i == 12){
																$scope.sceneDesc.character12 = obj.character;
																$scope.sceneDesc.whisper12 = obj.whisper != "" ? obj.whisper : obj.stageDirection;
																$scope.stageDirection[12] = obj.stageDirection != "" ? true : false;
															}   
				
				if(i <= 11){
					$('#hideButton').html("<button class='btn btn-info' onclick='nextWhisper("+(i + 1)+");'>Add next whisper</button>");
				}else{
					$('#hideButton').html("");
				}
			}            	  
			$scope.isLoading=false;
		});                	
	}
	
	$scope.close=function(){		
		ngDialog.close();			
	}	
	
	$scope.deleteScene=function(id){	
		var val = confirm("Are you sure?");
		if(val){			
			$scope.isLoading=true;	
			var data = [{id : id,roleId:localStorage.getItem('roleId'),modifiedBy:localStorage.getItem('userId'), 
				createdBy:localStorage.getItem('userId')}];
			$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/deleteSceneRecords.anka',data);
			$scope.requestData.then(function(data){				
				$scope.jsonData=data.data;
				//$scope.renderTable($scope.jsonData[0].response.scenes);
				var flag = $scope.jsonData[0].success;
				var msg = $scope.jsonData[0].messages.message
				if(flag){
					$location.path('/abc');
					notify("success","Scene deleted successfully");
					setTimeout(function(){
						$location.path('/manageScenes');
						$scope.isLoading=false;
					},500);
				}else{
					notify("error",msg);
					$scope.isLoading=false;
				}								
			},function(data){
				notify("error","Error in request");
				$scope.isLoading=false;
			});
		}			
	}    
	
	$scope.submitScene = function(scene){		
		
		var is12Whisper = JSON.parse(scene.sceneDescription).length;
		
		if(is12Whisper == 12){
			var val = confirm("Are you sure?");
			if(val){				
				var allow = 0;
				$('#allowComments:checked').each(function(){						
					if(scene.id == $(this).val()){
						allow  = 1;
					} 		        			    		        	    		        	    		     
				},this);		
		
				var data = {id:scene.id,allowComments:allow,modifiedBy:localStorage.getItem('userId'),isSubmitted:1,dateSubmitted:new Date()};
				$scope.requestData=transport.request(mySettings.remoteServerUrl+'web/updateSceneRecord.anka',data);
			    
				$scope.requestData.then(function(data){
					$scope.jsonData=data.data;
					var flag = $scope.jsonData[0].success;
	    	 		if(flag){
						$location.path('/abc');
						notify("success","Scene submitted successfully");
						setTimeout(function(){
							$location.path('/manageScenes');
							$scope.isLoading=false;
						},1500);
					}else{
						notify("error",msg);
					}											
				},function(data){
					notify("error","Error in request");	
				});	
			}	
		}
		else{
			notify("error","can't submit. Please enter all 12 whispers for this scene");			
		}					
	} 
}]);

var whispersApp=angular.module('whispers',['ngRoute','ConstantVar','ui.bootstrap','ngDialog','ngTable','transportService','notification','toggle-switch', 'angular-confirm']);
	
whispersApp.config(['$routeProvider','$locationProvider','growlProvider',function($routeProvider,$locationProvider,growlProvider){
		
	resolves = {	 
			user:['$q','$http','$route','$rootScope',function ($q, $http, $route, $rootScope) {       				
				
				if(localStorage.getItem('role') == 'Admin'){									
					$rootScope.validadmin=true;			
					$rootScope.valid=true;			
					$rootScope.validUser = true;	 
					return $route.current.templateUrl;
				}else if(localStorage.getItem('role') == 'Playwriter'){									    	
			    	$rootScope.validPlayWriter=true;				
					$rootScope.valid=true;				
					$rootScope.validUser = true;		
					return $route.current.templateUrl;
				}else if(localStorage.getItem('role') == 'Dramaturg'){								
					$rootScope.validDramaturg=true;				
					$rootScope.valid=true;			
					$rootScope.validUser = true;		
					return $route.current.templateUrl;
				}												
			}]	
	},

	$routeProvider.
	when('/',{
		controller:'LoginCtrl',
		resolve :{
			user:resolves.user
		}		
	}).when('/login',{	
		controller:'LoginCtrl',
		resolve :{
			user:resolves.user
		}		
	}).when('/registration',{
		templateUrl:"releases/master/partials/adminDashBoard.html",		
		controller:'RegisterUserCtrl',
		resolve :{
			user:resolves.user
		}
	}).when('/adminDashboard',{
		templateUrl:"releases/master/partials/adminDashBoard.html",
		controller:'AdminDashboardCtrl',
		resolve :{
			user:resolves.user
		}		
	}).when('/dramaturg',{		
		templateUrl:"releases/master/partials/adminDashBoard.html",
		controller:'DramaturgListCtrl',
		resolve :{
			user:resolves.user
		}	
	}).when('/users',{		
		templateUrl:"releases/master/partials/userListTemplate.html",
		controller:'UserListCtrl',
		resolve :{
			user:resolves.user
		}
	}).when('/manageScenes',{			
		templateUrl:"releases/master/partials/SceneListTemplate.html",
		controller:'SceneListCtrl',
		resolve :{
			user:resolves.user
		}
	}).when('/sponsorcard',{		
		templateUrl:"releases/master/partials/SponserCardListTemplate.html",
		controller:'SponserCardListCtrl',				
		resolve :{
			user:resolves.user
		}
	}).when('/feedback',{			
		templateUrl:"releases/master/partials/FeedbackTemplate.html",					
		controller:'FeedbackCtrl',
		resolve :{
			user:resolves.user
		}
	}).when('/winner',{			
		templateUrl:"releases/master/partials/WinnerListTemplate.html",
		controller:'WinnerListCtrl',				
		resolve :{
			user:resolves.user
		}
	}).when('/addsponsercard',{			
		templateUrl:"releases/master/partials/cpmaster-designtemplate3.html",
		controller:'SponserCardListCtrl',
		resolve :{
			user:resolves.user
		}
	}).when('/comments',{		
		templateUrl:"releases/master/partials/CommentsTemplate.html",
		controller:'CommentsCtrl',				
		resolve :{
			user:resolves.user
		}
	}).when('/playwriterDashBoard',{
		templateUrl:'partials/playWriterDashboard.html', 				
		resolve :{
			user:resolves.user
		}
	}).when('/reports',{
		templateUrl:'releases/master/partials/ReportTemplate.html', 	
		controller:'ReportListCtrl',
		resolve :{
			user:resolves.user
		}
	}).when('/userReport',{	
		templateUrl:'releases/master/partials/UserReportTemplate.html', 	
		controller:'UserReportCtrl',
		resolve :{
			user:resolves.user
		}
	}).when('/feedbackReport',{	
		templateUrl:'releases/master/partials/FeedbackReportTemplate.html', 	
		controller:'FeedbackReportCtrl',
		resolve :{
			user:resolves.user
		}
	}).when('/tandC',{
		templateUrl:'releases/master/partials/TAndCTemplate.html', 	
		controller:'TAndCCtrl',
		resolve :{
			user:resolves.user
		}
	}).when('/activateUser',{
		templateUrl:'releases/master/partials/ActiveUserTemplate.html', 	
		controller:'activateUserCtrl',
		resolve :{
			user:resolves.user
		}
	}).otherwise({				
		redirectTo:'/'
	});		
}]);
	
whispersApp.directive('checkStrength',function(){
	
	return {
		replace: false,
		restrict: 'EAC',
		template: '<li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li><li class="point"></li>',
		link: function (scope, iElement, iAttrs) {
			var strength = {
					colors: ['#F00', '#F90', '#FF0', '#9F0', '#0F0'],
					mesureStrength: function (p) {

						var _force = 0;                    
						var _regex = /[$-/:-?{-~!"^_`\[\]]/g;
                                          
						var _lowerLetters = /[a-z]+/.test(p);                    
						var _upperLetters = /[A-Z]+/.test(p);
						var _numbers = /[0-9]+/.test(p);
						var _symbols = _regex.test(p);
                                          
						var _flags = [_lowerLetters, _upperLetters, _numbers, _symbols];                    
						var _passedMatches = $.grep(_flags, function (el) { return el === true;}).length;                                          
                    
						_force += 2 * p.length + ((p.length >= 10) ? 1 : 0);
						_force += _passedMatches * 10;
                        
						// penality (short password)
						_force = (p.length <= 6) ? Math.min(_force, 10) : _force;                                      
                    
						// penality (poor variety of characters)
						_force = (_passedMatches == 1) ? Math.min(_force, 10) : _force;
						_force = (_passedMatches == 2) ? Math.min(_force, 20) : _force;
						_force = (_passedMatches == 3) ? Math.min(_force, 40) : _force;
                    
						return _force;
					},
					getColor: function (s) {

						var idx = 0;
						if (s <= 10) { idx = 0; }
						else if (s <= 20) { idx = 1; }
						else if (s <= 30) { idx = 2; }
						else if (s <= 40) { idx = 3; }
						else { idx = 4; }

						return { idx: idx + 1, col: this.colors[idx] };
					}
			};

			scope.$watch(iAttrs.checkStrength, function () {
				if (scope.password == undefined) {
					iElement.css({ "display": "none"  });
				} else {           
					var c = strength.getColor(strength.mesureStrength(scope.password));
					iElement.css({ "display": "inline" });
					iElement.children('li')
					.css({ "background": "#DDD" })
					.slice(0, c.idx)
					.css({ "background": c.col });
				}
			});
		},
	};	
})

whispersApp.directive('ckEditor', [function () {
	return {
		require: '?ngModel',
		restrict: 'C',
		link: function(scope, elm, attr, ngModel) {
			var ck = CKEDITOR.replace(elm[0]);
			
			if (!ngModel) return;
        
			ck.on('pasteState', function() {
				scope.$apply(function() {
					ngModel.$setViewValue(ck.getData());                   
				});
			});   
        
			ngModel.$render = function(value) {
				ck.setData(ngModel.$viewValue);
			};
		}
	};
}]);
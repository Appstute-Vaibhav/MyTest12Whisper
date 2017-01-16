var transportServ=angular.module('transportService',[]);
	
transportServ.factory('transport',function($http,$q){
		
	var request=function(url,data){		
		return $http({
			method:data == undefined? 'GET' : 'POST',
					url:url	,
					data:data						
		});
	}	
	return {		
		request:request		
	}
})

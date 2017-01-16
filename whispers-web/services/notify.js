var notify=angular.module('notification', ['angular-growl']);
notify.factory('notify',['growl',function(growl){
	return function(type,msg){
		if(type=='warning'){
			growl.warning(msg,{title:''});
		}else if(type=='info'){
			growl.info(msg, {title: ''});
		}else if(type=='success'){
			growl.success(msg); //no title here
		}else if(type=='error'){
			growl.error(msg, {title: ''});
		}else if(type=='clear'){
			growl.stopTimeoutClose(msg);
		}
	}		
}]);

notify.config(['growlProvider',function(growlProvider){
	growlProvider.onlyUniqueMessages(true);
	growlProvider.globalReversedOrder(true);
	growlProvider.globalTimeToLive({success: 5000, error: 5000, warning: 5000, info: 5000});	
}]);








/**
 * 
 */


var myApp = angular.module("myApp", []);

angular.module("myApp").constant("registerUrl","user/");

myApp.controller("LoginCtrl", function ($scope) {

});
myApp.controller("NavbarCtrl", function ($scope) {
	

});

myApp.controller("RegiCtrl", function(UserService,$scope)
		{
    var registerData = this;
	
	registerData.newUser = {};
	registerData.message = "";
	
	registerData.register = function(){
		
		var promise = UserService.register(registerData.newUser);
/*		
		promise.then(function(response){

			if(response.data != null && response.data != ""){
				
				//iff register success then authenticate user
				var authpromise = ForumService.auth(registerData.newUser);
				authpromise.then(function(response) {
						if(response.data){
							ForumService.setAuthUser(response.data);
							$uibModalInstance.close();
							$state.go("allRoomsState");
						}
					}, function(response) {}); 
			}
		}, function(response){
			registerData.message = "username already exists";
		});*/
		
	}
	
});
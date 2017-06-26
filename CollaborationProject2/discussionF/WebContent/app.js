/**
 * 
 */


var myApp = angular.module("myApp", []);

myApp.controller("NavbarCtrl", function ($scope) {
	

});
myApp.controller("UserController", function(UserService,$scope)
		{
    var self = this;
	
    self.newUser = {};
    self.message = "";
	
    self.register = function(){
		
		var promise = UserService.register(self.newUser);
		
	}
    
    self.authenticate = function(user)
	{
		console.log("Authenticate Function");
		console.log(user);
		UserService.authenticate(user)
		.then 
		(
			function(d)
			{/*
				$scope.user = d;
				console.log("User ErrorCode - "+$scope.user.errorCode)
				if($scope.user.status == 'R')
					{
						alert("Your Registeration is Rejected. Please Contact ADMIN");
						user.setErrorCode("404");
						user.setErrorMsg("Your Registeration is Rejected");
					}
				if($scope.user.status == 'N')
				{
					alert("Your Registeration is Not Yet Approved. Please wait for some time.");
					user.setErrorCode("404");
					user.setErrorMsg("Your Registeration is Not Approved");
				}
				if($scope.user.username == null)
				{
					alert("Invalid Username or Password");
					console.log("Invalid Username or Password")
					$location.path("/login");
				}
				
				else
				{
					console.log("Valid Credentials, Navigating to home page "+$scope.user.status)
					$scope.message1="Successfully Logged in as ";
					$rootScope.currentUser = 
						{
							username: $scope.user.username,
							first_name: $scope.user.first_name,
							last_name: $scope.user.last_name,
							dob: $scope.user.dob,
							gender: $scope.user.gender,
							mail_id: $scope.user.mail_id,
							status: $scope.user.status,
							role: $scope.user.role,
							birthdate: $scope.user.birthdate,
							isOnline: $scope.user.isOnline,
							last_seen: $scope.user.last_seen
						};
					$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
					$cookieStore.put('currentUser', $rootScope.currentUser)
					$location.path("/");
				}
			*/}, 	function(errResponse)
			{
				console.error("Error Authenticating User");
				/*$scope.message = "Invalid username or password.";
				$location.path("/login");*/
			}
		);
	};
    
    self.login= function()
	{
		console.log("Validating Login ");
		console.log(self.newUser);
		self.authenticate(self.newUser);
	};

});



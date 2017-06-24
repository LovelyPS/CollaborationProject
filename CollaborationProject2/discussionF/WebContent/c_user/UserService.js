angular.module("myApp")
.service("UserService", function($http){
	
	var serviceData = this;
	
	serviceData.authUser = {};
	
	
	/*serviceData.register = function(user){
		
		return $http({
			method:'PUT',
			url:"user/",
			data:user
		}).then(function(response){
			return response;
		}, function(response){
			throw response;
		});
	}*/
	console.log("Entering UserService")
	var BASE_URL = "http://localhost:9007/discussionB/"
		return{
		//var userService = this;
		
		register: function(user)
		{
			console.log("Entering Function Register User")
			console.log(user)
		/*	return $http.post(BASE_URL + "user", "Hia...")*/
			return $http.post(BASE_URL + "user", user)
			.then(function(response)
				{
					alert("Regesteration Succcess. Wait for admin aproval")
					console.log(response.status)
					return response.status
				},function(errResponse)
				{					
					console.log(errResponse.status)
					return errResponse.status
				});
		}
	}
		
	
});
	
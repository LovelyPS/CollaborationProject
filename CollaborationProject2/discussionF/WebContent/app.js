/**
 * 
 */


var myApp = angular.module("myApp", ['ngRoute', 'ngCookies']);

myApp.config(["$routeProvider","$locationProvider", function ($routeProvider,$locationProvider) {
    $routeProvider
        .when("/login", {
            templateUrl: "c_user/login.html"
        })
        .when("/register", {
            templateUrl: "c_user/register.html"
        })
        .when("/admin", {
            templateUrl: "c_admin/aHome.html"
        })
        .when('/manageUsers',
			{
				templateUrl : 'c_admin/mUsers.html',	
				controller : 'AdminController'
			})
        .when('/manageBlogs',
			{
				templateUrl : 'c_admin/mBlogs.html',
				controller : 'AdminController'
			})
			.when('/manageForums',
			{
				templateUrl : 'c_admin/mForums.html',
				controller : 'AdminController'
			})
		.when('/manageJobs',
			{
				templateUrl : 'c_admin/mJobs.html',
				controller : 'AdminController'
			})
			
        .otherwise({
        redirectTo: "/"
    });
    $locationProvider.hashPrefix('');
}]);



myApp.run( function ($rootScope, $location, $cookieStore, $http) 
		{
			 $rootScope.$on('$locationChangeStart', function (event, next, current) 
			 {
				 console.log("$locationChangeStart")
				    
				 var userPages = ['/myProfile','myFriends','pendingRequests','sentRequests','/upload','/viewUsers','/addBlogs','/addForum','/viewProfile','/viewBlog','/viewForum','/viewForums'];
				 var adminPages = ['/admin','/manageUsers','/manageJobs','/manageEvents','/manageForums','/manageBlogs','/addEvents','/addJobs','/jred','/ered','/appliedJobs'];
				 
				 var currentPage = $location.path();
				 
				 var isUserPage = $.inArray(currentPage, userPages);
				 var isAdminPage = $.inArray(currentPage, adminPages);
				 
				 var isLoggedIn = $rootScope.currentUser.username;
			        
			     console.log("isLoggedIn:" +isLoggedIn)
			     console.log("isUserPage:" +isUserPage)
			     console.log("isAdminPage:" +isAdminPage)
			        
			        if(!isLoggedIn)
			        	{
			        	
			        		if(isUserPage!=-1 || isAdminPage!=-1)  
			        	 	{
				        	  console.log("Navigating to login page:")
				        	  alert("You need to Login first!")
				        	  $location.path('/login');
				         	}
			        	}
			        
					 else //logged in
			        	{
			        	
						 var role = $rootScope.currentUser.role;
						 if(isAdminPage!=-1 && role!='ADMIN' )
							 {
							  alert("You cannot view this page as a " + role )
							  $location.path('/');
							 }
			        	}
			 });
			 
			 // to keep the user logged in after page refresh
		    $rootScope.currentUser = $cookieStore.get('currentUser') || {};
		    if ($rootScope.currentUser)
		    {
		        $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser; 
		    }
		});

myApp.controller("UserController", function(UserService,$scope,$location,$rootScope,$http,$cookieStore)
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
			{
				console.log("Login Successfull...")
				console.log(d)
				$scope.user = d;
				console.log("User ErrorCode - "+$scope.user.errorCode)
				if($scope.user.status == 'R')
					{
						alert("Your Registeration is Rejected. Please Contact ADMIN");
					}
				else if($scope.user.status == 'N')
				{
					alert("Your Registeration is Not Yet Approved. Please wait for some time.");
			    }
				else if($scope.user.username == null)
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
							first_name: $scope.user.f_name,
							last_name: $scope.user.l_name,
							gender: $scope.user.gender,
							mail_id: $scope.user.mail_id,
							status: $scope.user.status,
							role: $scope.user.role,
							isOnline: $scope.user.isOnline,
							last_seen: $scope.user.last_seen
						};
					$http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser;
					$cookieStore.put('currentUser', $rootScope.currentUser)
					$location.path("/");
				}
			}, 	function(errResponse)
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
	self.logout= function()
	{
		document.getElementById("mySidenav").style.width = "0";
	    document.getElementById("main").style.marginLeft= "0";
		console.log("Entering Logout Function");
		$rootScope.currentUser = {};
		$cookieStore.remove('currentUser');
		
		console.log("Calling Session Logout");
		UserService.logout()
		$location.path('/login');
	};

});



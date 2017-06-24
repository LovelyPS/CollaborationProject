package com.niit.discussionB.restControllers;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.niit.discussionB.dao.UserDao;
import com.niit.discussionB.model.User;

@CrossOrigin(origins = {"http://localhost:9007"}, maxAge = 4800, allowCredentials = "false")
@RestController
public class UserController 
{
	private static final Logger log=LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private User user;
	
	/*@CrossOrigin(origins = {"http://localhost:9007"}, maxAge = 6000)
	@RequestMapping(value="user", method=RequestMethod.PUT)
	public User registerUser(@RequestBody User registerUser){
		System.out.println("In Rest Controller");
		userDao.save(registerUser);
		return registerUser;
	}*/
	/*@RequestMapping("/user")
	public void register(HttpServletRequest request){
		String name=request.getParameter("txtUser");
		String email=request.getParameter("rEmail");
		String password=request.getParameter("rPassword");
		
		user=new User();
		user.setName(name);
		user.setPassword(password);
		user.setMail(email);
		
		userDao.save(user);		
		
	}*/
	/*@PostMapping("/user")*/
	
	
	@RequestMapping(value="/user", method=RequestMethod.POST) 
	//public String addUser(@RequestBody User user)
	
	/*public ResponseEntity<User> addUser(@RequestBody User user)*/
	public ResponseEntity<User> addUser(@RequestBody User user)
	{	  
		System.out.println("In.....REgistration..."+user);
		user.setOnline(true);
		userDao.save(user);
		
	    /*user.setStatus('N');
	    user.setIsOnline('N');
		boolean value = userDao.save(user);
		if (value == true) 
		{
			user.setErrorCode("200");
			user.setErrorMsg("User added Successfully");
		} 
		else 
		{
			user.setErrorCode("100");
			user.setErrorMsg("Add User Failed");
		}*/
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
		/*return "Fine"+user;*/
	}

	
}

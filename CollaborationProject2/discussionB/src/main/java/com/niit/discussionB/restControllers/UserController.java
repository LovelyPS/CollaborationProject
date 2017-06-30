package com.niit.discussionB.restControllers;



import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	@Autowired
	HttpSession session;
	
	
	@RequestMapping("/getUserList")
	public ResponseEntity<List<User>> getUserList() throws NullPointerException
	{
			List<User> list = userDao.list();
			if (list.isEmpty()) 
			{
				user.setErrorCode("100");
				user.setErrorMessage("Users are not available");
			}
			else
			{
				
				for(User user : list)
				{
					user.setErrorCode("200");
					user.setErrorMessage("Success");
				}
			}
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/user", method=RequestMethod.POST) 
	public ResponseEntity<User> addUser(@RequestBody User user)
	{	  
		System.out.println("In.....REgistration..."+user);
		user.setStatus('N');
	    user.setIsOnline('N');
		userDao.save(user);
		
	   
	    /*boolean value = userDao.save(user);
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
		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST) 
	public ResponseEntity<User> validateUser(@RequestBody User user) 
	{
		System.out.println(user);
		boolean value = userDao.validate(user.getUsername(), user.getPassword());
		
		System.out.println(value);
		if (value == false) 
		{
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Wrong username or password.");
			System.out.println("Wrong username or password.");
		}
		else
		{
			user=userDao.get(user.getUsername());
			System.out.println("User Exist..");
			System.out.println(user);
			if(user.getStatus()=='R')
			{
				
				user.setErrorCode("404");
				user.setErrorMessage("Registeration is rejected. Please Contact Admin");
				System.out.println("Registeration is rejected. Please Contact Admin");
				
			}
			if(user.getStatus()=='N')
			{
				
				user.setErrorCode("404");
				user.setErrorMessage("Registeration approval is pending. Please try again later");
				System.out.println("Registeration approval is pending. Please try again later");
			}
			else
			{
				
				user.setIsOnline('Y');
				/*Date_Time dt = new Date_Time();
				user.setLast_seen(dt.getDateTime());*/
				userDao.update(user);
				 
				/*friendDAO.setUsersOnline(user.getUsername());*/
				session.setAttribute("username", user.getUsername());
				session.setAttribute("role", user.getRole());
				session.setAttribute("isLoggedIn", "true");
				/*if(user.getDob()!=null)
					user.setBirthdate( dt.toStringDate(user.getDob()));*/
				
				user.setErrorCode("200");
				user.setErrorMessage("Success");
				System.out.println("Success");
				/*System.out.println("Name = "+session.getAttribute("username").toString());
				System.out.println("Role = "+session.getAttribute("role").toString());*/
			}
		}
		System.out.println("At Last....");
		System.out.println(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST) 
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		if(user != null)
		{
			boolean value = userDao.update(user);
			if (value == true) 
			{
				user.setErrorCode("200");
				user.setErrorMessage("User updated Successfully");
			} 
			else 
			{
				user.setErrorCode("100");
				user.setErrorMessage("Add User Failed");
				return null;
			}
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	

	
}

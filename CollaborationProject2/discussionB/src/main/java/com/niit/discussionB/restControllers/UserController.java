package com.niit.discussionB.restControllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.niit.discussionB.dao.UserDao;
import com.niit.discussionB.model.Blog;
import com.niit.discussionB.model.User;

@RestController
public class UserController 
{
	private static final Logger log=LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private User user;
	

	@RequestMapping(value = "/createusers/", method = RequestMethod.POST)
	public ResponseEntity<User> createusers(@RequestBody User user, HttpSession session) {
		log.debug("-->CALLING METHOD CREATE BLOG");
		String userid = (String) session.getAttribute("loggedInUserId");
		user.setId(userid);
		
		userDao.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> fetchAllUsers() {
		log.debug("-->CALLING METHOD LIST ALL USERS");
		List<User> users = userDao.list();
		
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}
}

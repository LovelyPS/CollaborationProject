package com.niit.discussionB.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.discussionB.dao.UserDao;
import com.niit.discussionB.model.User;

@EnableTransactionManagement
@Repository("userDao")
public class UserDaoImpl implements UserDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDaoImpl(SessionFactory sessionFactory)
	{
		
		this.sessionFactory = sessionFactory;
	}
	

	public UserDaoImpl() {
		super();
	}


	public User get(String id) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void save(User u)
	{
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		s.save(u);
		s.getTransaction().commit();
		s.close();
		

	}

	public void update(User u) {
		// TODO Auto-generated method stub

	}

	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validate(String id, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}

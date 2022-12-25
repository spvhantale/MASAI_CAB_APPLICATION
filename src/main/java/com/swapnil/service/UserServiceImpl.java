package com.swapnil.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swapnil.dto.LoginDTO;
import com.swapnil.exception.CurrentUserException;
import com.swapnil.exception.DriverException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Driver;
import com.swapnil.model.MyUser;
import com.swapnil.model.User;
import com.swapnil.repository.DriverDAO;
import com.swapnil.repository.MyUserDAO;
import com.swapnil.repository.SessionDAO;
import com.swapnil.repository.UserDAO;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private SessionDAO sDao;
	@Autowired
	private UserDAO uDao;
	@Autowired
	private DriverDAO dDao;
	@Autowired
	private MyUserDAO mDao;
	@Autowired
	private PasswordEncoder pEnd;
	
	@Override
	public User registerUser(User user) throws UserException {
		Optional<User> u=uDao.findByEmail(user.getEmail());
		
		if(u.isPresent()) {
			throw new UserException("Change email");
		}else {
			user.setPassword(pEnd.encode(user.getPassword()));
			MyUser myuser=new MyUser(user.getEmail(), user.getFirstName(), user.getPassword(), "user");
			mDao.save(myuser);
			User us=uDao.save(user);
			us.setPassword("*******");
			return us;
		}
	}

	@Override
	public CurrentUserSession login(LoginDTO login) throws CurrentUserException {
		
		Optional<CurrentUserSession> c=sDao.findByUserName(login.getUserName());
		
		if(c.isPresent()) {
			throw new CurrentUserException("Already login");
		}else {
			Optional<User> u=uDao.findByEmail(login.getUserName());
			if(u.isPresent()) {
			String key=RandomString.make(6);
			CurrentUserSession cu=new CurrentUserSession(u.get().getUserId(), login.getUserName(), key, LocalDateTime.now());
			
			CurrentUserSession curr=sDao.save(cu);
			return curr;
			}else {
				throw new CurrentUserException("User not found");
			}
		}
	}

	@Override
	public String logout(String key) throws CurrentUserException {
		Optional<CurrentUserSession> c=sDao.findByUuid(key);
		if(c.isPresent()) {
			
			sDao.delete(c.get());
			return "Logout";
		}else {
			throw new CurrentUserException("User not login OR key is wrong");
		}
		
		
	}

	@Override
	public List<Driver> getAllDriver(String key) throws DriverException,CurrentUserException {

		Optional<CurrentUserSession> c=sDao.findByUuid(key);
		if(c.isPresent()) {
			
			CurrentUserSession cu=c.get();
			Optional<User> u=uDao.findByEmail(cu.getUserName());
			if(u.isPresent()) {
				User us=u.get();
				List<Driver> dlist=dDao.findAll();
				Integer[] arr=us.getCurrentPosition();
				int x1=arr[0];
				int y1=arr[1];
				List<Driver> ddlist=new ArrayList<>();
				for(Driver d:dlist) {
					Integer[] arr1=d.getPosition();
					int x2=arr1[0];
					int y2=arr1[1];
					
					double dr=Math.sqrt(Math.pow(Math.abs(x1-x2),2)+Math.pow(Math.abs(y1-y2),2));
					Integer dis=(int)dr;
					if(dis<=5) {
						ddlist.add(d);
					}
				}
				if(ddlist.isEmpty()) {
					throw new DriverException("Drivers are not available");
				}
				return ddlist;
				
			}else {
				throw new CurrentUserException("User not found");
			}
			
		}else {
			throw new CurrentUserException("User not login OR key is wrong");
		}
	}

	@Override
	public String updateDriverAndUser(Integer driverId, Integer x, Integer y, String key) throws DriverException ,CurrentUserException{

		Optional<CurrentUserSession> c=sDao.findByUuid(key);
		if(c.isPresent()) {
			Optional<Driver> d=dDao.findById(driverId);
			if(d.isPresent()) {
				CurrentUserSession cu=c.get();
				Optional<User> u=uDao.findByEmail(cu.getUserName());
				User us=u.get();
				Driver dr=d.get();
				Integer[] arr=us.getCurrentPosition();
				int x1=arr[0];
				int y1=arr[1];
				Integer[] arr1=dr.getPosition();
				int x2=arr1[0];
				int y2=arr1[1];
				double dss=Math.sqrt(Math.pow(Math.abs(x1-x2),2)+Math.pow(Math.abs(y1-y2),2));
				Integer dis=(int)dss;
				if(dis<=5) {
					Integer[] a=new Integer[2];
					a[0]=x;
					a[1]=y;
					dr.setPosition(a);
					dDao.save(dr);
					us.setCurrentPosition(a);
					uDao.save(us);
					
					return "Book"+" "+dr;
				}else {
					throw new DriverException("Enter driver distance is greater than 5");
				}
				
				
			}else {
				throw new DriverException("Driver not present");
			}
		}else {
			throw new CurrentUserException("User not found");
		}
		
	}

}

package com.bookmyshow.bootproject.Bookmyshow.boot.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Booking;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.User;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.BookingNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.LoginFailed;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.UserNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Bookingdao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Userdao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dto.Userdto;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	Userdao uDao;
	
	@Autowired
	Bookingdao bDao;
	
	
	
	public ResponseEntity<ResponseStructure<Userdto>> saveUser(User user)
	{
		
			ResponseStructure<Userdto> str = new ResponseStructure<Userdto>();
			Userdto uDto = new Userdto();
			ModelMapper mapper=new ModelMapper();
			mapper.map(uDao.saveUser(user), uDto);
			
			str.setMessage(user.getUsername()+" User has Saved");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(uDto);
			
			return new ResponseEntity<ResponseStructure<Userdto>>(str,HttpStatus.CREATED);
			
		
	}
	
	public ResponseEntity<ResponseStructure<Userdto>> findUser(int userid)
	{
		ResponseStructure<Userdto> str= new ResponseStructure<Userdto>();
		User u=uDao.findUser(userid);
		Userdto uDto = new Userdto();
		if(u!=null)
		{
			ModelMapper mapper=new ModelMapper();
			mapper.map(u, uDto);
			
			str.setMessage(u.getUsername()+" user has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(uDto);
			
			return new ResponseEntity<ResponseStructure<Userdto>>(str,HttpStatus.FOUND);
			
		}
		throw new UserNotFound("User not found with the given id"+userid);
	}
	
	public ResponseEntity<ResponseStructure<Userdto>> deleteUser(int userid, String email, String password)
	{
		User u =uDao.userLogin(email, password);
		if(u!=null)
		{
			ResponseStructure<Userdto> str= new ResponseStructure<Userdto>();
			
			User exu=uDao.findUser(userid);
			Userdto uDto = new Userdto();
			if(exu!=null)
			{
				ModelMapper mapper=new ModelMapper();
				mapper.map(uDao.deleteUser(userid), uDto);
				
				str.setMessage(u.getUsername()+" user has deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(uDto);
				
				return new ResponseEntity<ResponseStructure<Userdto>>(str,HttpStatus.OK);
			}
			throw new UserNotFound("User not found with the given id"+userid);
		}
		throw new LoginFailed("Enter valid email & password");
		
	}
	
	public ResponseEntity<ResponseStructure<Userdto>> updateUser(User user, int userid, String email, String password)
	{
		User u =uDao.userLogin(email, password);
		if(u!=null)
		{
			ResponseStructure<Userdto> str=new ResponseStructure<Userdto>();
			
			User exu=uDao.findUser(userid);
			Userdto uDto = new Userdto();
			if(exu!=null)
			{
				ModelMapper mapper=new ModelMapper();
				mapper.map(uDao.updateUser(user, userid), uDto);
				
				str.setMessage(user.getUsername()+" user has updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(uDto);
				
				return new ResponseEntity<ResponseStructure<Userdto>>(str,HttpStatus.OK);
			}
			throw new UserNotFound("User not found with the given id"+userid);
		}
		throw new LoginFailed("Enter valid email & password");
		
	}
	
	
	public ResponseEntity<ResponseStructure<List<Userdto>>> findAllUser()
	{
		List<User> uList=uDao.findAllUser();
		List<Userdto> udList= new ArrayList<Userdto>();
		if(!uList.isEmpty())
		{
			
			for (User u : uList) {
				Userdto uDto = new Userdto();
				ModelMapper mapper=new ModelMapper();
				mapper.map(u, uDto);
				udList.add(uDto);
			}
			ResponseStructure<List<Userdto>> str= new ResponseStructure<List<Userdto>>();
			
			str.setMessage("All user's are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(udList);
			
			return new ResponseEntity<ResponseStructure<List<Userdto>>>(str,HttpStatus.FOUND);
			
		}
		throw new UserNotFound("User not found");
	}
	
	public ResponseEntity<ResponseStructure<Userdto>> assignBookingToUser(int userid,int Bookingid,String email,String password)
	{
		User vu=uDao.userLogin(email, password);
		if(vu!=null)
		{
			ResponseStructure<Userdto> str = new ResponseStructure<Userdto>();
			User u=uDao.findUser(userid);
			Userdto uDto= new Userdto();
			List<Booking> bList=u.getUBooking();
			Booking b=bDao.findBooking(Bookingid);
			if(u!=null && b!=null)
			{
				bList.add(b);
				ModelMapper mapper=new ModelMapper();
				mapper.map(uDao.updateUser(u, userid), uDto);
				str.setMessage("booking assigned to user");
				str.setStatus(HttpStatus.OK.value());
				str.setData(uDto);
				
				return new ResponseEntity<ResponseStructure<Userdto>>(str,HttpStatus.OK);
				
			}
			throw new BookingNotFound("booking not founded with the given id "+Bookingid);
		}
		throw new LoginFailed("Enter valid email & password");
	}
	
	public ResponseEntity<ResponseStructure<Userdto>> deleteBookingFromUser(int userid, int Bookingid,String email,String password)
	{
		User vu=uDao.userLogin(email, password);
		if(vu!=null)
		{
			User u=uDao.findUser(userid);
			Userdto uDto= new Userdto();
			Booking b=bDao.findBooking(Bookingid);
			List<Booking> bList=u.getUBooking();
			ResponseStructure<Userdto> str=new ResponseStructure<Userdto>();
			for (Booking booking : bList) {
				
				if(booking.getBookingid()==Bookingid)
				{
					bList.remove(b);
					u.setUBooking(bList);
					bDao.deleteBooking(Bookingid);
					ModelMapper mapper=new ModelMapper();
					mapper.map(uDao.updateUser(u, userid), uDto);
					str.setMessage("booking has deleted from user");
					str.setStatus(HttpStatus.OK.value());
					str.setData(uDto);
					
					return new ResponseEntity<ResponseStructure<Userdto>>(str,HttpStatus.OK);
				}
				throw new BookingNotFound("booking not fount with the given id");
			}
			throw new BookingNotFound("booking not fount with the given id");
		}
		throw new LoginFailed("enter valid email & password");
	}
	
	public ResponseEntity<ResponseStructure<Userdto>> removeBookingFromUser(int userid, int Bookingid,String email,String password)
	{
		User vu=uDao.userLogin(email, password);
		if(vu!=null)
		{
			User u=uDao.findUser(userid);
			Userdto uDto= new Userdto();
			Booking b=bDao.findBooking(Bookingid);
			List<Booking> bList=u.getUBooking();
			ResponseStructure<Userdto> str=new ResponseStructure<Userdto>();
			for (Booking booking : bList) {
				
				if(booking.getBookingid()==Bookingid)
				{
					bList.remove(b);
					u.setUBooking(bList);
					ModelMapper mapper=new ModelMapper();
					mapper.map(uDao.updateUser(u, userid), uDto);
					str.setMessage("booking has removed from user");
					str.setStatus(HttpStatus.OK.value());
					str.setData(uDto);
					
					return new ResponseEntity<ResponseStructure<Userdto>>(str,HttpStatus.OK);
				}
				throw new BookingNotFound("booking not fount with the given id");
			}
			throw new BookingNotFound("booking not fount with the given id");
		}
		throw new LoginFailed("enter valid email & password");
	}
}
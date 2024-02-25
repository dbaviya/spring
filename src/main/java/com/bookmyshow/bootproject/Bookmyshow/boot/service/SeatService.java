package com.bookmyshow.bootproject.Bookmyshow.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Admin;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Seat;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.LoginFailed;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.SeatNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Admindao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Seatdao;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

@Service
public class SeatService {
@Autowired

  Seatdao sDao;

 @Autowired
 Admindao aDao;

  public ResponseEntity<ResponseStructure<Seat>> saveSeats(Seat seats,String adminemail,String adminpassword)
 {
	  Admin exa=aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
	ResponseStructure<Seat> str=new ResponseStructure<Seat>();
	
	str.setMessage(" seat has added");
	str.setStatus(HttpStatus.CREATED.value());
	str.setData(sDao.saveSeats(seats));
	
	return new ResponseEntity<ResponseStructure<Seat>>(str,HttpStatus.CREATED);
	
}
  throw new LoginFailed("Enter valid email & passworrd");
}
public ResponseEntity<ResponseStructure<Seat>> findSeats(int seatId)
{
	ResponseStructure<Seat> str=new ResponseStructure<Seat>();
	Seat s= sDao.findSeats(seatId);
	if(s!=null)
	{
		str.setMessage("Seats has founded");
		str.setStatus(HttpStatus.FOUND.value());
		str.setData(s);
		
		return new ResponseEntity<ResponseStructure<Seat>>(str,HttpStatus.FOUND);
	}
	throw new SeatNotFound("Seats not found with the given id"+seatId);
}

public ResponseEntity<ResponseStructure<Seat>> deleteSeats(int seatId,String adminemail,String adminpassword)
{

	Admin exa=aDao.adminLogin(adminemail, adminpassword);
	if(exa!=null)
	{
	ResponseStructure<Seat> str= new ResponseStructure<Seat>();
	
	Seat s= sDao.findSeats(seatId);
	
	if(s!=null)
	{
		str.setMessage("Seat has Deleted");
		str.setStatus(HttpStatus.OK.value());
		str.setData(sDao.deleteSeats(seatId));
		
		return new ResponseEntity<ResponseStructure<Seat>>(str,HttpStatus.OK);
	}
	throw new SeatNotFound("Seats not found with the given id"+seatId);
}
throw new LoginFailed("Enter valid email & passworrd");

	
}

public ResponseEntity<ResponseStructure<Seat>> updateSeats(Seat seats,int seatId ,String adminemail,String adminpassword)
{
	Admin exa=aDao.adminLogin(adminemail, adminpassword);
	if(exa!=null)
	{
	ResponseStructure<Seat> str= new ResponseStructure<Seat>();
	
	Seat s=sDao.findSeats(seatId);
	if(s!=null)
	{
		str.setMessage(" Seat has updated");
		str.setStatus(HttpStatus.OK.value());
		str.setData(sDao.updateSeats(seats, seatId));
		
		return new ResponseEntity<ResponseStructure<Seat>>(str, HttpStatus.OK);
	}
	throw new SeatNotFound("Seats not found with the given id"+seatId);
}
throw new LoginFailed("Enter valid email & passworrd");

}

public ResponseEntity<ResponseStructure<List<Seat>>> findAllSeats()
{
	List<Seat> sList=sDao.findAllSeats();
	ResponseStructure<List<Seat>> str= new ResponseStructure<List<Seat>>();
	
	if(sList!=null)
	{
		str.setMessage("All seats are founded");
		str.setStatus(HttpStatus.FOUND.value());
		str.setData(sList);
		
		return new ResponseEntity<ResponseStructure<List<Seat>>>(str,HttpStatus.FOUND);
		
	}
	throw new SeatNotFound("Seats not found");
	
}
}

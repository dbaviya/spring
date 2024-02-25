package com.bookmyshow.bootproject.Bookmyshow.boot.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Booking;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Movies;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Shows;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.User;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.BookingNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.LoginFailed;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.ShowsNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Bookingdao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Moviedao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Seatdao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Userdao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Showsdao;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

@Service
public class BookingService {
	@Autowired
	Bookingdao bDao;
	
	@Autowired
	Userdao uDao;
	
	@Autowired
	Moviedao mDao;
	
	@Autowired
	Showsdao sDao;
	
	@Autowired
	Seatdao seatDao;
	
	ModelMapper mapper=new ModelMapper();
	
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(Booking booking,String email,String password)
	{

		User exu=uDao.userLogin(email, password);
		if(exu!=null)
		{
			ResponseStructure<Booking> str = new ResponseStructure<Booking>();
			int noOfTickets=booking.getNooftickets();
			Movies exm=mDao.findmovietitle(booking.getBMovieName().toLowerCase());
			
			booking.setBprice(exm.getMovieprice()*noOfTickets);
			booking.setBookingDate(exm.getMovieDate());
			booking.setBseats(noOfTickets);
			booking.setBShows(exm.getMShow());
			str.setMessage("booking has done");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(bDao.saveBooking(booking));
			
			return new ResponseEntity<ResponseStructure<Booking>>(str,HttpStatus.CREATED);
		}
		throw new LoginFailed("Enter the valid email & password");
	}
	
	public ResponseEntity<ResponseStructure<Booking>> findBooking(int bookingid)
	{
		ResponseStructure<Booking> str= new ResponseStructure<Booking>();
		Booking b=bDao.findBooking(bookingid);
		
		if(b!=null)
		{
			
			str.setMessage("Booking has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(b);
			return new ResponseEntity<ResponseStructure<Booking>>(str,HttpStatus.FOUND);
		}
		throw new BookingNotFound("booking not found with the given id"+bookingid);
	}
	
	public ResponseEntity<ResponseStructure<Booking>> deleteBooking(int bookingid,String email,String password)
	{
		User exu=uDao.userLogin(email, password);
		if(exu!=null)
		{
			ResponseStructure<Booking> str= new ResponseStructure<Booking>();
			
			Booking b= bDao.findBooking(bookingid);
			if(b!=null)
			{
				str.setMessage("Booking has Deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(bDao.deleteBooking(bookingid));
				
				return new ResponseEntity<ResponseStructure<Booking>>(str,HttpStatus.OK);
			}
			throw new BookingNotFound("booking not found with the given id"+bookingid);
		}
		throw new LoginFailed("Enter the valid email & password");
	}
	
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(Booking booking,int bookingid,String email,String password)
	{
		User exu=uDao.userLogin(email, password);
		if(exu!=null)
		{
			ResponseStructure<Booking> str=new  ResponseStructure<Booking>();
			
			Booking b=bDao.findBooking(bookingid);
			if(b!=null)
			{
				str.setMessage("Booking has updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(bDao.updateBooking(booking, bookingid));
				
				return new ResponseEntity<ResponseStructure<Booking>>(str, HttpStatus.OK);
			}
			throw new BookingNotFound("booking not found with the given id"+bookingid);
		}
		throw new LoginFailed("Enter the valid email & password");
		
	}
	
	public ResponseEntity<ResponseStructure<List<Booking>>> findAllBooking()
	{
		ResponseStructure<List<Booking>> str=new ResponseStructure<List<Booking>>();
		List<Booking> bList=bDao.findAllBooking();
		
		if(bList!=null)
		{
			str.setMessage("All Bookings are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(bList);
			
			return new ResponseEntity<ResponseStructure<List<Booking>>>(str,HttpStatus.FOUND);
		}
		throw new BookingNotFound("booking not found ");
	}
	
	public ResponseEntity<ResponseStructure<Booking>> assignShowToBooking(int bookingid,String email,String password)
	{
		ResponseStructure<Booking> str= new ResponseStructure<Booking>();
		
		
		Booking exb=bDao.findBooking(bookingid);
		Movies m= mDao.findmovietitle(exb.getBMovieName());
		Shows exs=sDao.findShows(m.getMShow().getShowid());
		
		if(exs!=null)
		{
			exb.setBShows(exs);
			str.setMessage("Show is assigned to booking");
			str.setStatus(HttpStatus.OK.value());
			str.setData(bDao.updateBooking(exb, bookingid));
			
			return new ResponseEntity<ResponseStructure<Booking>>(str,HttpStatus.OK);
		}
		throw new ShowsNotFound("Show not found with the given id");
		
	}
}

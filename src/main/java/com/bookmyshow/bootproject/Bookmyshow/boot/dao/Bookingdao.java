package com.bookmyshow.bootproject.Bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Booking;
import com.bookmyshow.bootproject.Bookmyshow.boot.repo.Bookingrepo;
@Repository
public class Bookingdao {

	@Autowired
	Bookingrepo bRepo;
	
	public Booking saveBooking(Booking book)
	{
		return bRepo.save(book);
	}
	
	public Booking findBooking(int Bookingid)
	{
		Optional<Booking> opBooking=bRepo.findById(Bookingid);
		if(opBooking.isPresent())
		{
			return opBooking.get();
		}
		return null;
	}
	
	public Booking deleteBooking(int Bookingid)
	{
		Booking b=findBooking(Bookingid);
		bRepo.delete(b);
		return b;
	}
	
	public Booking updateBooking(Booking book, int Bookingid)
	{
		Booking exb=findBooking(Bookingid);
		if(exb!=null)
		{
			book.setBookingid(Bookingid);
			return bRepo.save(book);
		}
		return null;
	}
	
	public List<Booking> findAllBooking()
	{
		List<Booking> book=bRepo.findAll();
		return book;
	}
}

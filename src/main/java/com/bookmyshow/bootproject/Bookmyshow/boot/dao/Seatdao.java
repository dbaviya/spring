package com.bookmyshow.bootproject.Bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Seat;
import com.bookmyshow.bootproject.Bookmyshow.boot.repo.Seatrepo;
@Repository
public class Seatdao {
	@Autowired
	Seatrepo seatsRepo;
	
	public Seat saveSeats(Seat seats)
	{
		return seatsRepo.save(seats);
	}
	
	public Seat findSeats(int seatid)
	{
		Optional<Seat> opSeats=seatsRepo.findById(seatid);
		if(opSeats.isPresent())
		{
			return opSeats.get();
		}
		return null;
	}
	
	public Seat deleteSeats(int seatid)
	{
		Seat s =findSeats(seatid);
		seatsRepo.delete(s);
		
		return s;
	}
	
	public Seat updateSeats(Seat seats,int seatid)
	{
		Seat exs=findSeats(seatid);
		
		if(exs!=null)
		{
			seats.setSeatid(seatid);
			return seatsRepo.save(seats);
		}
		return null;
	}
	
	public List<Seat> findAllSeats()
	{
		List<Seat> seats=seatsRepo.findAll();
		return seats;
	}
	
}

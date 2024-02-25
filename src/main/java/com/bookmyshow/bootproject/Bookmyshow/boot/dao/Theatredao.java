package com.bookmyshow.bootproject.Bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Theatre;
import com.bookmyshow.bootproject.Bookmyshow.boot.repo.Theatrerepo;

@Repository
public class Theatredao {
	@Autowired
	Theatrerepo tRepo;
	
	public Theatre saveTheatre(Theatre theatre)
	{
		return tRepo.save(theatre);
	}
	
	public Theatre findTheatre(int TheatreId)
	{
		Optional<Theatre> optheatre=tRepo.findById(TheatreId);
		
		if(optheatre.isPresent())
		{
			return optheatre.get();
		}
		return null;
	}
	
	public Theatre deleteTheatre(int TheatreId)
	{
		Theatre t= findTheatre(TheatreId);
		tRepo.delete(t);
		return t;
	}
	
	public Theatre updatetheatre(Theatre theatre,int TheatreId)
	{
		Theatre ext=findTheatre(TheatreId);
		if(ext!=null)
		{
			theatre.setTheatreId(TheatreId);
			return tRepo.save(theatre);
		}
		return null;
	}
	
	public List<Theatre> findAllTheatre()
	{
		List<Theatre> theatre=tRepo.findAll();
		return theatre;
	}
}

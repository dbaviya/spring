package com.bookmyshow.bootproject.Bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Shows;
import com.bookmyshow.bootproject.Bookmyshow.boot.repo.Showsrepo;

@Repository
public class Showsdao {

	@Autowired
	Showsrepo sRepo;
	
	public Shows saveShows(Shows shows)
	{
		return sRepo.save(shows);
	}
	
	public Shows findShows(int showid)
	{
		Optional<Shows> opShows=sRepo.findById(showid);
		if(opShows.isPresent())
		{
			return opShows.get();
		}
		return null;
	}
	
	public Shows deleteShows(int showid)
	{
		Shows s=findShows(showid);
		sRepo.delete(s);
		
		return s;
	}
	
	public Shows updateShows(Shows shows, int showid)
	{
		Shows exs=findShows(showid);
		if(exs!=null)
		{
			shows.setShowid(showid);
			return sRepo.save(shows);
		}
		return null;
	}
	
	public List<Shows> findAllShows()
	{
		List<Shows> shows=sRepo.findAll();
		return shows;
	}
	
}


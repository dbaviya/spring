package com.bookmyshow.bootproject.Bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Movies;

public interface Movierepo extends JpaRepository<Movies, Integer>{
	public Movies findBymovietitle(String movietitle);
	
}

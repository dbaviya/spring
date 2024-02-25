package com.bookmyshow.bootproject.Bookmyshow.boot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Admin;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Movies;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Shows;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.LoginFailed;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.SeatNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.ShowsNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Admindao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Moviedao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Theatredao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Showsdao;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

@Service
public class ShowsService {
	@Autowired
	Showsdao sDao;
	
	@Autowired
	Admindao aDao;
	
	@Autowired
	Theatredao tDao;
	
	@Autowired
	Moviedao mDao;
	
	public ResponseEntity<ResponseStructure<Shows>> saveShows(Shows shows,String adminemail,String adminpassword)
	{
		Admin exa=aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			
			ResponseStructure<Shows> str=new ResponseStructure<Shows>();
			shows.setSname(shows.getSname().toLowerCase());
			str.setMessage(" Show has added");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(sDao.saveShows(shows));
			
			return new ResponseEntity<ResponseStructure<Shows>>(str,HttpStatus.CREATED);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponseStructure<Shows>> findShows(int showid)
	{
		ResponseStructure<Shows> str=new ResponseStructure<Shows>();
		Shows s= sDao.findShows(showid);
		if(s!=null)
		{
			str.setMessage("Shows has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(s);
			
			return new ResponseEntity<ResponseStructure<Shows>>(str,HttpStatus.FOUND);
		}
		throw new ShowsNotFound("Shows not found with the given id"+showid);
	}
		
	public ResponseEntity<ResponseStructure<Shows>> deleteShows(int showid ,String adminemail,String adminpassword)
	{
		Admin exa=aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Shows> str= new ResponseStructure<Shows>();
			
			Shows s= sDao.findShows(showid);
			
			if(s!=null)
			{
				str.setMessage("Seat has Deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(sDao.deleteShows(showid));
				
				return new ResponseEntity<ResponseStructure<Shows>>(str,HttpStatus.OK);
			}
			throw new ShowsNotFound("Shows not found with the given id"+showid);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponseStructure<Shows>> updateShows(Shows shows,int seatid ,String adminemail,String adminpassword)
	{
		Admin exa=aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Shows> str= new ResponseStructure<Shows>();
			
			Shows s=sDao.findShows(seatid);
			if(s!=null)
			{
				str.setMessage(" Seat has updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(sDao.updateShows(shows, seatid));
				
				return new ResponseEntity<ResponseStructure<Shows>>(str, HttpStatus.OK);
			}
			throw new ShowsNotFound("Shows not found with the given id"+seatid);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponseStructure<List<Shows>>> findAllShows()
	{
		List<Shows> sList=sDao.findAllShows();
		ResponseStructure<List<Shows>> str= new ResponseStructure<List<Shows>>();
		
		if(sList!=null)
		{
			str.setMessage("All seats are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(sList);
			
			return new ResponseEntity<ResponseStructure<List<Shows>>>(str,HttpStatus.FOUND);
			
		}
		throw new ShowsNotFound("Shows not found");
	}
	
	public ResponseEntity<ResponseStructure<Shows>> assignMovieToShows(int movieid, int showid, String adminemail, String adminpassword)
	{
		Admin exa = aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Shows> str= new ResponseStructure<Shows>();
			Shows exs=sDao.findShows(showid);
			Movies exm=mDao.findMovie(movieid);
			exs.setSMovie(exm);
			
			str.setMessage("movie has assigned to show");
			str.setStatus(HttpStatus.OK.value());
			str.setData(sDao.updateShows(exs, showid));
			
			return new ResponseEntity<ResponseStructure<Shows>>(str,HttpStatus.OK);
			
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponseStructure<Shows>> deleteMovieFromShows(int movieid, int showid ,String adminemail,String adminpassword)
	{
		Admin exa = aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Shows> str= new ResponseStructure<Shows>();
			Shows exs=sDao.findShows(showid);
			if(exs!=null && exs.getSMovie().getMovieid()==movieid)
			{
				exs.setSMovie(null);
				mDao.deleteMovie(movieid);
				
				str.setMessage("Movie deleted from show");
				str.setStatus(HttpStatus.OK.value());
				str.setData(sDao.updateShows(exs, showid));
				
				return new ResponseEntity<ResponseStructure<Shows>>(str,HttpStatus.OK);
			}
			throw new ShowsNotFound("Show not found with the given id (or) not match with the movie id");
			
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponseStructure<Shows>> removeMovieFromShows(int movieid, int showid ,String adminemail,String adminpassword)
	{
		Admin exa = aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Shows> str= new ResponseStructure<Shows>();
			Shows exs=sDao.findShows(showid);
			if(exs!=null && exs.getSMovie().getMovieid()==movieid)
			{
				exs.setSMovie(null);
				
				str.setMessage("Movie deleted from show");
				str.setStatus(HttpStatus.OK.value());
				str.setData(sDao.updateShows(exs, showid));
				
				return new ResponseEntity<ResponseStructure<Shows>>(str,HttpStatus.OK);
			}
			throw new SeatNotFound("Show not found with the given id (or) not match with the movie id");
			
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	
	
}
package com.bookmyshow.bootproject.Bookmyshow.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Movies;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.LoginFailed;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.MovieNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Admindao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Moviedao;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

@Service
public class MovieService {
  
	@Autowired
     Moviedao mDao;
	@Autowired
	 Admindao aDao;
	
	public ResponseEntity<ResponseStructure<Movies>> saveMovie(Movies movie ,String adminemail,String adminpassword)
	{
		com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Admin exa=aDao.adminLogin(adminemail,adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Movies> str=new ResponseStructure<Movies>();
			movie.setMovietitle(movie.getMovietitle().toLowerCase());
			str.setMessage(movie.getMovietitle()+" Movie has saved");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(mDao.saveMovie(movie));
			
			return new ResponseEntity<ResponseStructure<Movies>>(str,HttpStatus.CREATED);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponseStructure<Movies>> findMovie(int mId)
	{
		ResponseStructure<Movies> str=new ResponseStructure<Movies>();
		Movies m= mDao.findMovie(mId);
		if(m!=null)
		{
			str.setMessage(m.getMovietitle()+"Movie has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(m);
			
			return new ResponseEntity<ResponseStructure<Movies>>(str,HttpStatus.FOUND);
		}
		throw new MovieNotFound("movie not found with the given id"+mId);
	}
	
	public ResponseEntity<ResponseStructure<Movies>> deleteMovie(int movieid ,String adminemail,String adminpassword)
	{
		com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Admin exa=aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Movies> str= new ResponseStructure<Movies>();
			
			Movies m= mDao.findMovie(movieid);
			
			if(m!=null)
			{
				str.setMessage(m.getMovietitle()+"Movie has Deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(mDao.deleteMovie(movieid));
				
				return new ResponseEntity<ResponseStructure<Movies>>(str,HttpStatus.OK);
			}
			throw new MovieNotFound("movie not found with the given id"+movieid);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
		
	}
	
	public ResponseEntity<ResponseStructure<Movies>> updateMovie(Movies movie,int movieid ,String adminemail,String adminpassword)
	{
		com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Admin exa=aDao.adminLogin(adminemail , adminpassword );
		if(exa!=null)
		{
			ResponseStructure<Movies> str= new ResponseStructure<Movies>();
			
			Movies m=mDao.findMovie(movieid);
			if(m!=null)
			{
				str.setMessage(movie.getMovietitle()+" movie has updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(mDao.updateMovie(movie, movieid));
				
				return new ResponseEntity<ResponseStructure<Movies>>(str, HttpStatus.OK);
			}
			throw new MovieNotFound("movie not found with the given id"+movieid);
		}
		throw new LoginFailed("Enter valid email & passworrd");
	}
	
	public ResponseEntity<ResponseStructure<List<Movies>>> findAllMovies()
	{
		List<Movies> mList=mDao.findAllMovie();
		ResponseStructure<List<Movies>> str= new ResponseStructure<List<Movies>>();
		
		if(mList!=null)
		{
			str.setMessage("All movies are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(mList);
			
			return new ResponseEntity<ResponseStructure<List<Movies>>>(str,HttpStatus.FOUND);
			
		}
		throw new MovieNotFound("movie not found");
	}
}
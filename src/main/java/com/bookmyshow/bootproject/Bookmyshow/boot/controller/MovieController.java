package com.bookmyshow.bootproject.Bookmyshow.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Movies;
import com.bookmyshow.bootproject.Bookmyshow.boot.service.MovieService;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("Movie")
public class MovieController {
	@Autowired
	MovieService mService;
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<Movies>> saveMovie(@Valid @RequestBody Movies movie,BindingResult result ,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return mService.saveMovie(movie, adminemail, adminpassword);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponseStructure<Movies>> findMovie(@RequestParam int movieid)
	{
		return mService.findMovie(movieid);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponseStructure<Movies>> delelteMovie(@RequestParam int movieid,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return mService.deleteMovie(movieid, adminemail, adminpassword);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponseStructure<Movies>> updateMovie(@RequestBody Movies movie,@RequestParam int mId ,@RequestParam String aEmail,@RequestParam String aPassword)
	{
		return mService.updateMovie(movie, mId, aEmail, aPassword);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponseStructure<List<Movies>>> findAllMovie()
	{
		return mService.findAllMovies();
	}

}
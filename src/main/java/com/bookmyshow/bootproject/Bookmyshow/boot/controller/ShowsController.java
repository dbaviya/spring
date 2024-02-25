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

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Shows;
import com.bookmyshow.bootproject.Bookmyshow.boot.service.ShowsService;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("shows")
public class ShowsController {
  
	@Autowired
	ShowsService sService;
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<Shows>> saveShows(@Valid @RequestBody Shows shows,BindingResult result ,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return sService.saveShows(shows, adminemail, adminpassword);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponseStructure<Shows>> findShows(@RequestParam int showid)
	{
		return sService.findShows(showid);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponseStructure<Shows>> delelteShows(@RequestParam int showid,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return sService.deleteShows(showid, adminemail, adminpassword);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponseStructure<Shows>> updateShows(@RequestBody Shows shows, @RequestParam int showid,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return sService.updateShows(shows, showid, adminemail, adminpassword);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponseStructure<List<Shows>>> findAllShows()
	{
		return sService.findAllShows();
	}
	
	@PutMapping("assignMovie")
	public ResponseEntity<ResponseStructure<Shows>> assignMovieToShows(@RequestParam int movieid,@RequestParam int showid,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return sService.assignMovieToShows(movieid, showid, adminemail, adminpassword);
	}
	@PutMapping("deleteMovie")
	public ResponseEntity<ResponseStructure<Shows>> deleteMovieFromShow(@RequestParam int movieid,@RequestParam int showid,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return sService.deleteMovieFromShows(movieid, showid, adminemail, adminpassword);
	}
	@PutMapping("removeMovie")
	public ResponseEntity<ResponseStructure<Shows>> removeMovieFromShow(@RequestParam int movieid,@RequestParam int showid,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return sService.removeMovieFromShows(movieid, showid, adminemail, adminpassword);
	}


}
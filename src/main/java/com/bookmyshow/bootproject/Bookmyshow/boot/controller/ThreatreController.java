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

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Theatre;
import com.bookmyshow.bootproject.Bookmyshow.boot.service.TheatreService;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("Threatre")
public class ThreatreController {
 
	@Autowired
	TheatreService tService;
	
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@Valid @RequestBody Theatre theatre,BindingResult result ,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return tService.saveTheatre(theatre, adminemail, adminpassword);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(int TheatreId)
	{
		return tService.findTheatre(TheatreId);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponseStructure<Theatre>> delelteTheatre(@RequestParam int TheatreId ,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return tService.deleteTheatre(TheatreId, adminemail, adminpassword);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponseStructure<Theatre>> updateShows(@RequestBody Theatre theatre,@RequestParam int TheatreId ,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return tService.updateTheatre(theatre, TheatreId, adminemail, adminpassword);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponseStructure<List<Theatre>>> findAllShows()
	{
		return tService.findAllTheatre();
	}
	@PutMapping("assignShows")
	public ResponseEntity<ResponseStructure<Theatre>> assignShows(@RequestParam int TheatreId ,@RequestParam int showid ,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return tService.assignShowToTheatre(TheatreId, showid, adminemail, adminpassword);
	}
	@PutMapping("deleteShows")
	public ResponseEntity<ResponseStructure<Theatre>> deleteShows(@RequestParam int TheatreId ,@RequestParam int showid ,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return tService.deleteShowFromTheatre(TheatreId, showid, adminemail, adminpassword );
	}
	@PutMapping("removeShows")
	public ResponseEntity<ResponseStructure<Theatre>> removeShows(@RequestParam int TheatreId ,@RequestParam int showid ,@RequestParam String adminemail,@RequestParam String adminpassword)
	{
		return tService.removeShowsFromTheatre(TheatreId, showid, adminemail, adminpassword );
	}
	
}
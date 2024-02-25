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

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Booking;
import com.bookmyshow.bootproject.Bookmyshow.boot.service.BookingService;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

import jakarta.validation.Valid;
@RestController
@RequestMapping("Booking")
	public class BookingController{
	@Autowired
	BookingService bService;
	@PostMapping("save")
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(@Valid @RequestBody Booking booking, BindingResult result ,@RequestParam String email, @RequestParam String password)
	{
		return bService.saveBooking(booking, email, password);
	}
	
	@GetMapping("find")
	public ResponseEntity<ResponseStructure<Booking>> findBooking(int Bookingid)
	{
		return bService.findBooking(Bookingid);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<ResponseStructure<Booking>> delelteBooking(@RequestParam int Bookingid,@RequestParam String email, @RequestParam String password)
	{
		return bService.deleteBooking(Bookingid, email, password);
	}
	
	@PutMapping("update")
	public ResponseEntity<ResponseStructure<Booking>> updateBooking(@RequestBody Booking booking, @RequestParam int Bookingid,@RequestParam String email, @RequestParam String password)
	{
		return bService.updateBooking(booking, Bookingid , email, password);
	}
	
	@GetMapping("findAll")
	public ResponseEntity<ResponseStructure<List<Booking>>> findAllBooking()
	{
		return bService.findAllBooking();
	}
	@PutMapping("assignShow")
	public ResponseEntity<ResponseStructure<Booking>> assignShow( @RequestParam int Bookingid,@RequestParam String email, @RequestParam String password)
	{
		return bService.assignShowToBooking(Bookingid, email, password);
	}
	
}
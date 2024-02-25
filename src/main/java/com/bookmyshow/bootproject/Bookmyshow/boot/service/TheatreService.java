package com.bookmyshow.bootproject.Bookmyshow.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Admin;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Shows;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Theatre;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.AdminNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.LoginFailed;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.ShowsNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.TheatreNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Admindao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Theatredao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Showsdao;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

@Service
public class TheatreService {
	@Autowired
	Theatredao tDao;
	@Autowired
	Admindao aDao;
	
	@Autowired
	Showsdao sDao;
	
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(Theatre theatre ,String adminemail,String adminpassword)
	{
		Admin exa=aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Theatre> str=new ResponseStructure<Theatre>();
			
			str.setMessage("Theatre has saved");
			str.setStatus(HttpStatus.CREATED.value());
			str.setData(tDao.saveTheatre(theatre));
			
			return new ResponseEntity<ResponseStructure<Theatre>>(str,HttpStatus.CREATED);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(int TheatreId)
	{
		
		ResponseStructure<Theatre> str=new ResponseStructure<Theatre>();
		Theatre t= tDao.findTheatre(TheatreId);
		if(t!=null)
		{
			str.setMessage("Theatre has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(t);
			
			return new ResponseEntity<ResponseStructure<Theatre>>(str,HttpStatus.FOUND);
		}
		throw new TheatreNotFound("Theatre not found with the given id"+TheatreId);
		
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(int TheatreId ,String adminemail,String adminpassword)
	{
		Admin exa=aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Theatre> str= new ResponseStructure<Theatre>();
			
			Theatre t= tDao.findTheatre(TheatreId);
			
			if(t!=null)
			{
				str.setMessage("Theatre has Deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(tDao.deleteTheatre(TheatreId));
				
				return new ResponseEntity<ResponseStructure<Theatre>>(str,HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre not found with the given id"+TheatreId);
		}
		throw new LoginFailed("Enter valid email & passworrd");
		
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(Theatre theatre,int TheatreId ,String adminemail,String adminpassword)
	{
		Admin exa=aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Theatre> str= new ResponseStructure<Theatre>();
			
			Theatre t=tDao.findTheatre(TheatreId);
			if(t!=null)
			{
				str.setMessage(" Theatre has updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(tDao.updatetheatre(theatre,TheatreId));
				
				return new ResponseEntity<ResponseStructure<Theatre>>(str, HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre not found with the given id"+TheatreId);
		}
		throw new LoginFailed("Enter valid email & passworrd");
	}
	
	public ResponseEntity<ResponseStructure<List<Theatre>>> findAllTheatre()
	{
		List<Theatre> tList=tDao.findAllTheatre();
		ResponseStructure<List<Theatre>> str= new ResponseStructure<List<Theatre>>();
		
		if(tList!=null)
		{
			str.setMessage("All Theatre are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(tList);
			
			return new ResponseEntity<ResponseStructure<List<Theatre>>>(str,HttpStatus.FOUND);
			
		}
		throw new TheatreNotFound("Theatre not found ");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> assignShowToTheatre(int TheatreId,int showid,String adminemail, String adminpassword)
	{
		Admin va=aDao.adminLogin(adminemail, adminpassword);
		if(va!=null)
		{
			Theatre t= tDao.findTheatre(TheatreId);
			Shows s=sDao.findShows(showid);
			List<Shows> sList=t.getTShows();
			ResponseStructure<Theatre> str= new ResponseStructure<Theatre>();
			if (t!=null && s != null) {
				sList.add(s);
				t.setTShows(sList);
				
				str.setMessage("show assigned to the Theatre");
				str.setStatus(HttpStatus.OK.value());
				str.setData(tDao.updatetheatre(t, TheatreId));
				return new ResponseEntity<ResponseStructure<Theatre>>(str, HttpStatus.OK);
			}
			throw new TheatreNotFound("show not found with the given id");
		}
		throw new LoginFailed("Enter valid email & password");
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> deleteShowFromTheatre(int TheatreId, int showid, String adminemail, String adminpassword)
	{
		Admin a =aDao.adminLogin(adminemail, adminpassword);
		if(a!=null)
		{
			Shows shows=sDao.findShows(showid);
			Theatre t=tDao.findTheatre(TheatreId);
			List<Shows> sList=sDao.findAllShows();
			ResponseStructure<Theatre> str=new ResponseStructure<Theatre>();
			for (Shows s : sList) {
				if(s.getShowid()==showid)
				{
					sList.remove(shows);
					t.setTShows(sList);
					sDao.deleteShows(showid);
					str.setMessage("removed");
					str.setStatus(HttpStatus.OK.value());
					str.setData(tDao.updatetheatre(t, TheatreId));
					
					return new ResponseEntity<ResponseStructure<Theatre>>(str,HttpStatus.OK);
				}
				throw new ShowsNotFound("Shows not found with the given id");
			}
			throw new ShowsNotFound("Shows not found with the given id");
		}
		throw new AdminNotFound("Enter valid email & passworrd");
		
 	}
	public ResponseEntity<ResponseStructure<Theatre>> removeShowsFromTheatre(int TheatreId, int showid, String adminemail, String adminpassword)
	{
		Admin a =aDao.adminLogin(adminemail, adminpassword);
		if(a!=null)
		{
			Shows show=sDao.findShows(showid);
			Theatre t=tDao.findTheatre(TheatreId);
			List<Shows> sList=sDao.findAllShows();
			ResponseStructure<Theatre> str=new ResponseStructure<Theatre>();
			for (Shows s : sList) {
				if(s.getShowid()==showid)
				{
					sList.remove(show);
					t.setTShows(sList);
					str.setMessage("removed");
					str.setStatus(HttpStatus.OK.value());
					str.setData(tDao.updatetheatre(t, TheatreId));
					
					return new ResponseEntity<ResponseStructure<Theatre>>(str,HttpStatus.OK);
				}
				throw new ShowsNotFound("Shows not found with the given id");
			}
			throw new ShowsNotFound("Shows not found with the given id");
		}
		throw new AdminNotFound("Enter valid email & passworrd");
		
 	}
	
}
	
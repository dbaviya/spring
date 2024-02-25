package com.bookmyshow.bootproject.Bookmyshow.boot.service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Admin;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Theatre;
import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.User;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.AdminNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.LoginFailed;
import com.bookmyshow.bootproject.Bookmyshow.boot.Exception.TheatreNotFound;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Admindao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Theatredao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dao.Userdao;
import com.bookmyshow.bootproject.Bookmyshow.boot.dto.Admindto;
import com.bookmyshow.bootproject.Bookmyshow.boot.util.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	Admindao aDao;
	
	@Autowired
	Theatredao tDao;
	
	@Autowired
	Userdao uDao;
		
	public ResponseEntity<ResponseStructure<Admindto>> saveAdmin(Admin admin)
	{
		ResponseStructure<Admindto> str= new ResponseStructure<Admindto>();
		Admindto aDto= new Admindto();
		ModelMapper mapper =new  ModelMapper();
		mapper.map(aDao.saveAdmin(admin), aDto);
		
		str.setMessage(admin.getAdminName()+"Admin has Saved");
		str.setStatus(HttpStatus.CREATED.value());
		str.setData(aDto);
		
		return new ResponseEntity<ResponseStructure<Admindto>>(str,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Admindto>> findAdmin(int adminId)
	{
		ResponseStructure<Admindto> str= new ResponseStructure<Admindto>();
		Admindto aDto= new Admindto();
		Admin a = aDao.findadmin(adminId);
		ModelMapper mapper =new  ModelMapper();
		mapper.map(a, aDto);
		if(a!=null)
		{
			str.setMessage(a.getAdminName()+"Admin has founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(aDto);
			
			return new ResponseEntity<ResponseStructure<Admindto>>(str,HttpStatus.FOUND);
			
		}
		throw new AdminNotFound("Admin not found with the given id "+adminId);
	}
	
	public ResponseEntity<ResponseStructure<Admindto>> deleteAdmin(int adminId ,String adminemail,String adminpassword)
	{
		Admin exa=aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Admindto> str=new ResponseStructure<Admindto>();
			Admindto aDto= new Admindto();
			Admin a=aDao.findadmin(adminId);
			if(a!=null)
			{
				Admin delA=aDao.deleteAdmin(adminId);
				ModelMapper mapper =new  ModelMapper();
				mapper.map(delA, aDto);
				
				str.setMessage("Admin Deleted");
				str.setStatus(HttpStatus.OK.value());
				str.setData(aDto);
				
				return new ResponseEntity<ResponseStructure<Admindto>>(str,HttpStatus.OK);
				
			}
			throw new AdminNotFound("Admin not found with the given id "+adminId);
		}
		throw new LoginFailed(" Enter the valid email & password");
	}
	
	public ResponseEntity<ResponseStructure<Admindto>> updateAdmin(Admin admin,int adminId,String adminemail,String adminpassword)
	{
		Admin exa = aDao.adminLogin(adminemail, adminpassword);
		if(exa!=null)
		{
			ResponseStructure<Admindto> str= new ResponseStructure<Admindto>();
			Admindto aDto= new Admindto();
			Admin a=aDao.findadmin(adminId);
			if(a!=null)
			{
				ModelMapper mapper =new  ModelMapper();
				mapper.map(aDao.updateadmin(admin, adminId), aDto);
				
				str.setMessage(admin.getAdminName()+"Admin has Updated");
				str.setStatus(HttpStatus.OK.value());
				str.setData(aDto);
				
				return new ResponseEntity<ResponseStructure<Admindto>>(str,HttpStatus.OK);
			}
			throw new AdminNotFound("Admin not found with the given id "+adminId);
		}
		throw new LoginFailed("Enter the valid email & password");
	}
	public ResponseEntity<ResponseStructure<List<Admindto>>> findAllAdmin()
	{
		ResponseStructure<List<Admindto>> str = new ResponseStructure<List<Admindto>>();
		List<Admin>  adList = aDao.findAllAdmin();
		List<Admindto> aDtoList =new ArrayList<Admindto>();
		if(! adList.isEmpty())
		{
			for(Admin a : adList  )
			{
				Admindto aDto= new Admindto();
				ModelMapper mapper =new  ModelMapper();
				mapper.map(a,aDto);
				aDtoList.add(aDto);
				
			}
			
			str.setMessage("All Admin's are founded");
			str.setStatus(HttpStatus.FOUND.value());
			str.setData(aDtoList);
			return new ResponseEntity<ResponseStructure<List<Admindto>>>(str,HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admin not found");
	}
	
	public ResponseEntity<ResponseStructure<Admindto>> assignTheatreToAdmin(int adminId,int TheatreId,String adminemail, String adminPassword)
	{
		Admin va=aDao.adminLogin(adminemail, adminPassword);
		if(va!=null)
		{
			Admin a=aDao.findadmin(adminId);
			Admindto aDto = new Admindto();
			ModelMapper mapper =new  ModelMapper();
			Theatre t= tDao.findTheatre(TheatreId);
			List<Theatre> tList=a.getATheatre();
			ResponseStructure<Admindto> str= new ResponseStructure<Admindto>();
			if (a!=null && t != null) {
				tList.add(t);
				a.setATheatre(tList);
				
				mapper.map(aDao.updateadmin(a, adminId), aDto);
				str.setMessage("Theatre assigned to the admin");
				str.setStatus(HttpStatus.OK.value());
				str.setData(aDto);
				return new ResponseEntity<ResponseStructure<Admindto>>(str, HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre not found with the given id");
		}
		throw new LoginFailed("Enter valid email & password");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> deleteTheatreFromAdmin(int adminId,int TheatreId,String adminemail, String adminPassword)
	{
		Admin va=aDao.adminLogin(adminemail, adminPassword);
		if(va!=null)
		{
			Theatre t= tDao.findTheatre(TheatreId);
			Admin a=aDao.findadmin(adminId);
			List<Theatre> tList=a.getATheatre();
			ResponseStructure<Admin> str= new ResponseStructure<Admin>();
			for (Theatre theatre : tList) {
				int id=theatre.getTheatreId();
				if(id==TheatreId)
				{
					tList.remove(t);
					a.setATheatre(tList);
					tDao.deleteTheatre(id);
					str.setMessage("theatre is deleted from "+a.getAdminName());
					str.setStatus(HttpStatus.OK.value());
					str.setData(aDao.updateadmin(a, a.getAdminId()));
					
					return new ResponseEntity<ResponseStructure<Admin>>(str,HttpStatus.OK);
				}
				throw new TheatreNotFound("theatre not found with the given id");
			}
			return null;
		}
		throw new AdminNotFound("Enter valid email & password");
		
	}
	

	public ResponseEntity<ResponseStructure<Admin>> removeTheatreFromAdmin(int adminId,int TheatreId,String adminemail, String adminPassword)
	{
		Admin va=aDao.adminLogin(adminemail, adminPassword);
		if(va!=null)
		{
			Theatre t= tDao.findTheatre(TheatreId);
			Admin a=aDao.findadmin(adminId);
			List<Theatre> tList=a.getATheatre();
			
			ResponseStructure<Admin> str= new ResponseStructure<Admin>();
			for (Theatre theatre : tList) {
				int id=theatre.getTheatreId();
				if(id==TheatreId)
				{
					tList.remove(t);
					a.setATheatre(tList);
					str.setMessage("theatre is removed from "+a.getAdminName());
					str.setStatus(HttpStatus.OK.value());
					str.setData(aDao.updateadmin(a, a.getAdminId()));
					
					return new ResponseEntity<ResponseStructure<Admin>>(str,HttpStatus.OK);
				}
				throw new TheatreNotFound("theatre not found with the given id");
			}
			return null;
		}
		throw new AdminNotFound("Enter valid email & password");
		
	}
	
	public ResponseEntity<ResponseStructure<Admindto>> assignUserToAdmin(int adminId,int userId,String adminemail, String adminPassword)
	{
		Admin va=aDao.adminLogin(adminemail, adminPassword);
		if(va!=null)
		{
			Admin a=aDao.findadmin(adminId);
			Admindto aDto = new Admindto();
			ModelMapper mapper =new  ModelMapper();
			User u= uDao.findUser(userId);
			List<User> uList=a.getAUser();
			ResponseStructure<Admindto> str= new ResponseStructure<Admindto>();
			if (a!=null && u != null) {
				uList.add(u);
				mapper.map(aDao.updateadmin(a, adminId), aDto);
				str.setMessage("Theatre assigned to the admin");
				str.setStatus(HttpStatus.OK.value());
				str.setData(aDto);
				return new ResponseEntity<ResponseStructure<Admindto>>(str, HttpStatus.OK);
			}
			throw new TheatreNotFound("Theatre not found with the given id");
		}
		throw new LoginFailed("Enter valid email & password");
	}
	
	public ResponseEntity<ResponseStructure<Admin>> deleteUserFromAdmin(int adminId,int userid,String adminemail, String adminPassword)
	{
		Admin va=aDao.adminLogin(adminemail, adminPassword);
		if(va!=null)
		{
			User u= uDao.findUser(userid);
			Admin a=aDao.findadmin(adminId);
			List<User> uList=a.getAUser();
			ResponseStructure<Admin> str= new ResponseStructure<Admin>();
			for (User user : uList) {
				int id=user.getUserid();
				if(id==userid)
				{
					uList.remove(u);
					a.setAUser(uList);
					tDao.deleteTheatre(id);
					str.setMessage(user.getUsername()+" is deleted from "+a.getAdminName());
					str.setStatus(HttpStatus.OK.value());
					str.setData(aDao.updateadmin(a, a.getAdminId()));
					
					return new ResponseEntity<ResponseStructure<Admin>>(str,HttpStatus.OK);
				}
				throw new TheatreNotFound("");
			}
			return null;
		}
		throw new AdminNotFound("Enter valid email & password");
		
	}
	
	public ResponseEntity<ResponseStructure<Admin>> removeUserFromAdmin(int adminId,int userid,String adminemail, String adminPassword)
	{
		Admin va=aDao.adminLogin(adminemail, adminPassword);
		if(va!=null)
		{
			User u= uDao.findUser(userid);
			Admin a=aDao.findadmin(adminId);
			List<User> uList=a.getAUser();
			ResponseStructure<Admin> str= new ResponseStructure<Admin>();
			for (User user : uList) {
				int id=user.getUserid();
				if(id==userid)
				{
					uList.remove(u);
					a.setAUser(uList);
					str.setMessage(user.getUsername()+" is removed from "+a.getAdminName());
					str.setStatus(HttpStatus.OK.value());
					str.setData(aDao.updateadmin(a, a.getAdminId()));
					
					return new ResponseEntity<ResponseStructure<Admin>>(str,HttpStatus.OK);
				}
				throw new TheatreNotFound("theatre not found with given id");
			}
			return null;
		}
		throw new AdminNotFound("Enter valid email & password");
	}
}
package com.bookmyshow.bootproject.Bookmyshow.boot.dto;

import java.util.List;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Theatre;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Admindto {

	private int adminid;
	private String  adminName;
	private Long admincontact;
	private String adminemail;
	
private List<Theatre> aTheatre;
	
	private List<Userdto> aUser;
}

package com.bookmyshow.bootproject.Bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Ticket;

public interface Ticketrepo extends JpaRepository<Ticket,Integer>{

}

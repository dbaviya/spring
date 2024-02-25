package com.bookmyshow.bootproject.Bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Booking;

public interface Bookingrepo extends JpaRepository<Booking, Integer> {

}

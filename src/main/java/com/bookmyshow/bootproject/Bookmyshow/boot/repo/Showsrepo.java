package com.bookmyshow.bootproject.Bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmyshow.bootproject.Bookmyshow.boot.Entity.Shows;

public interface Showsrepo extends JpaRepository<Shows, Integer> {

}

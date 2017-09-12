package com.mvc.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Smgo;

public interface SmgoRepository extends JpaRepository<Smgo,Integer>{

}

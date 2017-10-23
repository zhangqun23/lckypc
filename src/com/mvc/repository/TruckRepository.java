package com.mvc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Truck;

public interface TruckRepository extends JpaRepository<Truck, Integer> {
	//Truck信息模态框显示
	@Query("select t from Truck t where trck_id = :trck_id ")
	Truck findTruck(@Param("trck_id") Integer trck_id);
}

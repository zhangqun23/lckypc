package com.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Smgo;

public interface SmgoRepository extends JpaRepository<Smgo,Integer>{

	//根据id获取smgo信息
	@Query("select tr from Smgo tr where smgo_id=:smgo_id")
	Smgo selectSmgoById(@Param("smgo_id") int smgo_id);

	//sego为null时返回全部smgo信息
	@Query("select tr from Smgo tr where smgo_id=:smgo_id")
	List<Smgo> findAlls();

	
}

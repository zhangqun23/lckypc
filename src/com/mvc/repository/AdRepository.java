package com.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Ad;

public interface AdRepository extends JpaRepository<Ad, Integer>{

	//详情
	@Query("select bn from Ad bn where ad_id=:ad_id and is_delete=0")
	Ad getAdInfo(Integer ad_id);
	//根据id获取ad信息
	@Query("select tr from Ad tr where ad_id=:ad_id ")
	Ad selectAdById(int ad_id);

/*	//根据state筛选ad信息
	@Query("select t from Ad t where ad_state = :ad_state and is_delete=0")
	List<Ad> findAdByState(@Param("ad_state")Integer adState);
*/
	//state为null时返回全部ad信息
	@Query("select a from Ad a where is_delete=0")
	List<Ad> findAlls();

	

}

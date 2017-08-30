package com.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Ad;

public interface AdRepository extends JpaRepository<Ad, Integer> {
		// 根据ID查询旅游信息
		@Query("select a from Ad a where ad_id = :ad_id")
		public Ad findAdById(@Param("ad_id") Integer ad_id);

		// 根据ID查询全部旅游信息
		@Query("select a from Ad a where is_delete=0 ")
		public List<Ad> findAdAlls();

		// 根据id删除
		@Query("update Ad set is_delete=1 where ad_id = :ad_id")
		public boolean deleteByAdId(@Param("ad_id") Integer ad_id);
		
		// 根据标题查询旅游信息是否存在,返回1存在，返回0不存在
		@Query("select count(ad_id) from Ad a where ad_title = :ad_title and is_delete=0")
		public Long countByAdTitle(@Param("ad_title") String ad_title);

		// 根据标题查询旅游信息
		@Query("select a from Ad a where ad_title = :ad_title and is_delete=0")
		public List<Ad> findAdByTitle(@Param("ad_title") String ad_title);
		
		// 根据类型查询旅游信息
		@Query("select a from Ad a where ad_type = :ad_type and is_delete=0")
		public List<Ad> findAdByType(@Param("ad_type") String ad_type);
		
		// 根据状态查询旅游信息
		@Query("select a from Ad a where ad_state = :ad_state and is_delete=0")
		public List<Ad> findAdByState(@Param("ad_state") String ad_state);

		//根据ID获取旅游信息
			@Query("select a from Ad a where ad_id=:ad_id ")
			Ad selectAdById(@Param("ad_id") Integer ad_id);
	}

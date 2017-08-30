package com.mvc.dao;

import java.util.List;

import com.mvc.entity.Ad;

public interface AdDao {
	
	// 查询ad信息总条数
	Integer countTotal(String searchKey);
	
	// 根据页数筛选全部旅游信息列表
	List<Ad> findAdByPage(String searchKey, Integer offset, Integer end);
	
	// 根据id修改
	Boolean updateState(Integer ad_id);
	
    }

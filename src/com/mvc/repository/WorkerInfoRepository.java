package com.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.WorkerInfo;

/**
 * 用户信息管理
 * 
 * @author wdh
 * @date 2017年8月28日
 */
public interface WorkerInfoRepository extends JpaRepository<WorkerInfo, Long> {
	// 根据ID查询用户信息
	@Query("select w from WorkerInfo w where woin_id = :woin_id")
	public WorkerInfo findWoinById(@Param("woin_id") Integer woin_id);

	// 根据ID查询全部用户信息
	@Query("select w from WorkerInfo w where woin_state=0 ")
	public List<WorkerInfo> findWoinAlls();

	// 根据woinNum查询用户账号是否存在,返回1存在，返回0不存在
	@Query("select count(woin_id) from WorkerInfo w where woin_num = :woin_num and woin_state=0 ")
	public Long countByWoinNum(@Param("woin_num") String woin_num);

	// 根据woinNum查询用户信息
	@Query("select w from WorkerInfo w where woin_num = :woin_num and woin_state=0 ")
	public WorkerInfo findByWoinNum(@Param("woin_num") String woin_num);

	// 根据id删除
	@Query("update WorkerInfo set woin_state=1 where woin_id = :woin_id")
	public boolean deleteByWoinId(@Param("woin_id") Integer woin_id);


	

}

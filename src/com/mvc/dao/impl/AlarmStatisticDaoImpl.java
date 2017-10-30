package com.mvc.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mvc.dao.AlarmStatisticDao;
import com.mvc.entity.AlarmStatistic;

/**
 * 报警统计持久层实现
 * 
 * @author wangrui
 * @date 2016-10-20
 */
@Repository("alarmStatisticDaoImpl")
public class AlarmStatisticDaoImpl implements AlarmStatisticDao {

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;

	// 报警统计
	@Override
	public Object findAlst() {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append(
				"select * from (select coalesce(sum(case when butr_state=0 and is_delete=0 then 1 else 0 end),0) busNeed_num from bus_need as bn) as aa, ");// 班车待交易
		sql.append(
				" (select coalesce(sum(case when ad_state=0 and is_delete=0 then 1 else 0 end),0) ad_num from ad as a) as bb, ");// 广告待审核
		sql.append(
				" (select coalesce(sum(case when is_finish=0 and is_delete=0 then 1 else 0 end),0) smallGoods_num from small_goods as sg) as cc, ");// 执行管控任务
		sql.append(
				" (select coalesce(sum(case when trck_check=0 and is_delete=0 then 1 else 0 end),0) truck_num from truck as tn) as dd ");// 普通任务
		Query query = em.createNativeQuery(sql.toString());
		Object object = query.getSingleResult();
		em.close();
		return object;
	}

}

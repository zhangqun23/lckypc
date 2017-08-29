package com.mvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.base.enums.IsDelete;
import com.mvc.dao.WorkerInfoDao;
import com.mvc.entity.WorkerInfo;
import com.mvc.repository.WorkerInfoRepository;

/**
 * WorkerInfo相关Dao层接口实现
 * 
 * @author wdh
 * @date 2017年8月28日
 */
@Repository("workerInfoDaoImpl")
public class WorkerInfoDaoImpl implements WorkerInfoDao {
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	@Autowired
	WorkerInfoRepository workerInfoRepository;

	/**
	 * 删除用户
	 */
	public boolean updateState(Integer woinid) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = " update worker_info set woin_state =:woin_state where woin_id = :woin_id ";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("woin_id", woinid);
			query.setParameter("woin_state", IsDelete.YES.value);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;

	}

	// 根据页数筛选全部用户列表
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkerInfo> findWoinAllByPage(String searchKey, Integer offset, Integer end) {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from worker_info where woin_state = 0";
		// 判断查找关键字是否为空
		if (null != searchKey) {
			selectSql += " and ( woin_name like '%" + searchKey + "%' or woin_num like '%" + searchKey + "%')";
		}
		selectSql += " order by woin_id desc limit :offset, :end";
		Query query = em.createNativeQuery(selectSql, WorkerInfo.class);
		query.setParameter("offset", offset);
		query.setParameter("end", end);
		List<WorkerInfo> list = query.getResultList();
		em.close();
		return list;
	}

	// // 查询用户总条数
	@SuppressWarnings("unchecked")
	public Integer countTotal(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String countSql = " select count(woin_id) from worker_info w where woin_state = 0 ";
		if (null != searchKey) {
			countSql += "   and (woin_name like '%" + searchKey + "%' or woin_num like '%" + searchKey + "%')";
		}
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}

}

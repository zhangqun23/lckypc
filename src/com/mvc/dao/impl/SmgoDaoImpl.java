package com.mvc.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.base.enums.IsDelete;
import com.mvc.dao.SmgoDao;
import com.mvc.entity.Smgo;
import com.mvc.repository.SmgoRepository;

@Repository("smgoDaoImpl")
public class SmgoDaoImpl implements SmgoDao{

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	@Autowired
	SmgoRepository smgoRepository;
	
	//查询全部smgo信息
	@Override
	public Integer countTotal(String searchKey) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String countSql = " select count(smgo_id) from Smgo tr where is_delete=0 ";
		if (null != searchKey) {
			countSql += "   and (smgo_name like '%" + searchKey + "%')";
		}
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}

	//根据页数筛选smgo信息
	@Override
	public List<Smgo> findSmgoByPage(String searchKey, int offset, int end) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from Smgo where is_delete=0";
	// 判断查找关键字是否为空
		if (null != searchKey) {
			selectSql += " and ( smgo_name like '%" + searchKey + "%')";
		}
		selectSql += " order by smgo_id desc limit :offset, :end";
		Query query = em.createNativeQuery(selectSql, Smgo.class);
		query.setParameter("offset", offset);
		query.setParameter("end", end);
		List<Smgo> list = query.getResultList();
		em.close();
		return list;
	}

	//根据id删除smgo信息
	@Override
	public boolean updateState(Integer smgo_id) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = "update smgo set is_delete =:is_delete where smgo_id =:smgo_id ";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("smgo_id", smgo_id);
			query.setParameter("is_delete", IsDelete.YES.value);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;
	}

	//根据smgoSego获得页数
	@Override
	public Integer countSegoTotal(String smgoSego) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String countSql = "select count(smgo_id) from Smgo where is_delete=0 and smgo_sego = " +smgoSego;
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}

	//根据smgoSego、page筛选smgo信息
	@Override
	public List<Smgo> findSmgoBySego(String smgoSego, int offset, int limit) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from Smgo where is_delete=0 and smgo_sego = " + smgoSego;
		selectSql += " order by smgo_id desc limit :offset, :end";
		Query query = em.createNativeQuery(selectSql, Smgo.class);
		query.setParameter("offset", offset);
		query.setParameter("end", limit);
		List<Smgo> list = query.getResultList();
		em.close();
		return list;
	}

	
}

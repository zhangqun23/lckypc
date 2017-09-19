package com.mvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.base.enums.EditState;
import com.base.enums.IsDelete;
import com.mvc.dao.AdDao;
import com.mvc.entity.Ad;

/**
 * 
 * @ClassName: AdDaoImpl
 * @Description: ad
 * @author ycj
 * @date 2017年9月6日 上午10:07:43 
 * 
 *
 */
@Repository("adDaoImpl")
public class AdDaoImpl implements AdDao{

	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	//初始化
	@Override
	public Integer countTotal() {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String countSql = "select count(ad_id) from Ad tr where is_delete=0" ;	
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
	@Override
	public List<Ad> findAdByPage(int offset, int limit) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from Ad where is_delete=0 limit :offset, :end";
		Query query = em.createNativeQuery(selectSql, Ad.class);
		query.setParameter("offset", offset);
		query.setParameter("end", limit);
		List<Ad> list = query.getResultList();
		em.close();
		return list;
	}
	
	//state限制
	@Override
	public Integer countTotalS(String adState) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String countSql = "select count(ad_id) from Ad tr where is_delete=0 " ;	
		if(adState != null){
			countSql += "and ad_state = " + adState;
		}else{
			countSql += "";
		}
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
	@Override
	public List<Ad> findAdByState(String adState, int offset, int limit) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from Ad where is_delete=0 ";
		if(adState != null){
			selectSql += " and ad_state = " + adState;
		}else{
			selectSql += "";
		}
		selectSql += " limit :offset, :end ";
		Query query = em.createNativeQuery(selectSql, Ad.class);
		query.setParameter("offset", offset);
		query.setParameter("end", limit);
		List<Ad> list = query.getResultList();
		em.close();
		return list;
	}
	
	//type限制
	@Override
	public Integer countTotalT(String adType) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String countSql = " select count(ad_id) from Ad tr where is_delete=0 " ;	
		if(adType != null){
			countSql += " and ad_type = " + adType;
		}else{
			countSql += "";
		}
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
	@Override
	public List<Ad> findAdByType(String adType, int offset, int limit) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from Ad where is_delete=0 ";
		if(adType != null){
			selectSql += " and ad_type = " + adType;
		}else{
			selectSql += "";
		}
		selectSql += " limit :offset, :end ";
		Query query = em.createNativeQuery(selectSql, Ad.class);
		query.setParameter("offset", offset);
		query.setParameter("end", limit);
		List<Ad> list = query.getResultList();
		em.close();
		return list;
	}
	
	//根据id变更state
	@Override
	public boolean editState(Integer ad_id) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = "update ad set ad_state =:ad_state where ad_id =:ad_id ";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("ad_id", ad_id);
			query.setParameter("ad_state",EditState.YES.value);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		}finally{
			em.close();
		}
		return true;
	}

	//根据id删除ad信息
	@Override
	public boolean updateState(Integer ad_id) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = "update ad set is_delete =:is_delete where ad_id =:ad_id ";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("ad_id", ad_id);
			query.setParameter("is_delete", IsDelete.YES.value);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;
	}
}

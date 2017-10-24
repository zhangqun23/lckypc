package com.mvc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.dao.TruckDriverDao;
import com.mvc.entity.Ad;
import com.mvc.entity.Truck;
import com.mvc.entity.TruckNeed;
import com.mvc.entity.TruckSend;

/**
 * 零担货运
 * 
 * @author ghl
 * @date 2017年9月9日
 */
@Repository("truckDriverDaoImpl")
public class TruckDriverDaoImpl implements TruckDriverDao {
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	/**
	 * Truck
	 */
	// 查询Truck信息
	@SuppressWarnings("uncheck")
	@Override
	public List<Truck> findTruck(String trState, Integer offset, Integer limit) {
		EntityManager em = emf.createEntityManager();
		String sql = "select * from Truck where is_delete=0 and trck_check = " + trState + " limit " + offset + " , " + limit;
		Query query = em.createNativeQuery(sql, Truck.class);
		List<Truck> list = query.getResultList();
		em.close();
		return list;
	}

	// 删除Truck
	@SuppressWarnings("uncheck")
	@Override
	public Boolean deleteTruck(Integer trckId) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			String selectSql = "update truck set truck.is_delete=1 where truck.trck_id = " + trckId;
			Query query = em.createNativeQuery(selectSql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;
	}

	// 根据条件获取总页数
	@SuppressWarnings("unchecked")
	@Override
	public Integer countTotal(String trState) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		String countSql = " select count(trck_id) from truck  where is_delete=0 and trck_check = " + trState + "";
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
	//模态框1
	@SuppressWarnings("unchecked")
	@Override
	public Boolean findTruck(Integer trckId, Integer trState) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			String sql = "update truck set truck.trck_check="+ trState +" where truck.trck_id ="+ trckId;
			Query query = em.createNativeQuery(sql);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.close();
		}
		return true;
	}
	/**
	 * TruckSend
	 */
	//查询TruckSend信息
	@SuppressWarnings("unchecked")
	@Override
	public List<TruckSend> getTruckSend(Integer offset, Integer limit) {
		EntityManager em = emf.createEntityManager();
		String sql = " select * from truck_send  order by trse_insert_time desc limit " + offset + " , " + limit;
		Query query = em.createNativeQuery(sql,TruckSend.class);
		List<TruckSend> list = query.getResultList();
		em.close();
		return list;
	}
	//获取总条数用来分页
	@SuppressWarnings("uncheck")
	@Override
	public Integer countTotalPage() {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		String sql = " select count(trse_id) from truck_send ";
		Query query = em.createNativeQuery(sql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
	/**
	 * TruckNeed
	 */
	//获取总条数用来分页
	@SuppressWarnings("uncheck")
	@Override
	public Integer TotalPage() {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		String sql = " select count(trne_id) from truck_need ";
		Query query = em.createNativeQuery(sql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
	//查询TruckNeed
	@SuppressWarnings("uncheck")
	@Override
	public List<TruckNeed> getTruckNeed(Integer offset, Integer limit) {
		EntityManager em = emf.createEntityManager();
		String sql = " select * from truck_need  order by trne_insert_time desc limit " + offset + " , " + limit;
		Query query = em.createNativeQuery(sql,TruckNeed.class);
		List<TruckNeed> list = query.getResultList();
		em.close();
		return list;
	}


}

package com.mvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.base.enums.IsDelete;
import com.mvc.dao.TravelDao;
import com.mvc.entity.Travel;
import com.mvc.entity.TravelTrade;
import com.mvc.repository.TravelRepository;
import com.mvc.repository.TravelTradeRepository;

/**
 * Travel相关Dao层接口实现
 * 
 * @author wdh
 * @date 2017年8月11日
 */
@Repository("travelDaoImpl")
public  class TravelDaoImpl implements TravelDao {
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	@Autowired
	TravelRepository travelRepository;
	@Autowired
	TravelTradeRepository travelTradeRepository;

	/**
	 * 删除旅游信息
	 */
	public boolean updateState(Integer travel_id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = " update travel set is_delete =:is_delete where travel_id =:travel_id ";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("travel_id", travel_id);
			query.setParameter("is_delete", IsDelete.YES.value);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;

	}

	// 根据页数筛选全部旅游信息列表
	@SuppressWarnings("unchecked")
	@Override
	public List<Travel> findTravelByPage(String searchKey, Integer offset, Integer end) {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from Travel where is_delete=0";
	// 判断查找关键字是否为空
		if (null != searchKey) {
			selectSql += " and ( travel_title like '%" + searchKey + "%' or travel_firm like '%" + searchKey + "%')";
		}
		selectSql += " order by travel_id desc limit :offset, :end";
		Query query = em.createNativeQuery(selectSql, Travel.class);
		query.setParameter("offset", offset);
		query.setParameter("end", end);
		List<Travel> list = query.getResultList();
		em.close();
		return list;
	}

	// 根据标题获取旅游信息
		@SuppressWarnings("unchecked")
		@Override
		public List<Travel> findTravelByTitle(String traTitle, Integer offset, Integer end) {
			EntityManager em = emf.createEntityManager();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from travel tr where tr.is_delete=0");// 在建
			if (null != traTitle) {
				sql.append(" and tr.travel_title like '%" + traTitle + "%'");
			}
			sql.append(" order by travel_id desc limit :offset,:end");
			Query query = em.createNativeQuery(sql.toString(), Travel.class);
			query.setParameter("offset", offset).setParameter("end", end);
			List<Travel> list = query.getResultList();
			em.close();
			return list;
		}
		
	//  查询旅游信息总条数
	@SuppressWarnings("unchecked")
	public Integer countTotal(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String countSql = " select count(travel_id) from Travel tr where is_delete=0 ";
		if (null != searchKey) {
			countSql += "   and (travel_title like '%" + searchKey + "%' or travel_firm like '%" + searchKey + "%')";
		}
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
	// 根据页数筛选全部旅游交易信息列表
		@SuppressWarnings("unchecked")
		@Override
		public List<TravelTrade> findTravelTradeByPage(String searchKey, Integer offset, Integer end) {
			EntityManager em = emf.createEntityManager();
			String selectSql = "select * from travel_trade";
		// 判断查找关键字是否为空
			if (null != searchKey) {
				selectSql += " where ( trtr_tel like '%" + searchKey + "%' or travel_id like '%" + searchKey + "%')";
			}
			selectSql += " order by trtr_id desc limit :offset, :end";
			Query query = em.createNativeQuery(selectSql, TravelTrade.class);
			query.setParameter("offset", offset);
			query.setParameter("end", end);
			List<TravelTrade> list = query.getResultList();
			em.close();
			return list;
		}

		//  查询旅游交易信息总条数
		@SuppressWarnings("unchecked")
		public Integer countTrTotal(String searchKey) {
			EntityManager em = emf.createEntityManager();
			String countSql = " select count(trtr_id) from travel_trade";
			if (null != searchKey) {
				countSql += "   where (trtr_tel like '%" + searchKey + "%' or travel_id like '%" + searchKey + "%')";
			}
			Query query = em.createNativeQuery(countSql);
			List<Object> totalRow = query.getResultList();
			em.close();
			return Integer.parseInt(totalRow.get(0).toString());
		}
	
}

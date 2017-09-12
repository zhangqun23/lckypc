package com.mvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.base.enums.IsDelete;
import com.mvc.dao.BusNeedDao;
import com.mvc.entity.BusNeed;
import com.mvc.entity.BusTrade;
import com.mvc.repository.BusNeedRepository;

/**
 * BusNeed相关Dao层接口实现
 * 
 * @author wdh
 * @date 2017年9月4日
 */
@Repository("busNeedDaoImpl")
public  class BusNeedDaoImpl implements BusNeedDao {
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	@Autowired
	BusNeedRepository busNeedRepository;
	
	/**
	 * 删除信息
	 */
	public Boolean updateBuneState(Integer bune_id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			String selectSql = " update bus_need set is_delete =:is_delete where bune_id =:bune_id ";
			Query query = em.createNativeQuery(selectSql);
			query.setParameter("bune_id", bune_id);
			query.setParameter("is_delete", IsDelete.YES.value);
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;

	}

	// 根据页数筛选全部信息
	@SuppressWarnings("unchecked")
	@Override
	public List<BusNeed> findBusNeedByPage(String searchKey, Integer offset, Integer end) {
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from bus_need where is_delete=0";
	// 判断查找关键字是否为空
		if (null != searchKey) {
			selectSql += " and ( bune_tel like '%" + searchKey + "%' or bune_goal_pla like '%" + searchKey + "%')";
		}
		selectSql += " order by bune_id desc limit :offset, :end";
		Query query = em.createNativeQuery(selectSql, BusNeed.class);
		query.setParameter("offset", offset);
		query.setParameter("end", end);
		List<BusNeed> list = query.getResultList();
		em.close();
		return list;
	}

	//  查询信息总条数
	@SuppressWarnings("unchecked")
	public Integer countBuneTotal(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String countSql = " select count(bune_id) from bus_need bn where is_delete=0 ";
		if (null != searchKey) {
			countSql += "   and (bune_tel like '%" + searchKey + "%' or bune_goal_pla like '%" + searchKey + "%')";
		}
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
	
		// 修改信息
		@Override
		public Boolean updateBusNeedById(Integer bune_id, BusNeed busNeed) {
			EntityManager em = emf.createEntityManager();
			try {
				em.getTransaction().begin();
				em.merge(busNeed);
				em.getTransaction().commit();
			} finally {
				em.close();
			}
			return true;
		}
		
	//////////////
		
		// 根据页数筛选全部信息
		@SuppressWarnings("unchecked")
		@Override
		public List<BusTrade> findBusTradeByPage(String searchKey, Integer offset, Integer end) {
			EntityManager em = emf.createEntityManager();
			String selectSql = "select * from bus_Trade";
		// 判断查找关键字是否为空
			if (null != searchKey) {
				selectSql += " and ( bune_bus like '%" + searchKey + "%' or invoice_num like '%" + searchKey + "%')";
			}
			selectSql += " order by butr_id desc limit :offset, :end";
			Query query = em.createNativeQuery(selectSql, BusTrade.class);
			query.setParameter("offset", offset);
			query.setParameter("end", end);
			List<BusTrade> list = query.getResultList();
			em.close();
			return list;
		}

		
		//  查询信息总条数
		@SuppressWarnings("unchecked")
		public Integer countTotal(String searchKey) {
			EntityManager em = emf.createEntityManager();
			String countSql = " select count(butr_id) from bus_Trade bt ";
			if (null != searchKey) {
				countSql += "   and (bune_bus like '%" + searchKey + "%' or invoice_num like '%" + searchKey + "%')";
			}
			Query query = em.createNativeQuery(countSql);
			List<Object> totalRow = query.getResultList();
			em.close();
			return Integer.parseInt(totalRow.get(0).toString());
		}
		
			// 修改信息
			@Override
			public Boolean updateBusTradeById(Integer butr_id, BusTrade busTrade) {
				EntityManager em = emf.createEntityManager();
				try {
					em.getTransaction().begin();
					em.merge(busTrade);
					em.getTransaction().commit();
				} finally {
					em.close();
				}
				return true;
			}
}

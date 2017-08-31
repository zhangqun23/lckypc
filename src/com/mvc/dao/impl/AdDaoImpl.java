package com.mvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.base.enums.IsDelete;
import com.mvc.dao.AdDao;
import com.mvc.entity.Ad;

@Repository("adDaoImpl")
public class AdDaoImpl implements AdDao {
		@Autowired
		@Qualifier("entityManagerFactory")
		EntityManagerFactory emf;
		/**
		 * 删除旅游信息
		 */
		public Boolean updateState(Integer ad_id) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			try {
				String selectSql = " update ad set is_delete =:is_delete where ad_id =:ad_id ";
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

		// 根据页数筛选全部旅游信息列表
		@SuppressWarnings("unchecked")
		@Override
		public List<Ad> findAdByPage(String searchKey, Integer offset, Integer end) {
			EntityManager em = emf.createEntityManager();
			String selectSql = "select * from Ad where is_delete=0";
			selectSql += " order by ad_id desc limit :offset, :end";
			Query query = em.createNativeQuery(selectSql, Ad.class);
			query.setParameter("offset", offset);
			query.setParameter("end", end);
			List<Ad> list = query.getResultList();
			em.close();
			return list;
		}
		//  查询旅游信息总条数
		@SuppressWarnings("unchecked")
		public Integer countTotal(String searchKey) {
			EntityManager em = emf.createEntityManager();
			String countSql = " select count(ad_id) from Ad a where is_delete=0 ";
			Query query = em.createNativeQuery(countSql);
			List<Object> totalRow = query.getResultList();
			em.close();
			return Integer.parseInt(totalRow.get(0).toString());
		}
			// 修改旅游信息
			@Override
			public Boolean updateAdById(Integer ad_id, Ad ad) {
				EntityManager em = emf.createEntityManager();
				try {
					em.getTransaction().begin();
					em.merge(ad);
					em.getTransaction().commit();
				} finally {
					em.close();
				}
				return true;
			}
}

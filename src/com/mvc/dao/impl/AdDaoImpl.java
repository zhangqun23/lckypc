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
import com.mvc.repository.AdRepository;

/**
 * 
 * @ClassName: AdDaoImpl
 * @Description: TODO
 * @author ycj
 * @date 2017年8月31日 下午4:15:47 
 * 
 *
 */
@Repository("adDaoImpl")
public  class AdDaoImpl implements AdDao {
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	@Autowired
	AdRepository adRepository;

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
	// 判断查找关键字是否为空
		if (null != searchKey) {
			selectSql += " and ( ad_title like '%" + searchKey + "%'";
		}
		selectSql += " order by ad_id desc limit :offset, :end";
		Query query = em.createNativeQuery(selectSql, Ad.class);
		query.setParameter("offset", offset);
		query.setParameter("end", end);
		List<Ad> list = query.getResultList();
		em.close();
		return list;
	}

	// 根据标题获取旅游信息
		@SuppressWarnings("unchecked")
		@Override
		public List<Ad> selectAdByTitle(String aTitle, Integer offset, Integer end) {
			EntityManager em = emf.createEntityManager();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from ad a where a.is_delete=0");// 在建
			if (null != aTitle) {
				sql.append(" and a.ad_title like '%" + aTitle + "%'");
			}
			sql.append(" order by ad_id desc limit :offset,:end");
			Query query = em.createNativeQuery(sql.toString(), Ad.class);
			query.setParameter("offset", offset).setParameter("end", end);
			List<Ad> list = query.getResultList();
			em.close();
			return list;
		}
		
	//  查询旅游信息总条数
	@SuppressWarnings("unchecked")
	public Integer countTotal(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String countSql = " select count(Ad_id) from Ad a where is_delete=0 ";
		if (null != searchKey) {
			countSql += "   and (ad_title like '%" + searchKey + "%')";
		}
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
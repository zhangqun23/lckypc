package com.mvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import com.base.enums.EditState;
import com.base.enums.IsDelete;
=======
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
import com.mvc.dao.AdDao;
import com.mvc.entity.Ad;

/**
 * 
 * @ClassName: AdDaoImpl
<<<<<<< HEAD
 * @Description: ad
=======
 * @Description: dao实现层
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
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
<<<<<<< HEAD
	@Autowired
	AdRepository adRepository;
	
	//查询全部ad信息
	@Override
	public Integer countTotal(String searchKey) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String countSql = " select count(ad_id) from Ad tr where is_delete=0 ";
		if (null != searchKey) {
			countSql += "   and (ad_title like '%" + searchKey + "%')";
		}
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
	
	//根据页数筛选ad信息
	@Override
	public List<Ad> findAdByPage(String searchKey, int offset, int end) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String selectSql = "select * from Ad where is_delete=0";
	// 判断查找关键字是否为空
		if (null != searchKey) {
			selectSql += " and ( ad_title like '%" + searchKey + "%')";
		}
		selectSql += " order by ad_id desc limit :offset, :end";
		Query query = em.createNativeQuery(selectSql, Ad.class);
		query.setParameter("offset", offset);
		query.setParameter("end", end);
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

	//根据state获得页数
	@Override
	public Integer countStateTotal(String adState) {
		EntityManager em = emf.createEntityManager();
		String countSql = "select count(ad_id) from Ad where is_delete=0 and ad_state = " +adState;
=======
	
    //查询ad信息总条数
	@SuppressWarnings("unchecked")
	@Override
	public Integer countTotal(String searchKey) {
		EntityManager em = emf.createEntityManager();
		String countSql = " select count(Ad_id) from Ad a where is_delete=0 ";
		if (null != searchKey) {
			countSql += "   and (ad_title like '%" + searchKey + "%')";
		}
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
<<<<<<< HEAD

	//根据state、page筛选ad信息
=======
	
	//根据页数筛选全部ad信息列表
	@SuppressWarnings("unchecked")
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
	@Override
	public List<Ad> findAdByStatePage(String adState, int offset, int limit) {
		EntityManager em = emf.createEntityManager();
<<<<<<< HEAD
		String selectSql = "select * from Ad where is_delete=0 and ad_state = " + adState;
=======
		String selectSql = "select * from Ad where is_delete=0";
		//判断查找关键字是否为空
		if (null != searchKey) {
			selectSql += " and ( ad_title like '%" + searchKey + "%'";
		}
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
		selectSql += " order by ad_id desc limit :offset, :end";
		Query query = em.createNativeQuery(selectSql, Ad.class);
		query.setParameter("offset", offset);
		query.setParameter("end", limit);
		List<Ad> list = query.getResultList();
		em.close();
		return list;
	}
<<<<<<< HEAD

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
=======
	
	//根据id删除广告
	@Override
	public Boolean deleteAd(Integer adId) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			String selectSql = "update ad set ad.is_delete=0 where ad.ad_id=:ad_id";
			Query query =  em.createNativeQuery(selectSql);
			query.setParameter("ad_id",adId);
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
			query.executeUpdate();
			em.flush();
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		return true;
	}
<<<<<<< HEAD
}
=======
	
	
	
		
		
		
		
	
	
	

	/**
	 * 删除旅游信息
	 */
//	public Boolean updateState(Integer ad_id) {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		try {
//			String selectSql = " update ad set is_delete =:is_delete where ad_id =:ad_id ";
//			Query query = em.createNativeQuery(selectSql);
//			query.setParameter("ad_id", ad_id);
//			query.setParameter("is_delete", IsDelete.YES.value);
//			query.executeUpdate();
//			em.flush();
//			em.getTransaction().commit();
//		} finally {
//			em.close();
//		}
//		return true;

//	}

	// 根据标题获取旅游信息
//		@SuppressWarnings("unchecked")
//		@Override
//		public List<Ad> selectAdByTitle(String aTitle, Integer offset, Integer end) {
//			EntityManager em = emf.createEntityManager();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select * from ad a where a.is_delete=0");// 在建
//			if (null != aTitle) {
//				sql.append(" and a.ad_title like '%" + aTitle + "%'");
//			}
//			sql.append(" order by ad_id desc limit :offset,:end");
//			Query query = em.createNativeQuery(sql.toString(), Ad.class);
//			query.setParameter("offset", offset).setParameter("end", end);
//			List<Ad> list = query.getResultList();
//			em.close();
//			return list;
//		}
		
		// 修改旅游信息
//		@Override
//		public Boolean updateAdById(Integer ad_id, Ad ad) {
//			EntityManager em = emf.createEntityManager();
//			try {
//				em.getTransaction().begin();
//				em.merge(ad);
//				em.getTransaction().commit();
//			} finally {
//				em.close();
//			}
//			return true;
//		}
}
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0

package com.mvc.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.base.enums.IsDelete;
import com.mvc.dao.SmgoDao;
import com.mvc.entity.SmallGoods;

/**
 * 
 * @ClassName: SmgoDaoImpl
 * @Description: 查询、补录
 * @author ycj
 * @date 2017年9月20日 下午6:55:46 
 * 
 *
 */
@Repository("smgoDaoImpl")
public class SmgoDaoImpl implements SmgoDao{
	@Autowired
	@Qualifier("entityManagerFactory")
	EntityManagerFactory emf;
	
	//根据限制条件筛选信息
	@Override
	public Integer countTotal(String smgoSego, Date startDate, Date endDate) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String countSql = " select count(smgo_id) from Small_goods where is_delete=0 ";
		if(smgoSego != null ){
			if(startDate != null && endDate != null){
				countSql += " and smgo_sego = " + smgoSego + " and (smgo_deal_time between " + startDate + " and " + endDate + " ) ";
			}else{
				countSql += " and smgo_sego = " + smgoSego;
			}
		}else if(startDate != null && endDate != null){
			countSql += " and (smgo_deal_time between " + startDate + " and " + endDate + " ) ";
		}
		Query query = em.createNativeQuery(countSql);
		List<Object> totalRow = query.getResultList();
		em.close();
		return Integer.parseInt(totalRow.get(0).toString());
	}
	@Override
	public List<SmallGoods> findSmgoByPage(String smgoSego, Date startDate, Date endDate, int offset, int limit) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String selectSql = " select * from Small_goods where is_delete=0 ";
		if(smgoSego != null){
			if(startDate != null && endDate != null){
				selectSql += " and smgo_sego = " + smgoSego + " and (smgo_deal_time between " + startDate + " and " + endDate + ")";
			}else{
				selectSql += "and smgo_sego = " + smgoSego;
			}
		}else if(startDate != null && endDate != null){
			selectSql += " and (smgo_deal_time between " + startDate + " and " + endDate + ")";
		}
		selectSql += " order by smgo_id desc limit :offset, :end ";
		Query query = em.createNativeQuery(selectSql, SmallGoods.class);
		query.setParameter("offset", offset);
		query.setParameter("end", limit);
		List<SmallGoods> list = query.getResultList();
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
			String selectSql = "update Small_goods set is_delete =:is_delete where smgo_id =:smgo_id ";
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

	@Transactional
	@Override
	public boolean updateEdit(String edittime, float editprice, Integer smgo_id) {
		// TODO 自动生成的方法存根
		EntityManager em = emf.createEntityManager();
		String sql = "update Small_goods set edit_time =:edit_time,edit_price =:edit_price where smgo_id =:smgo_id";
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter("smgo_id", smgo_id);
		query.setParameter("edit_price", editprice);
		query.setParameter("edit_time", edittime);//数据库更新多个字段;
		em.joinTransaction();//update有关,不知道其具体用法;
		query.executeUpdate();//sql语句使用Update更新数据时使用executeUpdate();
		em.close();
		return true;
	}
	
}

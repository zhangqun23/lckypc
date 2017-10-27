package com.mvc.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.mvc.entity.TruckSend;
public interface TruckSendRepository extends JpaRepository<TruckSend, Integer> {	
	
	@Query(" select t from TruckSend t where order by trse_insert_time desc ")
	List<TruckSend> getTruckSend( );

	
}

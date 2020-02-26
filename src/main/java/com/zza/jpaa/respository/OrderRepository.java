package com.zza.jpaa.respository;

import com.zza.jpaa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable> {

    @Query(value = "select * from orders where idcard  = :idcard and status!=3", nativeQuery = true)
    List<Order>  findOrdersByUser(String idcard);

    @Query(value = "select * from orders where status!=3 order by create_time asc limit 0,10", nativeQuery = true)
    List<Order> findTopByCreateTime();

}

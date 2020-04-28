package com.zza.jpaa.respository;

import com.zza.jpaa.entity.StatisticData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface StatisticDataRepository extends JpaRepository<StatisticData, Serializable> {


}

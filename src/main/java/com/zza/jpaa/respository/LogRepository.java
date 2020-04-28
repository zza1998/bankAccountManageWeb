package com.zza.jpaa.respository;

import com.zza.jpaa.entity.OperatorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface LogRepository extends JpaRepository<OperatorLog, Serializable> {

}

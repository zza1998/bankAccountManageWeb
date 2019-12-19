package com.zza.jpaa.respository.impl;

import com.zza.jpaa.entity.dto.BalanceDto;
import com.zza.jpaa.util.CommonUtil;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BalanceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<BalanceDto> getBalance(String userId){
        String sql = " select ba.balance as balance , b.bank_name as bankName " +
                "from bank_account  ba LEFT JOIN  " +
                "bank  b on ba.bank_id = b.id  " +
                "where ba.user_id = :userId ";
        System.out.println(userId);
        Query query = entityManager.createNativeQuery(sql)
                .setParameter("userId",userId);
        List queryList = query.getResultList();
        List<BalanceDto> result = CommonUtil.castEntity(queryList,BalanceDto.class);
        System.out.println(result);
        return result;
    }
}

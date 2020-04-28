package com.zza.jpaa.respository.impl;

import com.google.common.collect.Lists;
import com.zza.jpaa.entity.dto.StatisticAccountByBankDto;
import org.hibernate.jpa.spi.NativeQueryTupleTransformer;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
public class BankAccountRepositoryImpl {

    @Resource
    EntityManager entityManager;


    public List<StatisticAccountByBankDto> findAccountByBank(){

        String sql = "select ba.bank_code bankCode, b.bank_name as name, " +
                "count(*) as `value` from bank_account ba " +
                "left join bank b on ba.bank_code = b.id group by ba.bank_code ";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return nativeQuery.getResultList();

    }
}

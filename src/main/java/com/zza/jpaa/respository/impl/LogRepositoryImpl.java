package com.zza.jpaa.respository.impl;

import com.zza.jpaa.entity.vo.LogListVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

@Repository
@Slf4j
public class LogRepositoryImpl {

    @Resource
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<HashMap> findHistory(LogListVo logListVo) {
        StringBuilder sql = new StringBuilder("select * from operator_log where 1 ");
        if (!StringUtils.isEmpty(logListVo.getLogType())) {
            sql.append("and opera_type like ").append(logListVo.getLogType()).append(" ");
        }
        if (!StringUtils.isEmpty(logListVo.getRangeKey())) {
            sql.append("and opera_username like '")
                    .append(logListVo.getRangeKey())
                    .append("'");
        }
        if (logListVo.getStartDate() != null && logListVo.getEndDate() != null) {
            sql.append("and create_time between '")
                    .append(logListVo.getStartDate())
                    .append("' ").append(" and '")
                    .append(logListVo.getEndDate())
                    .append("' ");
        } else if (logListVo.getStartDate() != null) {
            sql.append("and create_time > '").append(logListVo.getStartDate()).append("' ");

        } else if (logListVo.getEndDate() != null) {
            sql.append("and create_time < '").append(logListVo.getEndDate()).append("' ");
        }
        sql.append(" order by create_time desc ");


        final String finalSql = sql.toString();
        log.info("日志查询语句为 {}", finalSql);
        Query nativeQuery = entityManager.createNativeQuery(finalSql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return nativeQuery.getResultList();
    }
}

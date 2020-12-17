package com.pipecode.topshop.configuration.dao;

import com.pipecode.topshop.configuration.dto.LoginDto;
import com.pipecode.topshop.configuration.entity.Acceso;
import com.pipecode.topshop.configuration.entity.Usuario;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 */
@Repository
@Transactional(transactionManager = "transactionManagerOracle")
public class BaseDAO1 {

    @PersistenceContext(unitName = "persistenceUnit")
    protected EntityManager em;

    public Object findById(Long id, String classe) throws ClassNotFoundException {
        Class c = Class.forName(classe);
        return em.find(c, id);
    }

    public void insert(Object entity) {
        em.persist(entity);
        em.flush();
    }

    public Object insertReturnsId(Object entity) {
        em.persist(entity);
        return entity;
    }

    public void update(Object entity) {
        em.merge(entity);
        em.flush();
    }

    
    
}

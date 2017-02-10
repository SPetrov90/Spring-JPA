package ru.sergey90.jpa.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sergey on 04.02.2017.
 */
@Service("contactSummaryUntype")
@Transactional
@Repository
public class ContactSummaryUntypeImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public void displayAllContactSummary(){
        List result = entityManager
                .createQuery("select c.firstName, c.lastName, t.telNumber " +
                        " from Contact c left join c.contactDetails t " +
                        " where t.telType='Home' ").getResultList();
        int count = 0;
        for (Iterator i = result.iterator(); i.hasNext(); ) {
            Object[] values = (Object[]) i.next();
            System.out.println(++count + ": " + values[0] + ", "
            + values[1] + ", " + values[2]);
        }
    }
}

package ru.sergey90.jpa.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey90.jpa.util.ContactSummary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Sergey on 04.02.2017.
 */
@Service("contactSummaryService")
@Transactional
@Repository
public class ContactSummaryServiceImpl implements ContactSummaryService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ContactSummary> findAll() {
        return entityManager.createQuery(
                "select new ru.sergey90.jpa.util.ContactSummary("
                + "c.firstName, c.lastName, t.telNumber) "
                 +" from Contact c left join c.contactDetails t "
                       + " where t.telType='Home' ",
                ContactSummary.class).getResultList();
    }
}

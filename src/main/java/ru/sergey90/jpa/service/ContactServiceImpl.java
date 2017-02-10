package ru.sergey90.jpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey90.jpa.entity.Contact;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Sergey on 02.02.2017.
 */

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
    private static final Logger LOG = LoggerFactory.getLogger(ContactServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        List<Contact> contacts = entityManager.createNamedQuery("Contact.findAll"
        ,Contact.class).getResultList();
        return contacts;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {
        List<Contact> contacts = entityManager.createNamedQuery("Contact.findAllWithDetails"
                ,Contact.class).getResultList();
        return contacts;
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(Integer id) {
        TypedQuery<Contact> query = entityManager.createNamedQuery(
                "Contact.findById", Contact.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Contact save(Contact contact) {
        if (contact.getId() == null){
            LOG.info("Inserting new Contact:" + contact);
            entityManager.persist(contact);
        } else {
            LOG.info("Updating existing contact: ");
            entityManager.merge(contact);
        }
        LOG.info("Updating contact with ID: " + contact.getId());
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        Contact mergedContact = entityManager.merge(contact);
        entityManager.remove(mergedContact);
        LOG.info("Contact with ID: " + contact.getId() + " deleted successfully");
    }
}

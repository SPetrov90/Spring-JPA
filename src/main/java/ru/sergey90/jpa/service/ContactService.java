package ru.sergey90.jpa.service;

import ru.sergey90.jpa.entity.Contact;

import java.util.List;

/**
 * Created by Sergey on 02.02.2017.
 */
public interface ContactService {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Integer id);
    Contact save(Contact contact);
    void delete(Contact contact);

}

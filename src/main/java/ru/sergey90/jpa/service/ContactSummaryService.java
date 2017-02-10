package ru.sergey90.jpa.service;

import ru.sergey90.jpa.util.ContactSummary;

import java.util.List;

/**
 * Created by Sergey on 04.02.2017.
 */
public interface ContactSummaryService {
    List<ContactSummary> findAll();
}

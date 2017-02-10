package ru.sergey90.jpa.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sergey90.jpa.config.AppConfig;
import ru.sergey90.jpa.entity.Contact;
import ru.sergey90.jpa.entity.ContactDetail;
import ru.sergey90.jpa.entity.Hobby;
import ru.sergey90.jpa.service.ContactService;
import ru.sergey90.jpa.service.ContactSummaryService;
import ru.sergey90.jpa.service.ContactSummaryUntypeImpl;
import ru.sergey90.jpa.util.ContactSummary;

import java.sql.Date;
import java.util.*;

/**
 * Created by Sergey on 03.02.2017.
 */
public class ContactServiceTest {
    private static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    private static ContactService contactJpa = context.getBean("jpaContactService", ContactService.class);
    private static ContactSummaryUntypeImpl untypeJpa = context.getBean("contactSummaryUntype", ContactSummaryUntypeImpl.class);
    private static ContactSummaryService contactSummaryService = context.getBean("contactSummaryService", ContactSummaryService.class);


    public static void main(String[] args) {
        listContact(contactJpa.findAll());
        listContactWithDetails(contactJpa.findAllWithDetail());
        findContactByID(1);
        showSummaryContactInfo();
        showSummaryContactService();
        addAndDeleteContact();
    }
    
    private static void addAndDeleteContact(){
        System.out.println("---------------------------------");
        Contact contactWithDetails = new Contact();
        contactWithDetails.setFirstName("Vladimir");
        contactWithDetails.setLastName("Kuzmin");
        contactWithDetails.setBirthDate(new Date(
                new GregorianCalendar(1955, 6, 31).getTime().getTime()));

        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setTelType("Home");
        contactDetail.setTelNumber("5-55-55-55");
        contactWithDetails.addContactDetail(contactDetail);

        ContactDetail contactDetail2 = new ContactDetail();
        contactDetail2.setTelType("Mobile");
        contactDetail2.setTelNumber("8-(911)-5-55-55-55");
        contactWithDetails.addContactDetail(contactDetail2);

        System.out.println("Insert contact with details: " + contactWithDetails);
        Contact result = contactJpa.save(contactWithDetails);
        listContactWithDetails(contactJpa.findAllWithDetail());
        System.out.println("Delete contact with details: " + result);
        contactJpa.delete(result);
        System.out.println("Contact list with details:");
        listContactWithDetails(contactJpa.findAllWithDetail());
        System.out.println("---------------------------------------------");
    }

    private static void showSummaryContactInfo(){
        System.out.println("---------------------------------------------");
        System.out.println("All contact summary:");
        untypeJpa.displayAllContactSummary();
        System.out.println("---------------------------------------------");
    }

    private static void showSummaryContactService(){
        System.out.println("---------------------------------------------");
        System.out.println("All contact summary with Service:");
        List<ContactSummary> contactSummaries = contactSummaryService.findAll();
        for (ContactSummary contact: contactSummaries){
            System.out.println(contact);
        }
        System.out.println("---------------------------------------------");
    }

    private static void findContactByID(Integer id) {
        System.out.println("search name by ID with SQL function: " + contactJpa.findById(id));
        System.out.println("---------------------------------------------");
    }

    private static void listContact(List<Contact> contactList){
        System.out.println("---------------------------------");
        System.out.println("Contact list without details:");
        for (Contact contact: contactList){
            System.out.println(contact);
        }
        System.out.println("---------------------------------");
    }

    private static void listContactWithDetails(List<Contact> contactList) {
        System.out.println("---------------------------------");
        System.out.println("Contact list with details:");
        for (Contact contact: contactList){
            System.out.println(contact);
            if (contact.getContactDetails() != null){
                for (ContactDetail c: contact.getContactDetails()){
                    System.out.println(c);
                }
            }
            if (contact.getHobbies() != null){
                for (Hobby hobby: contact.getHobbies()){
                    System.out.println(hobby);
                }
            }
        }
        System.out.println("---------------------------------");
    }
}

package ru.sergey90.jpa.util;

/**
 * Created by Sergey on 04.02.2017.
 */
public class ContactSummary {
    private String firstName;
    private String lastName;
    private String homeTelNumber;

    public ContactSummary(){

    }

    public ContactSummary(String firstName, String lastName, String homeTelNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeTelNumber = homeTelNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHomeTelNumber() {
        return homeTelNumber;
    }

    public void setHomeTelNumber(String homeTelNumber) {
        this.homeTelNumber = homeTelNumber;
    }

    @Override
    public String toString() {
        return "ContactSummary{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", homeTelNumber='" + homeTelNumber + '\'' +
                '}';
    }
}

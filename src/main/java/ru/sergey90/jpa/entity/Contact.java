package ru.sergey90.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sergey on 01.02.2017.
 */
@Entity
@Table(name = "contact")
@NamedQueries({
        @NamedQuery(name = "Contact.findAll",
        query = "select c from ru.sergey90.jpa.entity.Contact c"),
        @NamedQuery(name = "Contact.findById",
        query = "select distinct c from ru.sergey90.jpa.entity.Contact c left join fetch c.contactDetails t " +
                "left join fetch c.hobbies h where c.id =:id"),
        @NamedQuery(name="Contact.findAllWithDetails" ,
        query="select distinct c from ru.sergey90.jpa.entity.Contact c left join fetch c.contactDetails t " +
                "left join fetch c.hobbies h")
})
public class Contact implements Serializable {
    private Integer id;
    private int version;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Set<ContactDetail> contactDetails = new HashSet<>();
    private Set<Hobby> hobbies = new HashSet<>();

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DAY")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL,
    orphanRemoval = true)
    public Set<ContactDetail> getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(Set<ContactDetail> contactDetails) {
        this.contactDetails = contactDetails;
    }

    public void addContactDetail (ContactDetail contactDetail){
        contactDetail.setContact(this);
        contactDetails.add(contactDetail);
    }

    public void removeContactDetails(ContactDetail contactDetail){
        contactDetails.remove(contactDetail);
    }

    @ManyToMany
    @JoinTable (name = "contact_hobby_detail",
    joinColumns = @JoinColumn(name = "CONTACT_ID"),
    inverseJoinColumns = @JoinColumn (name = "HOBBY_ID"))
    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

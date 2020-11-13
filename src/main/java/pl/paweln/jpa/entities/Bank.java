package pl.paweln.jpa.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="BANK")
@Access(value= AccessType.FIELD)
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BANK_ID")
    private Long bankId;

    @Column(name="NAME")
    private String name;

    @Column(name="IS_INTERNATIONAL")
    private boolean isInternational;

    @Embedded // Composite Value Type
    private Address address = new Address();

    @Column(name="LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name="LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="CREATED_DATE")
    private Date createdDate;

    //Mapping collection of basic values
//    @ElementCollection
//    @CollectionTable(name="BANK_CONTACT", joinColumns=@JoinColumn(name="BANK_ID"))
//    @Column(name="NAME")
//    Collection<String> contacts = new ArrayList<String>();

    //Mapping a map of basic values
    @ElementCollection
    @CollectionTable(name="BANK_CONTACT", joinColumns=@JoinColumn(name="BANK_ID"))
    @MapKeyColumn(name="POSITION_TYPE")
    @Column(name="NAME")
    Map<String, String> contacts = new HashMap<String, String>();

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return this.address.getAddressLine1();
    }

    public void setAddressLine1(String addressLine1) {
        this.address.setAddressLine1(addressLine1);
    }

    public String getAddressLine2() {
        return this.address.getAddressLine2();
    }

    public void setAddressLine2(String addressLine2) {
        this.address.setAddressLine2(addressLine2);
    }

    public String getCity() {
        return this.address.getCity();
    }

    public void setCity(String city) {
        this.address.setCity(city);
    }

    public String getState() {
        return this.address.getState();
    }

    public void setState(String state) {
        this.address.setState(state);
    }

    public String getZipCode() {
        return this.address.getZipCode();
    }

    public void setZipCode(String zipCode) {
        this.address.setZipCode(zipCode);
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Map<String, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, String> contacts) {
        this.contacts = contacts;
    }
}

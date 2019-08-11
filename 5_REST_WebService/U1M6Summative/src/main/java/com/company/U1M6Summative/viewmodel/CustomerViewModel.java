package com.company.U1M6Summative.viewmodel;

import com.company.U1M6Summative.model.Invoice;

import java.util.List;
import java.util.Objects;

public class CustomerViewModel {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private String phone;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        CustomerViewModel that = (CustomerViewModel) o;
        return getCustomerId() == that.getCustomerId() &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getCompany(), that.getCompany()) &&
                Objects.equals(getPhone(), that.getPhone());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(),getFirstName(),getLastName(),getEmail(),getCompany(),getPhone());
    }
}

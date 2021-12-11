package com.alayon.msbank.account.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount")
    private Integer idAccount;
    @Column(name = "totalamount")
    private double totalAmount;
    @Column(name = "idcustomer")
    private Integer idCustomer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idcustomer" , insertable =false , updatable = false)
    @Fetch(FetchMode.JOIN)
    private CustomerModel customer ;

    public Integer getIdAccount() {
        return idAccount;
    }
    public CustomerModel getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
    public Integer getIdCustomer() {
        return idCustomer;
    }
    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }
}

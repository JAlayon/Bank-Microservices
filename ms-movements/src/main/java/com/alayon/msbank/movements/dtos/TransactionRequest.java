package com.alayon.msbank.movements.dtos;

public class TransactionRequest {

    private Integer id;
    private double amount;
    private String type;
    private String creationDate;
    private Integer accountId;

    public Integer getId() {
        return id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final String creationDate) {
        this.creationDate = creationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    public void setId(final Integer id) {
        this.id = id;
    }
}
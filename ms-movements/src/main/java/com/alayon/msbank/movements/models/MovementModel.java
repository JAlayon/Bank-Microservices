package com.alayon.msbank.movements.models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Document(collection = "movement")
public class MovementModel implements Serializable {

    private static final long serialVersionUID = 3738919547648776411L;

    @BsonId
    private String id;
    private Integer transactionId;
    private double amount;
    private String type;
    private Integer accountId;
    private String processDate;

    public MovementModel() {
        final Date date = Calendar.getInstance().getTime();
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.setProcessDate(dateFormat.format(date));
    }

    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(final String processDate) {
        this.processDate = processDate;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(final Integer accountId) {
        this.accountId = accountId;
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

    public void setTransactionId(final Integer transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "MovementModel [id=" + id + ", transactionId=" + transactionId + ", amount=" + amount + ", type=" + type
                + ", accountId=" + accountId + ", processDate=" + processDate + "]";
    }

}

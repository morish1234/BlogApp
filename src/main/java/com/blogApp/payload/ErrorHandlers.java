package com.blogApp.payload;

import java.util.Date;

public class ErrorHandlers {
    private Date date;
    private  String message;
    private String details;
private  String status;
    public ErrorHandlers(Date date, String message, String details,String status) {
        this.date = date;
        this.message = message;
        this.details = details;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

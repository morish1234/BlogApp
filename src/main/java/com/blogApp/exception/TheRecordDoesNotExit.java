package com.blogApp.exception;

public class TheRecordDoesNotExit extends RuntimeException {
    public TheRecordDoesNotExit(String msg) {
        super(msg);
    }
}

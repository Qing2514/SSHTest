package com.example.domain;

public class UserException  extends Exception{

    public static final String NULL_ERROR = " is null";
    public static final String LENGTH_ERROR = " length error";
    public static final String TYPE_ERROR = " type error";

    public UserException(String message) {
        super(message);
    }
}

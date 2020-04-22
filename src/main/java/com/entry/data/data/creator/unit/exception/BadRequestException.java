package com.entry.data.data.creator.unit.exception;

public class BadRequestException extends RuntimeException{

    public static final String MISSING_MANDATORY_FIELD = "Mandatory Field is missing from input : ";

    public BadRequestException(String message){

    }

}

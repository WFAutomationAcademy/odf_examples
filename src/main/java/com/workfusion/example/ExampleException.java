package com.workfusion.example;

public class ExampleException extends RuntimeException {
    // error message example with 1 parameter
    public ExampleException(String message) {
        super(message);
    }
    // error message example with 2 parameters 
    public ExampleException(String message, Throwable cause) {
        super(message, cause);
    }

}

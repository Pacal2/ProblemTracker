package com.example.problemtracker;

public class ProblemTrackerException extends RuntimeException {

    public ProblemTrackerException(String exceptionMessage, Exception exception) {
        super(exceptionMessage, exception);
    }

}

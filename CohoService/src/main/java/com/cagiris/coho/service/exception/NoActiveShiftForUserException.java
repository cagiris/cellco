package com.cagiris.coho.service.exception;

public class NoActiveShiftForUserException extends AttendenceReportingServiceException {

    private static final long serialVersionUID = 1L;

    public NoActiveShiftForUserException() {
    }

    public NoActiveShiftForUserException(String message) {
        super(message);
    }

    public NoActiveShiftForUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoActiveShiftForUserException(Throwable cause) {
        super(cause);
    }

}

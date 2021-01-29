package com.stockkeeper.Models.customexceptions;

public class NoUserFoundException extends Exception {

    public NoUserFoundException() {
        super("No user matches the credentials");
    }

}

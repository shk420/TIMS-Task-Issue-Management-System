package com.shiva.tims.Exceptions;

import java.io.Serial;

public class WrongPasswordException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

	public WrongPasswordException(String message) {
		super(message);
	}

}

package com.example.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public EmployeeNotFoundException(String arg0) {
		super(arg0);

	}

	public EmployeeNotFoundException(Throwable arg0) {
		super(arg0);

	}

}

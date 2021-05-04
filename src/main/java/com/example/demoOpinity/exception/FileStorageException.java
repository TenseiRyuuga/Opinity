package com.example.demoOpinity.exception;

// custom exception in case a file can't be uploaded properly
public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String msg;

	public FileStorageException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
package com.demo.ecommerce.enums;

public enum HttpStatusCode {
	OK(200, "OK"), ACCEPTED(202, "Accepted"),CREATED(201, "Created"), NO_CONTENT(204, "No content");

	private final int code;
	private final String message;

	HttpStatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}

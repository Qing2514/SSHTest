package com.example.domain;

import lombok.Data;

@Data
public class User {

	private static final int MAX_LENGTH = 10;
	private static final int MIN_LENGTH = 4;
	private static final String USERNAME_TYPE = "username";
	private static final String PASSWORD_TYPE = "password";

	private String username;
	private String password;

    public User(String username, String password) throws UserException {

		checkString(username, USERNAME_TYPE);
		checkString(password, PASSWORD_TYPE);

		this.username = username;
    	this.password = password;
	}

	private void checkString(String s, String type) throws UserException {
		if (s == null) {
			throw new UserException(type + UserException.NULL_ERROR);
		}
		if (s.length() < MIN_LENGTH || s.length() > MAX_LENGTH) {
			throw new UserException(type + UserException.LENGTH_ERROR);
		}
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i)) && !Character.isAlphabetic(s.charAt(i)) && s.charAt(i) != '_') {
				throw new UserException(type + UserException.TYPE_ERROR);
			}
		}
	}
}

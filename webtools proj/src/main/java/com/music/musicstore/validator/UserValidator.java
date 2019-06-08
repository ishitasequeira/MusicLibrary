package com.music.musicstore.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.music.musicstore.pojo.User;

public class UserValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		// This Validator validates *just* User instances
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// basic validations
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname", "empty.fname", "First Name is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname", "empty.lname", "Last Name is Required");
		ValidationUtils.rejectIfEmpty(errors, "email", "empty.email", "Email is Required");
		ValidationUtils.rejectIfEmpty(errors, "password", "empty.email", "Password is Required");

		// add additional custom validations below
	}
}

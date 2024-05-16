package com.exam.helper;

public class UserFoundException extends Exception{
	public UserFoundException() {
		super("User with this username is already there is DB !! try with another One");
		
	}
	public UserFoundException(String msg) {super(msg);}

}

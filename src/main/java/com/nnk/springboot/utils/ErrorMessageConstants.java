package com.nnk.springboot.utils;

import java.security.PublicKey;

public class ErrorMessageConstants {

    private ErrorMessageConstants(){}

    public static final String UNIQUE_PASSWORD ="P@ssw0rd!";
    public static final String PASSWORD_PATTERN ="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
    public static final String PASSWORD_ERROR_MSG = "Must contain at least one number, one uppercase letter, one lowercase letter, and one special character.";


    //========== User Entity =============//
    public static final String USER_NAME_IS_NULL_OR_BLANK = "Username should not be null or blank";
    public static final String PASSWORD_IS_NULL_OR_BLANK = "Username should not be null or blank";
    public static final String FULL_NAME_IS_NULL_OR_BLANK = "Username should not be null or blank";
    public static final String ROLE_NAME_IS_NULL_OR_BLANK = "Username should not be null or blank";

    public static final String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE = "Username or password incorrect";
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User id not found";
    public static final String USERNAME_ALREADY_EXIST = "Username already exist in database";

    //========== CurvePoint Entity =============//

    public static final String CURVE_POINT_IS_NOT_FOUND = "CurvePoint is not found";

}

package com.SaralSewa.SaralSewa.shared.core.config.constant;


public class ApiConstant {

    public static final String API = "/api/v1";
    public static final String SLASH = "/";


    public static final String AUTH = "admin";
    public static final String USERS = "users";

    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String REFRESH_TOKEN = "refreshToken";
    public static final String REGISTER = "register";


    public static final String CREATE = "create";
    public static final String LIST = "list";
    public static final String VIEW = "view";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String STATUS = "status";
    public static final String BLOCK = "block";
    public static final String UNBLOCK = "unblock";
    public static final String ACTIVE = "active";


    public static final String BOOKING = "booking";
    public static final String CREATE_BOOKING = "create";
    public static final String LIST_BOOKINGS = "list";
    public static final String GET_BOOKING_BY_ID = "{bookingId}";
    public static final String CANCEL_BOOKING = "cancel/{bookingId}";


    public static final String UPLOAD = "upload";
    public static final String UPLOAD_ONLY = "upload_only";
    public static final String ALL_PICTURE = "getProfilePictures";
    public static final String PROFILE_EMAIL = "profile/email/{email}";
    public static final String FILE_NAME = "view/{fileName}";
    public static final String LIST_FILES = "files";
    public static final String IMAGE_DOWNLOAD = "image/download/{fileName}";
    public static final String DIRECT_IMAGE = "image/{fileName}";


    public static final String RESEND_OTP = "resendOtp";
    public static final String BOOKING_OTP = "bookingOtp";
    public static final String FORGET_PASSWORD = "forgetPassword";
    public static final String SEND_OTP = "sendOtp";
    public static final String RESET_PASSWORD = "resetPassword";
    public static final String NEW_PASSWORD = "newPassword";
    public static final String FORGET_PASSWORD_CONFIRM = "forgetPasswordConfirm";


    public static final String CREATE_ORDER = "create_order";

}
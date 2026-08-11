package com.SaralSewa.SaralSewa.shared.core.config.constant;

public class ApiConstant {

    // ===== BASE =====
    public static final String API = "/api/v1";
    public static final String SLASH = "/";
    public static final String ID = "{id}";
    public static final String CODE = "code";

    // ===== MODULES =====
    public static final String AUTH = "auth";
    public static final String ADMIN = "admin";
    public static final String USERS = "users";
    public static final String PROVIDERS = "providers";
    public static final String BOOKINGS = "bookings";
    public static final String SERVICES = "services";
    public static final String REVIEWS = "reviews";
    public static final String CATEGORIES = "categories";
    public static final String CUSTOMER_PROFILES = "customer-profiles";
    public static final String NOTIFICATIONS = "notifications";
    public static final String FEEDBACKS = "feedbacks";
    public static final String SKILLS = "skills";
    public static final String SLOTS = "slots";
    public static final String DOCUMENTS = "documents";
    public static final String BOOKING_STATUSES = "booking-statuses";
    public static final String PROVIDER_PROFILES = "provider-profiles";
    public static final String ROLES = "roles";
    public static final String STATUSES = "statuses";
    public static final String APPROVAL_STATUSES = "approval-statuses";

    // ===== AUTHENTICATION =====
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String REGISTER = "register";
    public static final String REFRESH_TOKEN = "refresh-token";
    public static final String FORGET_PASSWORD = "forget-password";
    public static final String RESET_PASSWORD = "reset-password";
    public static final String CHANGE_PASSWORD = "change-password";
    public static final String SEND_OTP = "send-otp";
    public static final String RESEND_OTP = "resend-otp";
    public static final String VERIFY_OTP = "verify-otp";
    public static final String BOOKING_OTP = "booking-otp";
    public static final String NEW_PASSWORD = "new-password";
    public static final String FORGET_PASSWORD_CONFIRM = "forget-password-confirm";
    public static final String CURRENT_USER = "me";
    public static final String PROFILE = "profile";
    public static final String EMAIL = "email";

    // ===== CRUD OPERATIONS =====
    public static final String CREATE = "create";
    public static final String LIST = "list";
    public static final String VIEW = "view";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String SOFT_DELETE = "soft-delete";
    public static final String RESTORE = "restore";
    public static final String ACTIVATE = "activate";
    public static final String DEACTIVATE = "deactivate";
    public static final String BLOCK = "block";
    public static final String UNBLOCK = "unblock";

    // ===== BOOKING OPERATIONS =====
    public static final String BOOKING = "booking";
    public static final String CANCEL = "cancel";
    public static final String COMPLETE = "complete";
    public static final String STATUS = "status";
    public static final String CUSTOMER = "customer";
    public static final String PROVIDER = "provider";

    // ===== REVIEW OPERATIONS =====
    public static final String AVERAGE_RATING = "average-rating";
    public static final String COUNT = "count";

    // ===== PROVIDER SPECIFIC =====
    public static final String USER = "user";
    public static final String PROFESSION = "profession";
    public static final String APPROVE = "approve";
    public static final String REJECT = "reject";
    public static final String VERIFY = "verify";
    public static final String DOCUMENT = "document";
    public static final String VERIFICATION_STATUS = "verification-status";
    public static final String CITY = "city";
    public static final String DISTRICT = "district";

    // ===== FILE UPLOAD =====
    public static final String UPLOAD = "upload";
    public static final String UPLOAD_ONLY = "upload-only";
    public static final String FILE_NAME = "view/{fileName}";
    public static final String LIST_FILES = "files";
    public static final String ALL_PICTURE = "getProfilePictures";
    public static final String PROFILE_EMAIL = "profile/email/{email}";
    public static final String IMAGE_DOWNLOAD = "image/download/{fileName}";
    public static final String DIRECT_IMAGE = "image/{fileName}";

    // ===== ORDER =====
    public static final String CREATE_ORDER = "create-order";

    // ===== SEARCH =====
    public static final String SEARCH = "search";
    public static final String FILTER = "filter";

    // ===== STATISTICS =====
    public static final String STATISTICS = "statistics";
    public static final String SUMMARY = "summary";
    public static final String DASHBOARD = "dashboard";

    // ===== EXPORT =====
    public static final String EXPORT = "export";
    public static final String IMPORT = "import";
    public static final String CSV = "csv";
    public static final String EXCEL = "excel";
    public static final String PDF = "pdf";

    // ===== BULK OPERATIONS =====
    public static final String BULK = "bulk";
    public static final String BULK_DELETE = "bulk-delete";
    public static final String BULK_ACTIVATE = "bulk-activate";
    public static final String BULK_DEACTIVATE = "bulk-deactivate";

    // ===== ADDITIONAL OPERATION CONSTANTS =====
    public static final String READ = "read";
    public static final String READ_ALL = "read-all";
    public static final String TYPE = "type";
    public static final String BOOK = "book";
    public static final String AVAILABLE = "available";
    public static final String CATEGORY = "category";
}
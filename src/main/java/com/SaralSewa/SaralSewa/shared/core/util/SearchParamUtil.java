package com.SaralSewa.SaralSewa.shared.core.util;


import com.SaralSewa.SaralSewa.shared.core.search.SearchParam;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SearchParamUtil {
    public static String getString(SearchParam searchParam, String keyName) {
        return (String) searchParam.getParam().get(keyName);
    }
    public static String getInteger(SearchParam searchParam, String keyName) {
        return (String) searchParam.getParam().get(keyName);
    }

    public static Date getDate(SearchParam searchParam, String keyName, String dateFormat) {
        String stringDate = getString(searchParam, keyName);
        if (stringDate != null && !stringDate.isBlank()) {
            Instant instant = Instant.parse(stringDate);

            Date date = Date.from(instant);

            return DateUtility.getDateFromDate(date);
        }
        return null;
    }

    public static Boolean getBoolean(SearchParam searchParam, String keyName) {
        if (searchParam == null || searchParam.getParam() == null) {
            return null;
        }

        Object value = searchParam.getParam().get(keyName);
        if (value == null) {
            return null;
        }

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue() == 1;
        }

        if (value instanceof String) {
            String v = ((String) value).trim().toLowerCase();
            if ("true".equals(v) || "1".equals(v)) return true;
            if ("false".equals(v) || "0".equals(v)) return false;
        }

        throw new IllegalArgumentException(
                "Invalid boolean value for key '" + keyName + "': " + value
        );
    }

    public static Date getLocalDateTime(SearchParam searchParam, String keyName) {
        String stringDate = getString(searchParam, keyName);
        if (stringDate != null && !stringDate.isBlank()) {
            try {

                LocalDate localDate = LocalDate.parse(stringDate);
                LocalDateTime localDateTime;
                if ("endDate".equals(keyName)) {
                    localDateTime = localDate.atTime(23, 59, 59);
                } else {
                    localDateTime = localDate.atStartOfDay();
                }
                Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
                Date date = Date.from(instant);
                return DateUtility.getDateFromDate(date);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format: " + stringDate, e);
            }
        }
        return null;
    }

    public static Object getBigDecimal(SearchParam searchParam, String charge) {
        return searchParam.getParam().get(charge);
    }

    public static List<?> getList(SearchParam searchParam, String keyName) {
        if (searchParam == null || searchParam.getParam() == null) {
            return null;
        }

        Object value = searchParam.getParam().get(keyName);

        if (value == null) {
            return null;
        }

        if (value instanceof List<?>) {
            return ((List<?>) value).isEmpty() ? null : (List<?>) value;
        }
        return Collections.singletonList(value);
    }
    public static <T> T get(SearchParam searchParam, String key, Class<T> type) {
        if (searchParam == null || searchParam.getParam() == null) {
            return null;
        }
        Object value = searchParam.getParam().get(key);
        return type.isInstance(value) ? type.cast(value) : null;
    }

}


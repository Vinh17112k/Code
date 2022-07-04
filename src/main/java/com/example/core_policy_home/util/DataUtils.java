package com.example.core_policy_home.util;

import static org.apache.commons.lang3.StringUtils.replaceChars;
import static org.apache.commons.lang3.StringUtils.stripAccents;

import com.example.core_policy_home.constants.Constants;
import com.example.core_policy_home.constants.Constants.RegexPattern;
import com.example.core_policy_home.constants.enums.ErrorCodeEnum;
import com.example.core_policy_home.exception.InputInvalidException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataUtils {

  public static Boolean isNull(Object obj) {
    return obj == null;
  }

  public static Boolean notNull(Object obj) {
    return !isNull(obj);
  }

  public static Boolean isNullOrEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }
  public static boolean isNullOrEmptyStr(String str) {
    if (str == null || "".equalsIgnoreCase(str)) {
      return true;
    }
    return false;
  }
  public static boolean isOverLength(String str, int length) {
    if (str.length()>length) {
      return true;
    }
    return false;
  }
  public static Boolean notNullOrEmpty(Collection<?> collection) {
    return !isNullOrEmpty(collection);
  }

  public static String parseToString(Object obj) {
    if (isNull(obj).equals(Boolean.TRUE)) {
      return null;
    }
    return String.valueOf(obj);
  }
  public static String safeToString(Object obj) {
    if (isNull(obj).equals(Boolean.TRUE)) {
      return "";
    }
    return String.valueOf(obj);
  }
  public static List<String> parseToListString(Object obj) {
    String str = parseToString(obj);
    if(StringUtils.isNotBlank(str)){
      return Arrays.asList(str.split(","));
    }
    return new ArrayList<>();
  }

  public static BigDecimal parseToBigDecimal(Object obj) {
    if (obj instanceof BigDecimal) {
      return (BigDecimal) obj;
    }
    return new BigDecimal(obj.toString());
  }

  public static Long parseToLong(Object obj) {
    if (isNull(obj).equals(Boolean.TRUE)) {
      return null;
    }
    return Long.parseLong(parseToString(obj));
  }

  public static Integer parseToInteger(Object obj) {
    if (isNull(obj).equals(Boolean.TRUE)) {
      return null;
    }
    return Integer.parseInt(parseToString(obj));
  }

  public static LocalDate parseToLocalDate(Object obj) {
    if (isNull(obj).equals(Boolean.TRUE)) {
      return null;
    }
    return LocalDate.parse(parseToString(obj));
  }

  public static LocalDateTime parseToLocalDateTime(Object obj) {
    if (isNull(obj).equals(Boolean.TRUE)) {
      return null;
    }
    if (obj instanceof Timestamp) {
      return ((Timestamp) obj).toLocalDateTime();
    }
    return LocalDateTime.parse(parseToString(obj));
  }

  public static LocalDate longToLocalDate(Long input) {
    if (isNull(input).equals(Boolean.TRUE) || input <= 0L) {
      return null;
    }
    LocalDateTime date =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(input), ZoneId.systemDefault());
    return date.toLocalDate();
  }

  public static LocalDateTime longToLocalDateTime(Long input) {
    if (isNull(input).equals(Boolean.TRUE) || input <= 0L) {
      return null;
    }
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(input), ZoneId.systemDefault());
  }

  public static Long localDateToLong(LocalDate input, Long defaultValue) {
    if (input == null) {
      return defaultValue;
    }
    return input.toEpochDay();
  }

  public static Long localDateTimeToLong(LocalDateTime input, Long defaultValue) {
    if (input == null) {
      return defaultValue;
    }
    ZonedDateTime zdt = input.atZone(ZoneId.systemDefault());
    return zdt.toInstant().toEpochMilli();
  }

  public static String objectToJson(Object data) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.writeValueAsString(data);
  }

  public static <T> T jsonToObject(String jsonData, Class<T> classOutput) throws IOException {
    ObjectMapper mapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper.readValue(jsonData, classOutput);
  }

  public static <T> T base64ToObject(String encodedString, Class<T> classOutput)
      throws IOException {
    byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
    String decodedString = new String(decodedBytes, StandardCharsets.UTF_8.name());

    return jsonToObject(decodedString, classOutput);
  }

  public static <T> T byteToObject(byte[] input, Class<T> classOutput) {
    String jsonData = new String(input, StandardCharsets.UTF_8);
    try {
      return DataUtils.jsonToObject(jsonData, classOutput);
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }
    return null;
  }

  public static String formatIsdn(String msisdn) {
    if (msisdn.startsWith("0")) {
      return msisdn.substring(1);
    } else if (msisdn.startsWith("84")) {
      return msisdn.substring(2);
    } else if (msisdn.startsWith("+84")) {
      return msisdn.substring(3);
    }
    return msisdn;
  }

  public static String formatMsisdn(String isdn) {
    if (isdn.startsWith("84")) {
      return isdn;
    } else if (isdn.startsWith("+84")) {
      return isdn.substring(1);
    } else if (isdn.startsWith("0")) {
      isdn = isdn.substring(1);
    }
    return String.format("84%s", isdn);
  }

  public static String localDateTimeToString(LocalDateTime value, String format) {
    if (!notNull(value).equals(Boolean.TRUE)) {
      return null;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return value.format(formatter); // "1986-04-08 12:30"
  }

  public static String getSystemTrace(String requestId) {
    String sysTrace = requestId;
    sysTrace = sysTrace.substring(sysTrace.length() - 6);
    return sysTrace;
  }

  public static LocalDate convertStringToLocalDate(String value, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    if (value == null) {
      return null;
    } else if (value.contains(".")) {
      value = value.substring(0, value.indexOf('.'));
    }
    return LocalDate.parse(value, formatter);
  }

  public static LocalDateTime convertStringToLocalDateTime(String value, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    if (Boolean.TRUE.equals(nullOrEmpty(value))) {
      return null;
    }

    if (value != null && value.contains(".")) {
      value = value.substring(0, value.indexOf('.'));
    }
    return LocalDateTime.parse(value, formatter);
  }

  public static Boolean nullOrEmpty(String input) {
    return input == null || input.trim().isEmpty();
  }

  public static Boolean notNullOrEmpty(String input) {
    return input != null && !input.trim().isEmpty();
  }

  public static Long generateRequestId() {
    return System.currentTimeMillis();
  }

  public static boolean isNumeric(String isdn) {
    return isdn.trim().matches(RegexPattern.DIGITS);
  }

  public static boolean isValidIsdn(String isdn) {
    return isdn.trim().matches(RegexPattern.PHONE_NUMBER);
  }

  public static boolean isValidRange(Long minValue, Long maxValue, String value) {
    long range = Long.parseLong(value);
    return range >= minValue && range <= maxValue;
  }

  // Là số nguyên dương hoặc = 0
  public static boolean isPositiveNumber(BigDecimal value) {
    return value.compareTo(BigDecimal.ZERO) >= 0;
  }

  public static boolean notPositiveNumber(BigDecimal value) {
    return !isPositiveNumber(value);
  }

  public static boolean compareObject(Object obj1, Object obj2) {
    boolean valid = false;
    if ((isNull(obj1) && isNull(obj2))) {
      valid = true;
    }

    if ((notNull(obj1) && notNull(obj2) && obj1.equals(obj2))) {
      valid = true;
    }

    return valid;
  }

  public static String removeAccents(String accent) {
    return (replaceChars(
        replaceChars(stripAccents(accent), (char) 273, (char) 100),
        (char) 272, (char) 68));
  }

  public static String genPolicyCode(Long index) {
    if (Boolean.TRUE.equals(isNull(index))) {
      throw new InputInvalidException(ErrorCodeEnum.TB003, "index");
    }

    String datetime = localDateTimeToString(LocalDateTime.now(), Constants.YY_MM_DD);
    return String.format(Constants.POLICY_CODE_TEMPLATE, datetime, index);
  }

  public static Integer nvl(Integer src) {
    return src == null ? 0 : src;
  }

  public static String nvl(String src) {
    return src == null ? "" : src;
  }

  private static final DateTimeFormatter cacheKeyFormatter = DateTimeFormatter
      .ofPattern("yyyyMMdd");

  public static boolean isOUTSTATUS(Integer value) {

    return value.compareTo(Constants.STATUS_SERVICE.ACTIVE) >= 0||value.compareTo(Constants.STATUS_SERVICE.INACTIVE) >= 0;
  }
}

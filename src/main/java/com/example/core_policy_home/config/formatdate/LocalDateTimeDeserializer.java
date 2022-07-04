package com.example.core_policy_home.config.formatdate;

import com.example.core_policy_home.util.DataUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;

/**
 * Thực hiện parse data json request to localDateTime
 *
 * @author HoangTD5
 * @version 0.1
 * @date 8/26/2020
 */
@Slf4j
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

  public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
  public static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
  public static final String YYYY_MM_DD_FULL = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

  @Override
  public LocalDateTime deserialize(JsonParser jsonParser,
      DeserializationContext deserializationContext) throws IOException {
    String str = jsonParser.getText();
    try {
      return DataUtils.convertStringToLocalDateTime(str, YYYY_MM_DD_T_HH_MM_SS);
    } catch (DateTimeParseException e) {
      log.error(e.getMessage(), e);
      return deserialize(str);
    }
  }

  private LocalDateTime deserialize(String str) {
    try {
      return DataUtils.convertStringToLocalDateTime(str, YYYY_MM_DD_HH_MM_SS);
    } catch (DateTimeParseException e) {
      log.error(e.getMessage(), e);
      return deserialize2(str);
    }
  }

  private LocalDateTime deserialize2(String str) {
    try {
      return DataUtils.convertStringToLocalDateTime(str, YYYY_MM_DD_FULL);
    } catch (DateTimeParseException e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }
}

package com.example.core_policy_home.config.formatdate;

import com.example.core_policy_home.util.DataUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;

/**
 * Thực hiện parse data response to string json
 *
 * @author HoangTD5
 * @version 0.1
 * @date 8/26/2020
 */
@Slf4j
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

  public static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";

  @Override
  public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    try {
      String result = DataUtils.localDateTimeToString(localDateTime, YYYY_MM_DD_T_HH_MM_SS);
      jsonGenerator.writeString(result);
    } catch (DateTimeParseException e) {
      log.error(e.getMessage(), e);
      jsonGenerator.writeString("");
    }
  }
}

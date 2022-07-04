package com.example.core_policy_home.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuggestionEnum {
  SERVICE_ALL("all"),
  SERVICE_ID("id"),
  SERVICE_NAME("name"),
  SERVICE_SERVICE_CODE("serviceCode"),
  SERVICE_PROCESS_CODE("processCode");

  private final String value;
}

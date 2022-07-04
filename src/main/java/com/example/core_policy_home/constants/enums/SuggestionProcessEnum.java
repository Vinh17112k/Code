package com.example.core_policy_home.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuggestionProcessEnum {
  PROCEE_NAME("name"),
  PROCESS_CODE("processCode"),
  PROCESS_CODE_NAME("process Name_Code");

  private final String value;
}


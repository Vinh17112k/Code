package com.example.core_policy_home.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonStatusEnum {

  ENABLED(1, "ENABLED"),
  DISABLED(0, "DISABLED"),
  ALL(-1,"ALL");

  private final int value;
  private final String code;

}

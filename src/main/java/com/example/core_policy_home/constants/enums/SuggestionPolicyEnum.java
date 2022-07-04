package com.example.core_policy_home.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * (Write a succinct description of this class here.)
 *
 * @author FPT_KhanhPN5
 * @version v1
 * @date 12/7/2020
 */
@Getter
@AllArgsConstructor
public enum SuggestionPolicyEnum {

  POL_FEE_ID("id"),
  POL_FEE_NAME("namePolicy"),
  POL_FEE_NAME_FEE_EXPR("nameFeeExpr"),
  POL_FEE_SERVICE("polService"),
  POL_FEE_CUS("polCus");

  private final String value;
}

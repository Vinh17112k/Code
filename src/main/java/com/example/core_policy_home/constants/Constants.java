package com.example.core_policy_home.constants;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

  public static final String ACTION_USER_INFO = "ACTION_USER_INFO";
  public static final String ACTION_USER = "ACTION_USER";
  public static final String USER_DEFAULT = "ADMIN";
  public static final String PATH_MESSAGE = "i18n/messages";
  public static final String PATH_ERROR = "i18n/errors";
  public static final Integer MAX_LEVEL_SERVICE = 5;
  public static final String PREFIX_SERVICE = "DV_";
  public static final String POLICY_CODE_TEMPLATE = "PF%s_%010d"; // PF_20201209_0000000001
  public static final String YY_MM_DD = "yyMMdd";
  public static final String DD_MM_YYYY = "dd/MM/yyyy";
  public static final int YEARS = 100; // plus 100 year
  public static final int ONE = 1;

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class RegexPattern {

    public static final String DIGITS = "\\d+";
    public static final String HOURS_PARAM = "^([01][0-9]|2[0-3]):(00|15|30|45)$";
    public static final String NUMBER_SEPERATE_COMMA = "^(\\d+,)*\\d+$";
    public static final String PHONE_NUMBER = "^(0|84)[0-9]{0,13}$";
    public static final String RMV_COMMA = "[\\s],|,$|^,";
    public static final String COMMA = ",";
    public static final String COMMA_SPACE = ", ";
    public static final String PERCENT = "%";
  }

  // Điều kiện tính
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class ConditionCal {

    // Tất cả: Áp dụng cách tính cho tất cả
    public static final String ALL = "FEE_CONDITION_ALL";
    // Nguồn tiền
    public static final String MONEY_SOURCE = "FEE_MONEY_SOURCE";
    // Người thụ hưởng
    public static final String BENEFICIARY = "FEE_BENEFICIARY";
    // STT GD (Số thứ tự giao dịch)
    public static final String TXN_ORDER = "FEE_TXN_ORDER";
    // Tổng số tiền GD (Tổng số tiền giao dịch)
    public static final String TOTAL_TXN_AMOUNT = "FEE_TOTAL_TXN_AMOUNT";
    // GTGD (Giá trị giao dịch)
    public static final String TXN_AMOUNT = "FEE_TXN_AMOUNT";
    // Loại địa bàn
    public static final String LOCALITY = "FEE_LOCALITY";
    // Hình thức chuyển
    public static final String TRANSFER_TYPE = "FEE_TRANSFER_TYPE";
    // Ngày giao dịch
    public static final String TXN_DATE = "FEE_TXN_DATE";

  }

  // Điều kiện tính
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class MoneySource {
    // Nguồn tiền liên kết
    public static final String FEE_MONEY_SOURCE_DIRECT = "FEE_MONEY_SOURCE_DIRECT";
    // Nguồn tiền napas
    public static final String FEE_MONEY_SOURCE_NAPAS = "FEE_MONEY_SOURCE_NAPAS";
  }

  // Ngày giao dịch
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class TransDay {

    // Khoảng thời gian
    public static final String RANGE_DATE = "TIME_RANGE";
    // Ngày trong tuần (T7,CN)
    public static final String FEE_WEEK_DAY = "WEEK_DAY";
    // Ngày nghỉ, ngày lễ
    public static final String FEE_HOLIDAY = "HOLIDAY";
    // Ngày làm việc
    public static final String FEE_WORK_DAY = "WORK_DAY";
    // Ngày đầu tháng
    public static final String FEE_FIRST_DAY_MONTH = "FIRST_DAY_MONTH";
  }

  // Điều kiện tính
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class DetailCal {

    // Miễn phí
    public static final Integer FREE = 1;
    // Theo tỉ lệ %
    public static final Integer PERCENTAGE = 2;
    // Theo block
    public static final Integer BLOCK = 3;
  }

  // Tỉ lệ %
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Percent {

    public static final BigDecimal BD_100 = BigDecimal.valueOf(100);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Priority {

    public static final Integer FEE = 1;
    public static final Integer SURCHARGE = 9;
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class UniqueType {

    public static final int NAME = 1;
    public static final int DOCUMENT_ID = 2;
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class StatusPolicy {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;
    public static final int ALL = -1;
  }
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class StatusProcess {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;
    public static final int ALL = -1;
  }
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class PolicyType {

    public static final int FEE = 1;
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class SysParamGroupCode {

    public static final String FEE_CALCULATION_SCOPE = "FEE_CALCULATION_SCOPE";
    public static final String FEE_CALCULATION_METHOD = "FEE_CALCULATION_METHOD";
    public static final String FEE_TXN_CHANNEL = "FEE_TXN_CHANNEL";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class SysParamCode {

    public static final String WHOLE_TRANSACTION = "WHOLE_TRANSACTION";
    public static final String PORTION_WITHIN = "PORTION_WITHIN";
    public static final String FEE_FREE = "FEE_FREE";
    public static final String FEE_PERCENT = "FEE_PERCENT";
    public static final String FEE_BLOCK = "FEE_BLOCK";

  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class CacheName {

    public static final String SYS_PARAM_GROUP_CODE = "SYS_PARAM_GROUP_CODE";
    public static final String SYS_PARAM_GROUP_CODES = "SYS_PARAM_GROUP_CODES";
    public static final String AVAI_POLICIES_PROC = "AVAI_POLICIES_PROC";
    public static final String CALCULATE_FEE_PROC = "CALCULATE_FEE_PROC";
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class CacheManagerName {

    public static final String POLICY_CACHE_MANAGER = "policyCacheManager";
  }
  public static final class STATUS_SERVICE {

    private STATUS_SERVICE() {
    }

    public static final Integer ACTIVE = 1;
    public static final Integer INACTIVE = 0;
  }
}

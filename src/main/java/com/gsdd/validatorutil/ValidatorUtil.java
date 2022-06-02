package com.gsdd.validatorutil;

import com.gsdd.constants.GralConstants;
import com.gsdd.constants.NumericConstants;
import com.gsdd.constants.RegexConstants;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ValidatorUtil {

  public static final Pattern NUMBER_PATTERN = Pattern.compile(RegexConstants.NUMBER);
  public static final Pattern DECIMAL_PATTERN = Pattern.compile(RegexConstants.DECIMAL);

  public static boolean isNullOrEmpty(final Object obj) {
    boolean result = false;
    if (obj == null) {
      result = true;
    } else if (obj instanceof String data) {
      result = GralConstants.EMPTY.equals(data.trim());
    } else if (obj instanceof List<?> dataList) {
      result = dataList.isEmpty();
    } else if (obj instanceof Map<?, ?> dataMap) {
      result = dataMap.isEmpty();
    } else {
      result = isArrayEmpty(obj);
    }
    return result;
  }

  private static boolean isArrayEmpty(final Object o) {
    // data types boolean[], byte[], short[], char[], int[], long[], float[],
    // double[], Object[]
    Boolean result = false;
    if (o instanceof Object[] array) {
      result = array.length == NumericConstants.ZERO;
    } else if (o instanceof byte[] byteArray) {
      result = byteArray.length == NumericConstants.ZERO;
    }
    return result;
  }

  public static boolean isDecimal(final String str) {
    return Optional.ofNullable(str).map(s -> DECIMAL_PATTERN.matcher(s).matches()).orElse(false);
  }

  public static boolean isInteger(final String str) {
    return Optional.ofNullable(str).map(s -> NUMBER_PATTERN.matcher(s).matches()).orElse(false);
  }

  public static boolean multiAnd(boolean... operands) {
    Boolean resultAnd = true;
    if (!ValidatorUtil.isNullOrEmpty(operands)) {
      for (boolean oper : operands) {
        if (!oper) {
          resultAnd = oper;
          break;
        }
      }
    }
    return resultAnd;
  }

  public static boolean multiOr(boolean... operands) {
    Boolean resultOr = false;
    if (!ValidatorUtil.isNullOrEmpty(operands)) {
      for (boolean oper : operands) {
        if (oper) {
          resultOr = oper;
          break;
        }
      }
    }
    return resultOr;
  }
}

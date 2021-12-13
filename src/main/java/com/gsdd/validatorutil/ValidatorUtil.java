package com.gsdd.validatorutil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.gsdd.constants.GralConstants;
import com.gsdd.constants.NumericConstants;
import com.gsdd.constants.RegexConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidatorUtil {

  public static boolean isNullOrEmpty(final Object obj) {
    boolean result = false;
    if (obj == null) {
      result = true;
    } else if (obj instanceof String) {
      result = GralConstants.EMPTY.equals(((String) obj).trim());
    } else if (obj instanceof List<?>) {
      result = ((List<?>) obj).isEmpty();
    } else if (obj instanceof Map<?, ?>) {
      result = ((Map<?, ?>) obj).isEmpty();
    } else {
      result = isArrayEmpty(obj);
    }
    return result;
  }

  private static boolean isArrayEmpty(final Object o) {
    // data types boolean[], byte[], short[], char[], int[], long[], float[],
    // double[], Object[]
    Boolean result = false;
    if (o instanceof Object[]) {
      Object[] array = (Object[]) o;
      result = array.length == NumericConstants.ZERO;
    } else if (o instanceof byte[]) {
      byte[] array = (byte[]) o;
      result = array.length == NumericConstants.ZERO;
    }
    return result;
  }

  public static boolean isDecimal(final String str) {
    return Optional.ofNullable(str).map(s -> s.matches(RegexConstants.DECIMAL)).orElse(false);
  }

  public static boolean isInteger(final String str) {
    return Optional.ofNullable(str).map(s -> s.matches(RegexConstants.NUMBER)).orElse(false);
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

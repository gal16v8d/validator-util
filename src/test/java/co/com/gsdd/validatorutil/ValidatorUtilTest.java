package co.com.gsdd.validatorutil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import co.com.gsdd.constants.GralConstants;

public class ValidatorUtilTest {

    private static final String A = "a";

    @DisplayName("isNullOrEmpty true tests")
    @ParameterizedTest(name = "Using input = {0} should be true")
    @MethodSource("getNullOrEmptyTrueInputs")
    public void isNullOrEmptyTrueTest(Object input) {
        Assertions.assertTrue(ValidatorUtil.isNullOrEmpty(input));
    }

    private static Stream<Arguments> getNullOrEmptyTrueInputs() {
        return Stream.of(null, Arguments.of(GralConstants.EMPTY), Arguments.of(" "),
                Arguments.of(Collections.emptyList()), Arguments.of(Collections.emptyMap()), Arguments.of(new byte[0]));
    }

    @DisplayName("isNullOrEmpty with Object array true tests")
    @Test
    public void isNullOrEmptyObjectArrayTrueTest() {
        Assertions.assertTrue(ValidatorUtil.isNullOrEmpty(new Object[0]));
    }

    @DisplayName("isNullOrEmpty true tests")
    @ParameterizedTest(name = "Using input = {0} should be false")
    @MethodSource("getNullOrEmptyFalseInputs")
    public void isNullOrEmptyFalseTest(Object input) {
        Assertions.assertFalse(ValidatorUtil.isNullOrEmpty(input));
    }

    private static Stream<Arguments> getNullOrEmptyFalseInputs() {
        List<String> ls = Arrays.asList(A);
        Map<String, String> map = Map.of(A, A);
        byte[] byteArr = { (byte) 0xe0 };
        return Stream.of(Arguments.of(GralConstants.DOT), Arguments.of(" a"), Arguments.of(ls), Arguments.of(map),
                Arguments.of(byteArr), Arguments.of(new int[0]));
    }

    @DisplayName("isNullOrEmpty with Object array false tests")
    @Test
    public void isNullOrEmptyObjectArrayFalseTest() {
        Object[] objArr = { A };
        Assertions.assertFalse(ValidatorUtil.isNullOrEmpty(objArr));
    }

    @DisplayName("isDecimal true tests")
    @ParameterizedTest(name = "Using input = {0} should be true")
    @ValueSource(strings = { "1", "1.9" })
    public void isDecimalTrueTest(String input) {
        Assertions.assertTrue(ValidatorUtil.isDecimal(input));
    }

    @DisplayName("isDecimal false tests")
    @ParameterizedTest(name = "Using input = {0} should be false")
    @NullAndEmptySource
    @ValueSource(strings = { "1.", "1,", "l" })
    public void isDecimalFalseTest(String input) {
        Assertions.assertFalse(ValidatorUtil.isDecimal(input));
    }

    @DisplayName("isInteger true tests")
    @ParameterizedTest(name = "Using input = {0} should be true")
    @ValueSource(strings = { "1", "2", "3" })
    public void isIntegerTrueTest(String input) {
        Assertions.assertTrue(ValidatorUtil.isInteger(input));
    }

    @DisplayName("isInteger false tests")
    @ParameterizedTest(name = "Using input = {0} should be false")
    @NullAndEmptySource
    @ValueSource(strings = { "1.5", "2d", "l", "1." })
    public void isIntegerFalseTest(String input) {
        Assertions.assertFalse(ValidatorUtil.isInteger(input));
    }

    @DisplayName("multiAnd false tests")
    @ParameterizedTest(name = "Using input = {0} should be false")
    @MethodSource("getMultiAndFalseInputs")
    public void multiAndFalseTest(boolean[] input) {
        Assertions.assertFalse(ValidatorUtil.multiAnd(input));
    }

    private static Stream<Arguments> getMultiAndFalseInputs() {
        boolean[] a1 = { false, true, true };
        boolean[] a2 = { true, false, true };
        boolean[] a3 = { true, true, false };
        return Stream.of(Arguments.of(a1), Arguments.of(a2), Arguments.of(a3));
    }

    @DisplayName("multiAnd true tests")
    @ParameterizedTest(name = "Using input = {0} should be true")
    @MethodSource("getMultiAndTrueInputs")
    public void multiAndTrueTest(boolean[] input) {
        Assertions.assertTrue(ValidatorUtil.multiAnd(input));
    }

    private static Stream<Arguments> getMultiAndTrueInputs() {
        boolean[] a1 = { true, true, true };
        return Stream.of(null, Arguments.of(new boolean[0]), Arguments.of(a1));
    }

    @DisplayName("multiOr false tests")
    @ParameterizedTest(name = "Using input = {0} should be false")
    @MethodSource("getMultiOrFalseInputs")
    public void multiOrFalseTest(boolean[] input) {
        Assertions.assertFalse(ValidatorUtil.multiOr(input));
    }

    private static Stream<Arguments> getMultiOrFalseInputs() {
        boolean[] a1 = { false, false, false };
        return Stream.of(null, Arguments.of(new boolean[0]), Arguments.of(a1));
    }

    @DisplayName("multiOr true tests")
    @ParameterizedTest(name = "Using input = {0} should be true")
    @MethodSource("getMultiOrTrueInputs")
    public void multiOrTrueTest(boolean[] input) {
        Assertions.assertTrue(ValidatorUtil.multiOr(input));
    }

    private static Stream<Arguments> getMultiOrTrueInputs() {
        boolean[] a1 = { false, true, true };
        boolean[] a2 = { true, false, true };
        boolean[] a3 = { true, true, false };
        return Stream.of(Arguments.of(a1), Arguments.of(a2), Arguments.of(a3));
    }

}

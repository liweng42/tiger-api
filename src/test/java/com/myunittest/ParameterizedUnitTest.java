package com.myunittest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @ClassName ParameterizedUnitTest
 * @Description
 * @Author jerry
 * @Since 2022/2/9
 */
public class ParameterizedUnitTest {
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 9})
    void testNumberShouldBeEven(int num) {
        Assertions.assertEquals(0, num % 2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Effective Java", "Code Complete", "Clean Code"})
    void testPrintTitle(String title) {
        System.out.println(title);
    }

    @ParameterizedTest
    @CsvSource({"1,One", "2,Two", "3,Three"})
    void testDataFromCsv(long id, String name) {
        System.out.printf("id: %d, name: %s", id, name);
    }
}

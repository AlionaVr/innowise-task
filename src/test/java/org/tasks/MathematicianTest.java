package org.tasks;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathematicianTest {
    public static Stream<Arguments> testArguments() {
        return Stream.of(
                Arguments.of(4, 1),
                Arguments.of(1, 0),
                Arguments.of(5748, 6),
                Arguments.of(65, 3),
                Arguments.of(1586, 5));
    }


    @ParameterizedTest
    @MethodSource({"testArguments"})
    void givenNumber_whenCallFindNumber_thenReturnCorrectAnswer(int m, int expected) {
        //arrange
        Mathematician mathematician = new Mathematician();
        //act and assert
        assertEquals(expected, mathematician.findNumber(m));
    }
}
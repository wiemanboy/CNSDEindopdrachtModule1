package com.wiemanboy.board.domain;

import com.wiemanboy.board.domain.exceptions.InvalidHexCodeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {

    @Test
    @Description("Test if a tag can be created with a valid color")
    void testColorValid() {
        assertDoesNotThrow(() -> new Tag("tag", "#FFFFFF"));
    }

    @ParameterizedTest
    @CsvSource({
            "#FFFFF",
            "#FFFFFZ",
            "FFFFF",
            "FFFFFF",
    })
    @Description("Test if a tag can be created with an invalid color")
    void testColorInvalid(String color) {
        assertThrows(InvalidHexCodeException.class, () -> new Tag("tag", color));
    }
}
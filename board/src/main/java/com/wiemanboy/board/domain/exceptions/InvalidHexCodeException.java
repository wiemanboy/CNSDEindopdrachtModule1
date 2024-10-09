package com.wiemanboy.board.domain.exceptions;

import lombok.Getter;

@Getter
public class InvalidHexCodeException extends RuntimeException {
    private final String hexCode;

    public InvalidHexCodeException(String hexCode) {
        super(String.format("Invalid hex code: %s", hexCode));
        this.hexCode = hexCode;
    }
}

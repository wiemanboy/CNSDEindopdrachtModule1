package com.wiemanboy.board.domain;

import com.wiemanboy.board.domain.exceptions.InvalidHexCodeException;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.util.regex.Pattern;

@Entity
@Getter
public class Tag extends DatabaseObject {
    private String name;
    private String color;

    public Tag(String name, String color) {
        this.name = name;
        setColor(color);
    }

    protected Tag() {
    }

    public void setColor(String color) {
        if (!Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$").matcher(color).matches()) {
            throw new InvalidHexCodeException(color);
        }
        this.color = color;
    }
}

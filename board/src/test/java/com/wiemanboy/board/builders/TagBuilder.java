package com.wiemanboy.board.builders;

import com.wiemanboy.board.domain.Tag;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class TagBuilder {
    private String name = "Tag Name";
    private String color = "#FF0000";

    public Tag build() {
        return new Tag(name, color);
    }
}

package com.wiemanboy.board.builders;

import com.wiemanboy.board.domain.Board;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class BoardBuilder {
    private String title = "Board Title";

    public Board build() {
        return new Board(title);
    }
}

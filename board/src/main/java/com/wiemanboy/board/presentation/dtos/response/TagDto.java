package com.wiemanboy.board.presentation.dtos.response;

import com.wiemanboy.board.domain.Tag;

import java.util.List;
import java.util.UUID;

public record TagDto(
        UUID id,
        String name,
        String color
) {
    public static TagDto from(Tag tag) {
        return new TagDto(
                tag.getId(),
                tag.getName(),
                tag.getColor()
        );
    }

    public static List<TagDto> from(List<Tag> tags) {
        return tags.stream()
                .map(TagDto::from)
                .toList();
    }
}

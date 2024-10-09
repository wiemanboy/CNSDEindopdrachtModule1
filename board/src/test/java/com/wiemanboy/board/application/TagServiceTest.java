package com.wiemanboy.board.application;

import com.wiemanboy.board.data.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.*;
class TagServiceTest {

    private TagService tagService;
    private TagRepository tagRepository;

    @BeforeEach
    void setUp() {
        tagRepository = Mockito.mock(TagRepository.class);
        tagService = new TagService(tagRepository);
    }

    @Test
    @Description("Test if the tag is created")
    void createTag() {
        String name = "name";
        String color = "#FFFFFF";

        tagService.createTag(name, color);

        Mockito.verify(tagRepository).save(Mockito.any());
    }
}
package com.wiemanboy.board.application;

import com.wiemanboy.board.builders.TagBuilder;
import com.wiemanboy.board.data.TagRepository;
import com.wiemanboy.board.domain.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Description;

import java.util.List;

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

    @Test
    @Description("Test if all tags are retrieved")
    void getAllTags() {
        List<Tag> tags = List.of(new TagBuilder().build(), new TagBuilder().build());
        Mockito.when(tagRepository.findAll()).thenReturn(tags);

        assertEquals(tags, tagService.getAllTags());
        Mockito.verify(tagRepository).findAll();
    }

    @Test
    @Description("Test if a tag is retrieved by its id")
    void getTagById() {
        Tag tag = new TagBuilder().build();
        Mockito.when(tagRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(tag));

        assertEquals(tag, tagService.getTagById(tag.getId()));
        Mockito.verify(tagRepository).findById(Mockito.any());
    }
}
package com.wiemanboy.board.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wiemanboy.board.builders.TagBuilder;
import com.wiemanboy.board.data.TagRepository;
import com.wiemanboy.board.domain.Tag;
import com.wiemanboy.board.presentation.dtos.request.CreateTagDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TagControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        tagRepository.deleteAll();
    }

    @Test
    void getTags() throws Exception {
        tagRepository.save(new TagBuilder().build());
        tagRepository.save(new TagBuilder().build());

        mockMvc.perform(get("/api/tags/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getTag() throws Exception {
        Tag tag = tagRepository.save(new TagBuilder().build());

        mockMvc.perform(get("/api/tags/" + tag.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(tag.getId().toString()));
    }

    @Test
    void createTag() throws Exception {
        CreateTagDto createTagDto = new CreateTagDto("tag", "#FFFFFF");

        mockMvc.perform(post("/api/tags/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createTagDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void createTagInvalidColor() throws Exception {
        CreateTagDto createTagDto = new CreateTagDto("tag", "color");

        mockMvc.perform(post("/api/tags/")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createTagDto)))
                .andExpect(status().isBadRequest());
    }
}
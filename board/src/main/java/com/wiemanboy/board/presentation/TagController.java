package com.wiemanboy.board.presentation;

import com.wiemanboy.board.application.TagService;
import com.wiemanboy.board.presentation.dtos.request.CreateTagDto;
import com.wiemanboy.board.presentation.dtos.response.TagDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/")
    public List<TagDto> getTags() {
        return TagDto.from(tagService.getAllTags());
    }

    @GetMapping("/{id}")
    public TagDto getTag(@PathVariable UUID id) {
        return TagDto.from(tagService.getTagById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto createTag(@RequestBody CreateTagDto createTagDto) {
        return TagDto.from(tagService.createTag(createTagDto.name(), createTagDto.color()));
    }
}

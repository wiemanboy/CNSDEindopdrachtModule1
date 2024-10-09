package com.wiemanboy.board.application;

import com.wiemanboy.board.data.TagRepository;
import com.wiemanboy.board.domain.Tag;
import com.wiemanboy.board.domain.exceptions.TagNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(String name, String color) {
        return tagRepository.save(new Tag(name, color));
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(UUID tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> new TagNotFoundException(tagId));
    }
}

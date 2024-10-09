package com.wiemanboy.board.application;

import com.wiemanboy.board.data.TagRepository;
import com.wiemanboy.board.domain.Tag;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
}

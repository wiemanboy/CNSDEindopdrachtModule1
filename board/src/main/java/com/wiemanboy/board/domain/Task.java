package com.wiemanboy.board.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Task extends DatabaseObject {
    private final String title;
    private final String description;
    private final List<UUID> collaboratorIds = new ArrayList<>();
    private final List<Tag> tags = new ArrayList<>();

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void addCollaborator(UUID collaboratorId) {
        collaboratorIds.add(collaboratorId);
    }
}

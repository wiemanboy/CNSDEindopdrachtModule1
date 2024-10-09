package com.wiemanboy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class Task extends DatabaseObject {
    private String title;
    private String description;
    @ElementCollection
    private final List<UUID> collaboratorIds = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<Tag> tags = new ArrayList<>();

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    protected Task() {
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void addCollaborator(UUID collaboratorId) {
        collaboratorIds.add(collaboratorId);
    }
}

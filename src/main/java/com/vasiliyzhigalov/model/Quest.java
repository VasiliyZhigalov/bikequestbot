package com.vasiliyzhigalov.model;

import lombok.Data;

import java.util.List;

@Data
public class Quest {
    private Long id;
    private String name;
    private String photo;
    private String description;
    private User creator;
    private List<QuestStage> questStages;
    private boolean editable;
}

package com.vasiliyzhigalov.model;

import lombok.Data;

@Data
public class QuestStage {
    private Long id;
    private Float longitude;
    private Float latitude;
    private String description;
    private String photo;
    private QuestStage nextStage;
    private Quest quest;
}

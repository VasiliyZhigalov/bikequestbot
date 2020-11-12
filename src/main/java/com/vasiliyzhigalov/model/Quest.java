package com.vasiliyzhigalov.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "quests")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questId;
    private String name;
    private String description;
    private MyLocation location;
    @OneToMany
    private List<QuestStage> questStages;
}

package com.vasiliyzhigalov.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private MyLocation location;
    @OneToMany
    private List<QuestStage> questStages;

}

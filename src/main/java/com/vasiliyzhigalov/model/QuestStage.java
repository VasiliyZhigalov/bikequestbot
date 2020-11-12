package com.vasiliyzhigalov.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class QuestStage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private MyLocation location;
    private String description;
    @OneToOne
    private QuestStage nextStage;
    @OneToOne
    private Quest quest;
}

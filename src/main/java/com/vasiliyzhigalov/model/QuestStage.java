package com.vasiliyzhigalov.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "quest_stages")
public class QuestStage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private MyLocation location;
    private String description;
    @OneToOne
    private QuestStage nextStage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "id")
    private Quest quest;
}

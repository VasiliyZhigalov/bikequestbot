package com.vasiliyzhigalov.model;

import com.vasiliyzhigalov.statemachine.states.States;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User user;
    private States botState;
    @OneToOne
    private Quest currentEditableQuest;
    @OneToOne
    private QuestStage currentEditableStage;
    @OneToOne
    private QuestStage currentPassStage;

}
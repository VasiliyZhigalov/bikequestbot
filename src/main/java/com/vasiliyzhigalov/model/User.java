package com.vasiliyzhigalov.model;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private Long chatId;
    private String username;
    private String firstName;
    private String lastName;
    private Team team;
    private QuestStage passStage;
    private QuestStage editableStage;
}

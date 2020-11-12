CREATE TABLE IF NOT EXISTS quests(
    quest_id int PRIMARY KEY,
    quest_name varchar(250),
    creator_id int,
    location_id int
)
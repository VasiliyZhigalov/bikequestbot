CREATE TABLE users IF NOT EXISTS (
    id int PRIMARY KEY,
    user_id integer,
    user_name varchar(250),
    first_name varchar(250),
    last_name varchar(250)
)
--------------------------------------------------
---- DO NOT TOUCH
---- I've provided these tables for you.
---- You will need to use them, but you should not edit them.
---- There is space below for you to add your SQL.

CREATE TABLE IF NOT EXISTS users (
    username TEXT PRIMARY KEY CHECK (username <> ''),
    password TEXT NOT NULL
);


CREATE TABLE IF NOT EXISTS authorities (
    username TEXT NOT NULL REFERENCES users(username),
    authority TEXT NOT NULL,
    UNIQUE (username, authority)
);

CREATE INDEX ON authorities (username);

CREATE TABLE IF NOT EXISTS groups (
    id SERIAL PRIMARY KEY,
    group_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS group_members (
    username TEXT NOT NULL PRIMARY KEY REFERENCES users(username) CHECK (username <> ''),
    group_id INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS group_authorities (
    group_id INTEGER PRIMARY KEY NOT NULL,
    authority TEXT NOT NULL CHECK (authority <> '')
);

-----------------------------------------------------
---- Put your tables below

CREATE TABLE IF NOT EXISTS reflections (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL UNIQUE,
    questions TEXT NULL
);

CREATE TABLE IF NOT EXISTS questions (
    id SERIAL PRIMARY KEY,
    prompt TEXT NOT NULL,
    reflectionId INTEGER NOT NULL REFERENCES reflections(id)
);

CREATE TABLE IF NOT EXISTS responses (
    id SERIAL PRIMARY KEY,
    reflectionId INTEGER NOT NULL REFERENCES reflections(id),
    userName TEXT NOT NULL,
    answers TEXT NULL
);

CREATE TABLE IF NOT EXISTS answers (
  id SERIAL PRIMARY KEY,
  responseId INTEGER NOT NULL REFERENCES responses(id),
  questionId INTEGER NOT NULL REFERENCES questions(id),
  content TEXT NULL
);
DROP TABLE IF EXISTS actors;

CREATE TABLE actors (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);
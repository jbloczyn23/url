CREATE TABLE IF NOT EXISTS url(id serial PRIMARY KEY, shorturl VARCHAR(255), longurl VARCHAR(255));
CREATE TABLE IF NOT EXISTS access(id serial PRIMARY KEY, shorturl VARCHAR(255), accesstimestamp TIMESTAMP);
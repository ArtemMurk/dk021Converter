DROP TABLE DK_021;
CREATE TABLE DK_021
(
	id INTEGER NOT NULL,
	num SMALLINT NOT NULL,
	parentId INTEGER,
	name VARCHAR(100) NOT NULL,
	constraint code PRIMARY KEY(id,num),
	constraint uniqueId UNIQUE(id)
)
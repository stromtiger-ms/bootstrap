DROP TABLE IF EXISTS stromlastdaten, verbraucher;

CREATE TABLE "verbraucher"
(
    verbraucherId SERIAL NOT NULL PRIMARY KEY,
    name          VARCHAR
);

CREATE TABLE "stromlastdaten"
(
    stromlastdatenId SERIAL NOT NULL PRIMARY KEY,
    zeit             VARCHAR,
    kw               decimal,
    status           varchar,
    verbraucherId    int,
    isPrediction     boolean,
    FOREIGN KEY (verbraucherId) REFERENCES verbraucher (verbraucherId)
);
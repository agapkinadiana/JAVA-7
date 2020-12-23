CREATE DATABASE IF NOT EXISTS java_lab18;


CREATE TABLE IF NOT EXISTS java_lab18.references
(
    id          INT          NOT NULL AUTO_INCREMENT,
    url         VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    minus       INT          NULL,
    plus        INT          NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE
);

CREATE TABLE IF NOT EXISTS java_lab18.comments
(
    id           INT          NOT NULL AUTO_INCREMENT,
    reference_id INT          NOT NULL,
    session_id   VARCHAR(255) NULL,
    stamp        DATE         NULL,
    comment      VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE
);

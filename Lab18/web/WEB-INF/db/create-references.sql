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

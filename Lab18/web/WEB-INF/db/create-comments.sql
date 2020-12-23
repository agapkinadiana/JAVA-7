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

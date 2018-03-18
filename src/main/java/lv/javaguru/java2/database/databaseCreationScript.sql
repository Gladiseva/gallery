CREATE SEQUENCE gallery_seq;

CREATE SEQUENCE image_seq;

CREATE TABLE IF NOT EXISTS gallery
(
  id          BIGINT DEFAULT nextval('gallery_seq' :: REGCLASS) NOT NULL
    CONSTRAINT gallety_pkey
    PRIMARY KEY,
  title       VARCHAR(50)                                       NOT NULL,
  description VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS image
(
  id          BIGINT DEFAULT nextval('image_seq' :: REGCLASS) NOT NULL
    CONSTRAINT image_pkey
    PRIMARY KEY,
  title       VARCHAR(50)                                     NOT NULL,
  description VARCHAR(100),
  gallery_id  BIGINT
    CONSTRAINT image_fk_gallery
    REFERENCES gallery
    ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS fki_image_fk_gallery
  ON image (gallery_id);
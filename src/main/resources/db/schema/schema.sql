CREATE SCHEMA IF NOT EXISTS schoolpaz;
CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS schoolpaz.event_publication
(
    id               UUID NOT NULL ,
    listener_id      TEXT NOT NULL,
    event_type       TEXT NOT NULL,
    serialized_event TEXT NOT NULL,
    publication_date TIMESTAMP WITH TIME ZONE NOT NULL,
    completion_date  TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (id)
);
CREATE INDEX IF NOT EXISTS event_publication_serialized_event_hash_idx ON schoolpaz.event_publication USING hash(serialized_event);
CREATE INDEX IF NOT EXISTS event_publication_by_completion_date_idx ON schoolpaz.event_publication (completion_date);


alter table schoolpaz.event_publication
    owner to schoolpaz;


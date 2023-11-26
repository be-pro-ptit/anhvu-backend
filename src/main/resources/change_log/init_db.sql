CREATE DATABASE social_media
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    TEMPLATE template0;


CREATE TABLE IF NOT EXISTS public.users (
    user_id integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1),
    user_name varchar(50) NOT NULL UNIQUE,
    full_name varchar NOT NULL,
    date_of_birth date,
    phone_number varchar(10),
    email varchar NOT NULL,
    avatar varchar,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
    );

CREATE INDEX users_index_user_id ON public.users (user_id)
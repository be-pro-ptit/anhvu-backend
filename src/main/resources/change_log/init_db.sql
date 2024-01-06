CREATE
    DATABASE social_media
    WITH
    OWNER = postgres
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    TEMPLATE template0;


CREATE TABLE IF NOT EXISTS public.users
(
    user_id       integer     NOT NULL GENERATED ALWAYS AS IDENTITY
        (
        INCREMENT 1 START 1
        ),
    user_name     varchar(50) NOT NULL UNIQUE,
    full_name     varchar     NOT NULL,
    date_of_birth date,
    phone_number  varchar(10),
    email         varchar     NOT NULL,
    avatar        varchar,
    CONSTRAINT users_pkey PRIMARY KEY
        (
         user_id
            )
);

CREATE INDEX IF NOT EXISTS users_index_user_id ON public.users (user_id);

CREATE TABLE IF NOT EXISTS public.accounts
(
    user_name varchar(50) UNIQUE,
    password  varchar(50),
    user_id   integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1),
    CONSTRAINT accounts_pkey PRIMARY KEY (user_id)
);

ALTER TABLE public.accounts
    ALTER COLUMN password SET NOT NULL;

ALTER TABLE public.accounts
    ALTER COLUMN password TYPE varchar;

ALTER TABLE public.accounts
    ALTER COLUMN user_id DROP IDENTITY;

ALTER TABLE public.accounts
    ADD CONSTRAINT accounts_fkey FOREIGN KEY (user_id) REFERENCES public.users (user_id);

CREATE TABLE IF NOT EXISTS public.posts
(
    user_id     INTEGER      NOT NULL,
    content     VARCHAR(255) NOT NULL,
    id          INTEGER      NOT NULL GENERATED ALWAYS AS identity,
    "timestamp" TIMESTAMP    NOT NULL,
    CONSTRAINT post_PK PRIMARY KEY (id),
    CONSTRAINT post_FK FOREIGN KEY (user_id) REFERENCES public.users (user_id)
);

CREATE TABLE IF NOT EXISTS public.friendships
(
    id          INTEGER   NOT NULL GENERATED ALWAYS AS identity,
    user_id     INTEGER   NOT NULL,
    other_id    INTEGER   NOT NULL,
    "timestamp" TIMESTAMP NOT NULL,
    CONSTRAINT friendships_PK PRIMARY KEY (id),
    CONSTRAINT friendships_FK FOREIGN KEY (user_id) REFERENCES public.users (user_id)
);

ALTER TABLE public.friendships
    RENAME TO friend_requests;

CREATE TABLE IF NOT EXISTS public.friendships
(
    id         INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    other_id_1 INTEGER NOT NULL,
    other_id_2   INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (other_id_1) REFERENCES users (user_id),
    FOREIGN KEY (other_id_2) REFERENCES users (user_id)
);

ALTER TABLE public.friendships ADD COLUMN IF NOT EXISTS "timestamp" TIMESTAMP NOT NULL DEFAULT NOW();
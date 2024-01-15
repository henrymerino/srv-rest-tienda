-- Database: store

-- DROP DATABASE store;

CREATE DATABASE store
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Mexico.1252'
    LC_CTYPE = 'Spanish_Mexico.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE store
    IS 'tienda online';
	
-- Table: public.client

-- DROP TABLE public.client;

CREATE TABLE IF NOT EXISTS public.client
(
    id integer NOT NULL DEFAULT nextval('client_id_seq'::regclass),
    date_creation timestamp(6) without time zone,
    address character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    full_name character varying(255) COLLATE pg_catalog."default",
    identification character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT client_pkey PRIMARY KEY (id),
    CONSTRAINT client_identification_key UNIQUE (identification)
)

TABLESPACE pg_default;

ALTER TABLE public.client
    OWNER to postgres;
	
-- Table: public.client_order

-- DROP TABLE public.client_order;

CREATE TABLE IF NOT EXISTS public.client_order
(
    client_id integer NOT NULL,
    id integer NOT NULL DEFAULT nextval('client_order_id_seq'::regclass),
    orden_id integer NOT NULL,
    date_client_order timestamp(6) without time zone,
    CONSTRAINT client_order_pkey PRIMARY KEY (id),
    CONSTRAINT fkcggjtc9f3otuprl7takbttwuk FOREIGN KEY (client_id)
        REFERENCES public.client (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkqtyvh5946d9sjytqervefhrs2 FOREIGN KEY (orden_id)
        REFERENCES public.orden (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.client_order
    OWNER to postgres;
	
-- Table: public.detail_order

-- DROP TABLE public.detail_order;

CREATE TABLE IF NOT EXISTS public.detail_order
(
    amount real NOT NULL,
    id integer NOT NULL DEFAULT nextval('detail_order_id_seq'::regclass),
    orden_id integer NOT NULL,
    product_id integer NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT detail_order_pkey PRIMARY KEY (id),
    CONSTRAINT fkorj2bghq41j5l5lswqplnj1r4 FOREIGN KEY (orden_id)
        REFERENCES public.orden (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkpa5tj9byco7b9xq7cqtvuar3h FOREIGN KEY (product_id)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.detail_order
    OWNER to postgres;

-- Table: public.orden

-- DROP TABLE public.orden;

CREATE TABLE IF NOT EXISTS public.orden
(
    id integer NOT NULL DEFAULT nextval('orden_id_seq'::regclass),
    date_order timestamp(6) without time zone,
    state character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT orden_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.orden
    OWNER to postgres;
	
-- Table: public.product

-- DROP TABLE public.product;

CREATE TABLE IF NOT EXISTS public.product
(
    id integer NOT NULL DEFAULT nextval('product_id_seq'::regclass),
    price real,
    store_id integer NOT NULL,
    amount character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT product_pkey PRIMARY KEY (id),
    CONSTRAINT product_name_key UNIQUE (name),
    CONSTRAINT fkjlfidudl1gwqem0flrlomvlcl FOREIGN KEY (store_id)
        REFERENCES public.store (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.product
    OWNER to postgres;
	
-- Table: public.store

-- DROP TABLE public.store;

CREATE TABLE IF NOT EXISTS public.store
(
    id integer NOT NULL DEFAULT nextval('store_id_seq'::regclass),
    date_creation timestamp(6) without time zone,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT store_pkey PRIMARY KEY (id),
    CONSTRAINT store_name_key UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE public.store
    OWNER to postgres;
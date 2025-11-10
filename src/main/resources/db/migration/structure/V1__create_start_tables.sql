create table roles (
    id bigserial,
    name varchar(50),
    primary key (id)
);

create table users (
    id bigserial,
    username varchar(50),
    password varchar(256),
    role_id bigint references roles (id),
    primary key (id)
);

create table brands (
    id bigserial,
    name varchar(30),
    primary key (id)
);

create table tobaccos (
    id bigserial,
    name varchar(50),
    description varchar(100),
    brand_id bigserial references brands (id) on delete cascade,
    strength int,
    base_fl varchar(1) default 'N'
);

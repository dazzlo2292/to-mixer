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
    delete_fl varchar(1) default 'N',
    primary key (id)
);

create table tobaccos (
    id bigserial,
    name varchar(50),
    description varchar(100),
    brand_id bigserial references brands (id) on delete cascade,
    strength int,
    base_fl varchar(1) default 'N',
    delete_fl varchar(1) default 'N',
    primary key (id)
);

create table mixes (
    id bigserial,
    name varchar(100),
    delete_fl varchar(1) default 'N',
    day_fl varchar(1) default 'N',
    likes int,
    dislikes int,
    primary key (id)
);

create table mixes_tobaccos (
    mix_id bigserial references mixes (id),
    tobacco_id bigserial references tobaccos (id),
    primary key (mix_id, tobacco_id)
);

create table coupons (
    id bigserial,
    phone varchar(11),
    code varchar(6),
    status varchar(10),
    bonus varchar(20),
    created_at timestamp default CURRENT_TIMESTAMP,
    expiration_at timestamp,
    primary key (id)
);

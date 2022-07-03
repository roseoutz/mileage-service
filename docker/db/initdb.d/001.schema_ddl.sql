create table triple_mileage_history
(
    oid         varchar(36) not null primary key,
    action_type varchar(10) not null,
    has_bonus   varchar(1)  null,
    has_image   varchar(1)  null,
    has_text    varchar(1)  null,
    insert_time bigint      null,
    place_id    varchar(36) not null,
    review_id   varchar(36) not null,
    update_time bigint      null,
    user_id     varchar(36) null
);

create table triple_mileage_review
(
    place_id    varchar(36) not null,
    user_id     varchar(36) not null,
    has_bonus   varchar(1)  null,
    has_image   varchar(1)  null,
    has_text    varchar(1)  null,
    insert_time bigint      null,
    review_id   varchar(36) not null,
    update_time bigint      null,
    primary key (place_id, user_id)
);

create index idx_review_pk
    on triple_mileage_review (place_id, user_id);

create index idx_review_place_id
    on triple_mileage_review (place_id);

create table triple_mileage_user_info
(
    user_id varchar(36) not null primary key,
    point   bigint      not null
);

create index idx_user_pk
    on triple_mileage_user_info (user_id);

create table triple_mileage_config
(
    config_key   varchar(32) not null primary key,
    config_value varchar(32) not null ,
    insert_time  bigint      null,
    update_time  bigint      null
);

create index idx_config_key
    on triple_mileage_config (config_key);

insert into triple_mileage_config values ('review.point.text', '1', 1656855363000, 1656855363000);
insert into triple_mileage_config values ('review.point.image', '1', 1656855363000, 1656855363000);
insert into triple_mileage_config values ('review.point.bonus', '1', 1656855363000, 1656855363000);


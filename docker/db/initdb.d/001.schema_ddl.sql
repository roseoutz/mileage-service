create table if not exists triple_mileage_config
(
    config_key   varchar(32) not null
    primary key,
    config_value varchar(32) null,
    insert_time  bigint      null default 0,
    update_time  bigint      null default 0
    );

create index idx_config_key
    on triple_mileage_config (config_key);

create table if not exists triple_mileage_history
(
    oid          varchar(36) not null
    primary key,
    action_type  varchar(10) not null,
    bonus_point  bigint      null default 0,
    earned_point bigint      null default 0,
    image_point  bigint      null default 0,
    insert_time  bigint      null default 0,
    place_id     varchar(36) not null,
    review_id    varchar(36) not null,
    text_point   bigint      null default 0,
    update_time  bigint      null default 0,
    user_id      varchar(36) not null
    );

create index idx_history_userId
    on triple_mileage_history (user_id);

create table if not exists triple_mileage_review
(
    place_id    varchar(36) not null,
    user_id     varchar(36) not null,
    bonus       varchar(1)  null default 'N',
    image       varchar(1)  null default 'N',
    insert_time bigint      null default 0,
    review_id   varchar(36) not null,
    text        varchar(1)  null default 'N',
    update_time bigint      null default 0,
    primary key (place_id, user_id)
    );

create index idx_review_pk
    on triple_mileage_review (place_id, user_id);

create index idx_review_place_id
    on triple_mileage_review (place_id);

create table if not exists triple_mileage_user_info
(
    user_id varchar(36) not null primary key,
    point   bigint      not null default 0
    );

create index idx_user_pk
    on triple_mileage_user_info (user_id);



insert into triple_mileage_config values ('review.point.text', '1', 1656855363000, 1656855363000);
insert into triple_mileage_config values ('review.point.image', '1', 1656855363000, 1656855363000);
insert into triple_mileage_config values ('review.point.bonus', '1', 1656855363000, 1656855363000);



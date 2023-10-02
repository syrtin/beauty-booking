create table specialist
(
    id    bigserial primary key,
    name  varchar(255) not null,
    phone varchar(255) not null
);

create table procedure
(
    id       bigserial primary key,
    name     varchar(255) not null,
    duration int          not null,
    cost     bigint       not null
);

create table procedure_specialist
(
    id            bigserial primary key,
    procedure_id  bigint,
    specialist_id bigint,
    unique (procedure_id, specialist_id),
    foreign key (procedure_id) references procedure (id),
    foreign key (specialist_id) references specialist (id)
);

create table day_off
(
    id            bigserial primary key,
    day_off_date  date         not null,
    reason        varchar(255) not null,
    specialist_id bigint,
    unique (day_off_date, specialist_id),
    foreign key (specialist_id) references specialist (id)
);

create table client
(
    id    bigserial primary key,
    name  varchar(255) not null,
    phone varchar(255) not null
);

create table reservation
(
    id               bigserial primary key,
    reservation_time timestamp not null,
    client_id        bigint,
    specialist_id    bigint,
    procedure_id     bigint,
    foreign key (client_id) references client (id),
    foreign key (specialist_id) references specialist (id),
    foreign key (procedure_id) references procedure (id)
);

alter table specialist
    add constraint specialist_name_uk unique (name);
alter table procedure
    add constraint procedure_name_duration_uk unique (name, duration);
alter table client
    add constraint client_phone_uk unique (phone);

alter table reservation
    add constraint reservations_fk_client foreign key (client_id) references client (id);
alter table reservation
    add constraint reservations_fk_specialist foreign key (specialist_id) references specialist (id);
alter table reservation
    add constraint reservations_fk_procedure foreign key (procedure_id) references procedure (id);

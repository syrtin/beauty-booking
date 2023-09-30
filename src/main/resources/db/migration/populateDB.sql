-- Populating data for specialists table
insert into specialist (id, name, phone)
values
    (nextval('specialist_id_seq'), 'specialist 1', '+79116000000'),
    (nextval('specialist_id_seq'), 'specialist 2', '9216000000'),
    (nextval('specialist_id_seq'), 'specialist 3', '89316000000');

-- Populating data for procedures table
insert into procedure (id, name, duration, cost)
values
    (nextval('procedure_id_seq'), 'procedure 1', 60, 10000),
    (nextval('procedure_id_seq'), 'procedure 2', 90, 15000),
    (nextval('procedure_id_seq'), 'procedure 3', 120, 20000);

-- Populating data for specialists_procedures table
insert into procedure_specialist (procedure_id, specialist_id)
values
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 1),
    (3, 2),
    (3, 3);

-- Populating data for clients table
insert into client (id, name, phone)
values
    (nextval('client_id_seq'), 'client 1', '+79082950000'),
    (nextval('client_id_seq'), 'client 2', '+79512950000'),
    (nextval('client_id_seq'), 'client 3', '600000');

-- Populating data for reservations table
insert into reservation (id, reservation_time, client_id, specialist_id, procedure_id)
values
    (nextval('reservation_id_seq'), '2022-01-01 09:00:00', 1, 1, 1),
    (nextval('reservation_id_seq'), '2022-02-02 10:00:00', 2, 2, 2),
    (nextval('reservation_id_seq'), '2022-03-03 11:00:00', 3, 3, 3);

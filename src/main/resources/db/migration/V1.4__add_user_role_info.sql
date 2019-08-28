insert into role (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', 'Y', 'Y', 'Y', 'Y'),
('Manager', '/', 'Y', 'Y', 'Y', 'N'),
('user', '/houses,/customers', 'Y', 'N', 'N', 'N')
;
commit;

commit;
insert into users_role values
(1, 1),
(2, 2),
(3, 3)
;
commit;
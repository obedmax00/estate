alter table houses drop constraint house_customer_fk;
alter table houses drop column customer_id;

create table customers_houses(
    customer_id bigint not null,
    house_id bigint not null,
    constraint customers_id_fk foreign key (customer_id)
    references customers(id),
    constraint houses_id_fk foreign key (house_id)
    references  houses(id)
)
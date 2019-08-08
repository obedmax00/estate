insert into agents (name, last_name,first_name,email,phone_number,password,address)
values ('lukedj','dj','luke','lukedj@gmail.com',5713456654,'1234','116 falls church va'),
('jamescook','cook', 'james','jamescook@gmail.com',571344555,'1234','1234 falls church va'),
('perterhan','han','peter','peterhan@gmail.com',5734562454,'1234','1253 falls church va');

commit;

insert into customers (name, first_name,last_name,email,address,salary,phone_number,agent_id)
values ('amyjames','amy','james','amyjames@gmail.com','123 falls church va',3999.22,'4444444444',1),
('jacklawson','jack','lawson','jacklawson@gmail.com','145 falls church va',2299.34,'4444444444',2),
('jamespaterson','james','paterson','jamespaterson@gmail.com','193 falls church va',2319.32,'3333333333',3);

commit;

insert into houses (address,price,year,last_bought,last_sold,tax,customer_id)
values ('332 lynchburg va',12344.22,1993,'04/12/1943','12/04/1800',100,1),
('322 fairfax va',4321123.22,1943,'04/22/1803','06/13/1903',200,2),
('1995 falls church va',123453.33,1843,'03/01/1995','04/05/2006',300,3);

commit;
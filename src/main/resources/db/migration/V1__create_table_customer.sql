CREATE TABLE IF NOT EXISTS customer(
    id SERIAL primary key,
    name varchar(255) not null,
    email varchar(255) not null unique
);

insert into customer(name, email) values('wes', 'wes@gmail.com')
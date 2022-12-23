CREATE TABLE IF NOT EXISTS book(
    id SERIAL primary key,
    name varchar(255) not null,
    price decimal(10,2) not null,
    status varchar(255) not null,
    customer_id int not null,
    FOREIGN KEY (customer_id) references customer(id)
);

insert into book(name, price, status, customer_id) values('livro da vida', 15.90, 'ATIVO', 1)
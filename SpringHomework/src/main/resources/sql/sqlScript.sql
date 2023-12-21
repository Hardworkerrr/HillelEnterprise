CREATE TABLE IF NOT EXISTS customer
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(256),
    phone_number VARCHAR(256) UNIQUE,
    email        VARCHAR(256) UNIQUE ,
    birthday     DATE
);

CREATE TABLE IF NOT EXISTS address
(
    id          SERIAL PRIMARY KEY,
    street      VARCHAR(256),
    city        VARCHAR(256),
    postal_code VARCHAR(256),
    country     VARCHAR(256),
    customer_id INT UNIQUE,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS product
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(256),
    price    FLOAT,
    calories FLOAT,
    quantity INT
);

CREATE TABLE IF NOT EXISTS credentials
(
    id          SERIAL PRIMARY KEY,
    username    VARCHAR(256) UNIQUE,
    password    VARCHAR(256),
    customer_id INT UNIQUE,
    is_active   BOOLEAN,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS payment
(
    id          SERIAL PRIMARY KEY,
    type        VARCHAR(256),
    is_payed    boolean,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS category
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS product_category
(
    id          SERIAL PRIMARY KEY,
    category_id INT,
    product_id  INT,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS orders
(
    id          SERIAL PRIMARY KEY,
    customer_id INT,
    order_date  DATE,
    payment_id  INT,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE,
    FOREIGN KEY (payment_id) REFERENCES payment (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS order_details
(
    id                SERIAL PRIMARY KEY,
    order_id          INT,
    product_id        INT,
    units_price       FLOAT,
    products_quantity INT,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);



insert into customer (id, name, phone_number, email, birthday)
values (1, 'Mufi Seller', '983-954-9001', 'mseller0@youtu.be', '12/10/1976');
insert into customer (id, name, phone_number, email, birthday)
values (2, 'Sukey Cleghorn', '179-425-8771', 'scleghorn1@smh.com.au', '05/11/2000');
insert into customer (id, name, phone_number, email, birthday)
values (3, 'Doyle Darwood', '259-711-4923', 'ddarwood2@vinaora.com', '06/12/2014');
insert into customer (id, name, phone_number, email, birthday)
values (4, 'Don Chessil', '574-796-8324', 'dchessil3@epa.gov', '11/09/1963');
insert into customer (id, name, phone_number, email, birthday)
values (5, 'Mariya Poon', '259-324-8264', 'mpoon4@google.com.hk', '07/12/1987');


insert into product (id, name, price, calories, quantity)
values (1, 'Brown Bread', 150, 580, 9);
insert into product (id, name, price, calories, quantity)
values (2, 'Chips', 30, 669, 3);
insert into product (id, name, price, calories, quantity)
values (3, 'Salmon', 230, 349, 15);
insert into product (id, name, price, calories, quantity)
values (4, 'PopCorn', 10, 511, 10);
insert into product (id, name, price, calories, quantity)
values (5, 'White Chocolate', 40, 267, 14);


insert into address (id, street, city, postal_code, country, customer_id)
values (1, '8984 Monterey Trail', 'Carpentras', '84977', 'France', 3);
insert into address (id, street, city, postal_code, country, customer_id)
values (2, '0267 Alpine Avenue', 'Soufli', '3211', 'Greece', 2);
insert into address (id, street, city, postal_code, country, customer_id)
values (3, '15 Delladonna Place', 'Buenos Aires', '51222', 'Peru', 1);
insert into address (id, street, city, postal_code, country, customer_id)
values (4, '26434 Continental Court', 'Libano', '731048', 'Colombia', 5);
insert into address (id, street, city, postal_code, country, customer_id)
values (5, '7413 Northview Hill', 'Perstorp', '284-32', 'Sweden', 4);

insert into credentials (id, username, password, customer_id, is_active)
values (1, 'lmillom0', 'eW7}i@1%0xl1O5U', 3, true);
insert into credentials (id, username, password, customer_id, is_active)
values (2, 'acornbell1', 'aZ2#dGG?U', 1, true);
insert into credentials (id, username, password, customer_id, is_active)
values (3, 'dallery2', 'mN4_rTapn3ia4eXs', 5, true);
insert into credentials (id, username, password, customer_id, is_active)
values (4, 'sfairfull3', 'wO3*+2?hs19aZOjp', 2, true);
insert into credentials (id, username, password, customer_id, is_active)
values (5, 'dhalburton4', 'qS3?A*#)_W|', 4, true);

insert into category (id, name)
values (1, 'Prepared Food');
insert into category (id, name)
values (2, 'Snacks');
insert into category (id, name)
values (3, 'Bread');
insert into category (id, name)
values (4, 'Diet');
insert into category (id, name)
values (5, 'Meat');
insert into category (id, name)
values (6, 'Fish');
insert into category (id, name)
values (7, 'Chocolate');


insert into payment (id, type, is_payed, customer_id)
values (1, 'Credit Card', true, 1);
insert into payment (id, type, is_payed, customer_id)
values (2, 'Check', true, 2);
insert into payment (id, type, is_payed, customer_id)
values (3, 'Bank Payment', false, 3);
insert into payment (id, type, is_payed, customer_id)
values (4, 'Cash', true, 2);
insert into payment (id, type, is_payed, customer_id)
values (5, 'Cash', false, 5);
insert into payment (id, type, is_payed, customer_id)
values (6, 'Check', true, 1);
insert into payment (id, type, is_payed, customer_id)
values (7, 'Cash', false, 4);


insert into product_category (id, category_id, product_id)
values (1, 3, 1);
insert into product_category (id, category_id, product_id)
values (2, 4, 1);
insert into product_category (id, category_id, product_id)
values (3, 2, 2);
insert into product_category (id, category_id, product_id)
values (4, 6, 3);
insert into product_category (id, category_id, product_id)
values (5, 1, 4);
insert into product_category (id, category_id, product_id)
values (6, 7, 5);


insert into orders (id, customer_id, order_date, payment_id)
values (1, 1, '2/12/2023', 1);
insert into orders (id, customer_id, order_date, payment_id)
values (2, 2, '10/10/2023', 2);
insert into orders (id, customer_id, order_date, payment_id)
values (3, 3, '16/6/2023', 3);
insert into orders (id, customer_id, order_date, payment_id)
values (4, 2, '24/2/2023', 4);
insert into orders (id, customer_id, order_date, payment_id)
values (5, 5, '15/6/2023', 5);
insert into orders (id, customer_id, order_date, payment_id)
values (6, 1, '25/10/2023', 6);
insert into orders (id, customer_id, order_date, payment_id)
values (7, 4, '15/12/2023', 7);



insert into order_details (id, order_id, product_id, units_price, products_quantity)
values (2, 3, 2, 90, 3);
insert into order_details (id, order_id, product_id, units_price, products_quantity)
values (3, 1, 3, 230, 1);
insert into order_details (id, order_id, product_id, units_price, products_quantity)
values (4, 2, 3, 460, 2);
insert into order_details (id, order_id, product_id, units_price, products_quantity)
values (5, 4, 4, 100, 10);

SELECT c.id, c.name, a.id, a.street FROM customer AS c JOIN address AS a ON c.id = a.customer_id WHERE c.id=3;
SELECT c.id, c.name, o.id, o.order_date FROM customer as c JOIN orders as o ON c.id = o.customer_id where c.id=2;
SELECT c.id, c.name, p.id, p.type, p.customer_id FROM customer as c JOIN payment as p ON c.id = p.customer_id where c.id=1;
SELECT o.id, o.customer_id, o.order_date,x.product_id, x.order_id, p.name product_name FROM orders as o
    JOIN order_details as x ON o.id = x.order_id
    JOIN product as p ON x.product_id=p.id where p.id=3;

SELECT category_id, name  FROM product_category JOIN category ON category_id=category.id WHERE product_id = 4;

SELECT count(*) FROM customer;


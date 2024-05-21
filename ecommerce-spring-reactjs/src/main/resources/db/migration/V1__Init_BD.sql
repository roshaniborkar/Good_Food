create sequence users_id_seq start 4 increment 1;
create sequence order_item_seq start 12 increment 1;
create sequence orders_seq start 6 increment 1;


Create table ProductCategory(
    Id int4 GENERATED ALWAYS AS IDENTITY,
    CategoryName VARCHAR(255) not null,
    ImagePath              varchar(255),

    primary key (Id)
);

CREATE TABLE product
(
    id SERIAL PRIMARY KEY,
    productname VARCHAR(255),
    imagepath VARCHAR(255),
    producttag VARCHAR(100),
    price INTEGER,
    categoryid INTEGER REFERENCES productcategory(id),
    review VARCHAR(200),
    rating INTEGER,
    description VARCHAR(500),
    seller VARCHAR(100)
);

CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    activation_code VARCHAR(255),
    active BOOLEAN NOT NULL,
    address VARCHAR(255),
    city VARCHAR(255),
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    password_reset_code VARCHAR(255),
    phone_number VARCHAR(255),
    post_index VARCHAR(255),
    provider VARCHAR(255)
);

CREATE TABLE user_role
(
    user_id BIGINT NOT NULL REFERENCES users(id),
    roles VARCHAR(255),
    CONSTRAINT user_role_pk PRIMARY KEY (user_id, roles)
);

CREATE TABLE orders
(
    id BIGSERIAL PRIMARY KEY,
    address VARCHAR(255),
    city VARCHAR(255),
    date DATE,
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(255),
    post_index INTEGER,
    total_price DOUBLE PRECISION
);

create table order_item
(
    id         int8 generated by default as identity,
    amount     int8,
    quantity   int8,
    product_Id  int ,
    primary key (id),
    FOREIGN KEY (product_Id) REFERENCES Product(Id)
);

CREATE TABLE orders_order_items
(
    order_id BIGINT NOT NULL REFERENCES orders(id),
    order_items_id BIGINT NOT NULL REFERENCES order_item(id),
    PRIMARY KEY (order_id, order_items_id)
);
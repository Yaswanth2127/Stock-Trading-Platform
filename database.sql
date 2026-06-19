create database stock_trading_platform;
use stock_trading_platform;

CREATE TABLE users(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50),
    wallet_balance DECIMAL(12,2)
);

create table stocks(
stock_id int auto_increment primary key,
stock_name varchar(50),
current_price decimal(10,2),
available_quantity int
);


create table portifolio(
portifolia_id int auto_increment primary key ,
user_id int,
stock_id int,
quantity int ,
purchase_price decimal(10,2),
foreign key (user_id) references users(user_id),
foreign key (stock_id) references stocks(stock_id)
);


create table transactions(
transaction_id  int auto_increment primary key ,
user_id int,
stock_id int,
transaction_type varchar(10),
quantity int,
stock_price decimal(10,2),
transaction_date timestamp default current_timestamp,
foreign key (user_id) references users(user_id),
foreign key (stock_id) references stocks(stock_id)
);


ALTER TABLE portifolio
RENAME COLUMN portifolia_id TO portifolio_id;

INSERT INTO stocks(stock_name,current_price,available_quantity)
VALUES
('TCS',3500,1000),
('Infosys',1500,1000),
('Wipro',300,1000),
('Reliance',1400,1000);

INSERT INTO users(user_name,wallet_balance)
VALUES('Yaswanth',100000);

select * from stocks;
CREATE database if not exists vehicles;

DROP table if exists vehicles;

CREATE table vehicles (
car_id int not null auto_increment,
car_make varchar(40) not null,
car_model varchar(40) not null,
car_color varchar(40) not null,
PRIMARY KEY (car_id)
);
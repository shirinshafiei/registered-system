create database registered_system_database;
use registered_system_database;
create table persons (
    id int primary key auto_increment,
    firstName nvarchar(50) not null ,
    lastName nvarchar(50) not null ,
    age int not null ,
    email nvarchar(100) not null ,
    password nvarchar(50) not null
);


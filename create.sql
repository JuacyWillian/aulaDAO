drop database pessoas;

create database pessoas;
use pessoas;

create table pessoa (
id int not null auto_increment,
nome varchar(80) not null,
email varchar(120) not null unique,
cpf varchar(11) not null unique,
telefone varchar(15) not null,
sexo varchar(1) not null,
dataNascimento date not null,
primary key(id));


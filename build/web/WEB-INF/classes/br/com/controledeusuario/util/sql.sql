CREATE TABLE estado(
idestado serial primary key,
nomeestado varchar(40),
siglaestado varchar(2));


CREATE TABLE cidade(
idcidade serial primary key,
nomecidade varchar(100),
idestado integer,
CONSTRAINT fk_cidade_estado
FOREIGN KEY (idestado)
REFERENCES estado(idestado)
);

CREATE TABLE pessoa(
idpessoa serial primary key,
cpf varchar(11),
nome varchar(100),
datanascimento Date,
login varchar(30),
senha varchar(10),
idcidade int,
CONSTRAINT fk_pessoa_cidade
FOREIGN KEY (idcidade)
REFERENCES cidade(idcidade));

CREATE TABLE cliente(
idcliente serial primary key,
observacao varchar(500),
situacao varchar(1),
permitelogin varchar(1),
idpessoa int,
CONSTRAINT fk_cliente_pessoa
FOREIGN KEY (idpessoa)
REFERENCES pessoa(idpessoa));

CREATE TABLE fornecedor(
idfornecedor serial primary key,
url varchar(2000),
observacao varchar(500),
situacao varchar(1),
permitelogin varchar(1),
idpessoa int,
CONSTRAINT fk_fornecedor_pessoa
FOREIGN KEY (idpessoa)
REFERENCES pessoa(idpessoa));

CREATE TABLE administrador(
idadministrador serial primary key,
situacao varchar(1),
permitelogin varchar(1),
caminhofoto varchar(3000),
idpessoa int,
CONSTRAINT fk_administrador_pessoa
FOREIGN KEY (idpessoa)
REFERENCES pessoa(idpessoa));










-->Fazer Donwload do projeto
-->Abrir o projeto no netbenas
-->Abrir o pgAdmin e criar o banco
-->Criar as tabelas no SQL
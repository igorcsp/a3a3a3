create database db_almoxarifado;

use db_almoxarifado;

create table tb_funcionarios(
    id int primary key auto_increment,
    nome varchar(50) not null
);

insert into
    tb_funcionarios(nome)
values
    ('Igor');

create table tb_fornecedor(
    cnpj bigint primary key,
    razao_social varchar(50) unique not null,
    endereco varchar(50) not null
);

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        12345678901234,
        'Fornecedor LTDA',
        'Rua do Fornecedor'
    );

create table tb_estoque(
    codigo int primary key auto_increment,
    descricao varchar(50) not null,
    fornecedor varchar(50) not null,
    dataregistro Date,
    preco real not null,
    quantidade int not null,
    unidadeDeMedida varchar(10) not null
);

insert into
    tb_estoque (
        descricao,
        fornecedor,
        dataregistro,
        preco,
        quantidade,
        unidadeDeMedida
    )
values
    (
        'Material',
        'Fornecedor',
        '23-02-20',
        4.90,
        15,
        'Kg'
    );

create table tb_movimentacoes(
    idMovimentacoes int primary key auto_increment,
    tipoDeMovimentacao varchar(20) not null,
    produto varchar(50) not null,
    quantidade int not null,
    funcionario int,
    data_retirada Date not null
);

insert into
    tb_movimentacoes(
        tipoDeMovimentacao,
        produto,
        quantidade,
        funcionario,
        data_retirada
    )
values
    ('Entrada', 'Material', 15, 1, '2024-04-13');

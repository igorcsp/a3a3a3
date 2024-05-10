create database db_almoxarifado;

use db_almoxarifado;

-- FUNCIONARIOS
create table tb_funcionarios(
    id int primary key auto_increment,
    nome varchar(50) not null
);

insert into
    tb_funcionarios(nome)
values
    ('Igor');

insert into
    tb_funcionarios(nome)
values
    ('Ana');

insert into
    tb_funcionarios(nome)
values
    ('Carlos');

insert into
    tb_funcionarios(nome)
values
    ('Maria');

-- FORNECEDORES
create table tb_fornecedor(
    id int primary key auto_increment,
    cnpj varchar(40),
    razao_social varchar(50),
    endereco varchar(50)
);

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        '12345678901234',
        'Fornecedor LTDA',
        'Rua do Fornecedor'
    );

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        '98765432109876',
        'Fornecedor ABC Ltda',
        'Avenida do Fornecedor, 123'
    );

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        '56789012345678',
        'Fornecedor XYZ Ltda',
        'Rua do Comércio, 456'
    );

-- ESTOQUE
create table tb_estoque(
    codigo int primary key auto_increment,
    descricao varchar(50) not null,
    fornecedor varchar(40) not null,
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
        '12345678901234',
        '2020-02-23',
        4.90,
        15,
        'Kg'
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
        'Parafuso',
        '98765432109876',
        '2024-05-10',
        1.50,
        100,
        'Unidade'
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
        'Fio de Cobre',
        '56789012345678',
        '2024-05-10',
        10.75,
        50,
        'Metro'
    );

-- MOVIMENTACOES
create table tb_movimentacoes(
    idMovimentacoes int primary key auto_increment,
    tipoDeMovimentacao varchar(20) not null,
    produto varchar(50) not null,
    quantidade int not null,
    funcionario int,
    data_retirada Date
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

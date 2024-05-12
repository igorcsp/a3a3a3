create database db_almoxarifado;

use db_almoxarifado;

-- FUNCIONARIOS

create table tb_funcionarios(
    id int primary key auto_increment,
    nome varchar(50)
);

insert into
    tb_funcionarios(nome)
values
    ('Igor');

insert into
    tb_funcionarios(nome)
values
    ('João');

insert into
    tb_funcionarios(nome)
values
    ('Lucas');

insert into
    tb_funcionarios(nome)
values
    ('Lucão');

insert into
    tb_funcionarios(nome)
values
    ('Kath');

insert into
    tb_funcionarios(nome)
values
    ('Mike');

-- FORNECEDORES 

create table tb_fornecedor(
    id int primary key auto_increment,
    cnpj char(14),
    razao_social varchar(50),
    endereco varchar(50)
);

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        '12345678000100',
        'C&C Casa e Construção',
        'Rua das Ferramentas, 123'
    );

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        '98765432000199',
        'Leroy Merlin',
        'Avenida dos Materiais, 456'
    );

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        '56789012000188',
        'Telhanorte',
        'Rua dos Acabamentos, 789'
    );

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        '11223344000177',
        'Sodimac',
        'Avenida das Construções, 321'
    );

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        '99887766000166',
        'Tigre',
        'Rua das Tubulações, 654'
    );

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        '55443322000155',
        'Portobello Shop',
        'Avenida dos Revestimentos, 987'
    );

insert into
    tb_fornecedor(cnpj, razao_social, endereco)
values
    (
        '66554477000144',
        'Votorantim Cimentos',
        'Rua dos Cimentos, 741'
    );

-- ESTOQUE 

create table tb_estoque(
    codigo int primary key auto_increment,
    descricao varchar(50),
    fornecedor int,
    dataregistro Datetime,
    preco real,
    quantidade int,
    unidadeDeMedida varchar(10)
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
        1,
        '2020-02-23 00:00:00',
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
        2,
        '2024-05-10 00:00:00',
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
        3,
        '2024-05-10 00:00:00',
        10.75,
        50,
        'Metro'
    );

-- MOVIMENTACOES 

create table tb_movimentacoes(
    idMovimentacoes int primary key auto_increment,
    tipoDeMovimentacao varchar(20),
    produto varchar(50),
    quantidade int,
    funcionario int,
    data_retirada Datetime
);

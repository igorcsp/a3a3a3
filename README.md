create database db_almoxarifado;

use db_almoxarifado;

create table tb_estoque( codigo int primary key auto_increment, descricao varchar(50), fornecedor varchar(50), dataregistro Date, preco double, quantidade int, unidadeDeMedida varchar(10));

create table tb_funcionarios( id int primary key auto_increment, nome varchar(50) );

create table tb_fornecedor( idFornecedor int primary key auto_increment, cnpj varchar(50), empresa varchar(50) );

create table tb_movimentacoes( idMovimentacoes int, descricaoProduto varchar(50), funcionarioRetirou varchar(50), validadeProduto varchar(50) );

insert into tb_estoque (descricao, fornecedor, dataregistro, preco, quantidade, unidadeDeMedida, valorTotal) values('teste', 'teste', '23-02-20', 20, 20, 'kg', 20);

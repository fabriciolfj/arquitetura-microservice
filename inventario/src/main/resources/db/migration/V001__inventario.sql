create table inventario (
  id bigint not null auto_increment,
  codigo_produto varchar(50) not null,
  quantidade_entrada bigint default 0,
  quantidade_saida bigint default 0,
  saldo bigint default 0,
  primary key (id)
) engine=InnoDB default charset=utf8;
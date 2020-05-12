create table produto (
  id bigint not null auto_increment,
  descricao varchar(50) not null,
  preco decimal(15,4) not null,
  primary key (id)
) engine=InnoDB default charset=utf8;

alter table produto add codigo varchar(36) not null;
alter table produto add constraint uk_produto_codigo unique (codigo);
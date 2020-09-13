create table ativo_bovespa (
	codigo varchar(255) not null,
	acao varchar(255) not null,
	tipo varchar(150) not null,
	quantidade_teorica bigint not null,
	participacao decimal(6, 3) not null,
	
	primary key (codigo)
) engine=InnoDB default charset=utf8;
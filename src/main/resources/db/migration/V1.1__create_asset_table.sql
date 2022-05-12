create table tbl_assets(
	id_assets serial primary key,
	id_portfolio int not null,
	accouting int not null,
	name_asset varchar(50) not null,
	quantity int not null,
	price decimal(4,2) not null,
	daily_variation decimal(4,2) not null,
	holding decimal(4,2) not null,
	avg_buy_price decimal(4,2) not null,
	profit decimal(4,2) not null,
	loss decimal(4,2) not null,
	foreign key(id_portfolio) references tbl_portfolio(id_portfolio)
)
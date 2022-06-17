create table tbl_assets(
	id_assets serial primary key,
	symbol varchar(3) UNIQUE,
	id_portfolio int not null,
	accouting int not null,
	name_asset varchar(50) not null,
	quantity int not null,
	price double precision not null,
	daily_variation double precision not null,
	holding double precision not null,
	avg_buy_price double precision not null,
	profit double precision not null,
	loss double precision not null,
	foreign key(id_portfolio) references tbl_portfolio(id_portfolio)
)
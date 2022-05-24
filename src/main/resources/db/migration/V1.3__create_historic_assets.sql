create table tbl_historic_assets(
	id_historic_assets serial primary key,
	id_assets int not null,
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
	foreign key(id_assets) references tbl_assets(id_assets)
)
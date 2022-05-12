create table tbl_assets_transaction(
	id_transaction serial primary key,
	id_asset int not null,
	type char(25),
	price_transaction decimal(4,2),
	date_time date,
	fee decimal(4,2),
	notes varchar(100),
	amount decimal(4,2),
	foreign key(id_asset) references tbl_assets(id_assets)
	
)
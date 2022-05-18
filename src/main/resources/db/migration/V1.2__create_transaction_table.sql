create table tbl_assets_transaction(
	id_transaction serial primary key,
	id_asset int not null,
	type char(25),
	price_transaction double precision,
	date_time date,
	fee double precision,
	notes varchar(100),
<<<<<<< Updated upstream
	quantity int not null,
	amount double precision,
	foreign key(id_asset) references tbl_assets(id_assets)
=======
	amount decimal(4,2),
	foreign key(id_asset) references tbl_assets(id_assets),
	ON DELETE CASCADE
>>>>>>> Stashed changes
	
)
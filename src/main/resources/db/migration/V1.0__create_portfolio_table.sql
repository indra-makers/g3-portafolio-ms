create table tbl_portfolio(
	id_portfolio serial primary key,
	name_portfolio varchar(50) UNIQUE ,
	id_user int not null,
	balance_portfolio decimal(4,2)
)
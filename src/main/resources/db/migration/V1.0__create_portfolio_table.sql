create table tbl_portfolio(
	id_portfolio serial primary key,
	name_portfolio unique varchar(50),
	id_user int not null,
	balance_portfolio decimal(4,2)
)
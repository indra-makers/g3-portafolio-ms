create table tbl_portfolio(
	id_portfolio serial primary key,
	name_portfolio varchar(50),
	id_user int not null,
	balance_portfolio double precision
)
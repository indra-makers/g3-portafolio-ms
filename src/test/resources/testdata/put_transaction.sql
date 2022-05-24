INSERT INTO public.tbl_portfolio(id_portfolio, name_portfolio, id_user, balance_portfolio) values(1, 'TVQ', 12, 4.2);

insert into tbl_assets(id_assets,id_portfolio,accouting,name_asset,quantity,price,daily_variation,holding,avg_buy_price,profit,loss)
	values(1,1,1,'btc',5,44.44,54.44,42.23,34.34,34.34,34.34);
insert into tbl_assets_transaction(id_transaction,id_asset,type,price_transaction,date_time,fee,notes,quantity,amount) values(3,1,'BUY',2000.0,now(),20.0,'nnn',2,2.3);
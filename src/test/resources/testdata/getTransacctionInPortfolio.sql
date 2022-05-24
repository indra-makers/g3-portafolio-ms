insert into tbl_portfolio(id_portfolio, name_portfolio,id_user,balance_portfolio) values(1, 'prueba', 1, 458.45);
insert into tbl_assets(id_assets, id_portfolio, accouting, name_asset, quantity, price, daily_variation, holding, avg_buy_price, profit, loss) values(1, 1, 4, 'pruebaa',5,20,5,5,0,5,4);
insert into tbl_assets_transaction(id_asset,type,price_transaction,date_time,fee,notes,quantity,amount) values(1,'BUY',2000.0,now(),20.0,'nnn',2,2.3);

insert into stock_exchange (id, name)
values (1001, 'NY Stock Exchange');
insert into stock_exchange (id, name)
values (1002, 'Japan Exchange Group');
insert into stock_exchange (id, name)
values (1003, 'Nasdaq');

insert into remark (id, descr, stock_exchange_id)
values(2001, 'remark 1',1002);
insert into remark (id, descr, stock_exchange_id)
values(2002, 'remark 2',1002);
insert into remark (id, descr, stock_exchange_id)
values(2003, 'remark 3',1003);
insert into remark (id, descr, stock_exchange_id)
values(2004, 'remark 4',1003);
insert into remark (id, descr, stock_exchange_id)
values(2005, 'remark 5',1002);


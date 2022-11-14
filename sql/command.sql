#insert data to single_consumption_record for demo
INSERT INTO `single_consumption_record`(`amount_of_money`, `date`, `category_id`, `description`)
VALUES (100,'2022-11-01',1,''),
(150,'2022-11-01',1,''),
(80,'2022-11-02', 2,''),
(120,'2022-11-02',2,''),
(180,'2022-11-02',3,''),
(180,'2022-11-03',1,'');

#join single_consumption_record and consumption_category
select consumption.amount_of_money, consumption.date, consumption.description, cc.name
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id;

#
select consumption.*, cc.name as category
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id;

#select between now and specific date
SELECT * FROM `single_consumption_record` WHERE date>"2022-11-03" and date<now();

#select from a week
select * from single_consumption_record WHERE yearweek(date) = yearweek("2022-11-01");

#select from a month
select * from single_consumption_record WHERE month(date) = 10;

#insert into single_consumption_record
INSERT INTO `single_consumption_record`(`amount_of_money`, `date`, `category_id`, `description`)
VALUES (100,'2022-11-14',1,'insert into')
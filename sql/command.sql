--#insert data to single_consumption_record for demo
INSERT INTO `single_consumption_record`(`amount_of_money`, `date`, `category_id`, `description`)
VALUES (100,'2022-11-01',1,''),
(150,'2022-11-01',1,''),
(80,'2022-11-02', 2,''),
(120,'2022-11-02',2,''),
(180,'2022-11-02',3,''),
(180,'2022-11-03',1,'');

--#join single_consumption_record and consumption_category
select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id;

--#
select consumption.*, cc.name as category
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id;

--#select between now and specific date
SELECT * FROM `single_consumption_record` WHERE date>"2022-11-03" and date<now();

--#select from a week
select * from single_consumption_record WHERE yearweek(date) = yearweek("2022-11-01");

--#select from a month
select * from single_consumption_record WHERE month(date) = 10;

--#insert into single_consumption_record
INSERT INTO `single_consumption_record`(`amount_of_money`, `date`, `category_id`, `description`)
VALUES (100,'2022-11-14',1,'insert into')

--#update single_consumption_record
UPDATE single_consumption_record 
SET `amount_of_money` = ?,`date` = ?,category_id = ?,description = ?
WHERE id = ?;

--#delete
DELETE FROM single_consumption_record WHERE id = ?;

--#select the data from same day
select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id
WHERE date = ?
ORDER BY date


--#select the data from same month
select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id
WHERE MONTH(date) = MONTH(?)
ORDER BY date

--#select the data from same month and same year
select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id
WHERE MONTH(date) = MONTH(?) AND YEAR(date) = YEAR(?)
ORDER BY date

--#select the data from same month, same year and same category
select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id
WHERE MONTH(date) = MONTH(?) AND YEAR(date) = YEAR(?) AND cc.name = (SELECT consumption_category.name from consumption_category where name = ?)
ORDER BY date

--#select the data from same year
select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id
WHERE YEAR(date) = YEAR(?)
ORDER BY date

--#select the data from a category
select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id
WHERE cc.name = (SELECT consumption_category.name from consumption_category where name = ?)
ORDER BY date

--#select the data from same year and category
select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id
WHERE YEAR(date) = YEAR(?) AND cc.name = (SELECT consumption_category.name from consumption_category where name = ?)
ORDER BY date
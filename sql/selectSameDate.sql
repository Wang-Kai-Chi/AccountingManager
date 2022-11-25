select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description
from single_consumption_record as consumption
join consumption_category as cc
on consumption.category_id = cc.id
WHERE date = ?
ORDER BY date
package com.ericwang.jaccount.backend;

public class SqlCommandCollection {
	public static String selectPrettyConsumptionRecord = "select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description "
			+ "from single_consumption_record as consumption " + "join consumption_category as cc "
			+ "on consumption.category_id = cc.id;";
	
	public static String sameday = "select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE date = ?\r\n"
			+ "ORDER BY date";
	public static String sameMonth = "select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE MONTH(date) = MONTH(?)\r\n"
			+ "ORDER BY date";
	
	public static String sameMonthNYear = "select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE MONTH(date) = MONTH(?) AND YEAR(date) = YEAR(?)\r\n"
			+ "ORDER BY date";
	
	public static String sameMonthNYearNCategory = "select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE MONTH(date) = MONTH(?) AND YEAR(date) = YEAR(?) AND cc.name = (SELECT consumption_category.name from consumption_category where name = ?)\r\n"
			+ "ORDER BY date";
	
	public static String category = "select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE cc.name = (SELECT consumption_category.name from consumption_category where name = ?)\r\n"
			+ "ORDER BY date";
	
	public static String sameYear = "select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE YEAR(date) = YEAR(?)\r\n"
			+ "ORDER BY date";
	
	public static String sameMonthNcategory = "SET @selected_date := ?;\r\n"
			+ "\r\n"
			+ "select consumption.id, consumption.amount_of_money, consumption.date as date, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE MONTH(date) = MONTH(@selected_date) AND cc.name = (SELECT consumption_category.name from consumption_category where name = ?)\r\n"
			+ "ORDER BY date";
	
	public static String sameYearNCategory = "select consumption.id, consumption.date, consumption.amount_of_money, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE YEAR(date) = YEAR(?) AND cc.name = (SELECT consumption_category.name from consumption_category where name = ?)\r\n"
			+ "ORDER BY date";
}

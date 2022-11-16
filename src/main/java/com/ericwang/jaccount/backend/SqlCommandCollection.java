package com.ericwang.jaccount.backend;

public class SqlCommandCollection {
	public static String sameday = "SET @selected_date := ?;\r\n"
			+ "\r\n"
			+ "select consumption.id, consumption.amount_of_money, consumption.date as date, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE date = @selected_date\r\n"
			+ "ORDER BY date";
	public static String sameMonth = "SET @selected_date := ?;\r\n"
			+ "\r\n"
			+ "select consumption.id, consumption.amount_of_money, consumption.date as date, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE MONTH(date) = MONTH(@selected_date)\r\n"
			+ "ORDER BY date";
	
	public static String sameMonthNYear = "SET @selected_date := ?;\r\n"
			+ "\r\n"
			+ "select consumption.id, consumption.amount_of_money, consumption.date as date, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE MONTH(date) = MONTH(@selected_date) AND YEAR(date) = YEAR(@selected_date)\r\n"
			+ "ORDER BY date";
	
	public static String sameMonthNYearNCategory = "select consumption.id, consumption.amount_of_money, consumption.date as date, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE MONTH(date) = MONTH(@selected_date) AND YEAR(date) = YEAR(@selected_date) AND cc.name = (SELECT consumption_category.name from consumption_category where name = ?)\r\n"
			+ "ORDER BY date";
	
	public static String category = "select consumption.id, consumption.amount_of_money, consumption.date as date, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE cc.name = (SELECT consumption_category.name from consumption_category where name = ?)\r\n"
			+ "ORDER BY date";
	
	public static String sameYear = "SET @selected_date := ?;\r\n"
			+ "\r\n"
			+ "select consumption.id, consumption.amount_of_money, consumption.date as date, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE YEAR(date) = YEAR(@selected_date)\r\n"
			+ "ORDER BY date";
	
	public static String sameMonthNcategory = "SET @selected_date := ?;\r\n"
			+ "\r\n"
			+ "select consumption.id, consumption.amount_of_money, consumption.date as date, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE MONTH(date) = MONTH(@selected_date) AND cc.name = (SELECT consumption_category.name from consumption_category where name = ?)\r\n"
			+ "ORDER BY date";
	
	public static String sameYearNCategory = "SET @selected_date := ?;\r\n"
			+ "\r\n"
			+ "select consumption.id, consumption.amount_of_money, consumption.date as date, cc.name as category, consumption.description\r\n"
			+ "from single_consumption_record as consumption\r\n"
			+ "join consumption_category as cc\r\n"
			+ "on consumption.category_id = cc.id\r\n"
			+ "WHERE YEAR(date) = YEAR(@selected_date) AND cc.name = (SELECT consumption_category.name from consumption_category where name = ?)\r\n"
			+ "ORDER BY date";
}

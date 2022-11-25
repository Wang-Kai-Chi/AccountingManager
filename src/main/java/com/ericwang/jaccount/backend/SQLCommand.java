package com.ericwang.jaccount.backend;

public enum SQLCommand {
	SELECT_PRETTY,
	SELECT_SAME_DATE;
	
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}

package com.ericwang.jaccount.backend;

public class SQLCInitializer {
	private String[] urls = new String[]{
		"sql/prettySelect.sql",
		"sql/selectSameDate.sql"
	};
	
	public SQLCInitializer() {
		for(int i = 0;i<urls.length;i++) {
			MyFileReader r = new MyFileReader(urls[i]);
			SQLCommand.values()[i].setSql(r.getResult());
		}
	}
	
	
}

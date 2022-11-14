package com.ericwang.jaccount.backend;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TableHeaders{
    private final String[] headers;

    public TableHeaders(ResultSetMetaData r) throws SQLException {
        int colCount = r.getColumnCount();
        headers = new String[colCount];

        for (int i = 0; i < headers.length; i++) {
            headers[i] = r.getColumnName(i + 1);
        }
    }

    public String[] getHeaders() {
        return headers;
    }
}

package com.ericwang.jaccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ericwang.jaccount.backend.MyApplicationBackEnd;
import com.ericwang.jaccount.backend.MySQLConnectionBuilder;
import com.ericwang.jaccount.backend.SearchSet;
import com.ericwang.jaccount.backend.SqlCommandCollection;
import com.ericwang.jaccount.backend.TableHeaders;
import com.ericwang.jaccount.backend.cc.ConsumptionCategory;
import com.ericwang.jaccount.backend.cc.ConsumptionCategoryController;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecord;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordController;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordRepository;
import com.ericwang.jaccount.frontend.MyApplicationFrame;

public class Presentation {

}

class PrettyConsumptionRecordRepository {
	private ResultSet resultSet;
	private SqlCommandCollection commands;
	//...
	public void search(String sql, SearchSet searchSet) throws SQLException {
		PreparedStatement pstmt = 
				connection.prepareStatement(
						sql, 
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE
						); 
		
		if(sql.equals(commands.sameday)||
				sql.equals(commands.sameMonth)||
				sql.equals(commands.sameYear)) {
			pstmt.setString(1, searchSet.getDate());			
		}
		else if(sql.equals(commands.sameMonthNYear)) {
			pstmt.setString(1, searchSet.getDate());
			pstmt.setString(2, searchSet.getDate());
		}
		//...
		resultSet = pstmt.executeQuery();
	}
	//...
}

class PrettyConsumptionRecordController {
	private final ArrayList<PrettyConsumptionRecord> recordList;
	private PrettyConsumptionRecordRepository repo;
	// ...

	public PrettyConsumptionRecordController() {
		recordList = new ArrayList<>();
		// ...
	}

	public PrettyConsumptionRecordController(PrettyConsumptionRecordRepository repo) {
		this();
		this.repo = repo;
	}

	public void searchFromDb(String sql, SearchSet searchSet) {
		try {
			repo.search(sql, searchSet);

			refreshList();
			// ...
		} catch (SQLException e) {// ...
		}
	}
	//...
}

class Main {
	public static void main(String[] args) {
		ConsumptionCategoryController consumptionCategoryController;
		PrettyConsumptionRecordController prettyConsumptionRecordController;
		try {
			MySQLConnectionBuilder connectionBuilder = new MySQLConnectionBuilder("accounting_db01");
			MyApplicationBackEnd myApplicationBackEnd = new MyApplicationBackEnd(connectionBuilder.getConnection());
			// ...

			consumptionCategoryController = myApplicationBackEnd.getConsumptionCategoryCon();
			prettyConsumptionRecordController = myApplicationBackEnd.getPrettyConsumptionRecordCon();

		} catch (Exception e) {
			consumptionCategoryController = new ConsumptionCategoryController();
			prettyConsumptionRecordController = new PrettyConsumptionRecordController();
		}
		// ...
		MyApplicationFrame myApplicationFrame = new MyApplicationFrame(prettyConsumptionRecordController,
				categories.toArray());
	}
}
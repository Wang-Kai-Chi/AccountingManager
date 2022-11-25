package com.ericwang.jaccount.backend;

import java.util.ArrayList;

import com.ericwang.jaccount.backend.cc.ConsumptionCategory;
import com.ericwang.jaccount.backend.cc.ConsumptionCategoryController;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordController;

public class ApplicationBackEnd {
    private PrettyConsumptionRecordController prettyConsumptionRecordController;
    private Object[] categories;

	public ApplicationBackEnd() {
		ConsumptionCategoryController consumptionCategoryController;
		try {
            MySQLConnectionBuilder connectionBuilder = new MySQLConnectionBuilder("accounting_db01");
            ControllerService controllerService = new ControllerService(connectionBuilder.getConnection());

            controllerService.queryConsumptionCategory();
            controllerService.queryPrettyConsumptionRecord();

            controllerService.refresh();

            consumptionCategoryController = controllerService.getConsumptionCategoryCon();
            prettyConsumptionRecordController = controllerService.getPrettyConsumptionRecordCon();

        } catch (Exception e) {
            consumptionCategoryController = new ConsumptionCategoryController();
            prettyConsumptionRecordController = new PrettyConsumptionRecordController();
            e.printStackTrace();
        }
        ArrayList<String> arr = new ArrayList<>();

		for (ConsumptionCategory c : consumptionCategoryController.getRecordList())
			arr.add(c.getName());
		
		categories = arr.toArray();
	}

	public PrettyConsumptionRecordController getPrettyConsumptionRecordController() {
		return prettyConsumptionRecordController;
	}

	public Object[] getCategories() {
		return categories;
	}
	
	
}

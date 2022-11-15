package com.ericwang.jaccount.backend;

public class PrettyConsumptionRecord {
    private int id;
    private String date;
    private int amountOfMoney;
    private String category;
    private String description;
    
    public PrettyConsumptionRecord(int id) {
    	this.id = id;
    }

    public PrettyConsumptionRecord(String date,int amountOfMoney, String category, String description) {
        this.amountOfMoney = amountOfMoney;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    public PrettyConsumptionRecord(int id, String date,int amountOfMoney, String category, String description) {
        this.id = id;
        this.amountOfMoney = amountOfMoney;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    @Override
    public String toString() {
        return "PrettyConsumptionRecord{" +
                "id=" + id +
                ", amountOfMoney=" + amountOfMoney +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}

package com.ericwang.jaccount.backend;

public class PrettyConsumptionRecord {
    private int amountOfMoney;
    private String date;
    private String description;
    private String category;

    public PrettyConsumptionRecord(int amountOfMoney, String date, String description, String category) {
        this.amountOfMoney = amountOfMoney;
        this.date = date;
        this.description = description;
        this.category = category;
    }

    @Override
    public String toString() {
        return "PrettyConsumptionRecord{" +
                "amountOfMoney=" + amountOfMoney +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}

package com.ericwang.jaccount;

public class CashFlowRecord {
    private final int id;
    private int amount_of_money;
    private String date;
    private String category;

    public CashFlowRecord(int id) {
        this.id = id;
    }

    public CashFlowRecord(int id, int amount_of_money, String date, String category) {
        this.id = id;
        this.amount_of_money = amount_of_money;
        this.date = date;
        this.category = category;
    }

    public void setAmount_of_money(int amount_of_money) {
        this.amount_of_money = amount_of_money;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public int getAmount_of_money() {
        return amount_of_money;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "CashFlowRecord"+id+"{" +
                "id=" + id +
                ", amount_of_money=" + amount_of_money +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

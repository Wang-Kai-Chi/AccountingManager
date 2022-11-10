package com.ericwang.jaccount;

public class CashFlowRecord {
    private final int id;
    private int amount_of_money;
    private String date;
    private int category_id;

    public CashFlowRecord(int id) {
        this.id = id;
    }



    public CashFlowRecord(int id, int amount_of_money, String date, int category_id) {
        this.id = id;
        this.amount_of_money = amount_of_money;
        this.date = date;
        this.category_id = category_id;
    }

    public void setAmount_of_money(int amount_of_money) {
        this.amount_of_money = amount_of_money;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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

    public int getCategory_id() {
        return category_id;
    }

    @Override
    public String toString() {
        return "CashFlowRecord"+id+"{" +
                "id=" + id +
                ", amount_of_money=" + amount_of_money +
                ", date='" + date + '\'' +
                ", category='" + category_id + '\'' +
                '}';
    }
}

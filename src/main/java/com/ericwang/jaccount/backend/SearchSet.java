package com.ericwang.jaccount.backend;

public class SearchSet {
    private String date;
    private boolean isSameMonth, isSameYear, isCategory;
    private String category;

    public SearchSet(String date, boolean isSameMonth, boolean isSameYear, boolean isCategory, String category) {
        this.date = date;
        this.isSameMonth = isSameMonth;
        this.isSameYear = isSameYear;
        this.isCategory = isCategory;
        this.category = category;
    }

    public boolean isCategory() {
        return isCategory;
    }

    public void setCategory(boolean category) {
        isCategory = category;
    }

    public String getDate() {
        return date;
    }

    public boolean isSameMonth() {
        return isSameMonth;
    }

    public boolean isSameYear() {
        return isSameYear;
    }

    public String getCategory() {
        return category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSameMonth(boolean sameMonth) {
        isSameMonth = sameMonth;
    }

    public void setSameYear(boolean sameYear) {
        isSameYear = sameYear;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

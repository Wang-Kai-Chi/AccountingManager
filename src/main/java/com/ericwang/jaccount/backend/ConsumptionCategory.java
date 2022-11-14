package com.ericwang.jaccount.backend;

public class ConsumptionCategory {
	private int id;
	private String name;
	
	public ConsumptionCategory(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ConsumptionCategory"+id +" [id=" + id + ", name=" + name + "]";
	}
}

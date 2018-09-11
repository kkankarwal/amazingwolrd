package com.sap.dp.visitor;

public class CD implements Visitable {
	
	  private double price;
	  private double weight;
	  
	  //accept the visitor
	  public void accept(Visitor visitor) {
	    visitor.visit(this);
	  }
	  public double getPrice() {
	    return price;
	  }
	  public double getWeight() {
	    return weight;
	  }
	}
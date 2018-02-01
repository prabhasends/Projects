package com.prabha.inventory.model;

import java.util.Set;

public class InventoryReport {
	
	private Set<InventoryItem> inventoryItemList;
	
	private Double totalValueAllItems;
	
	private Double timeBasedProfit;

	public final Set<InventoryItem> getInventoryItemList() {
		return inventoryItemList;
	}

	public final void setInventoryItemList(Set<InventoryItem> inventoryItemList) {
		this.inventoryItemList = inventoryItemList;
	}


	public final Double getTimeBasedProfit() {
		return timeBasedProfit;
	}

	public final void setTimeBasedProfit(Double timeBasedProfit) {
		this.timeBasedProfit = timeBasedProfit;
	}

	/**
	 * @return the totalValueAllItems
	 */
	public final Double getTotalValueAllItems() {
		return totalValueAllItems;
	}

	/**
	 * @param totalValueAllItems the totalValueAllItems to set
	 */
	public final void setTotalValueAllItems(Double totalValueAllItems) {
		this.totalValueAllItems = totalValueAllItems;
	}
	

}

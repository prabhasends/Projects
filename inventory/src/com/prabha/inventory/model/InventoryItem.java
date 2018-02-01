package com.prabha.inventory.model;

public class InventoryItem implements Comparable<InventoryItem>{
	
	private String itemName;
	
	private Double boughtPrice;
	
	private Integer soldQuantity;
	
	private Double soldPrice;
	
	private Integer availableQuantity;
	
	private Double value;

	public final String getItemName() {
		return itemName;
	}

	public final void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the boughtPrice
	 */
	public final Double getBoughtPrice() {
		return boughtPrice;
	}

	/**
	 * @param boughtPrice the boughtPrice to set
	 */
	public final void setBoughtPrice(Double boughtPrice) {
		this.boughtPrice = boughtPrice;
	}

	/**
	 * @return the soldQuantity
	 */
	public final Integer getSoldQuantity() {
		return soldQuantity;
	}

	/**
	 * @param soldQuantity the soldQuantity to set
	 */
	public final void setSoldQuantity(Integer soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	/**
	 * @return the soldPrice
	 */
	public final Double getSoldPrice() {
		return soldPrice;
	}

	/**
	 * @param soldPrice the soldPrice to set
	 */
	public final void setSoldPrice(Double soldPrice) {
		this.soldPrice = soldPrice;
	}

	/**
	 * @return the availableQuantity
	 */
	public final Integer getAvailableQuantity() {
		return availableQuantity;
	}

	/**
	 * @param availableQuantity the availableQuantity to set
	 */
	public final void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	

	/**
	 * @return the value
	 */
	public final Double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public final void setValue(Double value) {
		this.value = value;
	}

	@Override
    public int compareTo(InventoryItem inventoryItem) {
        
        return this.itemName.compareTo(inventoryItem.itemName);
    }
	

}

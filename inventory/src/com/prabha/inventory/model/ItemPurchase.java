package com.prabha.inventory.model;

public class ItemPurchase {
	
	private Integer purchaseQuantity;
	
	private Double purchasePrice;

	/**
	 * @return the purchaseQuantity
	 */
	public final Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}

	/**
	 * @param purchaseQuantity the purchaseQuantity to set
	 */
	public final void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	/**
	 * @return the purchasePrice
	 */
	public final Double getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @param purchasePrice the purchasePrice to set
	 */
	public final void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	@Override
	public String toString() {
		return "ItemPurchase [purchaseQuantity=" + purchaseQuantity
				+ ", purchasePrice=" + purchasePrice + "]";
	}

}

package com.prabha.inventory.model;

public class ItemSale {
	
	private Double itemSalePrice;

	/**
	 * @return the itemSalePrice
	 */
	public final Double getItemSalePrice() {
		return itemSalePrice;
	}

	/**
	 * @param itemSalePrice the itemSalePrice to set
	 */
	public final void setItemSalePrice(Double itemSalePrice) {
		this.itemSalePrice = itemSalePrice;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemSale [itemSalePrice=" + itemSalePrice + "]";
	}

	
	
}

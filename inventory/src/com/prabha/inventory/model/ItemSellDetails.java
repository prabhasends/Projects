package com.prabha.inventory.model;

import java.util.Date;

public class ItemSellDetails {
	
	private Integer itemSellId;
	
	private Integer itemSellQuantity;
	
	private Double itemSellPrice;
	
	private Date createdAt;

	/**
	 * @return the itemSellId
	 */
	public final Integer getItemSellId() {
		return itemSellId;
	}

	/**
	 * @param itemSellId the itemSellId to set
	 */
	public final void setItemSellId(Integer itemSellId) {
		this.itemSellId = itemSellId;
	}

	/**
	 * @return the itemSellQuantity
	 */
	public final Integer getItemSellQuantity() {
		return itemSellQuantity;
	}

	/**
	 * @param itemSellQuantity the itemSellQuantity to set
	 */
	public final void setItemSellQuantity(Integer itemSellQuantity) {
		this.itemSellQuantity = itemSellQuantity;
	}

	/**
	 * @return the itemSellPrice
	 */
	public final Double getItemSellPrice() {
		return itemSellPrice;
	}

	/**
	 * @param itemSellPrice the itemSellPrice to set
	 */
	public final void setItemSellPrice(Double itemSellPrice) {
		this.itemSellPrice = itemSellPrice;
	}

	/**
	 * @return the createdAt
	 */
	public final Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public final void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "ItemSellDetails [itemSellId=" + itemSellId
				+ ", itemSellQuantity=" + itemSellQuantity + ", itemSellPrice="
				+ itemSellPrice + ", createdAt=" + createdAt + "]";
	}
	
	
	
}

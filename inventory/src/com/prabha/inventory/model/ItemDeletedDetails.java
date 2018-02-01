package com.prabha.inventory.model;

import java.util.Date;

public class ItemDeletedDetails {
	
	private Integer itemDeletedId;
	
	private Integer itemDeletedQuantity;
	
	private Double itemDeletedPrice;
	
	private Date createdAt;
	
	/**
	 * @return the itemDeletedId
	 */
	public final Integer getItemDeletedId() {
		return itemDeletedId;
	}

	/**
	 * @param itemDeletedId the itemDeletedId to set
	 */
	public final void setItemDeletedId(Integer itemDeletedId) {
		this.itemDeletedId = itemDeletedId;
	}

	/**
	 * @return the itemDeletedQuantity
	 */
	public final Integer getItemDeletedQuantity() {
		return itemDeletedQuantity;
	}

	/**
	 * @param itemDeletedQuantity the itemDeletedQuantity to set
	 */
	public final void setItemDeletedQuantity(Integer itemDeletedQuantity) {
		this.itemDeletedQuantity = itemDeletedQuantity;
	}

	/**
	 * @return the itemDeletedPrice
	 */
	public final Double getItemDeletedPrice() {
		return itemDeletedPrice;
	}

	/**
	 * @param itemDeletedPrice the itemDeletedPrice to set
	 */
	public final void setItemDeletedPrice(Double itemDeletedPrice) {
		this.itemDeletedPrice = itemDeletedPrice;
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
		return "ItemDeletedDetails [itemDeletedId=" + itemDeletedId
				+ ", itemDeletedQuantity=" + itemDeletedQuantity
				+ ", itemDeletedPrice=" + itemDeletedPrice + ", createdAt="
				+ createdAt + "]";
	}
	
	
	

}

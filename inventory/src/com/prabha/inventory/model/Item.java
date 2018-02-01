package com.prabha.inventory.model;

import java.util.Date;
import java.util.List;

public class Item {

	private Integer itemId;
	
	private String itemName;
	
	private ItemPurchase itemPurchase;
	
	private ItemSale itemSale;
	
	private Boolean active; // Item is exist if it is true else it is deleted
	
	private Date createdAt;
	
	private Date modifiedAt;
	
	private List<ItemSellDetails> itemSellDetailsList;
	
	private List<ItemDeletedDetails> itemDeletedDetailsList;

	/**
	 * @return the itemId
	 */
	public final Integer getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public final void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the itemName
	 */
	public final String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public final void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemPurchase
	 */
	public final ItemPurchase getItemPurchase() {
		return itemPurchase;
	}

	/**
	 * @param itemPurchase the itemPurchase to set
	 */
	public final void setItemPurchase(ItemPurchase itemPurchase) {
		this.itemPurchase = itemPurchase;
	}

	/**
	 * @return the itemSale
	 */
	public final ItemSale getItemSale() {
		return itemSale;
	}

	/**
	 * @param itemSale the itemSale to set
	 */
	public final void setItemSale(ItemSale itemSale) {
		this.itemSale = itemSale;
	}

	/**
	 * @return the active
	 */
	public final Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public final void setActive(Boolean active) {
		this.active = active;
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

	/**
	 * @return the modifiedDate
	 */
	public final Date getModifiedAt() {
		return modifiedAt;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public final void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	/**
	 * @return the itemSaleDetailsList
	 */
	public final List<ItemSellDetails> getItemSellDetailsList() {
		return itemSellDetailsList;
	}

	/**
	 * @param itemSaleDetailsList the itemSaleDetailsList to set
	 */
	public final void setItemSellDetailsList(List<ItemSellDetails> itemSellDetailsList) {
		this.itemSellDetailsList = itemSellDetailsList;
	}

	/**
	 * @return the itemDeletedDetailsList
	 */
	public final List<ItemDeletedDetails> getItemDeletedDetailsList() {
		return itemDeletedDetailsList;
	}

	/**
	 * @param itemDeletedDetailsList the itemDeletedDetailsList to set
	 */
	public final void setItemDeletedDetailsList(List<ItemDeletedDetails> itemDeletedDetailsList) {
		this.itemDeletedDetailsList = itemDeletedDetailsList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName
				+ ", itemPurchase=" + itemPurchase + ", itemSale=" + itemSale
				+ ", active=" + active + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + ", itemSellDetailsList="
				+ itemSellDetailsList + ", itemDeletedDetailsList="
				+ itemDeletedDetailsList + "]";
	}

	
	
		

}

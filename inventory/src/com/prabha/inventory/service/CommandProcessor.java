package com.prabha.inventory.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import com.prabha.inventory.model.CommandDetails;
import com.prabha.inventory.model.InventoryItem;
import com.prabha.inventory.model.InventoryReport;
import com.prabha.inventory.model.Item;
import com.prabha.inventory.model.ItemDeletedDetails;
import com.prabha.inventory.model.ItemPurchase;
import com.prabha.inventory.model.ItemSale;
import com.prabha.inventory.model.ItemSellDetails;
import com.prabha.inventory.model.ReportGenerateDetails;
import com.prabha.inventory.util.InventoryConstants;
import com.prabha.inventory.util.StringUtil;
import com.prabha.inventory.util.Util;

public class CommandProcessor {
	
	//Hold the items and details
	private Map<String, Item> itemMap;
	
	//Add or Minus operation string
	private enum OPERATION {ADD, MINUS};
	
	//To hold the generated report list
	private List<ReportGenerateDetails> reportGenerateDetailsList;
	
	
	/**
	 * Validate the input commands and prepare command list.
	 *
	 * @param commandList the command list
	 * @return the command list
	 */
	public List<CommandDetails> processValidateAndIdentifyCommands(List<String> commandList)
	{
		InputValidator inputValidator = new InputValidator();
		
		List<CommandDetails> filteredCommandList = new ArrayList<CommandDetails>();
		
		String command = "";
		CommandDetails commandDetails = null;
		if(!Util.isListEmpty(commandList))
		{
			for(int i = 0; i < commandList.size(); i++)
			{
				command = commandList.get(i);
				if(!StringUtil.isStringEmpty(command))
				{
					commandDetails = inputValidator.validateAndIdentifyCommand(command);
					if(commandDetails != null && commandDetails.isValidCommand())
					{
						filteredCommandList.add(commandDetails);
						
					}else 
					{
						System.err.println("Input command is not valid " + command);
					}
				}
			}
		}
		return filteredCommandList;
	}
	
	/**
	 * Initialize.
	 */
	private void init()
	{
		if(itemMap == null)
		{
			itemMap = new HashMap<String, Item>();
		}
		if(reportGenerateDetailsList == null)
		{
			reportGenerateDetailsList = new ArrayList<ReportGenerateDetails>();
		}
	}
	
	
	/**
	 * Process commands.
	 *
	 * @param commandDetailsList the command details list
	 */
	public void processCommands(List<CommandDetails> commandDetailsList)
	{
		init();
		
		for (CommandDetails commandDetails : commandDetailsList)
		{
			switch (commandDetails.getCommandType().toUpperCase())
			{
				case InventoryConstants.CREATE_ITEM_COMMAND:
					createItemByCommand(commandDetails);
					break;
				case InventoryConstants.DELETE_ITEM_COMMAND:
					deleteItem(commandDetails);
					break;
				case InventoryConstants.UPDATE_BUY_QTY_COMMAND:
					updateBuyQuantity(commandDetails);
					break;
				case InventoryConstants.UPDATE_SELL_QTY_COMMAND:
					updateSellQuantity(commandDetails);
					break;
				case InventoryConstants.INVENTORY_REPORT_COMMAND:
					printReport();
					break;
				case InventoryConstants.UPDATE_SELL_PRICE_COMMAND:
					updateSellPrice(commandDetails);
					break;
				default  :
				 break;
			}
		}
		//Added for debugging. 
		
		/*for(Entry<String,Item> entry : itemMap.entrySet())
		{
			System.out.println("Item Name " + entry.getValue());
			
		}*/
		
	}
	
	private void printReport()
	{
		//To hold the inventory items
		Set<InventoryItem> inventoryItemSet = new TreeSet<InventoryItem>();
		
		/*	Before Generation of report giving 1 second so that further commands will process after a sec. 
			Because commands are processed in same milli seconds */
		try{
			Thread.sleep(1000); 
		}
		catch(InterruptedException exception)
		{
			exception.printStackTrace();
		}
		
		Date date = new Date();
		
		InventoryItem inventoryItem = null;
		
		double purchasePrice = 0.0;
		int availableQuantity = 0;			
		double itemTotalValue = 0.0;		
		double allItemsTotalValue = 0.0;		
		
		//To create a new report
		InventoryReport inventoryReport = new InventoryReport();
		
		//To calculate the profit since previous report
		Double timeBasedProfit = 0.0;
		
		for(Entry<String, Item> entryItem : itemMap.entrySet())
		{
			String key = entryItem.getKey();
			Item item = entryItem.getValue();
			//If item is active then we bring to the inventory report
			if(item != null && Boolean.TRUE.equals(item.getActive()) ) 
			{
				purchasePrice = item.getItemPurchase().getPurchasePrice();
				availableQuantity = item.getItemPurchase().getPurchaseQuantity();
				
				inventoryItem = new InventoryItem();
				inventoryItem.setItemName(key);				
				inventoryItem.setBoughtPrice(purchasePrice); // Bought Price
				
				updateSoldPriceAndQuantity(item, inventoryItem); //To update the sold price and sold quantity
				
				inventoryItem.setAvailableQuantity(availableQuantity); // Current available Quantity
				itemTotalValue = availableQuantity * purchasePrice; // Current Value
				inventoryItem.setValue(itemTotalValue);
				
				allItemsTotalValue = allItemsTotalValue + itemTotalValue; // Calculate all items total value
				
				inventoryItemSet.add(inventoryItem);
			}
			
			inventoryReport.setInventoryItemList(inventoryItemSet);
			inventoryReport.setTotalValueAllItems(allItemsTotalValue);
			
			//Get the profit
			timeBasedProfit = calculateProfit();
			inventoryReport.setTimeBasedProfit(timeBasedProfit);
		}
		//Maintain Report generation details
		reportUpdate(date, timeBasedProfit);
		//To format the report
		formatReport(inventoryReport);
	}
	
	/**
	 * Format report.
	 *
	 * @param inventoryReport the inventory report
	 */
	private void formatReport(InventoryReport inventoryReport)
	{
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.format("%45s", "INVENTORY REPORT");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------");
	    System.out.printf("%25s %15s %15s %15s %15s", "Item Name", "Bought At", "Sold At", "Available Qty", "Value");
	    System.out.println();
	    System.out.println("-------------------------------------------------------------------------------------------");
	    for(InventoryItem inventoryItem: inventoryReport.getInventoryItemList()){
	        System.out.format("%25s %15.2f %15.2f %15d %15.2f", 
	        		inventoryItem.getItemName(), inventoryItem.getBoughtPrice(), 
	        		inventoryItem.getSoldPrice(), inventoryItem.getAvailableQuantity(), inventoryItem.getValue());
	        System.out.println();
	    }
	    System.out.println("-------------------------------------------------------------------------------------------");
	    
	    System.out.format("%13s %75.2f", " Total Value ", inventoryReport.getTotalValueAllItems());
	    System.out.println();
	    System.out.format("%35s %53.2f", " Profit since from previous report ",  inventoryReport.getTimeBasedProfit());
	    System.out.println();
	}
	
	/**
	 * Update sold price and quantity.
	 *
	 * @param item the item
	 * @param inventoryItem the inventory item
	 */
	private void updateSoldPriceAndQuantity(Item item,
			InventoryItem inventoryItem)
	{
		double itemTotalSoldValue = 0.0; //Item based total sold value
		int itemTotalSoldQuantity = 0;	//Item based total sold quantity
		if(item.getItemSellDetailsList() != null && item.getItemSellDetailsList().size() > 0)
		{
			for(ItemSellDetails itemSellDetails : item.getItemSellDetailsList() )
			{
				//Calculate each item sold value and sum all
				itemTotalSoldValue = itemTotalSoldValue + (itemSellDetails.getItemSellPrice() * itemSellDetails.getItemSellQuantity()); 
				//Sum all sold quantity
				itemTotalSoldQuantity = itemTotalSoldQuantity + itemSellDetails.getItemSellQuantity();
			}
			inventoryItem.setSoldPrice(itemTotalSoldValue / itemTotalSoldQuantity); // Average Sold Price each item = Total Sold Value / Total Sold Quantity
			inventoryItem.setSoldQuantity(itemTotalSoldQuantity);
		}
		else
		{
			inventoryItem.setSoldPrice(item.getItemSale().getItemSalePrice()); // If 0 items sold then displays the expected selling price of the item.
			inventoryItem.setSoldQuantity(0);
		}
	}
	
	/**
	 * Once the Report generated then 
	 * update report details in the list.
	 *
	 * @param date the date
	 * @param timeBasedProfit the time based profit
	 */
	private void reportUpdate(Date date, Double timeBasedProfit)
	{
		reportInit();
		ReportGenerateDetails reportGenerateDetails = new ReportGenerateDetails();
		reportGenerateDetails.setReportId(reportGenerateDetailsList.size() + 1);
		reportGenerateDetails.setReportedAt(date);
		reportGenerateDetails.setIncomePrice(timeBasedProfit);
		reportGenerateDetailsList.add(reportGenerateDetails);
	}
	
	/**
	 * Report initialize.
	 */
	private void reportInit()
	{
		if(reportGenerateDetailsList == null)
		{
			reportGenerateDetailsList = new ArrayList<ReportGenerateDetails>();
		}
	}
	
	/**
	 * Calculate profit.
	 *
	 * @return the double
	 */
	private Double calculateProfit()
	{
		Date lastReportGeneratedAt = getLastReportedDate();
		double timeBasedProfit = 0.0;
		for(Entry<String, Item> entryItem : itemMap.entrySet())
		{
			Item item = entryItem.getValue();
			if(item != null && (lastReportGeneratedAt == null || 
					(lastReportGeneratedAt != null && item.getModifiedAt().getTime() >= lastReportGeneratedAt.getTime()))) 
			{
				timeBasedProfit = timeBasedProfit + calculateSellItemProfit(item, lastReportGeneratedAt);
				timeBasedProfit = timeBasedProfit + calculateDeletedItemProfit(item, lastReportGeneratedAt);
			}
		}
		return timeBasedProfit;
	}
	
	/**
	 * Calculate deleted item profit.
	 *
	 * @param item the item
	 * @param lastReportGeneratedAt the last report generated at
	 * @return the double
	 */
	private Double calculateDeletedItemProfit(Item item, Date lastReportGeneratedAt)
	{
		Double timeBasedProfit = 0.0;
		if (item != null && item.getItemDeletedDetailsList() != null && item.getItemDeletedDetailsList().size() > 0)
		{
			for (ItemDeletedDetails itemDeletedDetails : item.getItemDeletedDetailsList())
			{
				if ((lastReportGeneratedAt == null || (lastReportGeneratedAt != null
						&& itemDeletedDetails.getCreatedAt().getTime() >= lastReportGeneratedAt.getTime())))
				{
					timeBasedProfit = timeBasedProfit
							- (itemDeletedDetails.getItemDeletedPrice() * itemDeletedDetails.getItemDeletedQuantity());
				}
			}
		}
		return timeBasedProfit;
	}
	
	/**
	 * Calculate sell item profit.
	 *
	 * @param item the item
	 * @param lastReportGeneratedAt the last report generated at
	 * @return the double
	 */
	private Double calculateSellItemProfit(Item item, Date lastReportGeneratedAt)
	{
		Double timeBasedProfit = 0.0;
		if (item != null && item.getItemSellDetailsList() != null && item.getItemSellDetailsList().size() > 0)
		{
			for (ItemSellDetails itemSellDetails : item.getItemSellDetailsList())
			{
				if ((lastReportGeneratedAt == null || (lastReportGeneratedAt != null
						&& itemSellDetails.getCreatedAt().getTime() >= lastReportGeneratedAt.getTime())))
				{
					timeBasedProfit = timeBasedProfit
							+ ((itemSellDetails.getItemSellPrice() - item.getItemPurchase().getPurchasePrice()) 
									* itemSellDetails.getItemSellQuantity());
				}
			}
		}
		return timeBasedProfit;
	}
	
	
	/**
	 * Gets the last reported date.
	 *
	 * @return the last reported date
	 */
	private Date getLastReportedDate()
	{
		ReportGenerateDetails reportGenerateDetails = null;
		Date date = null;
		if(reportGenerateDetailsList != null && !reportGenerateDetailsList.isEmpty())
		{
			reportGenerateDetails = reportGenerateDetailsList.get(reportGenerateDetailsList.size() -1); 
			if(reportGenerateDetails !=null)
			{
				date = reportGenerateDetails.getReportedAt();
			}
		}
		return date;
	}
	
	
	/**
	 * Update sell price.
	 *
	 * @param commandDetails the command details
	 */
	private void updateSellPrice(CommandDetails commandDetails)
	{
		Date date = new Date();
		if(isValidPriceCommand(commandDetails))
		{
			if(isItemExist(commandDetails))
			{
				findItemAndUpdateSellPrice(commandDetails, date);
			}
			else
			{
				System.err.println("Item is not exist so you cannont update the selling price of non exist item. Passed command "+ commandDetails.getCommand());
			}
		}
	}
	
	/**
	 * Find item and update sell price.
	 *
	 * @param commandDetails the command details
	 * @param date the date
	 */
	private void findItemAndUpdateSellPrice(CommandDetails commandDetails, Date date)
	{
		Item item = findItem(commandDetails);
		if(item != null)
		{
			item.getItemSale().setItemSalePrice(Double.valueOf(commandDetails.getCommandArray()[2].trim()));
			item.setModifiedAt(date);
		}
		else
		{
			System.err.println("You cannot sell more items than available quantity. Passed command "+ commandDetails.getCommand());
		}
		
	}
	
	/**
	 * Update sell quantity.
	 *
	 * @param commandDetails the command details
	 */
	private void updateSellQuantity(CommandDetails commandDetails)
	{
		Date date = new Date();
		if(isValidQuantityCommand(commandDetails))
		{
			if(isItemExist(commandDetails))
			{
				findItemAndUpdateSellQuantity(commandDetails, date);
			}
			else
			{
				System.err.println("Item is not exist so you cannont update the quantity of non exist item. Passed command "+ commandDetails.getCommand());
			}
		}

	}
	
	/**
	 * Find item and update sell quantity.
	 *
	 * @param commandDetails the command details
	 * @param date the date
	 */
	private void findItemAndUpdateSellQuantity(CommandDetails commandDetails, Date date)
	{
		Item item = findItem(commandDetails);
		Integer sellQuantity = Integer.valueOf(commandDetails.getCommandArray()[2].trim());
		if(item.getItemPurchase().getPurchaseQuantity() >= sellQuantity)
		{
			updateItemPurchaseQty(item, OPERATION.MINUS, sellQuantity, date);
			
			List<ItemSellDetails> itemSellDetailsList = item.getItemSellDetailsList();
			ItemSellDetails itemSellDetails = new ItemSellDetails();
			
			if(itemSellDetailsList == null)
			{
				itemSellDetailsList = new ArrayList<ItemSellDetails>();				
				itemSellDetails.setItemSellId(1);
			}
			else
			{
				itemSellDetails.setItemSellId(itemSellDetailsList.size() + 1); //find max id
			}
			itemSellDetails.setItemSellQuantity(sellQuantity);
			itemSellDetails.setItemSellPrice(item.getItemSale().getItemSalePrice());
			itemSellDetails.setCreatedAt(date);
			itemSellDetailsList.add(itemSellDetails);
			
			item.setItemSellDetailsList(itemSellDetailsList);
		}
		else
		{
			System.err.println("You cannot sell more items than available quantity. "
					+ "Passed command "+ commandDetails.getCommand());
		}
		
	}
	
	
	/**
	 * Delete item.
	 *
	 * @param commandDetails the command details
	 */
	private void deleteItem(CommandDetails commandDetails)
	{
		Date date = new Date();
		if(isItemExist(commandDetails))
		{
			Item item = findItem(commandDetails);
			item.setActive(Boolean.FALSE);
			item.setModifiedAt(date);
			List<ItemDeletedDetails> itemDeletedDetailsList = item.getItemDeletedDetailsList();
			ItemDeletedDetails itemDeletedDetails = new ItemDeletedDetails();
			
			if(itemDeletedDetailsList == null)
			{
				itemDeletedDetailsList = new ArrayList<ItemDeletedDetails>();				
				itemDeletedDetails.setItemDeletedId(1);
			}
			else
			{
				itemDeletedDetails.setItemDeletedId(itemDeletedDetailsList.size() + 1); //find max id + 1
			}
			itemDeletedDetails.setItemDeletedQuantity(item.getItemPurchase().getPurchaseQuantity());
			itemDeletedDetails.setItemDeletedPrice(item.getItemPurchase().getPurchasePrice());
			itemDeletedDetails.setCreatedAt(date);
			itemDeletedDetailsList.add(itemDeletedDetails);
			
			item.setItemDeletedDetailsList(itemDeletedDetailsList);
		}
		else 
		{
			System.err.println("Item is not found to delete. "
					+ "Passed command "+ commandDetails.getCommand());
		}
		
	}
	
	
	/**
	 * Update buy quantity.
	 *
	 * @param commandDetails the command details
	 */
	private void updateBuyQuantity(CommandDetails commandDetails)
	{
		Date date = new Date();
		if(isValidQuantityCommand(commandDetails))
		{
			if(isItemExist(commandDetails))
			{
				Item item = findItem(commandDetails);
				updateItemPurchaseQty(item, OPERATION.ADD, 
						Integer.valueOf(commandDetails.getCommandArray()[2].trim()), date);
			}
			else
			{
				System.err.println("Item is not exist so you cannont update the quantity of non exist item. "
						+ "Passed command "+ commandDetails.getCommand());
			}
		}
	}
	
	
	/**
	 * Update item purchase qty.
	 *
	 * @param item the item
	 * @param operation the operation
	 * @param quantity the quantity
	 * @param date the date
	 */
	private void updateItemPurchaseQty(Item item, OPERATION operation, Integer quantity, Date date)
	{
		if(OPERATION.ADD.equals(operation))
		{
			item.getItemPurchase().setPurchaseQuantity(
				item.getItemPurchase().getPurchaseQuantity() + quantity);
			
		}else if(OPERATION.MINUS.equals(operation))
		{
			item.getItemPurchase().setPurchaseQuantity(
				item.getItemPurchase().getPurchaseQuantity() - quantity);
		}
		item.setModifiedAt(date);
	}
	
	
	/**
	 * Find item.
	 *
	 * @param commandDetails the command details
	 * @return the item
	 */
	private Item findItem(CommandDetails commandDetails)
	{
		Item item = null;
		for(Entry<String, Item> entry : itemMap.entrySet())
		{
			if (entry.getKey().equalsIgnoreCase(commandDetails.getCommandArray()[1].trim())) 
			{
				item = entry.getValue();
			}
		}
		return item;
	}
	
	
	/**
	 * Checks if is valid price command.
	 *
	 * @param commandDetails the command details
	 * @return true, if is valid price command
	 */
	private boolean isValidPriceCommand(CommandDetails commandDetails) 
	{
		boolean isValid = false;
		if (commandDetails != null)
		{
			try
			{
				String[] stringArr = commandDetails.getCommandArray();
				Double.valueOf(stringArr[2].trim());
				isValid = true;
			}
			catch (Exception exception)
			{
				System.err.println("Price value is not valid. Passed command "+ commandDetails.getCommand());
			}
		}
		return isValid;
	}
	
	
	/**
	 * Checks if is valid quantity command.
	 *
	 * @param commandDetails the command details
	 * @return true, if is valid quantity command
	 */
	private boolean isValidQuantityCommand(CommandDetails commandDetails) 
	{
		boolean isValid = false;
		if(commandDetails != null)
		{
			try
			{
				String[] stringArr = commandDetails.getCommandArray();
				Integer.valueOf(stringArr[2].trim());
				isValid = true;
			}
			catch(Exception exception)
			{
				System.err.println("Quantity value is not valid. Passed command "+ commandDetails.getCommand());
			}
		}
		return isValid;
	}
	
	
	/**
	 * Creates the item by command.
	 *
	 * @param commandDetails the command details
	 */
	private void createItemByCommand(CommandDetails commandDetails)
	{
		Date date = new Date();
		Item item = null;
		String[] commandArray = null;
		if(isValidCreateCommand(commandDetails))
		{
			if(isItemExist(commandDetails))
			{
				item = findItem(commandDetails);
				if(Boolean.FALSE.equals(item.getActive()))
				{
					createItem(item, commandDetails.getCommandArray(), date);					
				}
				else
				{
					System.err.println("Item exist so you cannot add again. Entered command " + commandDetails.getCommand());
				}
			}
			else 
			{
				commandArray = commandDetails.getCommandArray();
				item = new Item();
				item.setItemId(itemMap.size() + 1);
				item.setItemName(commandArray[1].trim());
				createItem(item, commandArray, date);
				itemMap.put(commandArray[1].trim(), item);
				
			}
			
		}
		
	}
	
	/**
	 * Creates the item.
	 *
	 * @param item the item
	 * @param commandArray the command array
	 * @param date the date
	 */
	private void createItem(Item item, String[] commandArray, Date date)
	{
		item.setCreatedAt(date);
		item.setActive(Boolean.TRUE);
		
		ItemPurchase itemPurchase = new ItemPurchase();
		itemPurchase.setPurchasePrice(Double.valueOf(commandArray[2].trim()));
		itemPurchase.setPurchaseQuantity(0);
		item.setItemPurchase(itemPurchase);
		
		ItemSale itemSale = new ItemSale();
		itemSale.setItemSalePrice(Double.valueOf(commandArray[3].trim()));
		item.setItemSale(itemSale);
	}
	
	
	/**
	 * Checks if valid create command.
	 *
	 * @param commandDetails the command details
	 * @return true, if is valid create command
	 */
	private boolean isValidCreateCommand(CommandDetails commandDetails)
	{
		boolean isValid = false;
		if(commandDetails != null)
		{
			try
			{
				String[] stringArr = commandDetails.getCommandArray();
				Double.valueOf(stringArr[2].trim());
				Double.valueOf(stringArr[3].trim());
				isValid = true;
			}
			catch(Exception exception)
			{
				System.err.println("Passed value is not double in the command "+ commandDetails.getCommand());
				isValid = false;
			}
		}
		return isValid;
	}
	
	/**
	 * Checks if item is exist.
	 *
	 * @param commandDetails the command details
	 * @return true, if is item exist
	 */
	private boolean isItemExist(CommandDetails commandDetails)
	{
		boolean isExist = false;
		for(Entry<String, Item> entry : itemMap.entrySet())
		{
			if (entry.getKey().equalsIgnoreCase(commandDetails.getCommandArray()[1].trim())) 
			{
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	

}

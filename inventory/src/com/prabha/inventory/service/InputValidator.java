package com.prabha.inventory.service;

import com.prabha.inventory.model.CommandDetails;
import com.prabha.inventory.util.InventoryConstants;
import com.prabha.inventory.util.StringUtil;

public class InputValidator {
	
	
	/**
	 * Validate and identify command.
	 *
	 * @param userCommand the user command
	 * @return the command details
	 */
	public CommandDetails validateAndIdentifyCommand(String userCommand) 
	{
		CommandDetails commandDetails = null;
		
		if(!StringUtil.isStringEmpty(userCommand))
		{
			String[] commandInputs = StringUtil.parseString(userCommand);
			
			if(!StringUtil.isStringEmpty(commandInputs[0]))
			{
				switch (commandInputs[0].toUpperCase())
				{
					case InventoryConstants.CREATE_ITEM_COMMAND:
						commandDetails = createCommandDetailsAndValidate(userCommand, 
								commandInputs, 4, InventoryConstants.CREATE_ITEM_COMMAND);
						break;
					case InventoryConstants.DELETE_ITEM_COMMAND:
						commandDetails = createCommandDetailsAndValidate(userCommand, 
								commandInputs, 2, InventoryConstants.DELETE_ITEM_COMMAND);
						break;
					case InventoryConstants.UPDATE_BUY_QTY_COMMAND:
						commandDetails = createCommandDetailsAndValidate(userCommand, 
								commandInputs, 3, InventoryConstants.UPDATE_BUY_QTY_COMMAND);
						break;
					case InventoryConstants.UPDATE_SELL_QTY_COMMAND:
						commandDetails = createCommandDetailsAndValidate(userCommand, 
								commandInputs, 3, InventoryConstants.UPDATE_SELL_QTY_COMMAND);
						break;
					case InventoryConstants.INVENTORY_REPORT_COMMAND:
						commandDetails = createCommandDetailsAndValidate(userCommand, 
								commandInputs, 1, InventoryConstants.INVENTORY_REPORT_COMMAND);
						break;
					case InventoryConstants.UPDATE_SELL_PRICE_COMMAND:
						commandDetails = createCommandDetailsAndValidate(userCommand, 
								commandInputs, 3, InventoryConstants.UPDATE_SELL_PRICE_COMMAND);
						break;
					default :
						break;
				}
			}
		}
		return commandDetails;
	}
	
	/**
	 * Creates the command details and validate.
	 *
	 * @param command the command
	 * @param commandInputs the command inputs
	 * @param lengthArguments the length arguments
	 * @param commandType the command type
	 * @return the command details
	 */
	private CommandDetails createCommandDetailsAndValidate(String command, 
			String[] commandInputs, int lengthArguments,
			String commandType)
	{
		CommandDetails commandDetails = new CommandDetails();
		commandDetails.setCommand(command);		
		commandDetails.setCommandArray(commandInputs);		
		commandDetails.setValidCommand(StringUtil.isValidArgumentLength(commandInputs, lengthArguments));
		commandDetails.setCommandType(commandType);	
		return commandDetails;
	}
	
	
	

}

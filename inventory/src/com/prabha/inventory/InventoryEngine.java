package com.prabha.inventory;

import java.util.List;

import com.prabha.inventory.model.CommandDetails;
import com.prabha.inventory.service.CommandProcessor;
import com.prabha.inventory.util.ScanHelper;

public class InventoryEngine {

	public static void main(String[] args) 
	{
		//To scan input commands and get the comamnd list
		ScanHelper scanHelper = new ScanHelper();
		List<String> commandList = scanHelper.scanningProcess();
		
		//To validate and process the input commands
		CommandProcessor commandProcessor = new CommandProcessor();		
		List<CommandDetails> commandDetailsList = commandProcessor.processValidateAndIdentifyCommands(commandList);
		commandProcessor.processCommands(commandDetailsList);
	}
	
}

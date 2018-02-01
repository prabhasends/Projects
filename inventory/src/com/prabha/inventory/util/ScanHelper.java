package com.prabha.inventory.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScanHelper {
	
	/**
	 * Scanning process.
	 *
	 * @return the list
	 */
	public List<String> scanningProcess() {
		
		//Create a list of String which can be used to add the input commands for processing
		List<String> commandList = new ArrayList<String>();
	    // create a scanner so we can read the command-line input
		
		File file = null;
		Scanner scanner = null;
		try
		{
			
			file = new File("..\\inventory\\src\\commands.txt");			
			scanner = new Scanner(file);
			//scanner = new Scanner(System.in); // Added file for easy inputs

			do {
				String scancommand = scanner.nextLine();
				if(scancommand.contains("##")) //This is comment in command input file so ignore it
				{
					continue;
				}
				else if (scancommand.equals("#")) //Assume this is end of inventory
				{
					break; 
				} 
				else 
				{
					commandList.add(scancommand); //Add commands in the list
				}
		
			} while (scanner.hasNext());
		}
		catch(FileNotFoundException fnfException)
		{
			System.err.println("Please input the file path of input commands" + fnfException.getMessage());
		}
		catch(Exception exception)
		{
			System.err.println("Exception occured while processing the input commands" + exception.getMessage());
		}finally
		{
			scanner.close();
		}
		
		return commandList;
		
	}

}

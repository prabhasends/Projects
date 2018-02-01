package com.prabha.inventory.model;

import java.util.Arrays;

public class CommandDetails {
	
	private String command;
	
	private String commandArray[];
	
	private String commandType;
	
	private boolean validCommand;
	
	/**
	 * @return the command
	 */
	public final String getCommand() {
		return command;
	}

	/**
	 * @param command the command to set
	 */
	public final void setCommand(String command) {
		this.command = command;
	}
	
	

	/**
	 * @return the commandArray
	 */
	public final String[] getCommandArray() {
		return commandArray;
	}

	/**
	 * @param commandArray the commandArray to set
	 */
	public final void setCommandArray(String[] commandArray) {
		this.commandArray = commandArray;
	}

	/**
	 * @return the commandType
	 */
	public final String getCommandType() {
		return commandType;
	}

	/**
	 * @param commandType the commandType to set
	 */
	public final void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	/**
	 * @return the validCommand
	 */
	public final boolean isValidCommand() {
		return validCommand;
	}

	/**
	 * @param validCommand the validCommand to set
	 */
	public final void setValidCommand(boolean validCommand) {
		this.validCommand = validCommand;
	}

	@Override
	public String toString() {
		return "CommandDetails [command=" + command + ", commandArray="
				+ Arrays.toString(commandArray) + ", commandType="
				+ commandType + ", validCommand=" + validCommand + "]";
	}
	
	
	
	

}

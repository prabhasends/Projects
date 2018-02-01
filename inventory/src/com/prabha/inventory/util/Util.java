package com.prabha.inventory.util;

import java.util.List;

public class Util {
	
	/**
	 * Checks if list is empty.
	 *
	 * @param listObject the list object
	 * @return true, if is list empty
	 */
	public static boolean isListEmpty(List<?> listObject)
	{
		boolean isEmpty = true;
		if(listObject != null && !listObject.isEmpty())
		{
			isEmpty = false;
		}
		return isEmpty;
	}

}

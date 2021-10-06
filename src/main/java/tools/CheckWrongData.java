package tools;

import models.Users;

/** 
 * Checks if the input of things are correct for their fields. 
 *  **/
public class CheckWrongData {
	/** 
	 * Checks if any number of strings are empty
	 * @param strings - any number of strings
	 *  @return TRUE if any of the strings are empty.
	 *  **/
	public static boolean isEmptyString(String... strings) {
		for(String tempo : strings)
			if(tempo == null || tempo.isEmpty())
				return true;
		return false;
	}

	/**
	 * 
	 * @return FALSE if user is null or the password is wrong
	 */
	public static boolean checkUsersPassword(Users user, String pWord) {
		if(VerboseFlags.checkUsersPassword())
			System.out.println("------------------checkUsersPassword-------------------");
		
		if(user==null) {
			if(VerboseFlags.checkUsersPassword()) {
				System.out.println("There was no user with that id");
				System.out.println("-----------------/checkUsersPassword-------------------");
			}
			return false;
		}

		if(pWord.equals(user.getNonSecurePassword())) { 
			if(VerboseFlags.checkUsersPassword())
				System.out.println("-----------------/checkUsersPassword-------------------");
			return true;
		} else {
			if(VerboseFlags.checkUsersPassword()) {
				System.out.println("Password was wrong");
				System.out.println("-----------------/checkUsersPassword-------------------");
			}
			return false;
		}

		
	}

	/** @return TRUE if all of the strings can be parsed as a Double **/
	public static boolean canParseAsDouble(String... isInt) {
		if(isEmptyString(isInt))
			return false;
		for(String tempo : isInt)
			try {
				Double.parseDouble(tempo);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}

	/** @return TRUE if all of the strings can be parsed as a Boolean **/
	public static boolean canParseAsBoolean(String... isBool) {
		if(isEmptyString(isBool))
			return false;
		for(String tempo : isBool)
			try {
				Boolean.parseBoolean(tempo);
			} catch(NumberFormatException e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}
}

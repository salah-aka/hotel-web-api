package dev.hotel.utile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Salaheddine El Majdoub
 *
 */
public class Messages {
	
	/**
	 * Listes des messages 
	 */
	private List<String> messages;

	

	/**
	 * @param messages
	 */
	public Messages(List<String> messages) {
		super();
		this.messages = messages;
	}
	
	
	/**
	 * 
	 */
	public Messages() {
		super();
		this.messages = new ArrayList<String>();
	}
	
	/**
	 * @param message
	 */
	public Messages(String message) {

		super();
		this.messages = Arrays.asList(message);

	}
	
	public void addMessage(String message) {

		messages.add(message);

	}
	
	public boolean isEmpty() {

		return this.messages.isEmpty();

	}
	

}

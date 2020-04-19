package dev.hotel.exception;

import dev.hotel.utile.Messages;

public class ReservationNTException extends HotelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Messages messages;




	/**
	 * @param messages
	 */
	public ReservationNTException(Messages messages) {
		super();
		this.messages = messages;
	}


	public ReservationNTException() {

		super();
	}
	
	public ReservationNTException(String message, Throwable cause) {
		super(message, cause);

	}


	public ReservationNTException(String message) {
		super(message);

	}


	public ReservationNTException(Throwable cause) {
		super(cause);
	}
	

	public Messages getMessages() {
		return messages;
	}


}

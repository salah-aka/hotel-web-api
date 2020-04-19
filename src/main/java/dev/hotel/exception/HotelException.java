package dev.hotel.exception;

/**
 * @author Salaheddine El Majdoub
 *
 */
public class HotelException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HotelException() {
	}

	public HotelException(String message) {
		super(message);
	}

	public HotelException(String message, Throwable cause) {
		super(message, cause);
	}

	public HotelException(Throwable cause) {
		super(cause);
	}

}

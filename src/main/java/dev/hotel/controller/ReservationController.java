package dev.hotel.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.exception.ReservationNTException;
import dev.hotel.metier.ReservationService;
import dev.hotel.utile.Messages;

/**
 * @author Salaheddine El Majdoub
 *
 */
@RestController
@RequestMapping("reservations")
public class ReservationController {

	private ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@PostMapping
	public ResponseEntity<?> postReservation(@RequestBody @Valid CreerReservationDto r, BindingResult result) {

		if (result.hasErrors()) {

			return ResponseEntity.badRequest().body(new Messages("Toutes les champs doivent etre remplis"));

		} else {

			return ResponseEntity.ok(reservationService.creerReservation(r.getDateDebut(), r.getDateFin(),

					r.getClientId(), r.getChambres()));
		}
	}

	@ExceptionHandler(ReservationNTException.class)

	public ResponseEntity<Messages> actiReservationException(ReservationNTException e) {

		return ResponseEntity.badRequest().body(e.getMessages());

	}

}

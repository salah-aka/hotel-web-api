package dev.hotel.metier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.exception.ReservationNTException;
import dev.hotel.exception.UuidIntrouvableException;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;
import dev.hotel.utile.Messages;

/**
 * @author Salaheddine El Majdoub
 *
 */
@Service
public class ReservationService {

	private ReservationRepository reservationRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository,
			ChambreRepository chambreRepository) {
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}

	@Transactional
	public Reservation creerReservation(LocalDate dateDebut, LocalDate dateFin, String clientId, List<String> chambresId) {
		
		Optional<Client> client = null;
		List<Chambre> chambres = new ArrayList<>();
		Messages messages = new Messages();
		String chambreIdError = "";

		// recherche du client
		try {

			Optional<Client> isClientExistent = clientRepository.findById(UUID.fromString(clientId));
			if (!isClientExistent.isPresent()) {
				
				messages.addMessage("Uuid client non trouvé");
			}

			// recherche des chambres
			try {
				
				for (String chambreId : chambresId) {
					Optional<Chambre> isChambreExist = chambreRepository.findById(UUID.fromString(chambreId));

					if (isChambreExist.isPresent()) {

						chambres.add(isChambreExist.get());

					} else { messages.addMessage("la chambre " + chambreId + " n'existe pas");
					}
				}

			} catch (IllegalArgumentException e) {
				messages.addMessage("L'uuid de la chambre " + chambreIdError + " est invalide.");
			}
		} catch (IllegalArgumentException e) {
			messages.addMessage("Uuid du client " + clientId + " est invalide.");
		}

		// creation et sauvegarde de la reservation et le catch de l'exception en cas d'erreur

		if (messages.isEmpty()) {

			Reservation reservation = new Reservation(dateDebut, dateFin, client.get(), chambres);
			return reservationRepository.save(reservation);
			
		} else {
			throw new ReservationNTException(messages);
		}
		
	
	}
}

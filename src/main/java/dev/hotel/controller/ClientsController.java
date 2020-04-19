package dev.hotel.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.exception.UuidIntrouvableException;
import dev.hotel.metier.ClientService;

@RestController
@RequestMapping("clients")
public class ClientsController {

	private ClientService clientService;

	public ClientsController(ClientService clientService) {
		this.clientService = clientService;
	}

	// @GetMapping
	@RequestMapping(method = RequestMethod.GET)
	public List<Client> listerClients(@RequestParam Integer start, @RequestParam Integer size) {
		return clientService.listerClients(start, size);
	}

	@GetMapping("{uuid}")
	public ResponseEntity<Client> identifiantClient(@PathVariable UUID uuid) {

		return clientService.chercherClient(uuid).map(client -> ResponseEntity.ok().body(client))
				.orElseThrow(UuidIntrouvableException::new);
	}

	@ExceptionHandler(UuidIntrouvableException.class)
	public ResponseEntity<String> onUuidNotFound(UuidIntrouvableException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UUID non valide");
	}

	@PostMapping
	public ResponseEntity<?> ajouterClient(@RequestBody @Valid CreerClientDto client, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.header("result", "Les noms et prénoms doivent contenir au moins 2 caractères").build();
		} else {
			Client clientSauvegarde = clientService.creerClient(client.getNom(), client.getPrenoms());
			return ResponseEntity.status(HttpStatus.FOUND)
					.header("result", "Le client a été créé").body(clientSauvegarde);
		}
	}
}

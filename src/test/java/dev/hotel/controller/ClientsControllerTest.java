package dev.hotel.controller;


import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import dev.hotel.entite.Client;
import dev.hotel.metier.ClientService;
import dev.hotel.repository.ClientRepository;

@WebMvcTest(ClientsController.class)
class ClientsControllerTest {

	@Autowired
	MockMvc mockMvc; // simuler les requettes HTTP

//	@MockBean
//	ClientRepository clientRepository;
	
	@MockBean 
	ClientService clientService;

	// envoie d'un requette http et verification de données
	@Test
	void listeClientsTest() throws Exception {
		when(this.clientService.listerClients(0, 2))
        .thenReturn(Arrays.asList(new Client("El Majdoub", "Salaheddine")));

		mockMvc.perform(MockMvcRequestBuilders.get("/clients?start=0&size=2"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("El Majdoub"));

	}

	@Test
	void clientUuidTest() throws Exception {
		UUID uuid = UUID.fromString("f9a18170-9605-4fe6-83c8-d03a53e08bfe");
		when(this.clientService.chercherClient(uuid))
		.thenReturn(Optional.of(new Client("Don", "Duck")));

		this.mockMvc.perform(MockMvcRequestBuilders.get("/clients/f9a18170-9605-4fe6-83c8-d03a53e08bfe"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.prenoms").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Don"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.prenoms").value("Duck"));
	}

	@Test
	void ajouterClient() throws Exception {
		
		
	}

}

package dev.hotel.metier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@Service
public class ClientService {
	
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> listerClients(Integer page, Integer size) {
        return clientRepository.findAll(PageRequest.of(page, size)).toList();
    }

    public Optional<Client> chercherClient(UUID uuidClient) {
        return clientRepository.findById(uuidClient);
    }

    @Transactional
    public Client creerClient(String nom, String prenoms) {
        return clientRepository.save(new Client(nom, prenoms));
    }


}

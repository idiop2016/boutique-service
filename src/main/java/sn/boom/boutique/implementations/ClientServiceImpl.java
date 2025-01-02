package sn.boom.boutique.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.boom.boutique.entities.Client;
import sn.boom.boutique.exceptions.ResourceNotFoundException;
import sn.boom.boutique.repositories.ClientRepository;
import sn.boom.boutique.services.ClientService;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'id : " + id));
    }

    @Override
    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<Client> listClients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listClients'");
    }
}
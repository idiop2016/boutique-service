package sn.boom.boutique.services;

import sn.boom.boutique.entities.Client;
import java.util.List;

public interface ClientService {
    Client saveClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Long id);
    Client getClient(Long id);
    Client getClientByEmail(String email);
    List<Client> getAllClients();
}
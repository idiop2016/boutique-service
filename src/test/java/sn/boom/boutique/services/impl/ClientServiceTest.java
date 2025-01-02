package sn.boom.boutique.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sn.boom.boutique.entities.Client;
import sn.boom.boutique.exceptions.ResourceNotFoundException;
import sn.boom.boutique.impl.ClientServiceImpl;
import sn.boom.boutique.repositories.ClientRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        client = new Client();
        client.setId(1L);
        client.setNom("Doe");
        client.setPrenom("John");
        client.setEmail("john.doe@example.com");
        client.setTelephone("123456789");
    }

    @Test
    void saveClient_Success() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedClient = clientService.saveClient(client);

        assertNotNull(savedClient);
        assertEquals(client.getNom(), savedClient.getNom());
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void getClient_Success() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Client foundClient = clientService.getClient(1L);

        assertNotNull(foundClient);
        assertEquals(client.getId(), foundClient.getId());
    }

    @Test
    void getClient_NotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.getClient(1L));
    }

    @Test
    void listClients_Success() {
        List<Client> clients = Arrays.asList(client);
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> foundClients = clientService.listClients();

        assertNotNull(foundClients);
        assertFalse(foundClients.isEmpty());
        assertEquals(1, foundClients.size());
    }

    @Test
    void deleteClient_Success() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        doNothing().when(clientRepository).deleteById(1L);

        clientService.deleteClient(1L);

        verify(clientRepository).deleteById(1L);
    }

    @Test
    void deleteClient_NotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.deleteClient(1L));
    }
}
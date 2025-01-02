package sn.boom.boutique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.boom.boutique.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
}
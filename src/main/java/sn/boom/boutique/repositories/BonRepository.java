package sn.boom.boutique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.boom.boutique.entities.Bon;
import sn.boom.boutique.entities.Client;
import java.util.List;

public interface BonRepository extends JpaRepository<Bon, Long> {
    List<Bon> findByClient(Client client);
    Bon findByNumero(String numero);
}
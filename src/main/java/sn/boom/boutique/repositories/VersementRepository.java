package sn.boom.boutique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.boom.boutique.entities.Compte;
import sn.boom.boutique.entities.Versement;
import java.util.List;

public interface VersementRepository extends JpaRepository<Versement, Long> {
    List<Versement> findByCompte(Compte compte);
}
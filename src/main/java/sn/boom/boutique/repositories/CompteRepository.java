package sn.boom.boutique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.boom.boutique.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    Compte findByNumero(String numero);
}
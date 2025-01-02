package sn.boom.boutique.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.boom.boutique.entities.Produit;
import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByCategorie(String categorie);
    List<Produit> findByPrixLessThan(Double prix);
}
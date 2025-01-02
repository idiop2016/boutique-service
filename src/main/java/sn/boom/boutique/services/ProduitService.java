package sn.boom.boutique.services;

import sn.boom.boutique.entities.Produit;
import java.util.List;

public interface ProduitService {
    Produit saveProduit(Produit produit);
    Produit getProduit(Long id);
    Produit updateProduit(Produit produit);
    Produit updateStock(Long id, int quantite);
    void deleteProduit(Long id);
    List<Produit> getAllProduits();
    List<Produit> getProduitsByCategorie(String categorie);
    List<Produit> getProduitsByPrixLessThan(Double prix);
    
}
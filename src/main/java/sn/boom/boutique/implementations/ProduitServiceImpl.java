package sn.boom.boutique.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.boom.boutique.entities.Produit;
import sn.boom.boutique.exceptions.InsufficientStockException;
import sn.boom.boutique.exceptions.ResourceNotFoundException;
import sn.boom.boutique.repositories.ProduitRepository;
import sn.boom.boutique.services.ProduitService;
import java.util.List;

/**
 * Implémentation du service de gestion des produits de la boutique.
 * Cette classe fournit toutes les opérations nécessaires pour gérer le cycle de vie
 * des produits : création, lecture, mise à jour, suppression et gestion des stocks.
 *
 * @author Ibrahima DIOP
 * @version 1.0
 * @see ProduitService
 * @see Produit
 */
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    /**
     * Repository pour l'accès aux données des produits.
     */
    private final ProduitRepository produitRepository;

    /**
     * Constructeur pour l'injection de dépendances.
     *
     * @param produitRepository le repository des produits
     */
    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    /**
     * Enregistre un nouveau produit ou met à jour un produit existant.
     * Si le produit a un ID, il sera mis à jour, sinon un nouveau produit sera créé.
     *
     * @param produit le produit à sauvegarder
     * @return le produit sauvegardé avec son ID généré
     */
    @Override
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    /**
     * Recherche un produit par son identifiant.
     *
     * @param id l'identifiant du produit recherché
     * @return le produit trouvé
     * @throws ResourceNotFoundException si aucun produit n'est trouvé avec cet ID
     */
    @Override
    public Produit getProduit(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'id : " + id));
    }

    /**
     * Récupère la liste complète des produits disponibles dans la boutique.
     *
     * @return une liste contenant tous les produits
     */
    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    /**
     * Met à jour les informations d'un produit existant.
     * Vérifie d'abord si le produit existe avant la mise à jour.
     *
     * @param produit le produit avec les nouvelles informations
     * @return le produit mis à jour
     * @throws ResourceNotFoundException si le produit à mettre à jour n'existe pas
     */
    @Override
    public Produit updateProduit(Produit produit) {
        getProduit(produit.getId()); // Vérifie l'existence du produit
        return produitRepository.save(produit);
    }

    /**
     * Met à jour le stock d'un produit en ajoutant ou retirant une quantité spécifiée.
     * Une quantité positive augmente le stock, une quantité négative le diminue.
     *
     * @param id l'identifiant du produit
     * @param quantite la quantité à ajouter (positive) ou retirer (négative)
     * @return le produit avec son stock mis à jour
     * @throws ResourceNotFoundException si le produit n'existe pas
     * @throws InsufficientStockException si la quantité à retirer est supérieure au stock disponible
     */
    @Override
    public Produit updateStock(Long id, int quantite) {
        Produit produit = getProduit(id);
        int newStock = produit.getQuantiteStock() + quantite;
        if (newStock < 0) {
            throw new InsufficientStockException("Stock insuffisant pour le produit : " + id);
        }
        produit.setQuantiteStock(newStock);
        return produitRepository.save(produit);
    }

    /**
     * Supprime un produit de la base de données.
     * Vérifie d'abord si le produit existe avant la suppression.
     *
     * @param id l'identifiant du produit à supprimer
     * @throws ResourceNotFoundException si le produit à supprimer n'existe pas
     */
    @Override
    public void deleteProduit(Long id) {
        getProduit(id); // Vérifie l'existence du produit
        produitRepository.deleteById(id);
    }

    /**
     * Recherche tous les produits d'une catégorie spécifique.
     * Cette méthode permet de filtrer les produits par leur catégorie.
     *
     * @param categorie la catégorie de produits recherchée
     * @return une liste des produits appartenant à la catégorie spécifiée
     */
    @Override
    public List<Produit> getProduitsByCategorie(String categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    /**
     * Recherche tous les produits dont le prix est inférieur à une valeur donnée.
     * Cette méthode est utile pour trouver des produits dans une gamme de prix spécifique.
     *
     * @param prix le prix maximum des produits à rechercher
     * @return une liste des produits dont le prix est inférieur au prix spécifié
     */
    @Override
    public List<Produit> getProduitsByPrixLessThan(Double prix) {
        return produitRepository.findByPrixLessThan(prix);
    }
}

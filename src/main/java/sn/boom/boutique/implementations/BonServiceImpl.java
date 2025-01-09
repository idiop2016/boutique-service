package sn.boom.boutique.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.boom.boutique.entities.Bon;
import sn.boom.boutique.entities.Client;
import sn.boom.boutique.entities.Produit;
import sn.boom.boutique.exceptions.InsufficientStockException;
import sn.boom.boutique.exceptions.ResourceNotFoundException;
import sn.boom.boutique.repositories.BonRepository;
import sn.boom.boutique.services.BonService;
import sn.boom.boutique.services.ProduitService;
import java.util.List;

/**
 * Implémentation du service BonService.
 * Cette classe gère les opérations liées aux bons, y compris la création, la mise à jour, la suppression et la récupération des bons.
 */
@Service
@Transactional
@AllArgsConstructor
public class BonServiceImpl implements BonService {
    
    private final BonRepository bonRepository; // Repository pour gérer les bons
    private final ProduitService produitService; // Service pour gérer les produits

    /**
     * Enregistre un nouveau bon.
     * Vérifie d'abord si le stock du produit est suffisant avant d'enregistrer le bon.
     *
     * @param bon Le bon à enregistrer
     * @return Le bon enregistré
     * @throws InsufficientStockException si le stock est insuffisant pour le produit associé
     */
    @Override
    public Bon saveBon(Bon bon) {
        Produit produit = produitService.getProduit(bon.getProduit().getId());
        if (produit.getQuantiteStock() < bon.getQuantite()) {
            throw new InsufficientStockException("Stock insuffisant pour le produit : " + produit.getId());
        }
        produitService.updateStock(produit.getId(), -bon.getQuantite());
        return bonRepository.save(bon);
    }

    /**
     * Met à jour un bon existant.
     *
     * @param bon Le bon à mettre à jour
     * @return Le bon mis à jour
     */
    @Override
    public Bon updateBon(Bon bon) {
        return bonRepository.save(bon);
    }

    /**
     * Supprime un bon par son ID.
     *
     * @param id L'ID du bon à supprimer
     */
    @Override
    public void deleteBon(Long id) {
        bonRepository.deleteById(id);
    }

    /**
     * Récupère un bon par son ID.
     *
     * @param id L'ID du bon à récupérer
     * @return Le bon correspondant
     * @throws ResourceNotFoundException si aucun bon n'est trouvé avec l'ID spécifié
     */
    @Override
    public Bon getBon(Long id) {
        return bonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bon non trouvé avec l'id : " + id));
    }

    /**
     * Récupère un bon par son numéro.
     *
     * @param numero Le numéro du bon à récupérer
     * @return Le bon correspondant
     */
    @Override
    public Bon getBonByNumero(String numero) {
        return bonRepository.findByNumero(numero);
    }

    /**
     * Récupère tous les bons.
     *
     * @return La liste de tous les bons
     */
    @Override
    public List<Bon> getAllBons() {
        return bonRepository.findAll();
    }

    /**
     * Récupère tous les bons associés à un client.
     *
     * @param client Le client dont on veut récupérer les bons
     * @return La liste des bons associés au client
     */
    @Override
    public List<Bon> getBonsByClient(Client client) {
        return bonRepository.findByClient(client);
    }

    /**
     * Valide un bon par son ID.
     * La logique de validation doit être ajoutée ici.
     *
     * @param id L'ID du bon à valider
     * @return Le bon validé
     */
    @Override
    public Bon validerBon(Long id) {
        Bon bon = getBon(id);
        // Ajouter la logique de validation
        return bonRepository.save(bon);
    }

    /**
     * Annule un bon par son ID.
     * Restaure le stock du produit associé au bon.
     *
     * @param id L'ID du bon à annuler
     * @return Le bon annulé
     */
    @Override
    public Bon annulerBon(Long id) {
        Bon bon = getBon(id);
        // Restaurer le stock
        produitService.updateStock(bon.getProduit().getId(), bon.getQuantite());
        bonRepository.delete(bon);
        return bon;
    }
}
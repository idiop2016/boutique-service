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

@Service
@Transactional
@AllArgsConstructor
public class BonServiceImpl implements BonService {
    private final BonRepository bonRepository;
    private final ProduitService produitService;

    @Override
    public Bon saveBon(Bon bon) {
        Produit produit = produitService.getProduit(bon.getProduit().getId());
        if (produit.getQuantiteStock() < bon.getQuantite()) {
            throw new InsufficientStockException("Stock insuffisant pour le produit : " + produit.getId());
        }
        produitService.updateStock(produit.getId(), -bon.getQuantite());
        return bonRepository.save(bon);
    }

    @Override
    public Bon updateBon(Bon bon) {
        return bonRepository.save(bon);
    }

    @Override
    public void deleteBon(Long id) {
        bonRepository.deleteById(id);
    }

    @Override
    public Bon getBon(Long id) {
        return bonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bon non trouvé avec l'id : " + id));
    }

    @Override
    public Bon getBonByNumero(String numero) {
        return bonRepository.findByNumero(numero);
    }

    @Override
    public List<Bon> getAllBons() {
        return bonRepository.findAll();
    }

    @Override
    public List<Bon> getBonsByClient(Client client) {
        return bonRepository.findByClient(client);
    }

    @Override
    public Bon validerBon(Long id) {
        Bon bon = getBon(id);
        // Ajouter la logique de validation
        return bonRepository.save(bon);
    }

    @Override
    public Bon annulerBon(Long id) {
        Bon bon = getBon(id);
        // Restaurer le stock
        produitService.updateStock(bon.getProduit().getId(), bon.getQuantite());
        bonRepository.delete(bon);
        return bon;
    }
}
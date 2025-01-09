package sn.boom.boutique.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sn.boom.boutique.entities.Produit;
import sn.boom.boutique.exceptions.InsufficientStockException;
import sn.boom.boutique.exceptions.ResourceNotFoundException;
import sn.boom.boutique.implementations.ProduitServiceImpl;
import sn.boom.boutique.repositories.ProduitRepository;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour le service ProduitServiceImpl.
 * Cette classe contient des tests unitaires pour vérifier le comportement des méthodes de ProduitServiceImpl.
 */
class ProduitServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;

    private Produit produit;

    /**
     * Méthode exécutée avant chaque test.
     * Initialise les mocks et crée un produit avec des valeurs prédéfinies.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        produit = new Produit();
        produit.setId(1L);
        produit.setNom("Produit Test");
        produit.setPrix(new BigDecimal("100.00"));
        produit.setQuantiteStock(10);
    }

    /**
     * Teste la méthode updateStock() pour s'assurer qu'elle met à jour le stock avec succès.
     * Vérifie que la quantité de stock a été mise à jour et que la méthode save a été appelée sur le produitRepository.
     */
    @Test
    void updateStock_Success() {
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        Produit updatedProduit = produitService.updateStock(1L, 5);

        assertEquals(15, updatedProduit.getQuantiteStock());
        verify(produitRepository).save(any(Produit.class));
    }

    /**
     * Teste la méthode updateStock() pour s'assurer qu'elle lance une exception lorsque le stock est insuffisant.
     */
    @Test
    void updateStock_InsufficientStock() {
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));

        assertThrows(InsufficientStockException.class, 
            () -> produitService.updateStock(1L, -15));
    }

    /**
     * Teste la méthode getProduit() pour s'assurer qu'elle lance une exception lorsque le produit n'est pas trouvé.
     */
    @Test
    void getProduit_NotFound() {
        when(produitRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, 
            () -> produitService.getProduit(1L));
    }
}
package sn.boom.boutique.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.boom.boutique.entities.Compte;
import sn.boom.boutique.exceptions.InsufficientBalanceException;
import sn.boom.boutique.exceptions.ResourceNotFoundException;
import sn.boom.boutique.repositories.CompteRepository;
import sn.boom.boutique.services.CompteService;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CompteServiceImpl implements CompteService {
    private final CompteRepository compteRepository;

    @Override
    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public Compte updateCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }

    @Override
    public Compte getCompte(Long id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte non trouvé avec l'id : " + id));
    }

    @Override
    public Compte getCompteByNumero(String numero) {
        return compteRepository.findByNumero(numero);
    }

    @Override
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    @Override
    public Compte crediterCompte(String numero, BigDecimal montant) {
        Compte compte = getCompteByNumero(numero);
        compte.setSolde(compte.getSolde().add(montant));
        return compteRepository.save(compte);
    }

    @Override
    public Compte debiterCompte(String numero, BigDecimal montant) {
        Compte compte = getCompteByNumero(numero);
        if (compte.getSolde().compareTo(montant) < 0) {
            throw new InsufficientBalanceException("Solde insuffisant pour le compte : " + numero);
        }
        compte.setSolde(compte.getSolde().subtract(montant));
        return compteRepository.save(compte);
    }
}
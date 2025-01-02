package sn.boom.boutique.services;

import sn.boom.boutique.entities.Compte;
import java.math.BigDecimal;
import java.util.List;

public interface CompteService {
    Compte saveCompte(Compte compte);
    Compte updateCompte(Compte compte);
    void deleteCompte(Long id);
    Compte getCompte(Long id);
    Compte getCompteByNumero(String numero);
    List<Compte> getAllComptes();
    Compte crediterCompte(String numero, BigDecimal montant);
    Compte debiterCompte(String numero, BigDecimal montant);
}
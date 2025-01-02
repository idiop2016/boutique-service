package sn.boom.boutique.services;

import sn.boom.boutique.entities.Compte;
import sn.boom.boutique.entities.Versement;
import java.util.List;

public interface VersementService {
    Versement saveVersement(Versement versement);
    void deleteVersement(Long id);
    Versement getVersement(Long id);
    List<Versement> getAllVersements();
    List<Versement> getVersementsByCompte(Compte compte);
}
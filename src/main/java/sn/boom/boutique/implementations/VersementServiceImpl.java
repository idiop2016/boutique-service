package sn.boom.boutique.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.boom.boutique.entities.Compte;
import sn.boom.boutique.entities.Versement;
import sn.boom.boutique.exceptions.ResourceNotFoundException;
import sn.boom.boutique.repositories.VersementRepository;
import sn.boom.boutique.services.CompteService;
import sn.boom.boutique.services.VersementService;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class VersementServiceImpl implements VersementService {
    private final VersementRepository versementRepository;
    private final CompteService compteService;

    @Override
    public Versement saveVersement(Versement versement) {
        // Créditer le compte
        compteService.crediterCompte(
            versement.getCompte().getNumero(), 
            versement.getMontant()
        );
        return versementRepository.save(versement);
    }

    @Override
    public void deleteVersement(Long id) {
        Versement versement = getVersement(id);
        // Débiter le compte pour annuler le versement
        compteService.debiterCompte(
            versement.getCompte().getNumero(), 
            versement.getMontant()
        );
        versementRepository.deleteById(id);
    }

    @Override
    public Versement getVersement(Long id) {
        return versementRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Versement non trouvé avec l'id : " + id));
    }

    @Override
    public List<Versement> getAllVersements() {
        return versementRepository.findAll();
    }

    @Override
    public List<Versement> getVersementsByCompte(Compte compte) {
        return versementRepository.findByCompte(compte);
    }
}
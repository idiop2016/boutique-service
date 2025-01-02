package sn.boom.boutique.services;

import sn.boom.boutique.entities.Bon;
import sn.boom.boutique.entities.Client;
import java.util.List;

public interface BonService {
    Bon saveBon(Bon bon);
    Bon updateBon(Bon bon);
    void deleteBon(Long id);
    Bon getBon(Long id);
    Bon getBonByNumero(String numero);
    List<Bon> getAllBons();
    List<Bon> getBonsByClient(Client client);
    Bon validerBon(Long id);
    Bon annulerBon(Long id);
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import java.util.HashMap;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOPromeniRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
        Racun r = (Racun) ado;
        if (r.getMestoIzdavanja() == null || r.getMestoIzdavanja().isEmpty()) {
            throw new Exception("Neophodno je uneti mesto izdavanja racuna!");
        }
        if (r.getKlijent() == null) {
            throw new Exception("Neophodno je odabrati klijenta!");
        }
        if (r.getKrojacica() == null) {
            throw new Exception("Neophodno je odabrati krojacicu!");
        }
        if (r.getStavkeRacuna() == null || r.getStavkeRacuna().isEmpty()) {
            throw new Exception("Racun mora imati bar jednu stavku!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Racun r = (Racun) ado;

        // DELETE svih starih stavki
        StavkaRacuna filter = new StavkaRacuna();
        filter.setRacun(r); // kada se prosledi rb = 0 brisu se sve stavke tog racuna
        DBBroker.getInstance().delete(filter);

        // INSERT novih stavki
        int rb = 1;
        for (StavkaRacuna stavka : r.getStavkeRacuna()) {
            stavka.setRacun(r);
            stavka.setRb(rb++);
            DBBroker.getInstance().insert(stavka);
        }

        // UPDATE racuna sa novim ukupnim iznosom
        DBBroker.getInstance().update(r);

        /*
        Racun r = (Racun) ado;

        // ucitavamo stare stavke iz baze
        ArrayList<StavkaRacuna> stareStavke = (ArrayList<StavkaRacuna>) (ArrayList<?>) DBBroker.getInstance().select(new StavkaRacuna(r, 0, 0, 0, 0, null));

        HashMap<Integer, StavkaRacuna> mapaStarih = new HashMap<>();
        for (StavkaRacuna sr : stareStavke) {
            mapaStarih.put(sr.getRb(), sr);
        }

        HashMap<Integer, StavkaRacuna> mapaNovih = new HashMap<>();
        for (StavkaRacuna nova : r.getStavkeRacuna()) {
            mapaNovih.put(nova.getRb(), nova);
        }

        // DELETE stavki koje vise ne postoje
        for (StavkaRacuna stara : stareStavke) {
            if (!mapaNovih.containsKey(stara.getRb())) {
                DBBroker.getInstance().delete(stara);
            }
        }

        // UPDATE stavki kojima je izmenjena kolicina (isti rb)
        for (StavkaRacuna nova : r.getStavkeRacuna()) {
            if (mapaStarih.containsKey(nova.getRb())) {
                nova.setRacun(r);
                DBBroker.getInstance().update(nova);
            }
        }

        // INSERT novih stavke (rb = -1)
        for (StavkaRacuna nova : r.getStavkeRacuna()) {
            if (!mapaStarih.containsKey(nova.getRb())) {
                nova.setRacun(r);
                DBBroker.getInstance().insert(nova);
            }
        }

        // UPDATE racuna sa novim ukupnim iznosom
        DBBroker.getInstance().update(r);
         */
    }

}

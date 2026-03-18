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
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOVratiListuRacun extends AbstractSO {

    private ArrayList<Racun> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
        Racun r = (Racun) ado;
        if ((r.getKrojacica() == null || r.getKrojacica().getIdKrojacica() <= 0)
                && (r.getKlijent() == null || r.getKlijent().getIdKlijent() <= 0)) {
            throw new Exception("Morate odabrati kriterijum pretrage (krojacica ili klijent)!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaRacuna = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Racun>) (ArrayList<?>) listaRacuna;
        for (Racun r : lista) {
            StavkaRacuna s = new StavkaRacuna();
            s.setRacun(r);
            ArrayList<AbstractDomainObject> listaStavki = DBBroker.getInstance().select(s);
            r.setStavkeRacuna((ArrayList<StavkaRacuna>) (ArrayList<?>) listaStavki);
        }
    }

    public ArrayList<Racun> getLista() {
        return lista;
    }
}

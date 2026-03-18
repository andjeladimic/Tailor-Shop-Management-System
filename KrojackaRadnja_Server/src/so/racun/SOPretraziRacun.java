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
public class SOPretraziRacun extends AbstractSO {

    private Racun racun;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaRacuna = DBBroker.getInstance().select(ado);
        if (listaRacuna.isEmpty()) {
            throw new Exception("Racun nije pronadjen!");
        }
        racun = (Racun) listaRacuna.get(0);

        // ucitavamo stavke za pronadjeni racun
        StavkaRacuna s = new StavkaRacuna();
        s.setRacun(racun);
        ArrayList<AbstractDomainObject> listaStavki = DBBroker.getInstance().select(s);
        racun.setStavkeRacuna((ArrayList<StavkaRacuna>) (ArrayList<?>) listaStavki);
    }

    public Racun getRacun() {
        return racun;
    }
}

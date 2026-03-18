/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.klijent;

import db.DBBroker;
import domain.Klijent;
import domain.AbstractDomainObject;
import domain.Racun;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOObrisiKlijent extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }

       /* Klijent k = (Klijent) ado;
        Racun filter = new Racun();
        filter.setKlijent(k);
        ArrayList<AbstractDomainObject> racuni = DBBroker.getInstance().select(filter);
        if (!racuni.isEmpty()) {
            throw new Exception("Nije moguce obrisati klijenta jer ima vezane racune!");
        }*/
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.klijent;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Klijent;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOPretraziKlijent extends AbstractSO {
    private Klijent klijent;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> lista = DBBroker.getInstance().select(ado);
        if (lista.isEmpty()) {
            throw new Exception("Klijent nije pronadjen!");
        }
        klijent = (Klijent) lista.get(0);
    }

    public Klijent getKlijent() {
        return klijent;
    }
}

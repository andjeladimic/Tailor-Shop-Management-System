/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.klijent;

import db.DBBroker;
import domain.Klijent;
import domain.AbstractDomainObject;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOVratiListuSviKlijent extends AbstractSO {

    private ArrayList<Klijent> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> klijenti = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Klijent>) (ArrayList<?>) klijenti;
    }

    public ArrayList<Klijent> getLista() {
        return lista;
    }
}

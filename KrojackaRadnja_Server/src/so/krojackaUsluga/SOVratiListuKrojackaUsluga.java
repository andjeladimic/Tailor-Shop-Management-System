/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.krojackaUsluga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.KrojackaUsluga;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOVratiListuKrojackaUsluga extends AbstractSO {

    private ArrayList<KrojackaUsluga> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof KrojackaUsluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase KrojackaUsluga!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaUsluga = DBBroker.getInstance().select(ado);
        lista = (ArrayList<KrojackaUsluga>) (ArrayList<?>) listaUsluga;
    }

    public ArrayList<KrojackaUsluga> getLista() {
        return lista;
    }
}

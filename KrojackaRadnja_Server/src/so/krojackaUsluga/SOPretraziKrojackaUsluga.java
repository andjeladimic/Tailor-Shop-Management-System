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
public class SOPretraziKrojackaUsluga extends AbstractSO {

    private KrojackaUsluga krojackaUsluga;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof KrojackaUsluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase KrojackaUsluga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> lista = DBBroker.getInstance().select(ado);
        if (lista.isEmpty()) {
            throw new Exception("Krojacka usluga nije pronadjena!");
        }
        krojackaUsluga = (KrojackaUsluga) lista.get(0);
    }

    public KrojackaUsluga getKrojackaUsluga() {
        return krojackaUsluga;
    }
}

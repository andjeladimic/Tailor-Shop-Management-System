/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.krojackaUsluga;

import db.DBBroker;
import domain.KrojackaUsluga;
import domain.AbstractDomainObject;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOObrisiKrojackaUsluga extends AbstractSO {
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof KrojackaUsluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase KrojackaUsluga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}

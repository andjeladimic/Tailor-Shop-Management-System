/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.krojackaUsluga;

import db.DBBroker;
import domain.KrojackaUsluga;
import domain.AbstractDomainObject;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOUbaciKrojackaUsluga extends AbstractSO {
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof KrojackaUsluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase KrojackaUsluga!");
        }

        KrojackaUsluga ku = (KrojackaUsluga) ado;

        if (ku.getCena()<= 0) {
            throw new Exception("Cena krojacke usluge mora biti veća od nule!");
        }

        if (ku.getNaziv() == null || ku.getNaziv().length()==0) {
            throw new Exception("Krojacka usluga mora imati naziv!");
        }
        
        if (ku.getOpis() == null || ku.getOpis().length()==0) {
            throw new Exception("Krojacka usluga mora imati opis!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}

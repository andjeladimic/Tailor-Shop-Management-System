/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.Krojacica;
import domain.AbstractDomainObject;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOPrijaviKrojacica extends AbstractSO {

    Krojacica ulogovana;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Krojacica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Krojacica!");
        }

        Krojacica k = (Krojacica) ado;

        for (Krojacica krojacica : ServerController.getInstance().getUlogovaneKrojacice()) {
            if (krojacica.getUsername().equals(k.getUsername())) {
                throw new Exception("Prosledjena krojacica je vec ulogovana na sistem!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Krojacica k = (Krojacica) ado;

        ArrayList<Krojacica> listaKrojacica
                = (ArrayList<Krojacica>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Krojacica krojacica : listaKrojacica) {
            if (krojacica.getUsername().equals(k.getUsername())
                    && krojacica.getPassword().equals(k.getPassword())) {
                ulogovana = krojacica;
                ServerController.getInstance().getUlogovaneKrojacice().add(krojacica);
                return;
            }
        }

        throw new Exception("Korisnicko ime i sifra nisu ispravni.");

    }

    public Krojacica getUlogovana() {
        return ulogovana;
    }
}

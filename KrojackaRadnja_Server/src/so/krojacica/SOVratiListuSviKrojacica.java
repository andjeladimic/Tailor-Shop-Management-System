/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.krojacica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Krojacica;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOVratiListuSviKrojacica extends AbstractSO {

    private ArrayList<Krojacica> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Krojacica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Krojacica!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaKrojacica = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Krojacica>) (ArrayList<?>) listaKrojacica;
    }

    public ArrayList<Krojacica> getLista() {
        return lista;
    }
}

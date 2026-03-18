/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kategorijaKlijenta;

import db.DBBroker;
import domain.KategorijaKlijenta;
import domain.AbstractDomainObject;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOVratiListuSviKategorijaKlijenta extends AbstractSO {

    private ArrayList<KategorijaKlijenta> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof KategorijaKlijenta)) {
            throw new Exception("Prosledjeni objekat nije instanca klase KategorijaKlijenta!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> kategorije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<KategorijaKlijenta>) (ArrayList<?>) kategorije;
    }

    public ArrayList<KategorijaKlijenta> getLista() {
        return lista;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Andjela
 */
public class SOUbaciRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
        Racun r = (Racun) ado;

        if (r.getMestoIzdavanja() == null || r.getMestoIzdavanja().isEmpty()) {
            throw new Exception("Neophodno je uneti mesto izdavanja racuna!");
        }
        if (r.getKlijent() == null) {
            throw new Exception("Neophodno je odabrati klijenta!");
        }
        if (r.getStavkeRacuna() == null || r.getStavkeRacuna().isEmpty()) {
            throw new Exception("Racun mora imati bar jednu stavku!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Racun r = (Racun) ado;

        // upisuje se u bazu racun i dobija auto-gen. ID
        PreparedStatement ps = DBBroker.getInstance().insert(r);
        ResultSet generatedKeys = ps.getGeneratedKeys();

        if (generatedKeys.next()) {
            r.setIdRacun(generatedKeys.getInt(1));
        } else {
            throw new Exception("Nije moguce dobiti ID racuna!");
        }

        // svaka stavka dobija referencu na racun i rb, pa se upisuje u bazu
        int rb = 1;
        for (StavkaRacuna stavka : r.getStavkeRacuna()) {
            stavka.setRacun(r);
            stavka.setRb(rb++);
            DBBroker.getInstance().insert(stavka);
        }
    }
}

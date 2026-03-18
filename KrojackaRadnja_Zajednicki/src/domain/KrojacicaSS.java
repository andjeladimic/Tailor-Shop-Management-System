/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Andjela
 */
public class KrojacicaSS extends AbstractDomainObject {

    private Krojacica krojacica;
    private StrucnaSprema strucnaSprema;
    private Date datumDobijanja;

    public KrojacicaSS(Krojacica krojacica, StrucnaSprema strucnaSprema, Date datumDobijanja) {
        this.krojacica = krojacica;
        this.strucnaSprema = strucnaSprema;
        this.datumDobijanja = datumDobijanja;
    }

    public KrojacicaSS() {
    }

    public Krojacica getKrojacica() {
        return krojacica;
    }

    public void setKrojacica(Krojacica krojacica) {
        this.krojacica = krojacica;
    }

    public StrucnaSprema getStrucnaSprema() {
        return strucnaSprema;
    }

    public void setStrucnaSprema(StrucnaSprema strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }

    public Date getDatumDobijanja() {
        return datumDobijanja;
    }

    public void setDatumDobijanja(Date datumDobijanja) {
        this.datumDobijanja = datumDobijanja;
    }

    @Override
    public String nazivTabele() {
        return " krojacica_ss ";
    }

    @Override
    public String alijas() {
        return " krojSS ";
    }

    @Override
    public String join() {
        return " JOIN krojacica kroj ON (kroj.idKrojacica = krojSS.idKrojacica)\n"
                + "JOIN strucna_sprema ss ON (ss.idStrucnaSprema = krojSS.idStrucnaSprema) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Krojacica k = new Krojacica(rs.getInt("idKrojacica"),
                    rs.getString("kroj.ime"), rs.getString("kroj.prezime"), rs.getString("kroj.email"),
                    rs.getString("username"), rs.getString("password"));

            StrucnaSprema s = new StrucnaSprema(rs.getInt("idStrucnaSprema"),
                    rs.getString("ss.naziv"), rs.getInt("trajanjeObrazovanja"));

            KrojacicaSS kss = new KrojacicaSS(k, s,
                    rs.getDate("datumDobijanja"));

            lista.add(kss);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (idKrojacica, idStrucnaSprema, datumDobijanja) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + krojacica.getIdKrojacica() + ", " + strucnaSprema.getIdStrucnaSprema() + ", "
                + " '" + new java.sql.Date(datumDobijanja.getTime()) + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " idStrucnaSprema = " + strucnaSprema.getIdStrucnaSprema();
    }

    @Override
    public String dodatniUslov() {
        return "";
    }

    @Override
    public String orderBy() {
        return "";
    }

}

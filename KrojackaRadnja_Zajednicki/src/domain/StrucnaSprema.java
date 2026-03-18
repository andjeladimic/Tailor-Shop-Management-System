/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Andjela
 */
public class StrucnaSprema extends AbstractDomainObject {

    private int idStrucnaSprema;
    private String naziv;
    private int trajanjeObrazovanja;

    public StrucnaSprema() {
    }

    public StrucnaSprema(int idStrucnaSprema, String naziv, int trajanjeObrazovanja) {
        this.idStrucnaSprema = idStrucnaSprema;
        this.naziv = naziv;
        this.trajanjeObrazovanja = trajanjeObrazovanja;
    }

    public int getIdStrucnaSprema() {
        return idStrucnaSprema;
    }

    public void setIdStrucnaSprema(int idStrucnaSprema) {
        this.idStrucnaSprema = idStrucnaSprema;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrajanjeObrazovanja() {
        return trajanjeObrazovanja;
    }

    public void setTrajanjeObrazovanja(int trajanjeObrazovanja) {
        this.trajanjeObrazovanja = trajanjeObrazovanja;
    }

    @Override
    public String nazivTabele() {
        return " strucna_sprema ";
    }

    @Override
    public String alijas() {
        return " ss ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            StrucnaSprema ss = new StrucnaSprema(rs.getInt("licencaID"),
                    rs.getString("ss.naziv"), rs.getInt("trajanjeObrazovanja"));

            lista.add(ss);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, trajanjeObrazovanja) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + naziv + "', " + trajanjeObrazovanja + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', " + "trajanjeObrazovanja = " + trajanjeObrazovanja + " ";
    }

    @Override
    public String uslov() {
        return " idStrucnaSprema = " + idStrucnaSprema;
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

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
public class KrojackaUsluga extends AbstractDomainObject {

    private int idKrojackaUsluga;
    private String naziv;
    private String opis;
    private double cena;

    public KrojackaUsluga() {
    }

    public KrojackaUsluga(int idKrojackaUsluga, String naziv, String opis, double cena) {
        this.idKrojackaUsluga = idKrojackaUsluga;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public int getIdKrojackaUsluga() {
        return idKrojackaUsluga;
    }

    public void setIdKrojackaUsluga(int idKrojackaUsluga) {
        this.idKrojackaUsluga = idKrojackaUsluga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return naziv + ", Cena: " + cena + " din ";
    }

    @Override
    public String nazivTabele() {
        return " krojacka_usluga ";
    }

    @Override
    public String alijas() {
        return " krUsl ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            KrojackaUsluga k = new KrojackaUsluga(rs.getInt("idKrojackaUsluga"), rs.getString("krUsl.naziv"),
                    rs.getString("opis"),
                    rs.getDouble("cena"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, opis, cena) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + opis + "', " + cena;
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', opis = '" + opis + "', cena = " + cena;
    }

    @Override
    public String uslov() {
        return " idKrojackaUsluga = " + idKrojackaUsluga;
    }

    @Override
    public String dodatniUslov() {
        String upit = "";
        if (idKrojackaUsluga > 0) {
            upit = " WHERE krUsl.idKrojackaUsluga = " + idKrojackaUsluga;
        } else if (naziv != null && !naziv.isEmpty()) {
            upit = " WHERE LOWER(krUsl.naziv) LIKE '%" + naziv.toLowerCase() + "%' ";
        }
        return upit;
    }

    @Override
    public String orderBy() {
        return " ORDER BY idKrojackaUsluga ASC ";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KrojackaUsluga other = (KrojackaUsluga) obj;
        return this.idKrojackaUsluga == other.idKrojackaUsluga;
    }

    
}

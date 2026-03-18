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
public class KategorijaKlijenta extends AbstractDomainObject {

    private int idKategorijaKlijenta;
    private String naziv;

    public KategorijaKlijenta() {
    }

    public KategorijaKlijenta(int idKategorijaKlijenta, String naziv) {
        this.idKategorijaKlijenta = idKategorijaKlijenta;
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final KategorijaKlijenta other = (KategorijaKlijenta) obj;
        return this.idKategorijaKlijenta == other.idKategorijaKlijenta;
    }

    public int getIdKategorijaKlijenta() {
        return idKategorijaKlijenta;
    }

    public void setIdKategorijaKlijenta(int idKategorijaKlijenta) {
        this.idKategorijaKlijenta = idKategorijaKlijenta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String nazivTabele() {
        return " kategorija_klijenta ";
    }

    @Override
    public String alijas() {
        return " katKl ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            KategorijaKlijenta katKl = new KategorijaKlijenta(rs.getInt("idKategorijaKlijenta"),
                    rs.getString("katKl.naziv"));

            lista.add(katKl);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + naziv + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "' ";
    }

    @Override
    public String uslov() {
        return " idKategorijaKlijenta = " + idKategorijaKlijenta;
    }

    @Override
    public String dodatniUslov() {
        String upit = "";
        if (naziv != null && !naziv.isEmpty()) {
            upit = upit + " WHERE LOWER(naziv) LIKE '%" + naziv.toLowerCase() + "%' ";
        }
        return upit;
    }

    @Override
    public String orderBy() {
        return " ORDER BY idKategorijaKlijenta ASC ";
    }

}

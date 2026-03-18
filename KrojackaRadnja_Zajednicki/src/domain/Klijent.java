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
public class Klijent extends AbstractDomainObject {

    private int idKlijent;
    private String ime;
    private String prezime;
    private String email;
    private KategorijaKlijenta kategorijaKlijenta;

    public Klijent() {
    }

    public Klijent(int idKlijent, String ime, String prezime, String email, KategorijaKlijenta kategorijaKlijenta) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.kategorijaKlijenta = kategorijaKlijenta;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public KategorijaKlijenta getKategorijaKlijenta() {
        return kategorijaKlijenta;
    }

    public void setKategorijaKlijenta(KategorijaKlijenta kategorijaKlijenta) {
        this.kategorijaKlijenta = kategorijaKlijenta;
    }

    @Override
    public String nazivTabele() {
        return " klijent ";
    }

    @Override
    public String alijas() {
        return " kl ";
    }

    @Override
    public String join() {
        return " JOIN kategorija_klijenta katKl ON (katKl.idKategorijaKlijenta = kl.idKategorijaKlijenta) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            KategorijaKlijenta katKl = new KategorijaKlijenta(rs.getInt("idKategorijaKlijenta"),
                    rs.getString("katKl.naziv"));

            Klijent klijent = new Klijent(rs.getInt("idKlijent"), rs.getString("kl.ime"),
                    rs.getString("kl.prezime"), rs.getString("kl.email"), katKl);

            lista.add(klijent);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ime, prezime, email, idKategorijaKlijenta) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '"
                + email + "', " + kategorijaKlijenta.getIdKategorijaKlijenta();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " ime = '" + ime + "', prezime = '" + prezime + "', email = '" + email + "', idKategorijaKlijenta = "
                + kategorijaKlijenta.getIdKategorijaKlijenta();
    }

    @Override
    public String uslov() {
        return " idKlijent = " + idKlijent;
    }

    @Override
    public String dodatniUslov() {
        String upit = "";

        if (idKlijent > 0) {
            upit = " WHERE kl.idKlijent = " + idKlijent;
        } else if (ime != null && !ime.isEmpty()) {
            upit = " WHERE LOWER(kl.ime) LIKE '%" + ime.toLowerCase() + "%' ";
        } else if (prezime != null && !prezime.isEmpty()) {
            upit = (" WHERE LOWER(kl.prezime) LIKE '%") + (prezime.toLowerCase()) + ("%' ");
        } else if (email != null && !email.isEmpty()) {
            upit = (" WHERE LOWER(kl.email) LIKE '%") + (email.toLowerCase()) + ("%' ");
        }

        return upit;
    }

    @Override
    public String orderBy() {
        return " ORDER BY idKlijent ASC ";
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
        final Klijent other = (Klijent) obj;
        return this.idKlijent == other.idKlijent;
    }

}

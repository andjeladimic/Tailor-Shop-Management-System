/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Andjela
 */
public class Racun extends AbstractDomainObject {

    private int idRacun;
    private Date datumIzdavanja;
    private String mestoIzdavanja;
    private double ukupanIznos;
    private Krojacica krojacica;
    private Klijent klijent;
    private ArrayList<StavkaRacuna> stavkeRacuna;

    public Racun() {
    }

    public Racun(int idRacun, Date datumIzdavanja, String mestoIzdavanja, double ukupanIznos, Krojacica krojacica, Klijent klijent,
            ArrayList<StavkaRacuna> stavkeRacuna) {
        this.idRacun = idRacun;
        this.datumIzdavanja = datumIzdavanja;
        this.mestoIzdavanja = mestoIzdavanja;
        this.ukupanIznos = ukupanIznos;
        this.krojacica = krojacica;
        this.klijent = klijent;
        this.stavkeRacuna = stavkeRacuna;
    }

    public int getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public String getMestoIzdavanja() {
        return mestoIzdavanja;
    }

    public void setMestoIzdavanja(String mestoIzdavanja) {
        this.mestoIzdavanja = mestoIzdavanja;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Krojacica getKrojacica() {
        return krojacica;
    }

    public void setKrojacica(Krojacica krojacica) {
        this.krojacica = krojacica;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public ArrayList<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }

    @Override
    public String nazivTabele() {
        return " racun ";
    }

    @Override
    public String alijas() {
        return " r ";
    }

    @Override
    public String join() {
        return " JOIN krojacica kroj ON ( kroj.idKrojacica = r.idKrojacica ) "
                + " JOIN klijent kl ON ( kl.idKlijent = r.idKlijent ) "
                + " JOIN kategorija_klijenta katKl ON ( katKl.idKategorijaKlijenta = kl.idKategorijaKlijenta ) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            KategorijaKlijenta katKl = new KategorijaKlijenta(rs.getInt("idKategorijaKlijenta"),
                    rs.getString("katKl.naziv"));

            Klijent k = new Klijent(rs.getInt("idKlijent"), rs.getString("kl.ime"),
                    rs.getString("kl.prezime"), rs.getString("kl.email"), katKl);

            Krojacica kr = new Krojacica(rs.getInt("idKrojacica"),
                    rs.getString("kroj.ime"), rs.getString("kroj.prezime"), rs.getString("kroj.email"),
                    rs.getString("username"), rs.getString("password"));

            Racun r = new Racun(
                    rs.getInt("idRacun"),
                    rs.getTimestamp("datumIzdavanja"),
                    rs.getString("mestoIzdavanja"),
                    rs.getDouble("ukupanIznos"),
                    kr, k, new ArrayList<>()
            );
            lista.add(r);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumIzdavanja, mestoIzdavanja, ukupanIznos, idKrojacica, idKlijent) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + new Timestamp(datumIzdavanja.getTime()) + "', "
                + "'" + mestoIzdavanja + "', " + ukupanIznos + ", "
                + krojacica.getIdKrojacica() + ", " + klijent.getIdKlijent();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ukupanIznos = " + ukupanIznos + ", "
                + "datumIzdavanja = '" + new java.sql.Timestamp(datumIzdavanja.getTime()) + "', "
                + "mestoIzdavanja = '" + mestoIzdavanja + "', "
                + "idKrojacica = " + krojacica.getIdKrojacica() + ", "
                + "idKlijent = " + klijent.getIdKlijent();
    }

    @Override
    public String uslov() {
        return " idRacun = " + idRacun;
    }

    @Override
    public String dodatniUslov() {
        String upit = "";
        if (idRacun > 0) {
            return " WHERE r.idRacun = " + idRacun;
        }
        if (krojacica != null && krojacica.getIdKrojacica() > 0) {
            upit = " WHERE r.idKrojacica = " + krojacica.getIdKrojacica();
        } else if (klijent != null && klijent.getIdKlijent() > 0) {
            upit = " WHERE r.idKlijent = " + klijent.getIdKlijent();
        }
        return upit;
    }

    @Override
    public String orderBy() {
        return " ORDER BY idRacun ASC ";
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Racun other = (Racun) obj;
        return this.idRacun == other.idRacun;
    }

}

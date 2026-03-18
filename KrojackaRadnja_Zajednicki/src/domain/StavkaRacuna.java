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
public class StavkaRacuna extends AbstractDomainObject {

    private Racun racun;
    private int rb;
    private int kolicina;
    private double prodajnaCena;
    private double iznosStavke;
    private KrojackaUsluga krojackaUsluga;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racun, int rb, int kolicina, double prodajnaCena, double iznosStavke, KrojackaUsluga krojackaUsluga) {
        this.racun = racun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.prodajnaCena = prodajnaCena;
        this.iznosStavke = iznosStavke;
        this.krojackaUsluga = krojackaUsluga;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getProdajnaCena() {
        return prodajnaCena;
    }

    public void setProdajnaCena(double prodajnaCena) {
        this.prodajnaCena = prodajnaCena;
    }

    public double getIznosStavke() {
        return iznosStavke;
    }

    public void setIznosStavke(double iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

    public KrojackaUsluga getKrojackaUsluga() {
        return krojackaUsluga;
    }

    public void setKrojackaUsluga(KrojackaUsluga krojackaUsluga) {
        this.krojackaUsluga = krojackaUsluga;
    }

    @Override
    public String nazivTabele() {
        return " stavka_racuna ";
    }

    @Override
    public String alijas() {
        return " stR ";
    }

    @Override
    public String join() {
        return " JOIN racun r ON ( r.idRacun = stR.idRacun ) "
                + " JOIN krojacica kroj ON ( kroj.idKrojacica = r.idKrojacica ) "
                + " JOIN klijent kl ON ( kl.idKlijent = r.idKlijent ) "
                + " JOIN kategorija_klijenta katKl ON ( katKl.idKategorijaKlijenta = kl.idKategorijaKlijenta ) "
                + " JOIN krojacka_usluga krUsl ON ( krUsl.idKrojackaUsluga = stR.idKrojackaUsluga ) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            KategorijaKlijenta kat = new KategorijaKlijenta(rs.getInt("idKategorijaKlijenta"),
                    rs.getString("katKl.naziv"));

            Klijent k = new Klijent(rs.getInt("idKlijent"), rs.getString("kl.ime"),
                    rs.getString("kl.prezime"), rs.getString("kl.email"),
                    kat);

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

            KrojackaUsluga krojackaUsluga = new KrojackaUsluga(rs.getInt("idKrojackaUsluga"), rs.getString("krUsl.naziv"),
                    rs.getString("opis"),
                    rs.getDouble("cena"));

            StavkaRacuna sr = new StavkaRacuna(
                    r,
                    rs.getInt("stR.rb"),
                    rs.getInt("stR.kolicina"),
                    rs.getDouble("stR.prodajnaCena"),
                    rs.getDouble("stR.iznosStavke"),
                    krojackaUsluga
            );

            lista.add(sr);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (idRacun, rb, kolicina, prodajnaCena, iznosStavke, idKrojackaUsluga) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + racun.getIdRacun()
                + ", " + rb
                + ", " + kolicina
                + ", " + prodajnaCena
                + ", " + iznosStavke
                + ", " + krojackaUsluga.getIdKrojackaUsluga();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " kolicina = " + kolicina + " ";
    }

    @Override
    public String uslov() {
        if (rb == 0) {
            return " idRacun = " + racun.getIdRacun();
        }
        return " idRacun = " + racun.getIdRacun() + " AND rb = " + rb;
        
    }

    @Override
    public String dodatniUslov() {
        return " WHERE r.idRacun = " + racun.getIdRacun();
    }

    @Override
    public String orderBy() {
        return " ORDER BY rb ASC ";
    }

}

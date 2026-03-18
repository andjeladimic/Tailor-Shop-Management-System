/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.AbstractDomainObject;
import domain.KategorijaKlijenta;
import domain.Klijent;
import domain.Krojacica;
import domain.KrojackaUsluga;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import so.kategorijaKlijenta.SOVratiListuSviKategorijaKlijenta;
import so.klijent.SOUbaciKlijent;
import so.klijent.SOObrisiKlijent;
import so.klijent.SOVratiListuSviKlijent;
import so.klijent.SOPretraziKlijent;
import so.klijent.SOPromeniKlijent;
import so.klijent.SOVratiListuKlijent;
import so.krojacica.SOVratiListuSviKrojacica;
import so.krojackaUsluga.SOUbaciKrojackaUsluga;
import so.krojackaUsluga.SOObrisiKrojackaUsluga;
import so.krojackaUsluga.SOVratiListuSviKrojackaUsluga;
import so.krojackaUsluga.SOPretraziKrojackaUsluga;
import so.krojackaUsluga.SOPromeniKrojackaUsluga;
import so.krojackaUsluga.SOVratiListuKrojackaUsluga;
import so.login.SOPrijaviKrojacica;
import so.racun.SOUbaciRacun;
import so.racun.SOObrisiRacun;
import so.racun.SOPretraziRacun;
import so.racun.SOVratiListuSviRacun;
import so.racun.SOPromeniRacun;
import so.racun.SOVratiListuRacun;

/**
 *
 * @author Andjela
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Krojacica> ulogovaneKrojacice = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Krojacica> getUlogovaneKrojacice() {
        return ulogovaneKrojacice;
    }

    public void setUlogovaneKrojacice(ArrayList<Krojacica> ulogovaneKrojacice) {
        this.ulogovaneKrojacice = ulogovaneKrojacice;
    }

    public Krojacica login(Krojacica krojacica) throws Exception {
        SOPrijaviKrojacica so = new SOPrijaviKrojacica();
        so.templateExecute(krojacica);
        return so.getUlogovana();
    }

    public void ubaciKlijenta(Klijent klijent) throws Exception {
        (new SOUbaciKlijent()).templateExecute(klijent);
    }

    public void ubaciRacun(Racun racun) throws Exception {
        (new SOUbaciRacun()).templateExecute(racun);
    }

    public void ubaciKrojackuUslugu(KrojackaUsluga krojackaUsluga) throws Exception {
        (new SOUbaciKrojackaUsluga()).templateExecute(krojackaUsluga);
    }

    public void obrisiKlijenta(Klijent klijent) throws Exception {
        (new SOObrisiKlijent()).templateExecute(klijent);
    }

    public void obrisiRacun(Racun racun) throws Exception {
        (new SOObrisiRacun()).templateExecute(racun);
    }

    public void obrisiKrojackuUslugu(KrojackaUsluga krojackaUsluga) throws Exception {
        (new SOObrisiKrojackaUsluga()).templateExecute(krojackaUsluga);
    }

    public void promeniKlijenta(Klijent klijent) throws Exception {
        (new SOPromeniKlijent()).templateExecute(klijent);
    }

    public void promeniRacun(Racun racun) throws Exception {
        (new SOPromeniRacun()).templateExecute(racun);
    }

    public void promeniKrojackuUslugu(KrojackaUsluga krojackaUsluga) throws Exception {
        (new SOPromeniKrojackaUsluga()).templateExecute(krojackaUsluga);
    }

    public ArrayList<Klijent> vratiListuSvihKlijenata() throws Exception {
        SOVratiListuSviKlijent so = new SOVratiListuSviKlijent();
        so.templateExecute(new Klijent());
        return so.getLista();
    }

    public ArrayList<Krojacica> vratiListuSvihKrojacica() throws Exception {
        SOVratiListuSviKrojacica so = new SOVratiListuSviKrojacica();
        so.templateExecute(new Krojacica());
        return so.getLista();
    }

    public ArrayList<Racun> vratiListuSvihRacuna() throws Exception {
        SOVratiListuSviRacun so = new SOVratiListuSviRacun();
        so.templateExecute(new Racun());
        return so.getLista();
    }

    public ArrayList<KategorijaKlijenta> vratiListuSvihKategorijaKlijenta() throws Exception {
        SOVratiListuSviKategorijaKlijenta so = new SOVratiListuSviKategorijaKlijenta();
        so.templateExecute(new KategorijaKlijenta());
        return so.getLista();
    }

    public ArrayList<KrojackaUsluga> vratiListuSvihKrojackihUsluga() throws Exception {
        SOVratiListuSviKrojackaUsluga so = new SOVratiListuSviKrojackaUsluga();
        so.templateExecute(new KrojackaUsluga());
        return so.getLista();
    }

    public void logout(Krojacica ulogovana) {
        ulogovaneKrojacice.remove(ulogovana);
    }

    public ArrayList<Klijent> vratiListuKlijenata(Klijent filter) throws Exception {
        SOVratiListuKlijent so = new SOVratiListuKlijent();
        so.templateExecute(filter);
        return so.getLista();
    }
   public Object vratiListuKrojackihUsluga(KrojackaUsluga filter) throws Exception {
        SOVratiListuKrojackaUsluga so = new SOVratiListuKrojackaUsluga();
        so.templateExecute(filter);
        return so.getLista();
    }

    public KrojackaUsluga pretraziKrojackuUslugu(KrojackaUsluga krojackaUsluga) throws Exception {
        SOPretraziKrojackaUsluga so = new SOPretraziKrojackaUsluga();
        so.templateExecute(krojackaUsluga);
        return so.getKrojackaUsluga();
    }

    public Klijent pretraziKlijenta(Klijent klijent) throws Exception {
        SOPretraziKlijent so = new SOPretraziKlijent();
        so.templateExecute(klijent);
        return so.getKlijent();
    }

    public ArrayList<Racun> vratiListuRacuna(Racun filter) throws Exception {
        SOVratiListuRacun so = new SOVratiListuRacun();
        so.templateExecute(filter);
        return so.getLista();
    }

    public Racun pretraziRacun(Racun racun) throws Exception {
        SOPretraziRacun so = new SOPretraziRacun();
        so.templateExecute(racun);
        return so.getRacun();
    }

}

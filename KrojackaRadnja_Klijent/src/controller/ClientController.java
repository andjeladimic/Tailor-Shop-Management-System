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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Receiver;
import transfer.Request;
import transfer.Response;
import transfer.Sender;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Andjela
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Krojacica login(Krojacica krojacica) throws Exception {
        return (Krojacica) sendRequest(Operation.LOGIN, krojacica);
    }

    public void logout(Krojacica ulogovana) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovana);
    }

    public void ubaciKlijenta(Klijent klijent) throws Exception {
        sendRequest(Operation.UBACI_KLIJENTA, klijent);
    }

    public void ubaciRacun(Racun racun) throws Exception {
        sendRequest(Operation.UBACI_RACUN, racun);
    }

    public void obrisiKlijenta(Klijent klijent) throws Exception {
        sendRequest(Operation.OBRISI_KLIJENTA, klijent);
    }

    public void obrisiRacun(Racun racun) throws Exception {
        sendRequest(Operation.OBRISI_RACUN, racun);
    }

    public void promeniKlijenta(Klijent klijent) throws Exception {
        sendRequest(Operation.PROMENI_KLIJENTA, klijent);
    }

    public void promeniRacun(Racun racun) throws Exception {
        sendRequest(Operation.PROMENI_RACUN, racun);
    }

    public ArrayList<Klijent> vratiListuSvihKlijenata() throws Exception {
        return (ArrayList<Klijent>) sendRequest(Operation.VRATI_LISTU_SVIH_KLIJENATA, null);
    }

    public ArrayList<Krojacica> vratiListuSvihKrojacica() throws Exception {
        return (ArrayList<Krojacica>) sendRequest(Operation.VRATI_LISTU_SVIH_KROJACICA, null);
    }

    public ArrayList<Racun> vratiListuSvihRacuna(Klijent klijent) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.VRATI_LISTU_SVIH_RACUNA, klijent);
    }

    public ArrayList<KategorijaKlijenta> vratiListuSvihKategorijaKlijenta() throws Exception {
        return (ArrayList<KategorijaKlijenta>) sendRequest(Operation.VRATI_LISTU_SVIH_KATEGORIJA_KLIJENTA, null);
    }

    public ArrayList<KrojackaUsluga> vratiListuSvihKrojackihUsluga() throws Exception {
        return (ArrayList<KrojackaUsluga>) sendRequest(Operation.VRATI_LISTU_SVIH_KROJACKIH_USLUGA, null);
    }

    private synchronized Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        Sender sender = new Sender(Session.getInstance().getSocket());
        sender.send(request);

        Receiver receiver = new Receiver(Session.getInstance().getSocket());
        Response response = (Response) receiver.receive();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

    public ArrayList<Klijent> vratiListuKlijenata(Klijent filter) throws Exception {
        return (ArrayList<Klijent>) sendRequest(Operation.VRATI_LISTU_KLIJENATA, filter);
    }

    public ArrayList<KrojackaUsluga> vratiListuKrojackihUsluga(KrojackaUsluga filter) throws Exception {
        return (ArrayList<KrojackaUsluga>) sendRequest(Operation.VRATI_LISTU_KROJACKIH_USLUGA, filter);
    }

    public void promeniKrojackuUslugu(KrojackaUsluga k) throws Exception {
        sendRequest(Operation.PROMENI_KROJACKU_USLUGU, k);
    }

    public void obrisiKrojackuUslugu(KrojackaUsluga k) throws Exception {
        sendRequest(Operation.OBRISI_KROJACKU_USLUGU, k);
    }

    public void ubaciKrojackuUslugu(KrojackaUsluga k) throws Exception {
        sendRequest(Operation.UBACI_KROJACKU_USLUGU, k);
    }

    public KrojackaUsluga pretraziKrojackuUslugu(KrojackaUsluga krojackaUsluga) throws Exception {
        return (KrojackaUsluga) sendRequest(Operation.PRETRAZI_KROJACKU_USLUGU, krojackaUsluga);
    }

    public Klijent pretraziKlijenta(Klijent klijent) throws Exception {
        return (Klijent) sendRequest(Operation.PRETRAZI_KLIJENTA, klijent);
    }

    public ArrayList<Racun> vratiListuRacuna(Racun filter) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.VRATI_LISTU_RACUNA, filter);
    }

    public ArrayList<Racun> vratiListuSvihRacuna() throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.VRATI_LISTU_SVIH_RACUNA, null);
    }

    public ArrayList<Racun> vratiListuRacun(Racun filter) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.VRATI_LISTU_RACUNA, filter);
    }

    public Racun pretraziRacun(Racun racun) throws Exception {
        return (Racun) sendRequest(Operation.PRETRAZI_RACUN, racun);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import controller.ServerController;
import domain.Klijent;
import domain.Krojacica;
import domain.KrojackaUsluga;
import domain.Racun;
import domain.StavkaRacuna;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                Receiver receiver = new Receiver(socket);
                Request request = (Request) receiver.receive();

                Response response = handleRequest(request);

                Sender sender = new Sender(socket);
                sender.send(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.UBACI_KLIJENTA:
                    ServerController.getInstance().ubaciKlijenta((Klijent) request.getData());
                    break;
                case Operation.UBACI_RACUN:
                    ServerController.getInstance().ubaciRacun((Racun) request.getData());
                    break;
                case Operation.UBACI_KROJACKU_USLUGU:
                    ServerController.getInstance().ubaciKrojackuUslugu((KrojackaUsluga) request.getData());
                    break;
                case Operation.OBRISI_KLIJENTA:
                    ServerController.getInstance().obrisiKlijenta((Klijent) request.getData());
                    break;
                case Operation.OBRISI_RACUN:
                    ServerController.getInstance().obrisiRacun((Racun) request.getData());
                    break;
                case Operation.OBRISI_KROJACKU_USLUGU:
                    ServerController.getInstance().obrisiKrojackuUslugu((KrojackaUsluga) request.getData());
                    break;
                case Operation.PROMENI_KLIJENTA:
                    ServerController.getInstance().promeniKlijenta((Klijent) request.getData());
                    break;
                case Operation.PROMENI_RACUN:
                    ServerController.getInstance().promeniRacun((Racun) request.getData());
                    break;
                case Operation.PROMENI_KROJACKU_USLUGU:
                    ServerController.getInstance().promeniKrojackuUslugu((KrojackaUsluga) request.getData());
                    break;
                case Operation.VRATI_LISTU_SVIH_KLIJENATA:
                    response.setData(ServerController.getInstance().vratiListuSvihKlijenata());
                    break;
                case Operation.VRATI_LISTU_SVIH_KATEGORIJA_KLIJENTA:
                    response.setData(ServerController.getInstance().vratiListuSvihKategorijaKlijenta());
                    break;
                case Operation.VRATI_LISTU_SVIH_RACUNA:
                    response.setData(ServerController.getInstance().vratiListuSvihRacuna());
                    break;
                case Operation.VRATI_LISTU_SVIH_KROJACKIH_USLUGA:
                    response.setData(ServerController.getInstance().vratiListuSvihKrojackihUsluga());
                    break;
                case Operation.LOGIN:
                    Krojacica krojacica = (Krojacica) request.getData();
                    Krojacica k = ServerController.getInstance().login(krojacica);
                    response.setData(k);
                    break;
                case Operation.LOGOUT:
                    Krojacica ulogovana = (Krojacica) request.getData();
                    ServerController.getInstance().logout(ulogovana);
                    break;
                case Operation.VRATI_LISTU_KLIJENATA:
                    response.setData(ServerController.getInstance().vratiListuKlijenata((Klijent) request.getData()));
                    break;
                case Operation.VRATI_LISTU_KROJACKIH_USLUGA:
                    response.setData(ServerController.getInstance().vratiListuKrojackihUsluga((KrojackaUsluga) request.getData()));
                    break;
                case Operation.PRETRAZI_KROJACKU_USLUGU:
                    response.setData(ServerController.getInstance().pretraziKrojackuUslugu((KrojackaUsluga) request.getData()));
                    break;
                case Operation.PRETRAZI_KLIJENTA:
                    response.setData(ServerController.getInstance().pretraziKlijenta((Klijent) request.getData()));
                    break;
                case Operation.PRETRAZI_RACUN:
                    response.setData(ServerController.getInstance().pretraziRacun((Racun) request.getData()));
                    break;
                case Operation.VRATI_LISTU_RACUNA:
                    response.setData(ServerController.getInstance().vratiListuRacuna((Racun) request.getData()));
                    break;
                case Operation.VRATI_LISTU_SVIH_KROJACICA:
                    response.setData(ServerController.getInstance().vratiListuSvihKrojacica());
                    break;

                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }
}

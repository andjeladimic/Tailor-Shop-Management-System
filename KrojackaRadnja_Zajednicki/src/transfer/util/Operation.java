/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transfer.util;

/**
 *
 * @author Andjela
 */
public interface Operation {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int UBACI_KLIJENTA = 2;
    public static final int OBRISI_KLIJENTA = 3;
    public static final int PROMENI_KLIJENTA = 4;
    public static final int VRATI_LISTU_SVIH_KLIJENATA = 5;

    public static final int UBACI_KROJACKU_USLUGU = 6;
    public static final int OBRISI_KROJACKU_USLUGU = 7;
    public static final int PROMENI_KROJACKU_USLUGU = 8;
    public static final int VRATI_LISTU_SVIH_KROJACKIH_USLUGA = 9;

    public static final int UBACI_RACUN = 10;
    public static final int OBRISI_RACUN = 11;
    public static final int PROMENI_RACUN = 12;
    public static final int VRATI_LISTU_SVIH_RACUNA = 13;

    public static final int VRATI_LISTU_SVIH_KATEGORIJA_KLIJENTA = 14;
    public static final int VRATI_LISTU_KLIJENATA = 15;
    public static final int VRATI_LISTU_KROJACKIH_USLUGA = 16;
    public static final int PRETRAZI_KROJACKU_USLUGU = 17;
    public static final int PRETRAZI_KLIJENTA = 18;
    public static final int VRATI_LISTU_RACUNA = 19;
    public static final int VRATI_LISTU_SVIH_KROJACICA = 20;
    public static final int PRETRAZI_RACUN = 21;
}

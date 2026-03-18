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
public class Krojacica extends AbstractDomainObject {

    private int idKrojacica;
    private String ime;
    private String prezime;
    private String email;
    private String username;
    private String password;

    public Krojacica(int idKrojacica, String ime, String prezime, String email, String username, String password) {
        this.idKrojacica = idKrojacica;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Krojacica() {
    }

    public int getIdKrojacica() {
        return idKrojacica;
    }

    public void setIdKrojacica(int idKrojacica) {
        this.idKrojacica = idKrojacica;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        final Krojacica other = (Krojacica) obj;
        return this.idKrojacica == other.idKrojacica;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return " krojacica ";
    }

    @Override
    public String alijas() {
        return " kroj ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Krojacica k = new Krojacica(rs.getInt("idKrojacica"),
                    rs.getString("kroj.ime"), rs.getString("kroj.prezime"), rs.getString("kroj.email"),
                    rs.getString("username"), rs.getString("password"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ime, prezime, email, username, password) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', '"
                + username + "', '" + password + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " ime = '" + ime + "', prezime = '" + prezime + "', " + "email = '" + email + "', "
                + "username = '" + username + "', password = '" + password + "' ";
    }

    @Override
    public String uslov() {
        return " idKrojacica = " + idKrojacica;
    }

    @Override
    public String dodatniUslov() {
        String upit = "";
        if (ime != null && !ime.isEmpty()) {
            upit = " WHERE LOWER(ime) LIKE '%" + ime.toLowerCase() + "%' ";
        } else if (prezime != null && !prezime.isEmpty()) {
            upit = " WHERE LOWER(prezime) LIKE '%" + prezime.toLowerCase() + "%' ";
        } else if (email != null && !email.isEmpty()) {
            upit = " WHERE LOWER(email) LIKE '%" + email.toLowerCase() + "%' ";
        } else if (username != null && !username.isEmpty()) {
            upit = " WHERE username = '" + username + "' ";
        }
        return upit;
    }

    @Override
    public String orderBy() {
        return " ORDER BY idKrojacica ASC ";
    }

    
}

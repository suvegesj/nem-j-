package com.example.FociMania;
import javax.persistence.*;


@Entity
@Table(name = "labdarugo")
public class Focistak {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMezszam() {
        return mezszam;
    }

    public void setMezszam(int mezszam) {
        this.mezszam = mezszam;
    }

    public int getKlubid() {
        return klubid;
    }

    public void setKlubid(int klubid) {
        this.klubid = klubid;
    }

    public int getPosztid() {
        return posztid;
    }

    public void setPosztid(int posztid) {
        this.posztid = posztid;
    }

    public String getUtonev() {
        return utonev;
    }

    public void setUtonev(String utonev) {
        this.utonev = utonev;
    }

    public String getVezeteknev() {
        return vezeteknev;
    }

    public void setVezeteknev(String vezeteknev) {
        this.vezeteknev = vezeteknev;
    }

    public String getSzulido() {
        return szulido;
    }

    public void setSzulido(String szulido) {
        this.szulido = szulido;
    }

    public int getMagyare() {
        return magyare;
    }

    public void setMagyare(int magyare) {
        this.magyare = magyare;
    }

    public int getKulfoldie() {
        return kulfoldie;
    }

    public void setKulfoldie(int kulfoldie) {
        this.kulfoldie = kulfoldie;
    }

    public int getErteke() {
        return erteke;
    }

    public void setErteke(int erteke) {
        this.erteke = erteke;
    }

    @Id
    private int id;
    @Column(name = "mezszam")
    private int mezszam;
    @Column(name = "klubid")
    private int klubid;
    @Column(name = "posztid")
    private int posztid;
    @Column(name = "utonev")
    private String utonev;
    @Column(name = "vezeteknev")
    private String vezeteknev;
    @Column(name = "szulido")
    private String szulido;
    @Column(name = "magyar")
    private int magyare;
    @Column(name = "kulfoldi")
    private int kulfoldie;
    @Column(name = "ertek")
    private int erteke;
}


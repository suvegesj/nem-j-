package com.example.FociMania;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "poszt")
public class Poszt {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosztnev() {
        return posztnev;
    }

    public void setPosztnev(String posztnev) {
        this.posztnev = posztnev;
    }

    @Id
    private int id;
    @Column(name = "nev")
    private String posztnev;

}

package com.example.FociMania;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "klub")
public class Klub {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCegnev() {
        return cegnev;
    }

    public void setCegnev(String cegnev) {
        this.cegnev = this.cegnev;
    }

    @Id
    private int id;
    @Column(name = "cegnev")
    private String cegnev;

}

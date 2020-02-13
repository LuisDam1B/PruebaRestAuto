package com.ejemplos.tema12.pruebarestauto;

/**
 * Created by xusa on 28/12/2017.
 */

public class Autos {
    public String make;
    public String model;
    public String year;
    public String msrp;
    public  int id;

    public  Autos()
    {

    }
    public Autos(String make, String model, String year, String msrp) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.msrp = msrp;
    }
     public Autos(String make, String model, String year, String msrp, int id) {
         this.make = make;
         this.model = model;
         this.year = year;
         this.msrp = msrp;
         this.id = id;
     }
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMsrp() {
        return msrp;
    }

    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }



    @Override
    public String toString() {
        return "Auto{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", msrp='" + msrp + '\'' +
                ", id='" + id+
        '}';
    }
}

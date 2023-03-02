class Kompor {
    String nama;
    boolean nyala = false;

    void nyalakan (){
        this.nyala = true;
        System.out.println("Kompor dinyalakan");
    }

    void matikan (){
        this.nyala = false;
        System.out.println("Kompor dimatikan");
    }
}

class Ruangan {
    String nama;
    Kompor kompor;
}

class Rumah {
    String alamat;
    Ruangan dapur, kamarTidur, kamarMandi;
}

class Warga {
    String nama;
    Rumah rumah;
}

class Latihan {
    public static void main(String[] args) {
        
        Warga warga = new Warga();
        warga.nama = "Yandiyan";

        Rumah rumah = new Rumah();
        Ruangan dapur = new Ruangan();
        Kompor kompor = new Kompor();

        dapur.kompor = kompor;
        rumah.dapur = dapur;
        warga.rumah = rumah;

        warga.rumah.dapur.kompor.nyalakan();
        warga.rumah.dapur.kompor.matikan();
    }
}

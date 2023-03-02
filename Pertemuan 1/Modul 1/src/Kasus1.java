class Manusia {
    String nama;
    int umur;
    int uang;

    void bilang(String kata) {
        System.out.println(this.nama + ": " + kata);
    }

    void pinjemUang(Manusia manusia, int nominalUangDipinjam) {
        this.bilang(manusia.nama + ", pinjem uang " + nominalUangDipinjam + " dong");
        if (manusia.uang <= nominalUangDipinjam) {
            manusia.bilang("Ga ada segitu mah, kumaha?\n");
        } else {
            manusia.uang -= nominalUangDipinjam;
            this.uang += nominalUangDipinjam;
            manusia.bilang("Nih");
        }
    }
}

class SimulasiPinjemUang {
    public static void main(String[] args) {

        Manusia m1 = new Manusia();
        m1.nama = "ïnsan";
        m1.uang = 15000;

        Manusia m2 = new Manusia();
        m2.nama = "Ica";
        m2.uang = 2000000;

        m1.pinjemUang(m2, 8000);
    }
}
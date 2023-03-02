public class Mahasiswa {
    private String nama;
    private boolean amalBaik, amalBuruk;
    private int poinAmal;

    public Mahasiswa(String nama, boolean amalBaik, boolean amalBuruk, int poinAmal) {
        this.nama = nama;
        this.amalBaik = amalBaik;
        this.amalBuruk = amalBuruk;
        this.poinAmal = poinAmal;
    }

    public String getNama (){
        return nama;
    }

    public boolean getAmalBaik (){
        return amalBaik;
    }

    public boolean getAmalBuruk (){
        return amalBuruk;
    }

    public int getPoinAmal (){
        return poinAmal;
    }

    public void setNama (String nama){
        this.nama = nama;
    }

    public void setAmalBaik (boolean amalBaik){
        this.amalBaik = amalBaik;
    }

    public void setAmalBuruk (boolean amalBuruk){
        this.amalBuruk = amalBuruk;
    }

    public void setPoinAmal (int poinAmal){
        this.poinAmal = poinAmal;
    }

    public void Ingpo (){
        System.out.println("Nama: " + nama);
        System.out.println("Poin Amal: " + poinAmal);
    }

    public void reportString (String report){
        System.out.println(this.nama + report);
    }

    public void melakukanKebaikan (boolean amalBaik){
        this.reportString(" telah melakukan kebaikan");
        poinAmal =+ 10;
    }

    public void melakukanKeburukan (boolean amalBuruk){
        this.reportString(" telah melakukan keburukan");
        poinAmal =+ 10;
    }
}

class Main {
    public static void main(String[] args) {
        Mahasiswa m1= new Mahasiswa("Yandiyan", false, false, 0);
        Mahasiswa m2= new Mahasiswa("Zuhad", false, false, 0);
        
        m1.Ingpo();
        m2.Ingpo();

        m1.melakukanKebaikan(true);
        m1.Ingpo();
        m2.melakukanKeburukan(true);
        m2.Ingpo();
    }    
}
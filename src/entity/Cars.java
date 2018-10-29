package entity;





public class Cars  implements java.io.Serializable {


     private Integer carId;
     private String marka;
     private String model;
     private String rok;
     private String nadwozie;
     private String paliwo;
     private int przebieg;
     private String kolor;
     private String skrzynia;
     private String stan;
     private String opis;

    public Cars() {
    }

	
    public Cars(String marka, String model, String rok, String nadwozie, String paliwo, int przebieg, String kolor, String skrzynia, String stan) {
        this.marka = marka;
        this.model = model;
        this.rok = rok;
        this.nadwozie = nadwozie;
        this.paliwo = paliwo;
        this.przebieg = przebieg;
        this.kolor = kolor;
        this.skrzynia = skrzynia;
        this.stan = stan;
    }
    public Cars(String marka, String model, String rok, String nadwozie, String paliwo, int przebieg, String kolor, String skrzynia, String stan, String opis) {
       this.marka = marka;
       this.model = model;
       this.rok = rok;
       this.nadwozie = nadwozie;
       this.paliwo = paliwo;
       this.przebieg = przebieg;
       this.kolor = kolor;
       this.skrzynia = skrzynia;
       this.stan = stan;
       this.opis = opis;
    }
   
    public Integer getCarId() {
        return this.carId;
    }
    
    public void setCarId(Integer carId) {
        this.carId = carId;
    }
    public String getMarka() {
        return this.marka;
    }
    
    public void setMarka(String marka) {
        this.marka = marka;
    }
    public String getModel() {
        return this.model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    public String getRok() {
        return this.rok;
    }
    
    public void setRok(String rok) {
        this.rok = rok;
    }
    public String getNadwozie() {
        return this.nadwozie;
    }
    
    public void setNadwozie(String nadwozie) {
        this.nadwozie = nadwozie;
    }
    public String getPaliwo() {
        return this.paliwo;
    }
    
    public void setPaliwo(String paliwo) {
        this.paliwo = paliwo;
    }
    public int getPrzebieg() {
        return this.przebieg;
    }
    
    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }
    public String getKolor() {
        return this.kolor;
    }
    
    public void setKolor(String kolor) {
        this.kolor = kolor;
    }
    public String getSkrzynia() {
        return this.skrzynia;
    }
    
    public void setSkrzynia(String skrzynia) {
        this.skrzynia = skrzynia;
    }
    public String getStan() {
        return this.stan;
    }
    
    public void setStan(String stan) {
        this.stan = stan;
    }
    public String getOpis() {
        return this.opis;
    }
    
    public void setOpis(String opis) {
        this.opis = opis;
    }




}



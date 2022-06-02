package MobileObjectMapper;

public class FreeParkingMapper {
	 private String parkingidentifier;
     private String promo;
     private String currency;
     
     public FreeParkingMapper() {
     }
     
     public String getcurrency() {
         return currency;
     }

     public void setcurrency(String currency) {
         this.currency = currency;
     }
     
     public String getParkingidentifier() {
         return this.parkingidentifier;           // {"parkingidentifier":"Raxstra","promo":"REGTESTATPASS"}
     }
     
     public void setParkingidentifier(String parkingidentifier) {
         this.parkingidentifier = parkingidentifier;
     }
     
     public String getpromo() {
         return this.promo;
     }
     
     public void setpromo(String promo)
     {
    	 this.promo=promo;
     }
     
     @Override
     public String toString() {
         return  "FreeParkingMapper{" +
                 "parkingidentifier='" + parkingidentifier + '\'' +
                 ", promo='" + promo + '\'' + 
                 ", currency='" + currency + '\'' + 
                 '}';
     }
    
}

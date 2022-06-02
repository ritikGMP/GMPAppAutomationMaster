package MobileObjectMapper;

public class BuyPassMapper {
	
     private String parkingidentifier;
     private String promo;
     
     public BuyPassMapper() {
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
         return  "BuyPassMapper{" +
                 "parkingidentifier='" + parkingidentifier + '\'' +
                 ", promo='" + promo + '\'' +  
                 '}';
     }
     
}

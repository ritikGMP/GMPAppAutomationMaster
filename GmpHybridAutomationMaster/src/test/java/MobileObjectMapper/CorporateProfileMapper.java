package MobileObjectMapper;

public class CorporateProfileMapper {
	 private String corporate;
     private String corporateid;
     private String email;
     
     public CorporateProfileMapper() {
     }
     
     public String getcorporate() {
         return this.corporate;           // {"parkingidentifier":"Raxstra","promo":"REGTESTATPASS"}
     }
     
     public void setcorporate(String corporate) {
         this.corporate = corporate;
     }
     
     public String getcorporateid() {
         return this.corporateid;
     }
     
     public void setcorporateid(String corporateid)
     {
    	 this.corporateid=corporateid;
     }
     
     public String getemail() {
         return this.email;
     }
     
     public void setemail(String email)
     {
    	 this.email=email;
     }
     
     @Override
     public String toString() {
         return  "CorporateProfileMapper{" +
                 "corporate='" + corporate + '\'' +
                 ", corporateid='" + corporateid + '\'' +  
                 ", email='" + email + '\'' + 
                 '}';
     }
     

}

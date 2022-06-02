package Mobile.Utils;

import MobileObjectMapper.ParkingMapper;
import Pages.Mobile.SessionCreationPage;
import TestNGListeners.ApcoaListeners;

import org.testng.asserts.SoftAssert;

public class SessionUtils {
    private final SessionCreationPage SC;
    private final String country;
    private final SoftAssert softAssert;

    public SessionUtils(SessionCreationPage SC, String country, SoftAssert softAssert) {
        this.SC = SC;
        this.country = country;
        this.softAssert = softAssert;
    }
    
    
    /*
     * Implementation:Check the Start button in the EpmpCarparks.
     */
    public void EpmpCheck()throws InterruptedException
    { 
    	
    	//String parkingName = parkingMapper.getEpmpParkingIdentifier();Turning Torso
       // String fullParkingName = parkingMapper.getEpmpParkingName();
    	String[] EpmpParkingIdentifier= new String[] {"3040","84010","6495","46010","2891","46079","7001","2932","32872","84012","84013","84011","8401","72600","7255","2780","2790","84016","712"}; 
    	String[] EpmpParkingName = new String[] {"Milleniumgaraget (Park & Go)","Centralsjukhuset parkeringsgarage P60","Kolmårdens Djurpark","Göteborg Centralstation (Park & Go)","P-hus Malmö C","Stockholms Centralstation","P-hus City, Göteborg","Skånes djurpark","Kista One","Centralsjukhuset P6","Centralsjukhuset P8","Centralsjukhuset Parkering P5","Centralsjukhuset parkeringshus P12","Frölunda Torg (Park & Go)","Solkatten (Park & Go)","Turning Torso","Malmö Entré","Centralsjukhuset Älvgatan P7","Platinan 1"};
    	
    	for(int i=0;i<EpmpParkingIdentifier.length;i++)
    	{
    	  this.SC.GettheParking(EpmpParkingIdentifier[i]);
    	  
    	this.softAssert.assertEquals(EpmpParkingName[i], this.SC.ActualParkingName, "Parking Not Found");
    	  
   	    this.softAssert.assertEquals(this.SC.checkEpmpStartButton(EpmpParkingName[i]),"True" ,"Test-FAILED");
    	 // System.out.println(this.SC.checkEpmpStartButton());
    	}
    	
       
   }
    
    
    public void startSession(ParkingMapper parkingMapper) throws InterruptedException {
        String parkingName = parkingMapper.getParkingidentifier();
        String fullParkingName = parkingMapper.getParkingname();

        this.SC.GettheParking(parkingName);
        this.softAssert.assertEquals(fullParkingName, this.SC.ActualParkingName, "Parking Not Found");
      //  Thread.sleep(3000);
        this.SC.StartsessionforParkingwithPass();
       // Thread.sleep(3000);
       
          
        this.SC.dialerMovement(this.country,parkingMapper);

        System.out.println("Main ActualInitialParkingPrice:" + this.SC.ActualInitialParkingPrice);//Main ActualInitialParkingPrice:1.14
        System.out.println("Main ActualParkingHour:" + this.SC.ActualParkingHour);//Main ActualParkingHour:00
        System.out.println("Main ActualParkingMin:" + this.SC.ActualParkingMin);//Main ActualParkingMin:12

        Thread.sleep(1000);
        //softAssert.assertEquals(initialparkingprice, 1.14,"Initial parking price fail.");
        //softAssert.assertEquals(initialparkinghours, 0,"Initial parking hours fail.");
        //softAssert.assertEquals(initialparkingminutes, 12,"Initial parking min fail.");
//here-----------------------------
       this.SC.PaymentConfirmation();
    
       if(!(this.country.equalsIgnoreCase("Denmark")||this.country.equalsIgnoreCase("Sweden")))
       {
    	   System.out.println("Main ActualPaymentSuccess:" + this.SC.ActualPaymentSuccess);//Main ActualPaymentSuccess:Payment Successful
           this.softAssert.assertEquals(this.SC.ActualPaymentSuccess, "Payment Successful", "Payment Successful message fail");
         //  Thread.sleep(8000);
       }
       else
       {
    	 //  Thread.sleep(8000);
       }
       
       
       
    }
    
    public void ExtendSessionTarrifCheck(ParkingMapper parkingMapper) throws InterruptedException
    {
    	 this.SC.PolandTarrif(this.country,parkingMapper);
    	 
    }
  
    

    public float extendSession(ParkingMapper parkingMapper) throws InterruptedException {
    	String symbol;
    	String currency=parkingMapper.getcurrency();
        this.SC.ExtendSession(this.country,parkingMapper);
        float initialparkingprice = Float.parseFloat(this.SC.ActualInitialParkingPrice);
        //float initialparkinghours = Float.parseFloat(SC.ActualParkingHour);
        //float initialparkingminutes = Float.parseFloat(SC.ActualParkingMin);
        float extendedParkingPrice = Float.parseFloat(this.SC.ActualExtendedParkingPrice);

        System.out.println("Main ActualExtendedParkingPrice:" + this.SC.ActualExtendedParkingPrice);//Main ActualExtendedParkingPrice:0.00
        
        Thread.sleep(3000);
       
        if(!(this.country.equals("Denmark")||this.country.equals("Sweden")))
        {
       this.SC.ExtendPaymentConfirmation();
       // Thread.sleep(3000);
        }
        
       
        float finalprice;
        if(this.country.equals("Sweden")||this.country.equals("Denmark")) {
        finalprice =initialparkingprice;
        }
        else
        {
        finalprice = extendedParkingPrice + initialparkingprice;
        }
        this.SC.GotoMyActiveSessions();
        
        if(this.country.equals("Denmark")||this.country.equals("Sweden")) {
        	// Thread.sleep(3000);
             return finalprice;
        }
        else {

        try {
           System.out.println(this.SC.ActiveSessionCost);
            float activesessionprice;
            if(currency.equals("$")||currency.equals("R$"))
            activesessionprice = Float.parseFloat(this.SC.ActiveSessionCost.split("\\"+"$")[1]);
            else
            activesessionprice = Float.parseFloat(this.SC.ActiveSessionCost.split(currency)[1]);
            
          /*  
            int len=currency.length();
            if(len>1){
            	symbol=currency.substring(len-1);
            	
            }
            else{
            	symbol=currency;
            }
            if(symbol.equals("D")||symbol.equals("N")){ 
            	activesessionprice = Float.parseFloat(this.SC.ActiveSessionCost.split(symbol)[1]); //  \\$
            }
            else{
            	activesessionprice = Float.parseFloat(this.SC.ActiveSessionCost.split("\\"+symbol)[1]);
            } */
         
           // System.out.println("hello"+activesessionprice);
            //System.out.println("hello"+finalprice);
            this.softAssert.assertEquals(activesessionprice, finalprice, "Final price in active session fail");
            }
            catch (Exception e)
            {
            	System.out.println(e.toString());
            }
        ApcoaListeners.logInfo("Main ActiveSessionID:" + this.SC.ActiveSessionID);
        ApcoaListeners.logInfo("Main ActiveSessionCost:" + this.SC.ActiveSessionCost);
        //System.out.println("Main ActiveSessionID:" + this.SC.ActiveSessionID);//Main ActiveSessionID:ID: #105899
       // System.out.println("Main ActiveSessionCost:" + this.SC.ActiveSessionCost);//Main ActiveSessionCost:Total : €1.14
     
        Thread.sleep(3000);
        return finalprice; }
    }

    public void stopSession(float finalprice,ParkingMapper parkingMapper) throws InterruptedException {
    	String symbol;
    	String currency=parkingMapper.getcurrency();
        float activesessionid = Float.parseFloat(this.SC.ActiveSessionID.split("#")[1]);
        this.SC.StopSession();

     //   Thread.sleep(3000);
        this.SC.GotoMyExpiredSessions();

        float expiredsessionid = Float.parseFloat(this.SC.ExpiredSessionID.split("#")[1]);
        float expiredsessionprice;
       
        ApcoaListeners.logInfo("Expired Session Cost----->"+this.SC.ExpiredSessionCost);
        if(currency.equals("$")||currency.equals("R$"))
        	expiredsessionprice = Float.parseFloat(this.SC.ExpiredSessionCost.split("\\"+"$")[1]);
        else
            expiredsessionprice = Float.parseFloat(this.SC.ExpiredSessionCost.split(currency)[1]);	
    	
    	
      /*  int len=currency.length();
        if(len>1){
        	symbol=currency.substring(len-1);
        	System.out.println(symbol);
        }
        else{
        	symbol=currency;
        }
        
        float expiredsessionprice;
        if(currency.equals("DKK"))
        {
        	expiredsessionprice = Float.parseFloat(this.SC.ExpiredSessionCost.split(currency)[1]);
        }
        else if(symbol.equals("D")||symbol.equals("K")||symbol.equals("N")){
        	ApcoaListeners.logInfo("Expired Session Cost----->"+this.SC.ExpiredSessionCost);
        	expiredsessionprice = Float.parseFloat(this.SC.ExpiredSessionCost.split(symbol)[1]);
        	
        }
        else {
        expiredsessionprice = Float.parseFloat(this.SC.ExpiredSessionCost.split("\\"+symbol)[1]);//  \\$
        }
       */
      
        if(!(this.country.equals("Sweden")||this.country.equals("Denmark")))
        this.softAssert.assertEquals(expiredsessionprice, finalprice, "Final price in expired session fail");
       
        this.softAssert.assertEquals(expiredsessionid, activesessionid, "Session ID is not matching in expired sessions.");
        
        ApcoaListeners.logInfo("Main ExpiredSessionID:" + this.SC.ExpiredSessionID);//Main ExpiredSessionID:ID: #105899
        ApcoaListeners.logInfo("Main ExpiredSessionCost:" + this.SC.ExpiredSessionCost);//Main ExpiredSessionCost:Total : €1.14
      //  Thread.sleep(8000);
        
      
       
    }
   
}
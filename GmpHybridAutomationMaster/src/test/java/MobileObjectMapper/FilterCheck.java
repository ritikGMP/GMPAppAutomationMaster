package MobileObjectMapper;

public class FilterCheck {
	private String parkingname;
    private String parkingidentifier;
   
    
    public FilterCheck(){}

   

    
    public String getParkingname() {
        return parkingname;
    }

    public void setParkingname(String parkingname) {
        this.parkingname = parkingname;
    }

    public String getParkingidentifier() {
        return parkingidentifier;
    }

    public void setParkingidentifier(String parkingidentifier) {
        this.parkingidentifier = parkingidentifier;
    }

    @Override
    public String toString() {
        return  "FilterCheck{" +
                "parkingname='" + parkingname + '\'' +
                ", parkingidentifier='" + parkingidentifier + '\'' +
               
                '}';
    }

}

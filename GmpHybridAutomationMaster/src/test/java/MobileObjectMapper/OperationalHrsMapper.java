package MobileObjectMapper;

public class OperationalHrsMapper {
	private String openinghrs;
	private String closinghrs;
    private String parkingidentifier;
   
    
    public OperationalHrsMapper(){}

   

    
    public String getopeninghrs() {
        return openinghrs;
    }

    public void setopeninghrs(String openinghrs) {
        this.openinghrs = openinghrs;
    }
    
    public String getclosinghrs() {
        return closinghrs;
    }

    public void setclosinghrs(String closinghrs) {
        this.closinghrs = closinghrs;
    }

    public String getParkingidentifier() {
        return parkingidentifier;
    }

    public void setParkingidentifier(String parkingidentifier) {
        this.parkingidentifier = parkingidentifier;
    }

    @Override
    public String toString() {
        return  "OperationalHrsMapper{" +
                ", parkingidentifier='" + parkingidentifier + '\'' +
                ", openinghrs='" + openinghrs + '\'' +
               ", closinghrs='" + closinghrs + '\'' +
                '}';
    }

}

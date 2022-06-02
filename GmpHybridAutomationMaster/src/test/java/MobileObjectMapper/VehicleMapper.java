package MobileObjectMapper;

public class VehicleMapper {

    private String lpr;

    public VehicleMapper() {
    }

    public String getLpr() {
        return lpr;
    }

    public void setLpr(String lpr) {
        this.lpr = lpr;
    }

    @Override
    public String toString() {
        return "VehicleMapper{" +
                "lpr='" + lpr + '\'' +
                '}';
    }
}
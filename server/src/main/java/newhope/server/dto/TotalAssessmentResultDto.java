package newhope.server.dto;

public class TotalAssessmentResultDto {

    Long id;
    Long shipperId;
    String shipperName;
    Integer position;
    Integer[] assessemnts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer[] getAssessemnts() {
        return assessemnts;
    }

    public void setAssessemnts(Integer[] assessemnts) {
        this.assessemnts = assessemnts;
    }
}

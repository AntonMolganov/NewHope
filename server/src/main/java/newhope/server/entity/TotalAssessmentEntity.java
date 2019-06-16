package newhope.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "V_SHIPPERS_RATING")
public class TotalAssessmentEntity {
    @Column(name = "SHIPPER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shipperId;

    @Column(name = "I_POSITION")
    private Long position;

    @Column(name = "S_SHIPPER_NAME")
    private String shipperName;

    @Column(name = "N_AVG_TOTAL_LEVEL")
    private Float avgLevel;

    @Column(name = "N_AVG_COMPLIANCE_LEVEL")
    private Float timeTableComplLevel;

    @Column(name = "N_AVG_FREE_SPACE_LEVEL")
    private Float freeSpaceLevel;

    @Column(name = "N_AVG_TECH_STATE_LEVEL")
    private Float techStateLevel;

    @Column(name = "N_AVG_LAW_VIOLENCE_LEVEL")
    private Float lawViolenceLevel;

    @Column(name = "N_AVG_SERVICE_LEVEL")
    private Float serviceLevel;


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

    public Float getAvgLevel() {
        return avgLevel;
    }

    public void setAvgLevel(Float avgLevel) {
        this.avgLevel = avgLevel;
    }

    public Float getTimeTableComplLevel() {
        return timeTableComplLevel;
    }

    public void setTimeTableComplLevel(Float timeTableComplLevel) {
        this.timeTableComplLevel = timeTableComplLevel;
    }

    public Float getFreeSpaceLevel() {
        return freeSpaceLevel;
    }

    public void setFreeSpaceLevel(Float freeSpaceLevel) {
        this.freeSpaceLevel = freeSpaceLevel;
    }

    public Float getTechStateLevel() {
        return techStateLevel;
    }

    public void setTechStateLevel(Float techStateLevel) {
        this.techStateLevel = techStateLevel;
    }

    public Float getLawViolenceLevel() {
        return lawViolenceLevel;
    }

    public void setLawViolenceLevel(Float lawViolenceLevel) {
        this.lawViolenceLevel = lawViolenceLevel;
    }

    public Float getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(Float serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}
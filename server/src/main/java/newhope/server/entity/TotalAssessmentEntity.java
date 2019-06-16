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
    private float avgLevel;

    @Column(name = "N_AVG_COMPLIANCE_LEVEL")
    private float timeTableComplLevel;

    @Column(name = "N_AVG_FREE_SPACE_LEVEL")
    private float freeSpaceLevel;

    @Column(name = "N_AVG_TECH_STATE_LEVEL")
    private float techStateLevel;

    @Column(name = "N_AVG_LAW_VIOLENCE_LEVEL")
    private float lawViolenceLevel;

    @Column(name = "N_AVG_SERVICE_LEVEL")
    private float serviceLevel;


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

    public float getAvgLevel() {
        return avgLevel;
    }

    public void setAvgLevel(float avgLevel) {
        this.avgLevel = avgLevel;
    }

    public float getTimeTableComplLevel() {
        return timeTableComplLevel;
    }

    public void setTimeTableComplLevel(float timeTableComplLevel) {
        this.timeTableComplLevel = timeTableComplLevel;
    }

    public float getFreeSpaceLevel() {
        return freeSpaceLevel;
    }

    public void setFreeSpaceLevel(float freeSpaceLevel) {
        this.freeSpaceLevel = freeSpaceLevel;
    }

    public float getTechStateLevel() {
        return techStateLevel;
    }

    public void setTechStateLevel(float techStateLevel) {
        this.techStateLevel = techStateLevel;
    }

    public float getLawViolenceLevel() {
        return lawViolenceLevel;
    }

    public void setLawViolenceLevel(float lawViolenceLevel) {
        this.lawViolenceLevel = lawViolenceLevel;
    }

    public float getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(float serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}
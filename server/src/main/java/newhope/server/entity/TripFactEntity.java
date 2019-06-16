package newhope.server.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "V_TRIPS_INFO")

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "P_CREATE",
                procedureName = "PCK_TRIPS_API.P_CREATE",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_client_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_route_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_shipper_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_d_trip_date", type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_i_compliance_level", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_s_compliance_comment", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_i_free_space_level", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_s_free_space_comment", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_i_tech_state_level", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_s_tech_state_comment", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_i_law_violence_level", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_s_law_violence_comment", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_i_service_level", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "i_s_service_comment", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_s_err_code", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "o_s_err_text", type = String.class)
                })
})


public class TripFactEntity {
    @Column(name = "TRIP_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "D_TRIP_DATE")
    private Date date;

    @Column(name = "CLIENT_ID")
    private Long clientId;
    @Column(name = "S_CLIENT_NAME")
    private String clientName;
    @Column(name = "S_EMAIL")
    private String clientEmail;
    @Column(name = "S_PHONE")
    private String clientPhone;

    @Column(name = "SHIPPER_ID")
    private Long shipperId;
    @Column(name = "S_SHIPPER_NAME")
    private String ShipperName;

    @Column(name = "ROUTE_ID")
    private Long routeId;
    @Column(name = "TRANSPORT_TYPE_ID")
    private Long routeTransportTypeId;
    @Column(name = "S_ROUTE_NUMBER")
    private String routeNumber;

    @Column(name = "I_SERVICE_LEVEL")
    private Integer serviceLevel;
    @Column(name = "I_TECH_STATE_LEVEL")
    private Integer techStateLevel;
    @Column(name = "I_LAW_VIOLENCE_LEVEL")
    private Integer lawViolenceLevel;
    @Column(name = "I_COMPLIANCE_LEVEL")
    private Integer timeComplLevel;
    @Column(name = "I_FREE_SPACE_LEVEL")
    private Integer freeSpaceLevel;
    @Column(name = "S_SERVICE_COMMENT")
    private String serviceComment;
    @Column(name = "S_TECH_STATE_COMMENT")
    private String techStateComment;
    @Column(name = "S_LAW_COMMENT")
    private String lawViolenceComment;
    @Column(name = "S_COMPLIANCE_COMMENT")
    private String timeComplComment;
    @Column(name = "S_FREE_SPACE_COMMENT")
    private String freeSpaceComment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipperName() {
        return ShipperName;
    }

    public void setShipperName(String shipperName) {
        ShipperName = shipperName;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getRouteTransportTypeId() {
        return routeTransportTypeId;
    }

    public void setRouteTransportTypeId(Long routeTransportTypeId) {
        this.routeTransportTypeId = routeTransportTypeId;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public Integer getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(Integer serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public Integer getTechStateLevel() {
        return techStateLevel;
    }

    public void setTechStateLevel(Integer techStateLevel) {
        this.techStateLevel = techStateLevel;
    }

    public Integer getLawViolenceLevel() {
        return lawViolenceLevel;
    }

    public void setLawViolenceLevel(Integer lawViolenceLevel) {
        this.lawViolenceLevel = lawViolenceLevel;
    }

    public Integer getTimeComplLevel() {
        return timeComplLevel;
    }

    public void setTimeComplLevel(Integer timeComplLevel) {
        this.timeComplLevel = timeComplLevel;
    }

    public Integer getFreeSpaceLevel() {
        return freeSpaceLevel;
    }

    public void setFreeSpaceLevel(Integer freeSpaceLevel) {
        this.freeSpaceLevel = freeSpaceLevel;
    }

    public String getServiceComment() {
        return serviceComment;
    }

    public void setServiceComment(String serviceComment) {
        this.serviceComment = serviceComment;
    }

    public String getTechStateComment() {
        return techStateComment;
    }

    public void setTechStateComment(String techStateComment) {
        this.techStateComment = techStateComment;
    }

    public String getLawViolenceComment() {
        return lawViolenceComment;
    }

    public void setLawViolenceComment(String lawViolenceComment) {
        this.lawViolenceComment = lawViolenceComment;
    }

    public String getTimeComplComment() {
        return timeComplComment;
    }

    public void setTimeComplComment(String timeComplComment) {
        this.timeComplComment = timeComplComment;
    }

    public String getFreeSpaceComment() {
        return freeSpaceComment;
    }

    public void setFreeSpaceComment(String freeSpaceComment) {
        this.freeSpaceComment = freeSpaceComment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

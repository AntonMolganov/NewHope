package newhope.server.dto;

import java.util.Date;

public class TripFactDto {

    public Long id;
    public Long userId;
    public Long routeType;
    public String routeName;
    public Date tripDateTime;
    public Integer serviceLevel;
    public Integer techStateLevel;
    public Integer lawViolenceLevel;
    public Integer timeTableComplianceLevel;
    public Integer freeSpaceLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTripDateTime() {
        return tripDateTime;
    }

    public void setTripDateTime(Date tripDateTime) {
        this.tripDateTime = tripDateTime;
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

    public Integer getTimeTableComplianceLevel() {
        return timeTableComplianceLevel;
    }

    public void setTimeTableComplianceLevel(Integer timeTableComplianceLevel) {
        this.timeTableComplianceLevel = timeTableComplianceLevel;
    }

    public Integer getFreeSpaceLevel() {
        return freeSpaceLevel;
    }

    public void setFreeSpaceLevel(Integer freeSpaceLevel) {
        this.freeSpaceLevel = freeSpaceLevel;
    }

    public Long getRouteType() {
        return routeType;
    }

    public void setRouteType(Long routeType) {
        this.routeType = routeType;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}

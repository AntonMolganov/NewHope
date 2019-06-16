package newhope.server.dto;

import java.util.Date;

public class TripFactDto {

    public Long id;
    public Long userId;
    public String routeType;
    public String routeName;
    public Date tripDateTime;
    public Integer serviceLevel;
    public Integer techStateLevel;
    public Integer lawViolenceLevel;
    public Integer timeTableComplianceLevel;
    public Integer freeSpaceLevel;
    public String serviceComment;
    public String techStateComment;
    public String lawViolenceComment;
    public String timeTableComplianceComment;
    public String freeSpaceComment;
    
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

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
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

    public String getTimeTableComplianceComment() {
        return timeTableComplianceComment;
    }

    public void setTimeTableComplianceComment(String timeTableComplianceComment) {
        this.timeTableComplianceComment = timeTableComplianceComment;
    }

    public String getFreeSpaceComment() {
        return freeSpaceComment;
    }

    public void setFreeSpaceComment(String freeSpaceComment) {
        this.freeSpaceComment = freeSpaceComment;
    }
}

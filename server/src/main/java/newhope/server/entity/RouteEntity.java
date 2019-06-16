package newhope.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "ROUTES")
public class RouteEntity {
    @Column(name = "ROUTE_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long routeId;

    @Column(name = "S_DESCRIPTION")
    private String routeDesc;

    @Column(name = "S_NAME")
    private String routeName;

    @Column(name = "TRANSPORT_TYPE_ID")
    private Long transportTypeId;

    @Column(name = "SHIPPER_ID")
    private Long shipperId;

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public Long getTransportTypeId() {
        return transportTypeId;
    }

    public void setTransportTypeId(Long transportTypeId) {
        this.transportTypeId = transportTypeId;
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public void setRouteDesc(String routeDesc) {
        this.routeDesc = routeDesc;
    }
}
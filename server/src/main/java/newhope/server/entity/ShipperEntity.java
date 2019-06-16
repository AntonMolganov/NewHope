package newhope.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "SHIPPERS")
public class ShipperEntity {
    @Column(name = "SHIPPER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "S_NAME", nullable = true, length = 255)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
package org.example.core.JPA.entities;

import jakarta.persistence.*;

/**
 * 充电地点实体类
 */

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer locationId;//充电地点id

    @Column(nullable = false, length = 50)
    private String location;//充电地点名称

    // 无参构造
    public Location() {}

    // 有参构造（不含主键）
    public Location(String location) {
        this.location = location;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

package hoon.sth.bustimely.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "busstops")
public class BusStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "routeId")
    private String routeId;

    @Column(name = "routeName")
    private String routeName;

    @Column(name = "orderNum")
    private int orderNum;

    @Column(name = "nodeId")
    private String nodeId;

    @Column(name = "arsId")
    private String arsId;

    @Column(name = "stationName")
    private String stationName;

    @Column(name = "xCoord")
    private double xCoord;

    @Column(name = "yCoord")
    private double yCoord;

}



package com.sofrecom.zones.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity

public class Zone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int idZone ;
    private String name;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<Affiliate> affiliates;
}

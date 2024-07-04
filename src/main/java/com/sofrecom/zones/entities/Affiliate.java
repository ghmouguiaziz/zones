package com.sofrecom.zones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Affiliate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int idAffiliate ;
    private String name ;
    private String fullName ;

    @JsonIgnore
    @ManyToOne
    private Zone zone;

    @OneToMany(mappedBy = "affiliate", cascade = CascadeType.ALL)
    private List<DataCenter> dataCenters;
}

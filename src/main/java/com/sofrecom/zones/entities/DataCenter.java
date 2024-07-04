package com.sofrecom.zones.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@Entity

public class DataCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int idDataCenter ;
    private String name ;

    @JsonIgnore
    @ManyToOne
    private Affiliate affiliate;












}

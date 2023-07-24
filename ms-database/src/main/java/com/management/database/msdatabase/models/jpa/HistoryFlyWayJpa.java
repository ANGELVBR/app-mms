package com.management.database.msdatabase.models.jpa;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "flyway_schema_history")
@Data
public class HistoryFlyWayJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "installed_rank", nullable = false)
    private Integer installedRank;
    
    @Column(name = "version", nullable = false)
    private String version;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "script", nullable = false)
    private String script;
    
    @Column(name = "checksum", nullable = false)
    private Integer checksum;
    
    @Column(name = "installed_by", nullable = false)
    private String installedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "installed_on", nullable = false)
    private Date installedOn;
    
    @Column(name = "execution_time", nullable = false)
    private Integer executionTime;
    
    @Column(name = "success", nullable = false)
    private Boolean success;
    
}

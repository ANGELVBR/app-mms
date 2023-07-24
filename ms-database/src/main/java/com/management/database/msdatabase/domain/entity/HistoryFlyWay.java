package com.management.database.msdatabase.domain.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HistoryFlyWay {

    private Integer installedRank;
    
    private String version;
    
    private String description;
    
    private String type;
    
    private String script;
    
    private Integer checksum;
    
    private String installedBy;

    private Date installedOn;
    
    private Integer executionTime;

    private Boolean success;
    
}

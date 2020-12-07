package com.ycxc.upload.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "quartz.upload")
public class QuartzProperties {

    private String fixAndInspectEntCronExpression;

    private String inspectDataCronExpression;

    private String transportationEntCronExpression;

    public String getFixAndInspectEntCronExpression() {
        return fixAndInspectEntCronExpression;
    }

    public void setFixAndInspectEntCronExpression(String fixAndInspectEntCronExpression) {
        this.fixAndInspectEntCronExpression = fixAndInspectEntCronExpression;
    }

    public String getInspectDataCronExpression() {
        return inspectDataCronExpression;
    }

    public void setInspectDataCronExpression(String inspectDataCronExpression) {
        this.inspectDataCronExpression = inspectDataCronExpression;
    }

    public String getTransportationEntCronExpression() {
        return transportationEntCronExpression;
    }

    public void setTransportationEntCronExpression(String transportationEntCronExpression) {
        this.transportationEntCronExpression = transportationEntCronExpression;
    }
}

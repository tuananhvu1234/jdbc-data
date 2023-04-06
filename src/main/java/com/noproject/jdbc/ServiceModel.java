/*
 *
 */
package com.noproject.jdbc;

import com.noproject.jdbc.sql.annotations.IdColumn;
import com.noproject.jdbc.sql.annotations.MapToColumn;
import com.noproject.jdbc.sql.annotations.MapToTable;
import java.math.BigDecimal;

/**
 *
 * @author DELL
 */
@MapToTable("service")
public class ServiceModel {

    @IdColumn
    @MapToColumn("service_id")
    private int serviceId;

    @MapToColumn("service_name")
    private String serviceName;

    @MapToColumn("service_type")
    private String serviceType;

    @MapToColumn("service_price")
    private BigDecimal servicePrice;

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Service{");
        sb.append("serviceId=").append(serviceId);
        sb.append(", serviceName=").append(serviceName);
        sb.append(", serviceType=").append(serviceType);
        sb.append(", servicePrice=").append(servicePrice);
        sb.append('}');
        return sb.toString();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.noproject.jdbc;

import com.noproject.jdbc.sql.data.MapToColumn;
import com.noproject.jdbc.sql.data.MapToTable;
import java.math.BigDecimal;

/**
 *
 * @author DELL
 */
@MapToTable(name = "service")
public class ServiceModel {

    @MapToColumn(name = "service_id")
    private int serviceId;

    @MapToColumn(name = "service_name")
    private String serviceName;

    @MapToColumn(name = "service_type")
    private String serviceType;

    @MapToColumn(name = "service_price")
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

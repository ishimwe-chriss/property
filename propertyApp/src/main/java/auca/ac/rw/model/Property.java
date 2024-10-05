package auca.ac.rw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Property {
    @Id
    private String propertyID;
    private String ownerID;
    private String address;
    private double size;
    private double valuation;
    private String valuationReport;

    public Property(String propertyID, String ownerID, String address, double size, double valuation, String valuationReport) {
        this.propertyID = propertyID;
        this.ownerID = ownerID;
        this.address = address;
        this.size = size;
        this.valuation = valuation;
        this.valuationReport = valuationReport;
    }

    public Property() {

    }

    public String getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getValuation() {
        return valuation;
    }

    public void setValuation(double valuation) {
        this.valuation = valuation;
    }

    public String getValuationReport() {
        return valuationReport;
    }

    public void setValuationReport(String valuationReport) {
        this.valuationReport = valuationReport;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyID='" + propertyID + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", address='" + address + '\'' +
                ", size=" + size +
                ", valuation=" + valuation +
                ", valuationReport='" + valuationReport + '\'' +
                '}';
    }
}

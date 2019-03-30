package main.model;

public class DistanceValue {

    private Double value;
    private String unitOfMeasure;
    private String[] unitsOfMeasure = {"mm", "cm", "dm", "m", "km"};

    public DistanceValue(Double value, String unitOfMeasure) {
        this.value = value;
        this.unitOfMeasure = unitOfMeasure;
    }

    public DistanceValue() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public String toString() {
        return value + unitOfMeasure;
    }
}

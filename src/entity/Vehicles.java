package entity;

public class Vehicles {
	private int carId;
	private String carMake;
	private String carModel;
	private String carColor;
	
	
	public int getCarId() {
		return carId;
	}
	public void setCarId(int id) {
		this.carId = id;
	}
	public String getCarMake() {
		return carMake;
	}
	public void setCarMake(String make) {
		this.carMake = make;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String model) {
		this.carModel = model;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String color) {
		this.carColor = color;
	}
	
	public String toString() {
		return "id= " + carId + ", make= " + carMake + ", "
				+ "model= " + carModel + ", color= " + carColor;
	}		
}		
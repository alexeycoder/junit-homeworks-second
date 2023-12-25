package edu.alexey.junit.homeworks.second;

public class Car extends Vehicle {

	public Car(String company, String model, int year) {
		super(company, model, year);
		this.numWheels = 4;
		this.speed = 0;
	}

	public void testDrive() {
		this.speed = 60;
	}

	public void park() {
		this.speed = 0;
	}

	public String toString() {
		return "This car is a " + yearRelease + " " + company + " " + model + ";";
	}
}

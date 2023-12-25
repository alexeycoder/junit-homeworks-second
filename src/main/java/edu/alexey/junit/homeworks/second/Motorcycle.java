package edu.alexey.junit.homeworks.second;

public class Motorcycle extends Vehicle {

	public Motorcycle(String company, String model, int year) {
		super(company, model, year);
		this.numWheels = 2;
		this.speed = 0;
	}

	public void testDrive() {
		this.speed = 75;
	}

	public void park() {
		this.speed = 0;
	}

	public String toString() {
		return "This motorcycle is a " + yearRelease + " " + company + " " + model + ";";
	}
}
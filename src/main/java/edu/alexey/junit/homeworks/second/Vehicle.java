package edu.alexey.junit.homeworks.second;

// Согласно условию базовый абстрактный класс Vehicle является носителем
// свойств company, model, yearRelease, numWheels и speed, поэтому определения
// getters соответствующих полей перенесены в него из подклассов-реализаций,
// а сами поля объявлены с модификатором доступа protected:

public abstract class Vehicle {

	protected String company;
	protected String model;
	protected int yearRelease;
	protected int numWheels;
	protected int speed;

	protected Vehicle(String company, String model, int year) {
		this.company = company;
		this.model = model;
		this.yearRelease = year;
	}

	public abstract void testDrive();

	public abstract void park();

	// 
	public String getCompany() {
		return company;
	}

	public String getModel() {
		return model;
	}

	public int getYearRelease() {
		return yearRelease;
	}

	public int getNumWheels() {
		return numWheels;
	}

	public int getSpeed() {
		return speed;
	}
}

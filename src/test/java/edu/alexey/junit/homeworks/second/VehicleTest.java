package edu.alexey.junit.homeworks.second;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/*
	Написать следующие тесты:
		- проверка того, что экземпляр объекта Car также является экземпляром транспортного средства; (instanceof)
		- проверка того, объект Car создается с 4-мя колесами
		- проверка того, объект Motorcycle создается с 2-мя колесами
		- проверка того, объект Car развивает скорость 60 в режиме тестового вождения (testDrive())
		- проверка того, объект Motorcycle развивает скорость 75 в режиме тестового вождения (testDrive())
		- проверить, что в режиме парковки (сначала testDrive, потом park, т.е эмуляция движения транспорта) машина останавливается (speed = 0)
		- проверить, что в режиме парковки (сначала testDrive, потом park  т.е эмуляция движения транспорта) мотоцикл останавливается (speed = 0)
*/

class VehicleTest {

	private static final Map<Class<? extends Vehicle>, Integer> WHEELS_BY_TYPE = Map.of(
			Car.class, 4,
			Motorcycle.class, 2);

	private static final Map<Class<? extends Vehicle>, Integer> TETSDRIVE_SPEED_BY_TYPE = Map.of(
			Car.class, 60,
			Motorcycle.class, 75);

	@Test
	void carInstanceIsOfVehicleSubtype() {

		// Использован оператор instanceof вместо метода аналога assertInstanceOf()
		// в соответствии с условием:
		assertTrue((new Car("Company", "Model", 2023)) instanceof Vehicle);
	}

	@ParameterizedTest
	@ValueSource(classes = { Car.class, Motorcycle.class })
	void vehicleHasRightWheelsNum(Class<? extends Vehicle> cls) {

		var vehicle = createInstance(cls);
		int wheelsActual = vehicle.getNumWheels();
		int wheelsExpected = WHEELS_BY_TYPE.get(cls);

		assertEquals(wheelsExpected, wheelsActual);
		// just log
		System.out.println(cls.getSimpleName() + " has " + wheelsExpected + " wheels.");
	}

	@ParameterizedTest
	@ValueSource(classes = { Car.class, Motorcycle.class })
	void vehicleOnTestDriveReachesRightSpeed(Class<? extends Vehicle> cls) {

		var vehicle = createInstance(cls);
		vehicle.testDrive();

		int speedActual = vehicle.getSpeed();
		int speedExpected = TETSDRIVE_SPEED_BY_TYPE.get(cls);

		assertEquals(speedExpected, speedActual);
		// just log
		System.out.println(cls.getSimpleName() + " has " + speedExpected + " speed on test drive.");
	}

	@ParameterizedTest
	@ValueSource(classes = { Car.class, Motorcycle.class })
	void parkingStopsVehicleAfterTestDrive(Class<? extends Vehicle> cls) {

		var vehicle = createInstance(cls);
		vehicle.testDrive();
		assertThat(vehicle.getSpeed()).isNotZero();
		vehicle.park();
		assertThat(vehicle.getSpeed()).isZero();
	}

	private static Vehicle createInstance(Class<? extends Vehicle> cls) {
		try {
			Constructor<? extends Vehicle> ctor = cls.getDeclaredConstructor(String.class, String.class, Integer.TYPE);
			return ctor.newInstance("Company", "Model", 2023);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}
}

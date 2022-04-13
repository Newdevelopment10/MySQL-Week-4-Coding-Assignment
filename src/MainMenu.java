import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import dao.VehiclesDao;
import entity.Vehicles;

public class MainMenu {
	private Scanner scanner = new Scanner(System.in);
	private VehiclesDao vehiclesDao = new VehiclesDao();

	// formatter:off
	private List<String> ops = List.of(
			"-1. Exit menu",
			" 1. Add car to vehicles table", 
			" 2. List the cars",
			" 3. Change car in vehicles table", 
			" 4. Delete car in vehicles table"
	);
	// formatter:on

	public static void main(String[] args) {
		new MainMenu().run();
	}

	private void run() {
		boolean done = false;

		while (!done) {
			try {
				int selection = getUserSelection();

				switch (selection) {
				case -1:
					done = exitMenu();
					break;

				case 1:
					addVehicle();
					break;

				case 2:
					listVehicles();
					break;

				case 3:
					changeVehicle();
					break;
					
				case 4:
					removeVehicle();
					break;

				default:
					System.out.println(selection + " is not a valid selection. Try again.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e + " Try again.");
			}
		}
	}

	private void removeVehicle() {
		listVehicles();
		
		Integer carId = getIntSelection("Enter the car ID of the car to delete");
	
		
		boolean removed = vehiclesDao.removeVehicle(carId);
		
		if(removed) {
		System.out.println("Car with ID= " + carId + " removed.");
	}
		else {
			System.out.println("Car with ID= " + carId + " does not exist.");
		}
	}
	private void changeVehicle() {
		listVehicles();
		
		int carId = getIntSelection("Enter car id to update");
		String carMake = getStringSelection("Enter the car make");
		String carModel = getStringSelection("Enter the car model");
		String carColor = getStringSelection("Enter the car color");

		Vehicles vehicle = new Vehicles();
		vehicle.setCarId(carId);
		vehicle.setCarMake(carMake);
		vehicle.setCarModel(carModel);
		vehicle.setCarColor(carColor);
		
		vehiclesDao.changeVehicle(vehicle);
		System.out.println("Car make changed to " + carMake );
		System.out.println("Car model changed to " + carModel);
		System.out.println("Car color changed to " + carColor) ;

	}

	private void listVehicles() {
		System.out.println("\nHere are the vehicles: ");
		vehiclesDao.retrieveAllVehicles().forEach(vehicle -> System.out.println("    " + vehicle));

	}

	private void addVehicle() {
		String carMake = getStringSelection("Enter car make");
		String carModel = getStringSelection("Enter car model");
		String carColor = getStringSelection("Enter car color");

		Vehicles vehicle = new Vehicles();
		vehicle.setCarMake(carMake);
		vehicle.setCarModel(carModel);
		vehicle.setCarColor(carColor);

		vehiclesDao.addVehicle(vehicle);
		System.out.println("Added car " + vehicle);
	}

	private boolean exitMenu() {
		System.out.println("Bye, have a good day!");
		return true;
	}

	private int getUserSelection() {
		printMenu();

		Integer selection = getIntSelection("Enter a menu selection (Press enter to quit)");

		return Objects.isNull(selection) ? -1 : selection;
	}

	private void printMenu() {
		System.out.println("\nHere are the menu options:");

		ops.forEach(System.out::println);
	}

	private Integer getIntSelection(String prompt) {
		String selection = getStringSelection(prompt);

		if (Objects.isNull(selection)) {
			return null;
		}

		try {
			return Integer.valueOf(selection);
		} catch (NumberFormatException e) {
			throw new RuntimeException(selection + " is not a valid number.");
		}
	}

	private String getStringSelection(String prompt) {
		System.out.print(prompt + ": ");
		String line = scanner.nextLine();
		return line.isBlank() ? null : line.trim();
	}	
}
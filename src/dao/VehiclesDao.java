package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import connection.DbConnection;
import entity.Vehicles;

public class VehiclesDao {
  private static final String VEHICLES_TABLE = "vehicles";
  
	public void addVehicle(Vehicles vehicle) {
		String sql = ""
			+ "INSERT INTO " + VEHICLES_TABLE + " (car_make, car_model, car_color) "
					+ " VALUES (?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection()) {
		  try(PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, vehicle.getCarMake());
			statement.setString(2, vehicle.getCarModel());
			statement.setString(3, vehicle.getCarColor());
			
			statement.executeUpdate();
		  }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Vehicles> retrieveAllVehicles() {
	  String sql = "SELECT * FROM " + VEHICLES_TABLE + " ORDER BY car_id";  
	  try(Connection conn = DbConnection.getConnection()) {
		 try(PreparedStatement statement = conn.prepareStatement(sql)) {
			try(ResultSet rs = statement.executeQuery()) {
				List<Vehicles> vehicles = new LinkedList<>();
				
				while(rs.next()) {
				  Vehicles vehicle = new Vehicles();
				  vehicle.setCarId(rs.getInt("car_id"));
				  vehicle.setCarMake(rs.getString("car_make"));
				  vehicle.setCarModel(rs.getString("car_model"));
				  vehicle.setCarColor(rs.getString("car_color"));
				  
				  vehicles.add(vehicle);
				}
				return vehicles;
			}
		 }
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
	}

	public void changeVehicle(Vehicles vehicle) {
		String sql = "UPDATE " + VEHICLES_TABLE + " SET car_make = ?, car_model = ?, car_color = ?"
				+ " WHERE car_id = ?";
		
		try(Connection conn = DbConnection.getConnection()) {
			try(PreparedStatement statement = conn.prepareStatement(sql)) {
				statement.setString(1, vehicle.getCarMake());
				statement.setString(2, vehicle.getCarModel());
				statement.setString(3, vehicle.getCarColor());
				statement.setInt(4, vehicle.getCarId());
				
				statement.executeUpdate();
	}
		}	catch (SQLException e) {
				throw new RuntimeException(e);
}
		}

	public boolean removeVehicle(Integer carId) {
		String sql = "DELETE FROM " + VEHICLES_TABLE + " WHERE car_id = ?";
		
		try(Connection conn = DbConnection.getConnection()) {
			try(PreparedStatement statement = conn.prepareStatement(sql)) {
		
			statement.setInt(1, carId);
			return statement.executeUpdate() == 1;
		}
	    } catch (SQLException e) {
		  throw new RuntimeException(e);
}
		}
}

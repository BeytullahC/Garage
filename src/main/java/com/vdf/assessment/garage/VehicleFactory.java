package com.vdf.assessment.garage;


import com.vdf.assessment.garage.model.Car;
import com.vdf.assessment.garage.model.Jeep;
import com.vdf.assessment.garage.model.Truck;
import com.vdf.assessment.garage.model.Vehicle;
import com.vdf.assessment.garage.model.VehicleType;

public class VehicleFactory {
  public static Vehicle getVehicle(VehicleType vehicleType,String color,String plate){
    if(vehicleType==null)
      throw new UnsupportedOperationException("Vehicle Type Cannot be empty or null");

    if(vehicleType==VehicleType.Car){
      return new Car(plate,color);
    } else if(vehicleType==VehicleType.Jeep){
      return new Jeep(plate,color);
    } else if(vehicleType==VehicleType.Truck){
      return new Truck(plate,color);
    }

    throw new UnsupportedOperationException("Vehicle Type Not Implemented yet for '"+vehicleType+"'.\n Supported Types:[Car,Jeep,Truck]");
  }
}

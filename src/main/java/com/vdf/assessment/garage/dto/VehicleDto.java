package com.vdf.assessment.garage.dto;

import com.vdf.assessment.garage.model.VehicleType;
import com.vdf.assessment.garage.validators.VehicleTypeSubset;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class VehicleDto {
  public static final String regexPlate="\\d{2}(-)?[A-Z]{1,3}?(-)\\d{4}";
  @NotBlank(message = "licencePlate mandatory")
  @Pattern(
      regexp = regexPlate
  ,message = "invalid Plate, examples: 34-ASD-155,34-TR-0001,34-T-0324, regex:"+regexPlate
  )
  private String licencePlate;
  @NotNull(message = "VehicleType mandatory")
  @VehicleTypeSubset(anyOf = {VehicleType.Car, VehicleType.Jeep, VehicleType.Truck},message = "Invalid Vehicle Type")
  private VehicleType vehicleType;

  @NotBlank(message = "color is mandatory")
  private String color;
}

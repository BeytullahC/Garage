package com.vdf.assessment.garage.validators;

import com.vdf.assessment.garage.model.VehicleType;
import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VehicleTypeSubSetValidator implements
    ConstraintValidator<VehicleTypeSubset, VehicleType> {
  private VehicleType[] subset;

  @Override
  public void initialize(VehicleTypeSubset constraint) {
    this.subset = constraint.anyOf();
  }

  @Override
  public boolean isValid(VehicleType value, ConstraintValidatorContext context) {
    return value == null || Arrays.asList(subset).contains(value);
  }
}
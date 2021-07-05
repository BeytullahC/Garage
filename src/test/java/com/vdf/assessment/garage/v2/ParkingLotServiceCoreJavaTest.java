package com.vdf.assessment.garage.v2;

import com.vdf.assessment.garage.GarageConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public
class ParkingLotServiceCoreJavaTest extends AbstractParkingLotServiceCoreJavaTest {
  private ParkingLotService parkingLotService;

  @BeforeEach
  public void initEach(){
    GarageConfig cfg = new GarageConfig();
    parkingLotService = cfg.parkingLot();
    setParkingLot(parkingLotService);
  }
}
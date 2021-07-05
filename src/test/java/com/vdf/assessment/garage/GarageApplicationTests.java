package com.vdf.assessment.garage;

import com.vdf.assessment.garage.v2.AbstractParkingLotServiceCoreJavaTest;
import com.vdf.assessment.garage.v2.ParkingLotService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class GarageApplicationTests extends AbstractParkingLotServiceCoreJavaTest {
  @Autowired
  ParkingLotService parkingLotService;

  @BeforeEach
  public void initEach(){
    setParkingLot(parkingLotService);
  }

}

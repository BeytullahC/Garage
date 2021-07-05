package com.vdf.assessment.garage;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdf.assessment.garage.v2.Level;
import com.vdf.assessment.garage.v2.ParkingLotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class GarageConfig {

  @Bean
  @ApplicationScope
  public Level levels(){
    return new Level(1,1,10);
  }

  @Bean
  public ParkingLotService parkingLot(){
    return new ParkingLotService(levels());
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT )
        .enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY );
  }
}

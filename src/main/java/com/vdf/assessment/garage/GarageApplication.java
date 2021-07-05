package com.vdf.assessment.garage;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GarageApplication {

  public static void main(String[] args) {
    SpringApplication.run(GarageApplication.class, args);
  }

  @Bean

  public OpenAPI customOpenAPI(
      @Value("${application-version}") String appVersion) {

    return new OpenAPI()

        .info(new Info()
            .title("Garage application API")
            .description("Garage\n"
                + "In this problem, you have a garage that can be parked up to 10 slots (you can consider each slot is 1 unit range) at any given point in time. You should create an automated ticketing system that allows your customers to use your garage without human intervention. When a car enters your garage, you give a unique ticket issued to the driver. The ticket issuing process includes us documenting the plate and the colour of the car and allocating an available slots to the car before actually handing over a ticket to the driver. When a vehicle holds number of slots with its own width, you have to leave 1 unit slot to next one. The customer should be allocated slot(s) which is nearest to the entry. At the exit the customer returns the ticket which then marks slot(s) they were using as being available.\n"
                + "Create a spring boot project and then, publish a rest controller. Your controller methods include park, leave and status.")
            .version(appVersion)
            .termsOfService("http://swagger.io/terms/")

            .license(new License().name("Apache 2.0").url("http://springdoc.org")));

  }
}

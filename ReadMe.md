
Garage application API
======================

Garage In this problem, you have a garage that can be parked up to 10 slots (you can consider each slot is 1 unit range) at any given point in time. You should create an automated ticketing system that allows your customers to use your garage without human intervention. When a car enters your garage, you give a unique ticket issued to the driver. The ticket issuing process includes us documenting the plate and the colour of the car and allocating an available slots to the car before actually handing over a ticket to the driver. When a vehicle holds number of slots with its own width, you have to leave 1 unit slot to next one. The customer should be allocated slot(s) which is nearest to the entry. At the exit the customer returns the ticket which then marks slot(s) they were using as being available. Create a spring boot project and then, publish a rest controller. Your controller methods include park, leave and status.

OpenAPI 3 Doc UI: [http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

Raw OpenAPI 3 Doc: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Contact Info: [beytullah.dakich@gmail.com](beytullah.dakich@gmail.com)

PostMan Collection: [Postman Collectioon](https://github.com/user/repo/blob/branch/other_file.md)

Version: 0.0.1-SNAPSHOT

Apache 2.0

http://springdoc.org

Access
------

Methods
-------

\[ Jump to [Models](#__Models) \]

### Table of Contents

#### [GarageResource](#GarageResource)

*   [`delete /leave`](#leaveByLicencePlate)
*   [`delete /leave/{parkingSpotNumber}`](#leaveByParkingSpot)
*   [`put /`](#park)
*   [`get /`](#status)

GarageResource
==============

[Up](#__Methods)

    delete /leave

Leave the vehicle by LicencePlate (leaveByLicencePlate)

### Query parameters

licencePlate (required)

Query Parameter —

### Return type

String

### Example data

Content-Type: application/json

    ""

### Produces

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header.

*   `*/*`

### Responses

#### 200

default response [String](#String)

* * *

[Up](#__Methods)

    delete /leave/{parkingSpotNumber}

Leave the vehicle by parkingSpotNumber (leaveByParkingSpot)

### Path parameters

parkingSpotNumber (required)

Path Parameter — format: int32

### Return type

String

### Example data

Content-Type: application/json

    ""

### Produces

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header.

*   `*/*`

### Responses

#### 200

default response [String](#String)

* * *

[Up](#__Methods)

    put /

Park the vehicle in a spot (or multiple spots) (park)

### Consumes

This API call consumes the following media types via the Content-Type request header:

*   `application/json`

### Request body

body [VehicleDto](#VehicleDto) (optional)

Body Parameter —

### Return type

String

### Example data

Content-Type: application/json

    ""

### Produces

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header.

*   `*/*`

### Responses

#### 200

default response [String](#String)

* * *

[Up](#__Methods)

    get /

Get Garage Status (status)

### Return type

String

### Example data

Content-Type: application/json

    ""

### Produces

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header.

*   `*/*`

### Responses

#### 200

default response [String](#String)

* * *

Models
------

\[ Jump to [Methods](#__Methods) \]

### Table of Contents

1.  [`VehicleDto`](#VehicleDto)

### `VehicleDto` [Up](#__Models)

licencePlate

[String](#string)

vehicleType

[String](#string)

Enum:

Car

Jeep

Truck

color

[String](#string)
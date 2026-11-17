# System design
## Architecture
```text
Browser (Thymeleaf HTML/CSS)
        |
        v
Spring MVC Controller (WebController)
        |
        v
Spring Data JPA Repositories
        |
        v
PostgreSQL (areas, vehicles, waste_collections)
        ^
        | SQL read-only retrieval
Python analysis (pandas + matplotlib)
        |
        v
Clean CSV + PNG charts
```

## Use-case diagram (PlantUML)
```plantuml
@startuml
left to right direction
actor Administrator
actor Supervisor
actor Analyst
rectangle EcoTrack {
 usecase "Manage residential areas" as A
 usecase "Manage vehicles" as V
 usecase "Record collection activity" as R
 usecase "Update collection status" as S
 usecase "View operational reports" as P
 usecase "Preprocess and visualize data" as D
}
Administrator --> A
Administrator --> V
Administrator --> P
Supervisor --> R
Supervisor --> S
Analyst --> D
@enduml
```

## Class diagram (PlantUML)
```plantuml
@startuml
class Area {
 -Long id
 -String areaName
 -String location
 -Integer population
}
class Vehicle {
 -Long id
 -String registrationNumber
 -String vehicleType
 -BigDecimal capacityTonnes
 -String availabilityStatus
}
class WasteCollection {
 -Long id
 -LocalDate collectionDate
 -String wasteCategory
 -BigDecimal quantityKg
 -String status
 -String notes
}
Area "1" <-- "0..*" WasteCollection
Vehicle "1" <-- "0..*" WasteCollection
class WebController
interface AreaRepository
interface VehicleRepository
interface WasteCollectionRepository
WebController --> AreaRepository
WebController --> VehicleRepository
WebController --> WasteCollectionRepository
@enduml
```

## Business rules
1. Area names and vehicle registration numbers are unique.
2. Population, capacity and collection quantity must be positive.
3. Each collection references an existing area and vehicle.
4. A collection quantity must not exceed selected vehicle capacity converted to kilograms.
5. Status is one of Pending, In Progress or Completed.

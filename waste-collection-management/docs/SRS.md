# Software Requirements Specification (SRS)
## 1. Introduction
**Product:** Waste Collection Management and Environmental Data Analysis System (EcoTrack)  
**Purpose:** Digitize municipal area, fleet and waste collection records and support operational/environmental analysis.  
**Users:** Municipal administrator and collection supervisor.

## 2. Scope
The web application maintains residential areas, collection vehicles and waste collection activities; tracks activity status; calculates operational reports; and supplies PostgreSQL data to a Python analysis workflow.

## 3. Functional requirements
- FR-01: Create and view residential area name, location and population.
- FR-02: Register and view vehicle registration, type, capacity and availability.
- FR-03: Record collection date, area, vehicle, waste category, quantity and notes.
- FR-04: Assign each collection record to one area and one vehicle.
- FR-05: Track Pending, In Progress and Completed status.
- FR-06: Reject non-positive quantities and quantities exceeding vehicle capacity.
- FR-07: Report area-wise, vehicle-wise, category-wise quantities and status counts.
- FR-08: Retrieve, preprocess and visualize collection data with Python.

## 4. Non-functional requirements
- NFR-01: Browser-based interface usable on desktop and mobile.
- NFR-02: PostgreSQL relational persistence with primary/foreign-key constraints.
- NFR-03: Validate user inputs on the server.
- NFR-04: Separate presentation, controller, model and repository layers.
- NFR-05: Reports should be reproducible from database records.

## 5. Main use cases
| Actor | Use case | Result |
|---|---|---|
| Administrator | Manage areas | Area master data maintained |
| Administrator | Manage vehicles | Fleet master data maintained |
| Supervisor | Record collection | Activity persisted with area/vehicle |
| Supervisor | Update status | Status changed and saved |
| Administrator | View reports | Aggregated operational summaries |
| Analyst | Run Python analysis | Clean CSV and charts generated |

## 6. Assumptions and constraints
A collection activity uses one area, one vehicle and one waste category. Quantity is stored in kilograms; vehicle capacity is stored in tonnes. Initial prototype uses a single administrator/supervisor deployment and does not implement login/role management.

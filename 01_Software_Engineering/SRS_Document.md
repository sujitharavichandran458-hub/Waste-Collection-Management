# SOFTWARE REQUIREMENTS SPECIFICATION
## Waste Collection Management and Environmental Data Analysis System
### Scenario 23 – EcoTrack

**Subject:** Software Engineering (25ITT33)

---

# 1. INTRODUCTION

## 1.1 Purpose

The purpose of this Software Requirements Specification (SRS) is to describe the functional and non-functional requirements of the Waste Collection Management and Environmental Data Analysis System named EcoTrack.

## 1.2 Project Scope

EcoTrack is a web-based application designed to manage waste collection activities. It maintains area details, vehicle information and waste collection records.

The system allows users to:
- View the dashboard.
- Manage collection areas.
- Manage collection vehicles.
- Record waste collection details.
- Update collection status.
- View collection reports.
- Analyze waste collection data using Python.

## 1.3 Existing System

Traditional waste collection management may involve manual record maintenance and separate data processing.

Limitations:
- Difficult to maintain collection records.
- Time-consuming data retrieval.
- Possibility of duplicate records.
- Limited data analysis.
- Difficult to monitor collection activities.

## 1.4 Proposed System

EcoTrack provides a centralized web-based platform using Java Spring Boot and PostgreSQL.

Advantages:
- Centralized data storage.
- Easy management of areas and vehicles.
- Efficient collection record maintenance.
- Collection status monitoring.
- Data visualization and analysis.
- Improved accessibility of reports.

# 2. OVERALL DESCRIPTION

## 2.1 Product Perspective

EcoTrack consists of a web application and a Python-based data analysis component.

Technology Stack:
- Programming Language: Java
- Backend Framework: Spring Boot
- Frontend: HTML, CSS, Thymeleaf
- Database: PostgreSQL
- Data Analysis: Python
- Libraries: Pandas and Matplotlib
- Build Tool: Maven

## 2.2 User Characteristics

**Operator:** Maintains area, vehicle and waste collection information.

**Project Evaluator:** Views the application, collection records and analysis outputs.

## 2.3 Operating Environment

- Windows Operating System
- Java Development Environment
- PostgreSQL Database Server
- Python Environment
- Modern Web Browser

# 3. FUNCTIONAL REQUIREMENTS

FR-01: The system shall display a dashboard.

FR-02: The system shall allow users to add and view area details.

FR-03: The system shall allow users to add and view vehicle details.

FR-04: The system shall allow users to record waste collection details.

FR-05: The system shall associate collection records with existing areas and vehicles.

FR-06: The system shall display saved collection records.

FR-07: The system shall allow users to update collection status.

FR-08: The system shall provide a reports page.

FR-09: The Python component shall retrieve collection data from PostgreSQL.

FR-10: The Python component shall process and analyze collection data.

FR-11: The system's analysis component shall generate data visualizations.

FR-12: The analysis component shall generate a text summary report.

# 4. NON-FUNCTIONAL REQUIREMENTS

**4.1 Usability:** The application should provide a simple and understandable interface.

**4.2 Performance:** The system should process normal user requests within a reasonable time.

**4.3 Reliability:** Valid records should be stored and retrieved consistently.

**4.4 Security:** Database credentials should be protected.

**4.5 Maintainability:** The application should have a structured and maintainable codebase.

**4.6 Portability:** The application should run in compatible environments with the required dependencies.

**4.7 Data Integrity:** Collection records should reference valid area and vehicle records.

# 5. HARDWARE AND SOFTWARE REQUIREMENTS

## 5.1 Hardware Requirements

- Computer or Laptop
- Minimum 4 GB RAM recommended
- Sufficient storage space
- Internet connection for software installation and repository access

## 5.2 Software Requirements

- Windows Operating System
- Java Development Kit
- Apache Maven
- PostgreSQL
- Python
- Visual Studio Code
- Web Browser

# 6. SYSTEM MODULES

## 6.1 Dashboard Module
Displays the main application interface and navigation options.

## 6.2 Area Management Module
Maintains waste collection area information.

## 6.3 Vehicle Management Module
Maintains vehicle registration, type, capacity and availability information.

## 6.4 Waste Collection Module
Records collection date, area, vehicle, waste category, quantity, status and notes.

## 6.5 Status Management Module
Allows collection status updates.

## 6.6 Reports Module
Displays collection-related reports.

## 6.7 Data Analysis Module
Uses Python to process collection data and generate charts and summary reports.

# 7. DATABASE REQUIREMENTS

The system uses PostgreSQL with three main entities:

1. AREAS
2. VEHICLES
3. WASTE_COLLECTIONS

Relationships:

- One area can have multiple waste collection records.
- One vehicle can be associated with multiple waste collection records.
- Each collection record references one area and one vehicle.

# 8. SYSTEM ACCEPTANCE CRITERIA

1. The application should start successfully.
2. Dashboard should load correctly.
3. Area records should be saved and displayed.
4. Vehicle records should be saved and displayed.
5. Collection records should be saved successfully.
6. Collection status should be updated correctly.
7. Reports page should open successfully.
8. Python analysis should retrieve and process available database records.
9. Expected charts and text summary should be generated.

# 9. FUTURE ENHANCEMENTS

- User authentication.
- Role-based access control.
- Automated vehicle route optimization.
- Advanced report generation.
- Real-time collection tracking.
- Interactive analytics dashboard.

# 10. CONCLUSION

EcoTrack provides a web-based solution for managing waste collection information and analyzing environmental data. It combines Java Spring Boot, PostgreSQL and Python to support collection record management, status monitoring, reporting and data visualization.
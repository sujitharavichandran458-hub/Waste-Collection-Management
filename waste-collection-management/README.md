# EcoTrack — Waste Collection Management & Environmental Data Analysis

A runnable educational web application for **Scenario 23** integrating Java, PostgreSQL/DBMS, Software Engineering documentation and Python FDS.

## Technology stack
- Java 21, Spring Boot 3.4.5, Spring MVC, Thymeleaf, Spring Data JPA
- PostgreSQL 15+ (database), SQL DDL/DML, foreign keys and indexes
- Python 3.10+, pandas, matplotlib, psycopg2
- Maven

## Features
- Residential area register (name, location, population)
- Vehicle register (registration, type, capacity and availability)
- Collection recording (date, area, vehicle, waste category, quantity, notes)
- Pending / In Progress / Completed status tracking
- Capacity validation (tonnes converted to kg)
- Dashboard and area-, vehicle-, category- and status-wise reports
- Python database retrieval, preprocessing, CSV export and four visualizations

## 1. Prerequisites (Windows)
Install:
1. JDK 21 and verify `java -version`.
2. Apache Maven 3.9+ and verify `mvn -version`.
3. PostgreSQL 15+ (pgAdmin can be used to manage it).
4. Python 3.10+ and verify `python --version`.

If `psql` is not recognized, open SQL Shell (psql) from the PostgreSQL Start Menu folder, or add PostgreSQL's `bin` folder to PATH. You can also run the SQL files in pgAdmin Query Tool.

## 2. Create the PostgreSQL database
In pgAdmin Query Tool or SQL Shell, run:
```sql
CREATE DATABASE waste_management;
```
Connect to `waste_management`, then execute `db/schema.sql` followed by `db/seed.sql`.
The seed script adds three sample areas, three vehicles and sample collection records.

Default local development connection:
- Database: `waste_management`
- User: `postgres`
- Password: `postgres`

If your PostgreSQL password differs, set environment variables before starting the app:
```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/waste_management"
$env:DB_USER="postgres"
$env:DB_PASSWORD="YOUR_POSTGRES_PASSWORD"
```
The same environment variables are used by the Python script. Change `DB_PASSWORD` to your actual local password. The Java app also creates/updates tables on startup using JPA (`ddl-auto=update`); running `schema.sql` first is recommended.

## 3. Run the Java web application
From the project root:
```powershell
mvn spring-boot:run
```
Open **http://localhost:8080** in your browser.

Pages:
- `/` dashboard
- `/areas` residential area management
- `/vehicles` fleet management
- `/collections` collection activity and status
- `/reports` operational summaries

Add areas and vehicles before recording collection activities. The app validates required fields and rejects quantities exceeding the selected vehicle capacity.

## 4. Run Python FDS analysis
In a second terminal, from project root:
```powershell
python -m venv .venv
.venv\Scripts\Activate.ps1
pip install -r python/requirements.txt
python python/analyze.py
```
Set `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD` if your database settings differ. For example:
```powershell
$env:DB_NAME="waste_management"
$env:DB_USER="postgres"
$env:DB_PASSWORD="YOUR_POSTGRES_PASSWORD"
python python/analyze.py
```
Outputs are written to `python/output/`:
- `cleaned_collections.csv`
- `area_wise_waste.png`
- `waste_category_share.png`
- `monthly_trend.png`
- `vehicle_utilization.png`
- `category_summary.csv`

## Subject-wise mapping
| Subject | Project deliverables |
|---|---|
| Software Engineering | SRS, use-case diagram, class diagram, architecture, test cases |
| Java | Spring Boot web application, Java entities/controllers/repositories, validation and business rules |
| DBMS | PostgreSQL schema, ER/relational model, SQL queries, keys, constraints and normalization |
| Foundations of Data Science | Python DB retrieval, preprocessing, CSV and charts |

## Repository structure
```text
src/main/java/       Java application source
src/main/resources/  application config, Thymeleaf pages and CSS
db/                  PostgreSQL schema and sample data
python/              FDS analysis script and requirements
docs/                SRS, design, database and test documentation
```

## Notes / prototype limitations
This is a functional academic prototype, not a production municipal platform. It does not include authentication/authorization, audit logging, GPS tracking, automated route optimization or deployment hardening. Test actual results on your machine and update `docs/TEST_CASES.md` before submission. Do not commit real database passwords.

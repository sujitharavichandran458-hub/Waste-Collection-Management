# Database design
## ER model
- AREA (1) to (many) WASTE_COLLECTION
- VEHICLE (1) to (many) WASTE_COLLECTION
- WASTE_COLLECTION is the transaction/assignment record.

## Relational model
- AREAS(**id**, area_name UNIQUE, location, population)
- VEHICLES(**id**, registration_number UNIQUE, vehicle_type, capacity_tonnes, availability_status)
- WASTE_COLLECTIONS(**id**, collection_date, area_id FK, vehicle_id FK, waste_category, quantity_kg, status, notes)

## Normalization
- 1NF: All attributes contain atomic values; each row has a primary key.
- 2NF: Each table uses a single-column primary key; every non-key attribute depends on the whole key.
- 3NF: Area details and vehicle details are stored only in their respective master tables. Collection rows store foreign keys, avoiding transitive dependencies and repeated descriptive data.

## Example analytical SQL
```sql
-- Area-wise waste
SELECT a.area_name, SUM(w.quantity_kg) AS total_kg
FROM areas a JOIN waste_collections w ON w.area_id=a.id
GROUP BY a.area_name ORDER BY total_kg DESC;

-- Vehicle utilization
SELECT v.registration_number, SUM(w.quantity_kg) AS total_kg
FROM vehicles v JOIN waste_collections w ON w.vehicle_id=v.id
GROUP BY v.registration_number ORDER BY total_kg DESC;

-- Category share
SELECT waste_category, SUM(quantity_kg) AS total_kg
FROM waste_collections GROUP BY waste_category;

-- Completion status
SELECT status, COUNT(*) FROM waste_collections GROUP BY status;
```

CREATE TABLE IF NOT EXISTS areas (
 id BIGSERIAL PRIMARY KEY,
 area_name VARCHAR(120) NOT NULL UNIQUE,
 location VARCHAR(180) NOT NULL,
 population INTEGER NOT NULL CHECK (population > 0)
);
CREATE TABLE IF NOT EXISTS vehicles (
 id BIGSERIAL PRIMARY KEY,
 registration_number VARCHAR(40) NOT NULL UNIQUE,
 vehicle_type VARCHAR(100) NOT NULL,
 capacity_tonnes NUMERIC(10,2) NOT NULL CHECK (capacity_tonnes > 0),
 availability_status VARCHAR(30) NOT NULL DEFAULT 'Available'
);
CREATE TABLE IF NOT EXISTS waste_collections (
 id BIGSERIAL PRIMARY KEY,
 collection_date DATE NOT NULL,
 area_id BIGINT NOT NULL REFERENCES areas(id),
 vehicle_id BIGINT NOT NULL REFERENCES vehicles(id),
 waste_category VARCHAR(40) NOT NULL CHECK (waste_category IN ('Biodegradable','Recyclable','Non-recyclable')),
 quantity_kg NUMERIC(10,2) NOT NULL CHECK (quantity_kg > 0),
 status VARCHAR(30) NOT NULL CHECK (status IN ('Pending','In Progress','Completed')),
 notes VARCHAR(500)
);
CREATE INDEX IF NOT EXISTS idx_collections_date ON waste_collections(collection_date);
CREATE INDEX IF NOT EXISTS idx_collections_area ON waste_collections(area_id);
CREATE INDEX IF NOT EXISTS idx_collections_vehicle ON waste_collections(vehicle_id);

-- 1. Display all waste collection records
SELECT * FROM waste_collections;

-- 2. Display completed collections
SELECT * FROM waste_collections
WHERE status = 'Completed';

-- 3. Calculate total waste collected
SELECT SUM(quantity_kg) AS total_waste
FROM waste_collections;

-- 4. Calculate average waste quantity
SELECT AVG(quantity_kg) AS average_waste
FROM waste_collections;

-- 5. Category-wise waste collection
SELECT waste_category,
       SUM(quantity_kg) AS total_quantity
FROM waste_collections
GROUP BY waste_category;

-- 6. Area-wise waste collection
SELECT area_id,
       SUM(quantity_kg) AS total_quantity
FROM waste_collections
GROUP BY area_id;

-- 7. Display records in descending order
SELECT * FROM waste_collections
ORDER BY quantity_kg DESC;
INSERT INTO areas(area_name,location,population) VALUES
 ('Perundurai','Ward 1',18000),('Thindal','Ward 2',12500),('Veerappampalayam','Ward 3',9200)
ON CONFLICT(area_name) DO NOTHING;
INSERT INTO vehicles(registration_number,vehicle_type,capacity_tonnes,availability_status) VALUES
 ('TN 56 AB 1001','Compactor Truck',5.00,'Available'),
 ('TN 56 AB 1002','Mini Tipper',2.00,'Available'),
 ('TN 56 AB 1003','Tractor Trailer',3.00,'Maintenance')
ON CONFLICT(registration_number) DO NOTHING;
INSERT INTO waste_collections(collection_date,area_id,vehicle_id,waste_category,quantity_kg,status,notes)
SELECT CURRENT_DATE-1,a.id,v.id,'Biodegradable',850,'Completed','Sample collection'
FROM areas a, vehicles v WHERE a.area_name='Perundurai' AND v.registration_number='TN 56 AB 1001'
AND NOT EXISTS (SELECT 1 FROM waste_collections WHERE notes='Sample collection');
INSERT INTO waste_collections(collection_date,area_id,vehicle_id,waste_category,quantity_kg,status,notes)
SELECT CURRENT_DATE-2,a.id,v.id,'Recyclable',320,'Completed','Sample collection'
FROM areas a, vehicles v WHERE a.area_name='Thindal' AND v.registration_number='TN 56 AB 1002'
AND NOT EXISTS (SELECT 1 FROM waste_collections WHERE notes='Sample collection' AND waste_category='Recyclable');
INSERT INTO waste_collections(collection_date,area_id,vehicle_id,waste_category,quantity_kg,status,notes)
SELECT CURRENT_DATE,a.id,v.id,'Non-recyclable',410,'In Progress','Sample collection'
FROM areas a, vehicles v WHERE a.area_name='Veerappampalayam' AND v.registration_number='TN 56 AB 1001'
AND NOT EXISTS (SELECT 1 FROM waste_collections WHERE notes='Sample collection' AND waste_category='Non-recyclable');

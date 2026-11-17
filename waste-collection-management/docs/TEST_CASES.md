# Test cases
Run the cases against the local application and record the actual result observed in your environment.

| ID | Scenario / Input | Expected result | Actual result | Status |
|---|---|---|---|---|
| TC-01 | Open `/` after startup | Dashboard loads | To be recorded | Not run |
| TC-02 | Add area with valid name, location and positive population | Area appears in area register | To be recorded | Not run |
| TC-03 | Add area with blank name | Validation prevents save | To be recorded | Not run |
| TC-04 | Add vehicle with positive capacity | Vehicle appears in fleet register | To be recorded | Not run |
| TC-05 | Record collection with valid area, vehicle and quantity within capacity | Collection appears in register | To be recorded | Not run |
| TC-06 | Record zero/negative quantity | Validation prevents save | To be recorded | Not run |
| TC-07 | Record quantity greater than vehicle capacity | Capacity validation message shown | To be recorded | Not run |
| TC-08 | Change status to Completed | Updated status is displayed | To be recorded | Not run |
| TC-09 | Open reports after adding records | Area, vehicle, category and status aggregates shown | To be recorded | Not run |
| TC-10 | Run Python analysis | Clean CSV and PNG charts generated | To be recorded | Not run |

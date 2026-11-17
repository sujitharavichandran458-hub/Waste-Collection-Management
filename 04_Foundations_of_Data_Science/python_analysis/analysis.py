import pandas as pd
import psycopg2

connection = psycopg2.connect(
    host="localhost",
    database="waste_management1",
    user="postgres",
    password="suji@2008"
)

query = "SELECT * FROM waste_collections"
df = pd.read_sql_query(query, connection)

# Data analysis
total_waste = df['quantity_kg'].sum()
average_waste = df['quantity_kg'].mean()
maximum_waste = df['quantity_kg'].max()
minimum_waste = df['quantity_kg'].min()

category_data = df.groupby('waste_category')['quantity_kg'].sum()
status_data = df.groupby('status')['quantity_kg'].sum()

# Generate report
report = f"""
WASTE COLLECTION MANAGEMENT
ENVIRONMENTAL DATA ANALYSIS REPORT

Total Records: {len(df)}
Total Waste Collected: {total_waste} KG
Average Waste: {average_waste:.2f} KG
Maximum Waste: {maximum_waste} KG
Minimum Waste: {minimum_waste} KG

WASTE CATEGORY ANALYSIS:
{category_data.to_string()}

COLLECTION STATUS ANALYSIS:
{status_data.to_string()}
"""

print(report)

with open("python_analysis/final_report.txt", "w") as file:
    file.write(report)

connection.close()

print("\nFinal Report Generated Successfully!")
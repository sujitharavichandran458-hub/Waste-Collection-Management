"""Read collection records from PostgreSQL, clean data and generate FDS charts."""
import os
from pathlib import Path
import pandas as pd
import matplotlib.pyplot as plt
import psycopg2

ROOT = Path(__file__).resolve().parent
OUT = ROOT / "output"
OUT.mkdir(exist_ok=True)

def main():
    conn = psycopg2.connect(
        host=os.getenv("DB_HOST", "localhost"),
        port=os.getenv("DB_PORT", "5432"),
        dbname=os.getenv("DB_NAME", "waste_management"),
        user=os.getenv("DB_USER", "postgres"),
        password=os.getenv("DB_PASSWORD", "postgres"),
    )
    query = """
      SELECT wc.id, wc.collection_date, a.area_name, v.registration_number,
             wc.waste_category, wc.quantity_kg, wc.status
      FROM waste_collections wc
      JOIN areas a ON a.id=wc.area_id
      JOIN vehicles v ON v.id=wc.vehicle_id
      ORDER BY wc.collection_date
    """
    try:
        df = pd.read_sql_query(query, conn)
    finally:
        conn.close()

    if df.empty:
        print("No collection data found. Add records in the web application first.")
        return

    # Preprocessing: normalize labels, parse dates, coerce numeric values,
    # remove duplicate collection IDs, and discard unusable quantities.
    df = df.drop_duplicates(subset=["id"]).copy()
    df["collection_date"] = pd.to_datetime(df["collection_date"], errors="coerce")
    df["quantity_kg"] = pd.to_numeric(df["quantity_kg"], errors="coerce")
    df["waste_category"] = df["waste_category"].astype(str).str.strip().str.title()
    df["status"] = df["status"].astype(str).str.strip().str.title()
    df = df.dropna(subset=["collection_date", "quantity_kg"])
    df = df[df["quantity_kg"] > 0]
    if df.empty:
        print("No valid rows remain after preprocessing.")
        return

    df.to_csv(OUT / "cleaned_collections.csv", index=False)
    area = df.groupby("area_name")["quantity_kg"].sum().sort_values(ascending=False)
    category = df.groupby("waste_category")["quantity_kg"].sum().sort_values(ascending=False)
    monthly = df.set_index("collection_date").resample("MS")["quantity_kg"].sum()
    vehicle = df.groupby("registration_number")["quantity_kg"].sum().sort_values(ascending=False)

    plt.figure(figsize=(9, 5))
    area.plot(kind="bar", title="Waste collected by residential area")
    plt.ylabel("Quantity (kg)"); plt.xlabel("Area"); plt.tight_layout()
    plt.savefig(OUT / "area_wise_waste.png", dpi=160); plt.close()

    plt.figure(figsize=(7, 7))
    category.plot(kind="pie", autopct="%1.1f%%", ylabel="", title="Waste category share")
    plt.tight_layout(); plt.savefig(OUT / "waste_category_share.png", dpi=160); plt.close()

    plt.figure(figsize=(9, 5))
    monthly.plot(kind="line", marker="o", title="Monthly waste collection trend")
    plt.ylabel("Quantity (kg)"); plt.xlabel("Month"); plt.grid(True, alpha=.25)
    plt.tight_layout(); plt.savefig(OUT / "monthly_trend.png", dpi=160); plt.close()

    plt.figure(figsize=(9, 5))
    vehicle.plot(kind="bar", title="Vehicle utilization by collected quantity")
    plt.ylabel("Quantity (kg)"); plt.xlabel("Vehicle"); plt.tight_layout()
    plt.savefig(OUT / "vehicle_utilization.png", dpi=160); plt.close()

    summary = df.groupby("waste_category")["quantity_kg"].agg(["count", "sum", "mean"])
    summary.to_csv(OUT / "category_summary.csv")
    print(f"Analysed {len(df)} valid collection records.")
    print(f"Total collected: {df['quantity_kg'].sum():,.2f} kg")
    print(f"Clean data and 4 charts saved to: {OUT}")

if __name__ == "__main__":
    main()

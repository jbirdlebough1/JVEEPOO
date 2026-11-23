"""
fhir_export_engine.py
Generates HL7 FHIR Release 4 JSON Collections conforming to USCDI v3 / US-Core specifications.
Specifically modeled after Epic Systems EHR Telemetry Ingestion Bundle:
EPIC_FHIR_R4_TELEMETRY_PATIENT-MRN-948201_YYYY-MM-DD.json
"""

import json
import sqlite3
import datetime
import hashlib
import os

def generate_fhir_bundle(db_path, output_dir, mrn="PATIENT-MRN-948201", user_uid="USR-9482-01"):
    os.makedirs(output_dir, exist_ok=True)
    now = datetime.datetime.utcnow()
    date_str = now.strftime("%Y-%m-%d")
    timestamp_iso = now.isoformat() + "Z"
    bundle_id = f"epic-fhir-bundle-{mrn}-{date_str}"

    conn = sqlite3.connect(db_path)
    cur = conn.cursor()

    # Query glucose records from vital_records
    cur.execute("""
        SELECT timestamp, value1, value2, unit, status, mode 
        FROM vital_records 
        WHERE metric IN ('GLUCOSE', 'GLUCOSE_MANUAL')
        ORDER BY timestamp ASC
    """)
    records = cur.fetchall()

    entries = []

    # Entry 0: Patient Resource
    patient_entry = {
        "fullUrl": f"urn:uuid:patient-{mrn}",
        "resource": {
            "resourceType": "Patient",
            "id": mrn,
            "identifier": [
                {
                    "system": "urn:healthsystem:evergreen",
                    "value": mrn
                }
            ],
            "active": True
        }
    }
    entries.append(patient_entry)

    # Observation entries
    obs_idx = 1
    for r in records:
        rec_time, val1, val2, unit, status, mode = r
        glucose_val = round(float(val1), 1) if val1 is not None else 0.0
        if glucose_val <= 0:
            continue

        try:
            # Format time to ISO 8601
            dt = datetime.datetime.fromisoformat(rec_time.replace("Z", ""))
            iso_time = dt.isoformat() + "Z"
        except:
            iso_time = rec_time

        obs_id = f"obs-glucose-{obs_idx}"
        obs_idx += 1

        obs_entry = {
            "fullUrl": f"urn:uuid:{obs_id}",
            "resource": {
                "resourceType": "Observation",
                "id": obs_id,
                "status": "final",
                "category": [
                    {
                        "coding": [
                            {
                                "system": "http://terminology.hl7.org/CodeSystem/observation-category",
                                "code": "laboratory",
                                "display": "Laboratory"
                            }
                        ]
                    }
                ],
                "code": {
                    "coding": [
                        {
                            "system": "http://loinc.org",
                            "code": "2339-0",
                            "display": "Glucose [Mass/volume] in Blood"
                        }
                    ],
                    "text": "Continuous Ambulatory Blood Glucose"
                },
                "subject": {
                    "reference": f"Patient/{mrn}"
                },
                "effectiveDateTime": iso_time,
                "valueQuantity": {
                    "value": glucose_val,
                    "unit": "mg/dL",
                    "system": "http://unitsofmeasure.org",
                    "code": "mg/dL"
                },
                "referenceRange": [
                    {
                        "low": {
                            "value": 70,
                            "unit": "mg/dL"
                        },
                        "high": {
                            "value": 140,
                            "unit": "mg/dL"
                        },
                        "type": {
                            "text": "Physician Target Band"
                        }
                    }
                ],
                "note": [
                    {
                        "text": f"Telemetry Mode: {mode or 'AMBULATORY'} | Hardware: Wearable Sensor Array (Star Ship GEMINI NCC-202X)"
                    }
                ]
            }
        }
        entries.append(obs_entry)

    # Query meals from meal_entries
    cur.execute("""
        SELECT timestamp, meal_name, calories, net_carbs, protein, fat 
        FROM meal_entries 
        ORDER BY timestamp ASC
    """)
    meals = cur.fetchall()

    for m in meals:
        m_time, m_name, cals, nc, prot, fat = m
        try:
            dt = datetime.datetime.fromisoformat(m_time.replace("Z", ""))
            iso_time = dt.isoformat() + "Z"
        except:
            iso_time = m_time

        obs_id = f"obs-nutrition-{obs_idx}"
        obs_idx += 1

        meal_entry = {
            "fullUrl": f"urn:uuid:{obs_id}",
            "resource": {
                "resourceType": "Observation",
                "id": obs_id,
                "status": "final",
                "category": [
                    {
                        "coding": [
                            {
                                "system": "http://terminology.hl7.org/CodeSystem/observation-category",
                                "code": "exam",
                                "display": "Exam"
                            }
                        ]
                    }
                ],
                "code": {
                    "coding": [
                        {
                            "system": "http://loinc.org",
                            "code": "9052-2",
                            "display": "Dietary intake"
                        }
                    ],
                    "text": f"Nutritional Ingestion: {m_name}"
                },
                "subject": {
                    "reference": f"Patient/{mrn}"
                },
                "effectiveDateTime": iso_time,
                "valueQuantity": {
                    "value": nc or 0,
                    "unit": "g",
                    "system": "http://unitsofmeasure.org",
                    "code": "g"
                },
                "note": [
                    {
                        "text": f"Calories: {cals} kcal | Net Carbs: {nc}g | Protein: {prot}g | Fat: {fat}g"
                    }
                ]
            }
        }
        entries.append(meal_entry)

    conn.close()

    bundle = {
        "resourceType": "Bundle",
        "id": bundle_id,
        "type": "collection",
        "timestamp": timestamp_iso,
        "meta": {
            "profile": [
                "http://hl7.org/fhir/us/core/StructureDefinition/us-core-bundle"
            ]
        },
        "entry": entries
    }

    filename = f"EPIC_FHIR_R4_TELEMETRY_{mrn}_{date_str}.json"
    filepath = os.path.join(output_dir, filename)

    with open(filepath, "w", encoding="utf-8") as f:
        json.dump(bundle, f, indent=2)

    file_size = os.path.getsize(filepath)
    file_sha256 = hashlib.sha256(open(filepath, "rb").read()).hexdigest()

    return {
        "filename": filename,
        "filepath": filepath,
        "bundle_id": bundle_id,
        "entry_count": len(entries),
        "file_size": file_size,
        "sha256": file_sha256
    }

if __name__ == "__main__":
    res = generate_fhir_bundle(r"D:\veepoo\w11_htm_app\telemetry.db", r"D:\veepoo\w11_htm_app\backups")
    print(f"Generated FHIR Bundle: {res['filename']} with {res['entry_count']} entries. SHA256: {res['sha256'][:16]}...")

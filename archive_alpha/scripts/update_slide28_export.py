import json, asyncio, edge_tts

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

s28 = slides[27]
s28['title'] = "EHR Direct Import Export: Standard HL7 FHIR R4 & Clinical CSV"
s28['desc'] = "Universal interoperability that Epic, Cerner, and other EHR systems import directly without conversion; patient can email or portal-send to their doctor."
s28['card_title'] = "UNIVERSAL EHR INTEROPERABILITY & DIRECT DOCTOR IMPORT"
s28['narration'] = "Displayed on your screen is our EHR Direct Import Export Suite in Tab 05 Reports Console. You can package your continuous ambulatory telemetry into universal standard formats that Epic, Cerner, and other electronic health records import directly with zero file conversion. With one click, generate an official HL7 FHIR Release 4 JSON bundle mapped with LOINC codes for glucose, blood pressure, and steps, or a standard clinical CSV. You can attach these files inside your Evergreen MyChart portal or use our one-click email drafter to send them straight to your physician, who can import them directly into your chart."
s28['bullets'] = [
    ["Universal HL7 FHIR Release 4 Standard", "Generates official JSON collection bundles with certified LOINC codes (2339-0 Glucose, 4548-4 eA1C, 8480-6 BP) and UCUM units."],
    ["Zero Conversion Required by Clinic", "Modern EHRs like Epic Hyperspace and Cerner Millennium import standard FHIR R4 and RFC-4180 CSV files directly into flowsheet records."],
    ["Patient-Directed Delivery", "Patient downloads the files and emails them to their doctor or attaches them inside MyChart with complete air-gapped privacy."],
    ["1-Click Email Drafter with Import Guide", "Pre-fills a clinical email to your physician with your MRN and clear instructions for medical records staff on importing the data."]
]
s28['takeaway'] = "Standard HL7 FHIR R4 and CSV formats ensure your doctor's office can import your continuous telemetry directly into Epic or Cerner without conversion, keeping you in complete control."
s28['tab_title'] = "05-REPORTS CONSOLE • HL7 FHIR R4 & EHR DIRECT IMPORT EXPORT"

with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides, f, indent=2)

print("[✔] Updated Slide 28 in all_slides_data.json")

async def gen():
    comm = edge_tts.Communicate(s28['narration'], 'en-AU-NatashaNeural')
    await comm.save(r'D:\veepoo\audio\slide_28.mp3')
    print("[✔] Generated audio/slide_28.mp3")

asyncio.run(gen())

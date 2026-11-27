# Glucose Watches & PPG Wearable Market Intelligence (2001–2026)

**System:** BFM Biometric Function Monitor (`JVEEPOO`, Beta)  
**Author & Architect:** The Author (Sole Pioneer, Inventor, System Architect, and Philosophical Leader)  
**Sponsorship Status:** Independent Open-Source Beta — currently **no sponsors** (actively seeking sponsorship from **Veepoo / FITAOS**, similar PPG watch vendors, and **Google Gemini**)  
**Document Purpose:** Marketing Intelligence, Competitive Landscape, Shipment/ASP Trending, Regulatory & Litigation History, and Outreach Strategy

The **BFM Biometric Function Monitor** (`JVEEPOO`) is an independent, open-source Beta continuous metabolic telemetry workstation and zero-account Android companion (*"That Others May Survive Type II Diabetes"*), designed and engineered to Beta in **8 working days using Google Gemini Pro** with full `.jsonl` AI session trajectories preserved in the repository. By collecting **288 automated PPG/biometric epochs per day** across multi-year timelines using off-the-shelf consumer hardware, BFM solves the 90-day clinical sampling blind spot while strictly operating as a transparent, non-diagnostic, self-help research platform (never as an FDA-cleared CGM or insulin-dosing replacement).

---

## 1. Historical Evolution (2001–2026) & Global Smartwatch Market Trends (2019–2026)

### Pre-2019 Historical Origins
- **2001–2007 (Cygnus GlucoWatch Biographer):** First FDA-approved wrist-worn non-invasive glucose device using **reverse iontophoresis** (micro-electric current extracting interstitial fluid). Pulled from market after widespread skin irritation/burns, warm-up/calibration burdens, sweat/motion artifacts, and physiological time lag — proving consumers reject uncomfortable or unreliable glucose watches.
- **2014–2018 (Shenzhen ODM & Consumer Optical PPG Rise):** **Shenzhen Veepoo Technology Co., Ltd.** (founded 2014) and competing Shenzhen ODMs miniaturized green/red/IR photoplethysmography (PPG) arrays into budget fitness bands paired with turnkey smartphone apps (**H Band**), laying the white-label hardware foundation later expanded to single-lead ECG, blood pressure trends, and optical glucose-trend algorithms.
- **2020–2023 (Silicon Photonics & Mass White-Label Boom):** Apple-backed **Rockley Photonics** pursued integrated clinic-on-a-chip IR spectroscopy for non-invasive glucose/hydration before filing Chapter 11 in 2023; concurrently, Shenzhen ODMs (Veepoo, Yucheng/HealthWear, JointCorp/J-Style) scaled 40–130 USD white-label PPG+ECG watches globally on Amazon, AliExpress, and social commerce.

### Global Smartwatch Shipments, Average Selling Price (ASP) & PPG Glucose-Trend Segment (2019–2026)

> *Note: Overall shipment totals vary across **IDC**, **Counterpoint Research**, and **Statista** depending on whether basic RTOS watches are included alongside HLOS smartwatches (`watchOS`, `Wear OS`, `HarmonyOS`). Industry trackers report ASPs primarily via YoY percentage shifts and price-tier splits.*

| Year | Global Smartwatch Shipments *(IDC / Counterpoint / Statista)* | YoY Shipment Trend | Global Smartwatch ASP Trend *(USD / Tier Shifts)* | Optical PPG "Glucose-Trend" Watch Segment Trend & Price Range *(USD)* |
| :--- | :--- | :--- | :--- | :--- |
| **2019** | ~143M – 145M units | Strong double-digit growth | Stable pre-pandemic baseline anchored by `watchOS` Series 4/5 ECG expansion | Early ODM prototypes & R&D (`>150 USD` specialist bands; no mainstream optical glucose SKU) |
| **2020** | ~164.5M *(IDC incl. basic)* / flat-to-+1.5% *(Counterpoint)* | +1.5% to +14.9% | **ASP compressed downward** during COVID-19 as budget (`0–99 USD`) trackers gained share | First `H Band` / `HealthWear` PPG+ECG watches enter Amazon/AliExpress (`80–150 USD`) |
| **2021** | ~127.5M *(Counterpoint)* / >100M advanced | **+24% YoY rebound** | ASPs stabilized and edged up as buyers returned to mid/high-tier health sensors | Early white-label glucose-trend claims emerge alongside 512 Hz ECG watches (`70–130 USD`) |
| **2022** | ~135M – 140M+ units | +9% to +12% YoY | **Distinct global ASP surge** led by >400 USD tier (`Apple Watch Ultra` at `799 USD`) & polarization | High-volume TikTok/Facebook/AliExpress wave (`45–120 USD`); Rockley silicon photonics distress |
| **2023** | ~160M – 161M units | Flat / maturation | Advanced smartwatch ASP averaged roughly **~259 USD** globally | Peak white-label proliferation (`40–100 USD`); Huawei Watch 4 adds study-backed **hyperglycemia risk** (`>400 USD`) |
| **2024** | ~149M – 150M units *(~-7% correction)* | **-7% YoY decline** *(inventory/entry fatigue)* | **ASP held resilient** via premiumization; cellular models averaged **>380 USD** | **Feb 21, 2024 FDA Safety Warning** + Health Canada alerts trigger scam press & Amazon crackdown on sub-50 USD drop-shippers |
| **2025** | ~170M – 191.7M units | **+4% YoY recovery** *(led by China subsidies & Apple)* | **Global ASP rose +5% YoY**: sub-200 USD fell `-9%`, while `200–400 USD` surged **+48%** | Consolidation: factory OEM SKUs (`ET480/ET580/E500`) settle at `40–80 USD`; DTC brands like FITAOS at `70–130 USD` (`0 USD` subscription) |
| **2026** | ~160M – 208M *(run-rate / projected)* | Mixed (`-2%` to `-4%` YoY in core quarters) | **ASP & revenue rising** despite longer replacement cycles & higher memory costs | Split market: regulated wellness/risk screening in tier-1 watches vs. transparent open-source biofeedback workflows (BFM on Model 5010) |

---

## 2. Currently Available PPG Smartwatches (Vendor, Price & Capability)

> *Clinical Notice: Optical non-invasive blood glucose and cuffless blood pressure estimates on consumer watches are general wellness/trend indicators only and are **not FDA-cleared CGM or diagnostic medical devices**. Only Veepoo/`H Band` (`0xFEE7`) protocol watches integrate natively with BFM today, with the **Model 5010 Veepoo / FITAOS** watch hardware-verified so far.*

| Vendor & Model | Official Vendor Link | Approx. Retail Price *(USD)* | Core PPG & Biometric Sensor Capabilities | BFM Protocol Status |
| :--- | :--- | :--- | :--- | :--- |
| **FITAOS P12 / Max / Pro / 5150 Series** | https://fitaos.com | 70 – 130 USD | Multi-LED PPG (HR, SpO2, HRV/Lorenz, optical BP & glucose trend, lipids/uric acid estimates), Lead-I ECG (512 Hz), Skin Temp, BIA (select), Sleep/Apnea | **Native Veepoo (`0xFEE7`)** *(Model 5010 verified)* |
| **Veepoo Watch RIG** | https://www.veepoo.cn | 70 – 120 USD *(OEM/partner retail)* | Multi-channel green/red/IR PPG (`MAX86176`: HR, SpO2, HRV, optical BP trend, sleep apnea), Lead-I ECG (512 Hz), Skin Temp | **Native Veepoo (`0xFEE7`)** *(untested)* |
| **Veepoo OEM ET480 / ET580 / E500** | https://www.veepoo.cn | 40 – 80 USD *(global resellers)* | Multi-LED PPG (HR, SpO2, HRV, optical BP & glucose trend), Lead-I ECG (512 Hz), Skin Temp, BIA Body Comp (`E500`), Sleep | **Native Veepoo (`0xFEE7`)** *(untested)* |
| **Samsung Galaxy Watch 8 / Ultra** | https://www.samsung.com/us/watches/ | 349 – 649 USD | Multi-LED BioActive PPG (HR, SpO2, HRV, cuff-calibrated BP, vascular load, antioxidant index), Lead-I ECG, **BIA Body Comp**, Skin Temp | Requires Wear OS / Galaxy Health bridge |
| **Apple Watch Series 11 / Ultra 3** | https://www.apple.com/watch/ | 399 – 799 USD | Multi-path optical PPG (HR, SpO2, HRV, irregular rhythm, sleep apnea breathing disturbances), Lead-I ECG, Wrist Temp *(no BP or BIA)* | iOS-only / HealthKit export |
| **Google Pixel Watch 4 / 5** | https://store.google.com/category/watches | 349 – 399 USD | Multi-path PPG (HR, SpO2, HRV, sleep apnea), Lead-I ECG, Skin Temp, cEDA stress sensor *(no BP or BIA)* | Requires Wear OS / Health Connect bridge |
| **Fitbit Sense 2** | https://store.google.com/category/watches | 200 – 300 USD | Multi-path optical PPG (HR, SpO2, HRV, irregular rhythm), Lead-I ECG, Skin Temp, cEDA stress | Requires Fitbit Web API / Health Connect |
| **Fitbit Charge 6** | https://store.google.com/category/watches | 160 USD | Multi-path optical PPG (HR, SpO2, HRV), Lead-I ECG, EDA stress *(no skin temp or BIA)* | Requires Fitbit Web API / Health Connect |
| **Huawei Watch D2** | https://consumer.huawei.com/en/wearables/ | 350 – 450 USD *(limited US retail)* | TruSense PPG (HR, SpO2, HRV, arterial stiffness), Lead-I ECG, **True Mechanical Micro-Airbag Cuff BP**, Skin Temp | Proprietary Huawei Health app |
| **Huawei Watch GT 5 Pro** | https://consumer.huawei.com/en/wearables/ | 330 – 450 USD | TruSense PPG (HR, SpO2, HRV, arterial stiffness), Lead-I ECG, Skin Temp | Proprietary Huawei Health app |
| **Garmin Venu 3 / 4 & Fenix 8** | https://www.garmin.com/en-US/c/wearables-smartwatches/ | 450 – 1,000+ USD | Elevate Gen 5 PPG (HR, Pulse Ox SpO2, continuous HRV status, respiration, Body Battery), Lead-I ECG, Skin Temp *(no BP or BIA)* | Requires Garmin Connect / FIT export |
| **Withings ScanWatch 2 / Nova** | https://www.withings.com/us/en/watches | 350 – 600 USD | Multi-wavelength PPG (HR, SpO2, HRV, respiratory insights), Medical-grade Lead-I ECG, TempTech 24/7 Baseline Temp *(no BP or BIA)* | Requires Withings Health Mate API |
| **Amazfit Balance** | https://www.amazfit.com | 199 – 249 USD | BioTracker 5.0 dual-LED PPG (HR, SpO2, HRV, stress, readiness), **BIA Body Composition** (muscle/fat/water), Skin Temp *(no ECG)* | Requires Zepp / Health Connect bridge |
| **Polar Vantage V3** | https://www.polar.com/us-en/vantage/v3 | 699 USD | Polar Elixir optical PPG (HR, SpO2, HRV), Wrist Lead-I ECG, Nocturnal Skin Temp *(no BP or BIA)* | Polar BLE / Polar Flow API |
| **OnePlus Watch 3** | https://www.oneplus.com/us/oneplus-watch-3 | 329 – 350 USD | 8-channel HR + 16-channel SpO2 optical PPG, Wrist Temp, Vascular health check *(no US ECG or BIA)* | Requires Wear OS / OHealth bridge |
| **Bangle.js 2 (Open-Source BLE)** | https://banglejs.com | 100 – 115 USD | Open-firmware `VC31B` optical PPG (raw PPG waveform & HR accessible directly via Web Bluetooth) *(no ECG, SpO2, BP, or BIA)* | **Direct Open Web Bluetooth** (candidate driver) |

---

## 3. Non-Invasive PPG Glucose-Trend ODM Ecosystems (Beyond Veepoo)

Veepoo is **not** the sole non-invasive PPG glucose-trend vendor. Four main underlying hardware/software stacks plus pre-commercial RF R&D teams operate globally:

| Underlying ODM / Platform | Companion App / Ecosystem | Vendor Site | Optical Glucose Approach | Compatible with BFM Today? |
| :--- | :--- | :--- | :--- | :--- |
| **Shenzhen Veepoo Technology** | **H Band** / **Veepoo Health** | https://www.veepoo.cn | Multi-LED PPG optical glucose trend + ECG + BP + Lipids + BIA (`0xFEE7` BLE GATT) | **Yes (Native — Model 5010 verified)** |
| **Yucheng Innovation Technology** | **HealthWear** | Shenzhen ODM *(white-label)* | Multi-wavelength PPG optical glucose, ECG, BP, lipids & uric acid trend estimation | No (different BLE GATT protocol; future driver candidate) |
| **JointCorp (J-Style)** | **JCHealth / JC Ring / BGEM** | https://www.jointcorp.com | `BGEM` non-invasive blood glucose risk/trend estimation + PPG + ECG (`2051F`/`2025F` watches & rings) | No (different BLE protocol; future driver candidate) |
| **Shenzhen Kronus Technology** | **HealthWear** / custom OEM apps | Shenzhen ODM *(white-label)* | White-label PPG glucose trend + ECG + temperature watches | No (`HealthWear` stack) |
| **Huawei (Watch 4 Series)** | **Huawei Health** | https://consumer.huawei.com | Multi-sensor PPG + pulse-wave **hyperglycemia risk assessment** (study-backed risk score, not raw `mg/dL`) | No (closed Huawei Health ecosystem) |
| **Afon Technology *(R&D / Licensing)*** | *Glucowear* module | https://afontechnology.com | Non-invasive RF/microwave wrist glucose sensor licensed to watchmakers (not optical PPG; pre-commercial) | No |

---

## 4. Veepoo Ecosystem (`0xFEE7` / `H Band`) Worldwide Units & Private-Label Ranking

### Cumulative Worldwide Unit Estimate (2014–2026)

> *Methodology Notice: Shenzhen Veepoo Technology and its private-label clients are private entities and do not publish audited unit shipments. The estimate below is synthesized from public app-store install telemetry and disclosed B2B scale.*

| Verified Anchor Metric | Public Figure | Implied Worldwide Units Contribution |
| :--- | :--- | :--- |
| **Google Play (`com.veepoo.hband` + Veepoo apps)** | **10M – 11M+ installs** *(Google Play / AppBrain)* | Baseline Android (ex-China) installed base |
| **Apple App Store (`H Band`, iOS)** | Active global listing *(~25%–35% of Android ratio for value wearables)* | **~3M – 4M** modeled iOS installs |
| **China Domestic Android Stores** *(Huawei / Xiaomi / Oppo / Vivo — no Play Store)* | Undisclosed *(Shenzhen ODM since 2014)* | **~3M – 6M** modeled domestic installs |
| **Custom White-Label SDK Apps** *(500+ B2B clients, 900+ projects)* | **500+ B2B clients** (`veepoo.net`), `40+` patents | Adds non-`H Band` branded units; offset by `~1.2x–1.4x` multi-phone reinstalls |
| **Estimated Cumulative Worldwide Total (2014–2026)** | **Modeled Range** | **~12M – 18M total units worldwide** *(midpoint ~15M; **~3M – 5M** recent PPG+ECG generation)* |

### Ranked Table of Veepoo-Platform Private-Label Brands & OEM Channels

> *Note: Per-brand figures are directional market-share estimates derived from SKU breadth, multi-channel presence, and marketplace visibility — not audited financial disclosures.*

| Rank | Brand / Private-Label Channel | Primary Distribution | Representative Veepoo / `H Band` Models | Est. Share of Veepoo Ecosystem | Est. Cumulative Units *(Modeled)* |
| :---: | :--- | :--- | :--- | :---: | :---: |
| **1** | **Unbranded / Rotating OEM Storefronts** *(500+ white-label resellers)* | AliExpress, Amazon, Temu, Banggood, Walmart, eBay, China domestic | `ET480`, `ET580`, `E500`, `E600`, `E66`, `EP03`, `F57L`, `B57`, `H8` | **~45% – 55%** | **~6.5M – 8.5M** |
| **2** | **Lige** *(select `H Band` lines)* | Amazon, AliExpress, DTC | Lige ECG / PPG health watch SKUs *(mixes Veepoo with `Da Fit`)* | **~10% – 15%** | **~1.5M – 2.3M** |
| **3** | **Regional Mass-Market OEM Partners** *(e.g., Fire-Boltt select SKUs, EU/LATAM/Asia retail house brands)* | India / EU / LATAM / APAC retail & online | `Fire-Boltt 084` & regional pharmacy/retail house labels | **~10% – 15%** | **~1.5M – 2.3M** |
| **4** | **MorePro** | Amazon US/EU, DTC | MorePro health/BP/ECG/glucose watches (`H Band` lines) | **~5% – 8%** | **~750K – 1.2M** |
| **5** | **Rogbid** *(select `H Band` lines)* | DTC, AliExpress, Amazon | Rogbid health-series watches *(mixes ODMs)* | **~4% – 7%** | **~600K – 1.0M** |
| **6** | **FITAOS** | Direct DTC (https://fitaos.com) | `5150` / `5010`, `P12`, `Pro`, `Max`, `RIG` variants | **~2% – 5%** | **~300K – 750K** |
| **7** | **Veepoo Direct (`Veepoo Watch`)** | Corporate / Alibaba / select resellers | `Veepoo Watch RIG`, `F57L`, ODM reference models | **~2% – 4%** | **~300K – 600K** |
| **8** | **Bearscome** | Amazon / DTC | Rebranded Veepoo Watch RIG & ECG+PPG health models | **~1% – 3%** | **~150K – 450K** |
| **9** | **Spovan & Niche Rugged/Health Labels** | Amazon, AliExpress | Rugged/outdoor `H Band` health models | **~1% – 2%** | **~150K – 300K** |
| **Total** | **All Veepoo (`0xFEE7` / `H Band`) Worldwide** | **500+ global B2B clients / 900+ projects** | **All generations (2014–2026)** | **100%** | **~12M – 18M units** *(~3M – 5M full PPG+ECG gen)* |

**Hardware Identification Rule:** Any third-party watch whose manual or marketplace listing specifies **"App: H Band"** (or **"Veepoo Health"**) runs Veepoo firmware (`0xFEE7` BLE service) and is a direct BFM candidate. Devices pairing with `HealthWear`, `DeepFit`, `JCHealth`, or `Da Fit` use competing non-Veepoo ODMs.

---

## 5. Regulatory Warnings, Lawsuits, Failures & Negative Press (2001–2026)

### Summary Ledger of Major Legal, Regulatory & Media Events

| Year / Era | Entity / Product | Event Type | Core Finding / Outcome & Press Impact |
| :--- | :--- | :--- | :--- |
| **2001–2007** | **Cygnus GlucoWatch Biographer** | FDA-approved product failure & market withdrawal | First FDA-cleared wrist glucose monitor (reverse iontophoresis) failed commercially and was pulled due to electrical skin burns, frequent fingerstick calibration requirements, sweat artifacts, and interstitial lag. |
| **2014–2018** | **Fitbit (`PurePulse` / Force)** | Class-Action Lawsuits (*Owen v. Fitbit*) & CPSC Penalty | Consumers sued over alleged inaccurate PPG heart-rate measurements under intense exercise; many suits faced mandatory binding arbitration hurdles. Separately, Fitbit paid a CPSC civil penalty following Fitbit Force band-rash recalls. |
| **2020–2026** | **Masimo vs. Apple Watch** | Trade Secret & PPG SpO2 Patent Litigation + **ITC Import Ban** | Masimo alleged engineer poaching & trade-secret theft on optical pulse oximetry. Resulted in **US ITC import bans** (forcing Apple to disable US blood-oxygen on Series 9/Ultra 2 initially), a **634M USD federal jury damages verdict**, and **~184.2M USD** prejudgment interest (**>818M USD total**), pending appeals. |
| **2021–2025** | **AliveCor vs. Apple Watch** | ECG Patent & Antitrust Litigation | AliveCor sued after Apple added native Watch ECG and changed `watchOS` heart-rate access. Federal Circuit invalidated key AliveCor ECG patents and Ninth Circuit affirmed antitrust summary judgment for Apple. |
| **2023** | **Rockley Photonics** | **Chapter 11 Bankruptcy** | Apple-backed silicon photonics pioneer developing wrist infrared spectroscopy for non-invasive glucose, hydration, and alcohol collapsed under severe R&D cash burn and commercialization delays. |
| **Feb 21, 2024** | **U.S. FDA Safety Communication** | **Federal Medical Safety Warning** | FDA explicitly warned consumers, patients, and clinicians **"Do Not Use Smartwatches or Smart Rings to Measure Blood Glucose"** — stating **zero** non-invasive glucose smartwatches/rings are FDA-cleared or authorized, citing fatal hypoglycemia/hyperglycemia insulin-dosing errors. Clarified that FDA-cleared CGM watch companion displays remain valid. |
| **2024** | **Health Canada, UK MHRA & Australia TGA** | International Regulatory Warnings & Enforcement | **Health Canada** warned social-media glucose smartwatches pose severe medical risks and initiated enforcement actions to stop unauthorized domestic sales; **UK MHRA** and **Australia TGA** issued aligned advisories against unvalidated non-invasive glucose wearables. |
| **2023–2026** | **Drop-Shipper / Social Ad Glucose Watches** | Consumer Watchdog Press, **FTC**, **BBB Scam Tracker** | **PCMag**, **PhoneArena**, **AP News**, and **MedTech Dive** published scam exposés targeting sub-50 USD Facebook/TikTok/YouTube ads showing fabricated glucose readings. **Zero large class-action settlements** exist against anonymous overseas shell sellers due to shifting storefronts and cross-border jurisdiction barriers — driving FTC fraud reports, chargebacks, and BBB Scam Tracker complaints instead. *(By contrast, class-action litigation such as **Abbott FreeStyle Libre** sensor accuracy suits applies to FDA-cleared needle-based CGMs.)* |

### How BFM Addresses Regulatory & Consumer Trust Risks
1. **Explicit Non-CGM Legal Gating:** BFM enforces an unavoidable startup acceptance modal (`BFM-BETA1-2026`) stating the software is **experimental self-help biofeedback**, never FDA-cleared, and must never replace prescription glucometers or cleared CGMs for insulin or medication dosing.
2. **Pure Zero-Data Reality (`Zero Is Zero`):** Unsynced streams display `0` rather than fabricated demo curves; unknown meals output `0` macros rather than hallucinated values; medication tables never invent prescriptions.
3. **Zero Subscriptions & Local Sovereignty:** All telemetry remains locally inside the user's `telemetry.db` SQLite database and standard `BFM.HTM` console with **0 USD recurring fees**, open-source GPL v3 visibility, and full `.jsonl` development audit logs.

---

## 6. Outreach Contacts & Pitch Template (Google, Veepoo & FITAOS)

### Verified Partner, Press & Vendor Contacts

| Organization | Channel / Role | Verified Contact / Portal | Notes |
| :--- | :--- | :--- | :--- |
| **Google (Global Press / AI & Health PR)** | Media, PR & AI Showcase Inquiries | `press@google.com` | Global press desk covering Google Research, Google Health & Gemini. |
| **Google Partner Marketing Hub** | Co-Marketing & Brand Partnerships | `hub-registrations@google.com` / https://www.withgoogle.com/intl/en_us/partner-marketing-hub/ | Official partner marketing portal & registration desk. |
| **Google Research** | Research Collaborations & Grants | https://research.google/ | Faculty/Research collaboration programs (portal-managed; no public open inbox). |
| **Google Health** | Clinical & Life Sciences Partnerships | https://health.google/ | Healthcare/wearable ecosystem partnerships via Google Health / Google Cloud Healthcare. |
| **Shenzhen Veepoo Technology (ODM / SDK)** | B2B Business, Marketing & SDK Partnerships | `global@veepoo.cn` / https://www.veepoo.cn/contact | Primary B2B, marketing, firmware & SDK partnership email (also `hi@veepoo.cn` product, `support@veepoo.cn` support). |
| **FITAOS (Direct Retail / Veepoo Ecosystem)** | Brand & Support Contact | `hello@fitaos.com` / https://fitaos.com | Direct-to-consumer vendor for Veepoo/`H Band` PPG/ECG watches (`0 USD` monthly fee). |

### Ready-to-Send Short Outreach Email

> **Subject:** Open-Source Type 2 Diabetes Beta Built in 8 Working Days with Gemini Pro — BFM Presentation  
>  
> Hello [Team / Name],  
>  
> I built **BFM Biometric Function Monitor** (`JVEEPOO`), an open-source Beta continuous metabolic telemetry platform for Type 2 Diabetes (*"That Others May Survive Type II Diabetes"*), designed and engineered to Beta in **8 working days using Google Gemini Pro** — with complete `.jsonl` AI session logs included directly in the repository for independent verification.  
>  
> BFM captures **288 automated PPG/biometric readings per day** across multi-year timelines (solving the 90-day clinical sampling blind spot) across watch, phone, and PC, exporting Epic FHIR R4 and SQLite reports for physicians. We currently have no sponsors and are seeking **sponsorship and technical collaboration** (Google Gemini, Veepoo / FITAOS, and PPG watch vendors), plus volunteer Type 2 diabetic clinicians and developers.  
>  
> - **Interactive 89-Slide Narrated Presentation:** https://jbirdlebough1.github.io/JVEEPOO/presentation/preview.html  
> - **Source Tree & Full `.jsonl` AI Trajectory Archive:** https://github.com/jbirdlebough1/JVEEPOO  
>  
> Best regards,  
> John Birdlebough  

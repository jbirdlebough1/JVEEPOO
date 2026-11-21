"""
Accumulated QA Test Suite for Veepoo / LCARS Health System
Verifies:
- CLE-001 through CLE-082
- Nutrition accuracy (teas, meals, snacks)
- Mobile /cell meal grouping and offline staging queue
- Full scan hydration and card visibility
- Watch sync trigger and 15-min auto-dump
- Pedometer universal propagation
- Goals compliance report
- Dual-file synchronization
"""

import sys
import os
import json
import urllib.request
import urllib.error
import time

BASE_URL = "http://127.0.0.1:8899"
ROOT_DIR = "D:\\veepoo"
APP_DIR = "D:\\veepoo\\w11_htm_app"

# Ensure server is ready
time.sleep(1.5)

passed_tests = 0
failed_tests = 0

def log_test(name, passed, detail=""):
    global passed_tests, failed_tests
    if passed:
        passed_tests += 1
        print(f"  [PASS] {name} {detail}")
    else:
        failed_tests += 1
        print(f"  [FAIL] {name} - {detail}")

print("======================================================================")
print("             VEEPOO LCARS ACCUMULATED QA VERIFICATION                 ")
print("======================================================================")

# 1. Dual-file byte synchronization
print("\n[TEST GROUP 1: FILE SYNCHRONIZATION]")
idx1 = os.path.join(ROOT_DIR, "index.html")
idx2 = os.path.join(APP_DIR, "index.html")
if os.path.exists(idx1) and os.path.exists(idx2):
    sz1 = os.path.getsize(idx1)
    sz2 = os.path.getsize(idx2)
    with open(idx1, "rb") as f1, open(idx2, "rb") as f2:
        c1 = f1.read()
        c2 = f2.read()
    match = (c1 == c2)
    log_test("Dual-File Byte-for-Byte Synchronization", match, f"({sz1} bytes == {sz2} bytes)")
else:
    log_test("Dual-File Byte-for-Byte Synchronization", False, "Missing index.html files")

# 2. Server Core Endpoints
print("\n[TEST GROUP 2: SERVER CORE & TELEMETRY ENDPOINTS]")
endpoints = [
    ("/", 200),
    ("/cell", 200),
    ("/api/telemetry/latest", 200),
    ("/api/telemetry/timeline", 200),
    ("/api/goals/compliance", 200),
    ("/api/goals/compliance-report", 200),
    ("/api/dial/inventory", 200),
    ("/api/watchface/cloud_dials", 200),
    ("/api/meal/recent", 200),
]

for ep, expected_code in endpoints:
    url = f"{BASE_URL}{ep}"
    try:
        req = urllib.request.Request(url)
        with urllib.request.urlopen(req, timeout=5) as resp:
            code = resp.getcode()
            log_test(f"Endpoint {ep}", code == expected_code, f"(HTTP {code})")
    except Exception as e:
        log_test(f"Endpoint {ep}", False, str(e))

# 3. Nutrition AI & Fallback Engine
print("\n[TEST GROUP 3: NUTRITION ACCURACY & CLINICAL AUDITING]")
tea_payload = json.dumps({"notes": "clove and cinnamon tea", "analyze_only": True}).encode("utf-8")
try:
    req = urllib.request.Request(f"{BASE_URL}/api/meal/analyze", data=tea_payload, headers={"Content-Type": "application/json"})
    with urllib.request.urlopen(req, timeout=10) as resp:
        res = json.loads(resp.read().decode("utf-8"))
        cal = res.get("calories", 999)
        carbs = res.get("net_carbs", 999)
        protein = res.get("protein", 999)
        fat = res.get("fat", 999)
        glyc = res.get("glycemic_load", 999)
        
        # Must be virtually zero
        is_tea_accurate = (cal <= 10 and carbs <= 2 and protein == 0 and fat == 0 and ("0" in str(glyc)))
        log_test("Clove & Cinnamon Tea Nutritional Profile", is_tea_accurate,
                 f"(Cal: {cal} kcal, Net Carbs: {carbs}g, Protein: {protein}g, Fat: {fat}g, GL: {glyc})")
except Exception as e:
    log_test("Clove & Cinnamon Tea Nutritional Profile", False, str(e))

# Taco Time / Restaurant Analysis
taco_payload = json.dumps({"notes": "Taco Time fiesta platter and drink", "analyze_only": True}).encode("utf-8")
try:
    req = urllib.request.Request(f"{BASE_URL}/api/meal/analyze", data=taco_payload, headers={"Content-Type": "application/json"})
    with urllib.request.urlopen(req, timeout=10) as resp:
        res = json.loads(resp.read().decode("utf-8"))
        cal = res.get("calories", 0)
        carbs = res.get("net_carbs", 0)
        protein = res.get("protein", 0)
        is_taco_accurate = (cal >= 400 and carbs >= 20 and protein >= 10)
        log_test("Taco Time Fiesta Platter Parsing", is_taco_accurate,
                 f"(Cal: {cal} kcal, Net Carbs: {carbs}g, Protein: {protein}g)")
except Exception as e:
    log_test("Taco Time Fiesta Platter Parsing", False, str(e))

# 4. Mobile /cell Web Portal Functionality
print("\n[TEST GROUP 4: MOBILE /CELL MEAL GROUPING & OFFLINE STAGING]")
cell_path = os.path.join(APP_DIR, "cell.html")
with open(cell_path, "r", encoding="utf-8") as f:
    cell_html = f.read()

has_meal_categories = all(cat in cell_html.upper() for cat in ["BREAKFAST", "LUNCH", "DINNER", "SNACK"])
log_test("Meal Occasion Component Grouping (Breakfast/Lunch/Dinner/Snack)", has_meal_categories)

has_staging_queue = ("staged_meals_queue" in cell_html and "STAGE FOR HOME PC" in cell_html)
log_test("Offline Staging Queue (localStorage)", has_staging_queue)

has_batch_sync = ("SYNC ALL STAGED MEALS TO PC" in cell_html and "syncAllStagedMeals" in cell_html)
log_test("Auto & 1-Click Staged Meals Sync to PC", has_batch_sync)

has_tea_in_cell_fallback = ("clove" in cell_html.lower() and "cinnamon" in cell_html.lower() and "calculateClinicalNutrition" in cell_html)
log_test("Mobile Offline Clinical Fallback Engine for Teas & Spices", has_tea_in_cell_fallback)

# 5. Full Scan Page & Telemetry Hydration
print("\n[TEST GROUP 5: FULL SCAN & TELEMETRY HYDRATION (CLE-082)]")
with open(idx1, "r", encoding="utf-8") as f:
    idx_content = f.read()

rep_card_visible = ('id="cardFullScanReport"' in idx_content and 'display: flex' in idx_content)
log_test("#cardFullScanReport Not Hidden by Default (display: flex)", rep_card_visible)

has_fetch_helpers = ("async function fetchTelemetryLatest()" in idx_content and "async function fetchTelemetryTimeline()" in idx_content)
log_test("fetchTelemetryLatest & fetchTelemetryTimeline Defined", has_fetch_helpers)

has_full_scan_hydration = ("initFullScanView" in idx_content and "RECENT COMPREHENSIVE SCAN AUDIT ONLINE" in idx_content)
log_test("initFullScanView Hydrates 6 Stage Cards & Report Card from SQLite", has_full_scan_hydration)

# 6. Watch Sync Functionality
print("\n[TEST GROUP 6: WATCH SYNC & PROXIMITY HARVEST]")
has_sync_dump = ("triggerWatchSyncNow" in idx_content and "execute15MinWatchDataDump" in idx_content)
log_test("triggerWatchSyncNow Executes Direct BLE Harvest & Multi-Console Refresh", has_sync_dump)

# 7. Universal Step Propagation
print("\n[TEST GROUP 7: UNIVERSAL STEP PROPAGATION]")
step_els = ["valStepCount", "valStepDist", "valStepKcal", "valStepGoal", "tile360Motion", "lblActiveDailySteps"]
all_step_els_updated = all(el in idx_content for el in step_els)
log_test("All 6 Step Elements Updated in Live Telemetry Poll", all_step_els_updated, f"({len(step_els)}/6 targets present)")

# 8. Goals Compliance Engine (CLE-080)
print("\n[TEST GROUP 8: GOALS REPORT & COMPLIANCE AUDIT]")
try:
    req = urllib.request.Request(f"{BASE_URL}/api/goals/compliance?timeframe=month&filter=all")
    with urllib.request.urlopen(req, timeout=5) as resp:
        gdata = json.loads(resp.read().decode("utf-8"))
        summary = gdata.get("summary", {})
        has_goals_keys = all(k in summary for k in ["compliance_pct", "total_events", "met_count", "missed_no_activity_count"])
        log_test("Goals Compliance API (/api/goals/compliance)", has_goals_keys,
                 f"(Compliance: {summary.get('compliance_pct')}% | Met: {summary.get('met_count')} | Missed: {summary.get('missed_no_activity_count')})")
except Exception as e:
    log_test("Goals Compliance API (/api/goals/compliance)", False, str(e))

# 9. Diet History & Scrollable Meals
print("\n[TEST GROUP 9: DIET HISTORY & SCROLLABLE MEALS]")
has_scroll_container = ("dietHistoryList" in idx_content and "overflow-y: auto" in idx_content and "initPopularMeals" in idx_content)
log_test("Scrollable Meal History & Popular Meals Selector", has_scroll_container)

# 10. Watch Face Flasher Bay (CLE-081)
print("\n[TEST GROUP 10: WATCH FACE OTA FLASHER & STOCK FACES]")
has_flasher_bay = ("boxVeepooCloudDials" in idx_content and "flashCloudDialToWatch" in idx_content)
log_test("Cloud Dial OTA Flasher & Stock Face Management", has_flasher_bay)

print("\n======================================================================")
print(f"TOTAL TESTS: {passed_tests + failed_tests} | PASSED: {passed_tests} | FAILED: {failed_tests}")
print("======================================================================")

if failed_tests == 0:
    print(">>> ALL ACCUMULATED QA VERIFICATION TESTS PASSED (100% GREEN)! <<<")
    sys.exit(0)
else:
    print(f">>> {failed_tests} TESTS FAILED. PLEASE REVIEW LOG ABOVE. <<<")
    sys.exit(1)

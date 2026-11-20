# build_presentation.py - Master 40-Slide Clinical Audio-Visual Walkthrough
import os
import subprocess
import json
import shutil

pres_dir = r"D:\veepoo\presentation"
slides_dir = os.path.join(pres_dir, "slides")
audio_dir = os.path.join(pres_dir, "audio")
images_dir = os.path.join(pres_dir, "images")
slides_images_dir = os.path.join(slides_dir, "images")

os.makedirs(slides_dir, exist_ok=True)
os.makedirs(audio_dir, exist_ok=True)
os.makedirs(images_dir, exist_ok=True)
os.makedirs(slides_images_dir, exist_ok=True)

# Copy watch images to both directories
for img_name in ["watch_fitaos5010.jpg", "watch_fitaos5150.jpg"]:
    src_img = os.path.join(images_dir, img_name)
    dst_img = os.path.join(slides_images_dir, img_name)
    if os.path.exists(src_img):
        shutil.copy2(src_img, dst_img)

# Load master curriculum data
data_file = r"D:\veepoo\all_slides_data.json"
with open(data_file, "r", encoding="utf-8") as f:
    slides_data = json.load(f)

# Ensure "G'day mate!" is used ONLY ONCE across the entire curriculum (on Slide 1 only)
for s in slides_data:
    if s["id"] != 1:
        if s["narration"].startswith("G'day mate! "):
            s["narration"] = s["narration"][len("G'day mate! "):]
        s["narration"] = s["narration"].replace("G'day mate!", "Welcome!")

print(f"Loaded {len(slides_data)} slides from {data_file}")

# Write TRANSCRIPT.md
transcript_path = os.path.join(pres_dir, "TRANSCRIPT.md")
with open(transcript_path, "w", encoding="utf-8") as f:
    f.write("# LCARS 5010 Clinical Ambulatory Telemetry Onboarding - Official Narration Transcript\n\n")
    f.write("**Audio Track:** Australian Professional Technical Narration (Female - Edge TTS / en-AU-NatashaNeural)  \n")
    f.write("**Hardware Platform:** FITAOS 5150 / Veepoo Model 5010 Biosensing Array  \n")
    f.write("**Protocol:** Bluetooth Low Energy 5.0 Local-First Air-Gapped Telemetry  \n")
    f.write("**License:** GNU General Public License v3 (Open Source Copyleft)  \n")
    f.write("**Engineering:** Powered by Google Gemini Pro (Google DeepMind Agentic AI)  \n")
    f.write("**Scope:** Complete End-to-End Clinical, Operational, and Architectural Walkthrough  \n\n---\n\n")
    for s in slides_data:
        f.write(f"## Slide {s['num']}: {s['title']}\n")
        f.write(f"**Module:** {s['module']}  \n")
        f.write(f"**Description:** {s['desc']}  \n\n")
        f.write(f"> \"{s['narration']}\"\n\n---\n\n")

print(f"Created TRANSCRIPT.md with all {len(slides_data)} slides!")

# -----------------------------------------------------------------------------
# DYNAMIC PANEL GENERATORS
# -----------------------------------------------------------------------------

TAB_MAP = [
    (1, "01-VITALS", "images/lcars_tab_vitals.png"),
    (2, "02-CARDIO", "images/lcars_tab_cardio.png"),
    (3, "03-METABOLIC", "images/lcars_tab_metabolic.png"),
    (4, "04-REPORTS", "images/lcars_tab_reports.png"),
    (5, "05-CONFIG", "images/lcars_tab_setup.png"),
    (6, "06-DIAG LOG", "images/lcars_tab_log.png"),
    (7, "07-OPERATIONS", "images/lcars_tab_operations.png"),
    (8, "08-HELP & FAQ", "images/lcars_tab_help.png"),
    (9, "09-ADA TRADES", "images/lcars_tab_diettrades.png"),
    (10, "10-WATCH FACES", "images/lcars_tab_watchfaces.png"),
    (11, "11-MEDICATION", "images/lcars_tab_medication.png"),
    (12, "12-FULL SCAN", "images/lcars_tab_fullscan.png")
]

def get_active_tab_num(s, tab_img):
    if s and s.get("tab_num"):
        return int(s.get("tab_num"))
    if not tab_img:
        return 1
    base = os.path.basename(tab_img).lower()
    if "chart_" in base or "report" in base:
        return 4
    for t_num, t_name, t_path in TAB_MAP:
        if os.path.basename(t_path).lower() in base or base in os.path.basename(t_path).lower():
            return t_num
    return 1

def generate_left_panel(s):
    bullets_html = ""
    for idx, (title, text) in enumerate(s.get("bullets", [])):
        bullets_html += f"""
          <li id="bullet-item-{idx}" class="bullet-item" data-idx="{idx}" style="margin-bottom: 5px; line-height: 1.35; padding: 4px 8px; border-radius: 4px; border-left: 4px solid transparent; transition: all 0.25s ease;">
            <strong style="color: #0369a1;">{title}:</strong> {text}
          </li>
        """

    card_title = s.get("card_title", "CLINICAL OBJECTIVE & ARCHITECTURE")
    desc = s.get("desc", "")
    takeaway = s.get("takeaway", "Continuous telemetry ensures patient safety and clinical insight.")

    tab_img = s.get("tab_screenshot") or s.get("image") or ""
    tab_title = s.get("tab_title") or s.get("title") or "LCARS WORKSTATION CONSOLE"
    active_tab = get_active_tab_num(s, tab_img)
    active_tab_name = dict([(t[0], t[1]) for t in TAB_MAP]).get(active_tab, "01-VITALS")

    screenshot_html = ""
    if tab_img:
        screenshot_html = f"""
        <!-- Real LCARS Page Under Discussion (Pure Authentic Light Theme Screenshot) -->
        <div class="left-screenshot-card" style="margin-top: 6px; margin-bottom: 6px; border: 2px solid #cbd5e1; border-radius: 8px; overflow: hidden; background: #ffffff; box-shadow: 0 4px 14px rgba(0,0,0,0.08); display: flex; flex-direction: column; flex: 1; min-height: 290px;">
          <!-- Continuous Monotonic Scroll Viewport (Clean Light Background) -->
          <div class="left-screenshot-viewport" style="text-align: center; background: #ffffff; overflow-y: auto; flex: 1; min-height: 250px; position: relative;">
            <img src="{tab_img}" onerror="this.src='../{tab_img}'" alt="{tab_title}" style="width: 100%; height: auto; display: block; object-fit: cover; object-position: top center;">
          </div>
        </div>
        """

    return f"""
      <!-- High-Contrast Dynamic Content Card -->
      <div class="content-card" style="background: #ffffff; color: #091428; border: 2px solid #cbd5e1; border-top: 6px solid #0284c7; padding: 10px 14px; border-radius: 8px; display: flex; flex-direction: column; height: 100%; min-height: 0; box-sizing: border-box; overflow-y: auto;">
        <h3 style="color: #0284c7; font-size: 1.05rem; font-weight: 800; border-bottom: 2px solid #e2e8f0; padding-bottom: 4px; margin-bottom: 5px; text-transform: uppercase;">{card_title}</h3>
        <p style="font-size: 0.85rem; color: #334155; margin-bottom: 5px; line-height: 1.35; font-weight: 500;">{desc}</p>
        <ul class="bullets-list" style="padding-left: 0; list-style-type: none; margin-bottom: 4px; color: #1e293b; font-size: 0.80rem;">
          {bullets_html}
        </ul>
        {screenshot_html}
      </div>
    """

def generate_right_panel(s):
    header = s.get("right_header", "LCARS 5010 • CLINICAL INTERFACE")
    badge = s.get("right_badge", "● ONLINE")
    badge_col = s.get("right_badge_col", "#15803d")
    html_content = s.get("right_html", "")
    takeaway = s.get("takeaway", "Continuous telemetry ensures patient safety and clinical insight.")

    return f"""
      <div class="visual-card" style="background: #ffffff; color: #091428; border: 2px solid #cbd5e1; border-top: 6px solid {badge_col}; padding: 10px 14px; border-radius: 8px; display: flex; flex-direction: column; height: 100%; min-height: 0; box-sizing: border-box; overflow-y: auto; scroll-behavior: smooth;">
        <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 2px solid #e2e8f0; padding-bottom: 4px; margin-bottom: 6px; flex-shrink: 0;">
          <span style="font-family: monospace; font-weight: 800; color: #0284c7; font-size: 0.84rem;">{header}</span>
          <span style="background: #f8fafc; color: {badge_col}; font-weight: 800; font-size: 0.68rem; padding: 2px 7px; border-radius: 4px; border: 1.5px solid {badge_col};">{badge}</span>
        </div>
        <div class="visual-content" style="flex: 1; display: flex; flex-direction: column;">
          {html_content}
        </div>
        <div style="background: #f0fdf4; border-left: 4px solid #16a34a; padding: 6px 10px; border-radius: 4px; font-size: 0.78rem; color: #14532d; margin-top: auto; flex-shrink: 0;">
          <strong style="color: #15803d;">Key Takeaway:</strong> {takeaway}
        </div>
      </div>
    """

# -----------------------------------------------------------------------------
# SLIDE HTML TEMPLATE
# -----------------------------------------------------------------------------

slide_template = """<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>LCARS 5010 - Slide {num}: {title}</title>
  <style>
    * {{ box-sizing: border-box; margin: 0; padding: 0; }}
    html, body {{
      width: 100%;
      height: 100%;
      background: #f1f5f9;
      color: #0f172a;
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
      overflow: hidden;
      display: flex;
      flex-direction: column;
    }}
    .slide-frame {{
      flex: 1;
      height: 100vh;
      display: flex;
      flex-direction: column;
      padding: 8px 16px;
      gap: 6px;
      box-sizing: border-box;
      min-height: 0;
    }}
    .slide-header {{
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 2.5px solid #0284c7;
      padding: 6px 14px;
      background: #ffffff;
      border-radius: 6px;
      box-shadow: 0 1px 4px rgba(0,0,0,0.04);
      border: 1px solid #e2e8f0;
      flex-shrink: 0;
    }}
    .slide-module {{
      font-size: 0.78rem;
      color: #0284c7;
      font-weight: 800;
      letter-spacing: 0.08em;
      text-transform: uppercase;
    }}
    .slide-title {{
      font-size: 1.35rem;
      font-weight: 900;
      color: #0f172a;
      margin-top: 0px;
      letter-spacing: -0.01em;
    }}
    .slide-badge {{
      background: #0284c7;
      color: #ffffff;
      font-family: monospace;
      font-weight: 800;
      font-size: 0.76rem;
      padding: 3px 10px;
      border-radius: 4px;
      letter-spacing: 0.05em;
      flex-shrink: 0;
    }}
    .slide-body {{
      flex: 1;
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 12px;
      min-height: 0;
      height: calc(100vh - 66px);
    }}
    .slide-footer {{
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: 0.70rem;
      color: #64748b;
      font-family: monospace;
      border-top: 1px solid #cbd5e1;
      padding-top: 4px;
      flex-shrink: 0;
    }}
  </style>
</head>
<body>
  <div class="slide-frame">
    <div class="slide-header">
      <div>
        <div class="slide-module">{module}</div>
        <div class="slide-title">{title}</div>
      </div>
      <div class="slide-badge">SLIDE {num} / {total}</div>
    </div>

    <div class="slide-body">
{left_panel}
{right_panel}
    </div>

    <div class="slide-footer">
      <span>VEEPOO 5010 CLINICAL AMBULATORY TELEMETRY ONBOARDING</span>
      <span>SLIDE {num} OF {total}</span>
    </div>
  </div>
</body>
</html>
"""

# Build all slides dynamically
total_slides_count = len(slides_data)
for s in slides_data:
    left_html = generate_left_panel(s)
    right_html = generate_right_panel(s)
    
    html = slide_template.format(
        num=s["num"],
        title=s["title"],
        module=s["module"],
        total=total_slides_count,
        left_panel=left_html,
        right_panel=right_html
    )
    
    # Save to presentation/slides/slide_XX.html
    slide_file_sub = os.path.join(slides_dir, f"slide_{s['num']}.html")
    with open(slide_file_sub, "w", encoding="utf-8") as f:
        f.write(html)
        
    # Also save to presentation/slide_XX.html for direct access
    slide_file_root = os.path.join(pres_dir, f"slide_{s['num']}.html")
    with open(slide_file_root, "w", encoding="utf-8") as f:
        f.write(html)

    # Also save to D:\veepoo\slides\slide_XX.html for direct access
    veepoo_slides_dir = r"D:\veepoo\slides"
    os.makedirs(veepoo_slides_dir, exist_ok=True)
    with open(os.path.join(veepoo_slides_dir, f"slide_{s['num']}.html"), "w", encoding="utf-8") as f:
        f.write(html)

print(f"Generated all {total_slides_count} synchronized slides in slides/, presentation/, and D:\\veepoo\\slides!")

# -----------------------------------------------------------------------------
# BUILD preview.html (MASTER TECHNICAL PLAYER WITH AUSTRALIAN FEMALE VOICE)
# -----------------------------------------------------------------------------
slides_json_str = json.dumps(slides_data, indent=2)

preview_html_content = f"""<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>LCARS 5010 Clinical Ambulatory Telemetry Onboarding - Windows 11 Technical Player</title>
  <style>
    * {{ box-sizing: border-box; margin: 0; padding: 0; }}
    html, body {{
      width: 100vw;
      height: 100vh;
      overflow: hidden;
      background: #f1f5f9;
      color: #0f172a;
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
      display: flex;
      flex-direction: column;
    }}
    .player-header {{
      height: 36px;
      background: #ffffff;
      border-bottom: 1.5px solid #cbd5e1;
      box-shadow: 0 1px 4px rgba(0,0,0,0.04);
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 16px;
      user-select: none;
    }}
    .player-title {{
      font-size: 13px;
      font-weight: 800;
      color: #0f172a;
      letter-spacing: 0.04em;
      display: flex;
      align-items: center;
      gap: 10px;
    }}
    .player-badge {{
      background: #0284c7;
      color: #ffffff;
      font-family: monospace;
      font-size: 11px;
      padding: 2px 8px;
      border-radius: 3px;
      font-weight: 800;
    }}
    .player-main {{
      flex: 1;
      position: relative;
      background: #f8fafc;
    }}
    iframe {{
      width: 100%;
      height: 100%;
      border: none;
      display: block;
    }}
    .player-controls {{
      height: 42px;
      background: #ffffff;
      border-top: 1.5px solid #cbd5e1;
      box-shadow: 0 -1px 4px rgba(0,0,0,0.04);
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 16px;
      gap: 14px;
      user-select: none;
    }}
    .ctrl-btn {{
      background: #f8fafc;
      color: #0f172a;
      border: 1.5px solid #cbd5e1;
      border-radius: 4px;
      padding: 4px 12px;
      font-size: 11.5px;
      font-weight: 700;
      cursor: pointer;
      display: flex;
      align-items: center;
      gap: 6px;
      transition: all 0.15s ease;
    }}
    .ctrl-btn:hover {{
      background: #0284c7;
      color: #ffffff;
      border-color: #0284c7;
    }}
    .ctrl-btn.active {{
      background: #0284c7;
      color: #ffffff;
      box-shadow: 0 0 8px rgba(2, 132, 199, 0.4);
    }}
    .progress-bar-container {{
      flex: 1;
      display: flex;
      align-items: center;
      gap: 12px;
    }}
    .scrubber {{
      flex: 1;
      height: 8px;
      background: #e2e8f0;
      border-radius: 4px;
      position: relative;
      cursor: pointer;
      overflow: hidden;
    }}
    .scrubber-fill {{
      height: 100%;
      background: linear-gradient(90deg, #0284c7, #38bdf8);
      width: 0%;
      transition: width 0.1s linear;
    }}
    .transcript-drawer {{
      position: absolute;
      bottom: 0;
      left: 0;
      right: 0;
      background: rgba(255, 255, 255, 0.98);
      border-top: 2px solid #0284c7;
      backdrop-filter: blur(8px);
      padding: 16px 32px;
      max-height: 140px;
      display: none;
      overflow-y: auto;
      font-size: 14px;
      line-height: 1.5;
      color: #0f172a;
      box-shadow: 0 -4px 15px rgba(0,0,0,0.1);
    }}
  </style>
</head>
<body>
  <div class="player-header">
    <div class="player-title">
      <span class="player-badge">LCARS 5010</span>
      <span>CLINICAL AMBULATORY TELEMETRY ONBOARDING</span>
    </div>
    <div style="display: flex; align-items: center; gap: 10px;">
      <button class="ctrl-btn" onclick="toggleFullscreen()" style="padding: 3px 8px; font-size: 11px;">⛶ FULLSCREEN</button>
    </div>
  </div>

  <div class="player-main">
    <iframe id="slideIframe" src="slides/slide_01.html"></iframe>
    <div id="transcriptDrawer" class="transcript-drawer">
      <strong style="color: #0284c7; display: block; margin-bottom: 4px;" id="lblTranscriptTitle">SLIDE 01 NARRATION:</strong>
      <div id="txtTranscript">Loading narration...</div>
    </div>
  </div>

  <div class="player-controls">
    <div style="display: flex; gap: 8px;">
      <button class="ctrl-btn" onclick="prevSlide()">⏮ PREV</button>
      <button class="ctrl-btn" id="btnPlayPause" onclick="togglePlayPause()" style="background: #16a34a; color: #ffffff; border: 1.5px solid #15803d; font-weight: 800; min-width: 82px;">▶ RUN</button>
      <button class="ctrl-btn" onclick="nextSlide()">NEXT ⏭</button>
    </div>

    <div class="progress-bar-container">
      <span id="lblTime" style="font-family: monospace; font-size: 11px; color: #64748b; width: 45px;">00:00</span>
      <div class="scrubber" onclick="onScrub(event)">
        <div id="scrubberFill" class="scrubber-fill"></div>
      </div>
      <span id="lblSlideCounter" style="font-family: monospace; font-size: 11px; color: #0284c7; font-weight: 800; width: 65px; text-align: right;">01 / {len(slides_data)}</span>
    </div>

    <div style="display: flex; gap: 8px; align-items: center;">
      <select id="selSlide" onchange="jumpToSlide(parseInt(this.value))" style="background: #ffffff; color: #0f172a; border: 1.5px solid #cbd5e1; border-radius: 4px; padding: 4px 8px; font-size: 11.5px;">
      </select>
      <button class="ctrl-btn" id="btnToggleTranscript" onclick="toggleTranscript()">📜 SCRIPT</button>
      <button class="ctrl-btn" id="btnSpeed" onclick="cycleSpeed()" style="width: 48px; justify-content: center;">1.0x</button>
    </div>
  </div>

  <!-- Audio Element with High-Fidelity Pre-Rendered Female MP3s -->
  <audio id="audioPlayer" preload="auto"></audio>

  <script>
    const slides = {slides_json_str};

    let currentIndex = 0;
    let isPlaying = false;
    let playbackSpeed = 1.0;
    let auFemaleVoice = null;
    let synth = window.speechSynthesis;
    let currentUtterance = null;
    let slideTimer = null;
    let audioUnlocked = false;

    function updatePlayPauseUI() {{
      const btn = document.getElementById('btnPlayPause');
      if (!btn) return;
      if (isPlaying) {{
        btn.innerText = "⏸ PAUSE";
        btn.style.background = "#0284c7";
        btn.style.borderColor = "#0369a1";
        btn.style.color = "#ffffff";
        btn.classList.add('active');
      }} else {{
        btn.innerText = "▶ RUN";
        btn.style.background = "#16a34a";
        btn.style.borderColor = "#15803d";
        btn.style.color = "#ffffff";
        btn.classList.remove('active');
      }}
    }}

    // Populate Slide Selector
    const sel = document.getElementById('selSlide');
    slides.forEach((s, idx) => {{
      const opt = document.createElement('option');
      opt.value = idx;
      opt.text = `${{s.num}}. ${{s.title}}`;
      sel.appendChild(opt);
    }});

    // Initialize Web Speech Fallback Voices (Prefer Female Australian)
    function initVoices() {{
      if (!synth) return;
      const voices = synth.getVoices();
      auFemaleVoice = voices.find(v => (v.lang === 'en-AU' || v.lang.startsWith('en-AU')) && (v.name.includes('Natasha') || v.name.includes('Catherine') || v.name.includes('Female') || v.name.includes('Karen'))) ||
                      voices.find(v => v.lang === 'en-AU' || v.lang.startsWith('en-AU')) ||
                      voices.find(v => (v.lang === 'en-GB' || v.lang.startsWith('en-GB')) && (v.name.includes('Female') || v.name.includes('Sonia') || v.name.includes('Libby'))) ||
                      voices.find(v => v.lang.startsWith('en'));
    }}
    if (synth) {{
      synth.onvoiceschanged = initVoices;
      initVoices();
    }}

    function showUnmuteBanner() {{
      const banner = document.getElementById('unmuteNotice');
      if (banner) banner.style.display = 'flex';
    }}

    function hideUnmuteBanner() {{
      const banner = document.getElementById('unmuteNotice');
      if (banner) banner.style.display = 'none';
      audioUnlocked = true;
    }}

    function handleUserGesture() {{
      hideUnmuteBanner();
      const s = slides[currentIndex];
      speakNarration(s.narration);
    }}

    // Global interaction listeners to guarantee audio un-mute on very first user interaction
    ['click', 'pointerdown', 'keydown', 'touchstart'].forEach(evt => {{
      window.addEventListener(evt, () => {{
        if (!audioUnlocked) handleUserGesture();
      }}, {{ passive: true }});
      document.addEventListener(evt, () => {{
        if (!audioUnlocked) handleUserGesture();
      }}, {{ passive: true }});
    }});

    function loadSlide(index) {{
      if (index < 0) index = 0;
      if (index >= slides.length) index = slides.length - 1;
      currentIndex = index;

      const s = slides[currentIndex];
      document.getElementById('slideIframe').src = `slides/slide_${{s.num}}.html`;
      document.getElementById('lblSlideCounter').innerText = `${{s.num}} / ${{slides.length}}`;
      document.getElementById('selSlide').value = currentIndex;
      document.getElementById('scrubberFill').style.width = `${{((currentIndex + 1) / slides.length) * 100}}%`;
      document.getElementById('lblTranscriptTitle').innerText = `SLIDE ${{s.num}}: ${{s.title}}`;
      document.getElementById('txtTranscript').innerText = s.narration;

      // Position IMMEDIATELY on transition to the specific initial screen area!
      const initialPct = (s.step_focus && s.step_focus.length > 0) ? s.step_focus[0].scroll_pct : 0.0;
      currentScrollLeft = initialPct * 1000;
      targetScrollLeft = initialPct * 1000;
      currentScrollRight = 0;
      targetScrollRight = 0;

      try {{
        const iframe = document.getElementById('slideIframe');
        iframe.onload = () => {{
          const doc = iframe.contentDocument;
          if (doc) {{
            doc.addEventListener('keydown', handleGlobalKeyDown);
            const leftViewport = doc.querySelector('.left-screenshot-viewport');
            if (leftViewport) {{
              const maxLeftScroll = leftViewport.scrollHeight - leftViewport.clientHeight;
              const initPos = (maxLeftScroll > 0) ? initialPct * maxLeftScroll : 0;
              leftViewport.scrollTop = initPos;
              currentScrollLeft = initPos;
              targetScrollLeft = initPos;
            }}
          }}
          currentScrollRight = 0;
          targetScrollRight = 0;
          syncSlideContent(0.0);
        }};
        if (iframe && iframe.contentDocument) {{
          iframe.contentDocument.addEventListener('keydown', handleGlobalKeyDown);
          const leftViewport = iframe.contentDocument.querySelector('.left-screenshot-viewport');
          if (leftViewport) {{
            const maxLeftScroll = leftViewport.scrollHeight - leftViewport.clientHeight;
            const initPos = (maxLeftScroll > 0) ? initialPct * maxLeftScroll : 0;
            leftViewport.scrollTop = initPos;
            currentScrollLeft = initPos;
            targetScrollLeft = initPos;
          }}
          currentScrollRight = 0;
          targetScrollRight = 0;
          syncSlideContent(0.0);
        }}
      }} catch(e) {{}}

      // Speak narration only if running
      if (isPlaying) {{
        speakNarration(s.narration);
      }}
    }}

    let currentScrollLeft = 0;
    let targetScrollLeft = 0;
    let currentScrollRight = 0;
    let targetScrollRight = 0;
    let animLoopId = null;

    function renderSmoothLoop() {{
      const audioEl = document.getElementById('audioPlayer');
      if (isPlaying && audioEl && audioEl.duration > 0 && !audioEl.paused) {{
        const norm = audioEl.currentTime / audioEl.duration;
        syncSlideContent(norm);
      }}

      try {{
        const iframe = document.getElementById('slideIframe');
        if (iframe && iframe.contentDocument) {{
          const leftViewport = iframe.contentDocument.querySelector('.left-screenshot-viewport');
          if (leftViewport && leftViewport.scrollHeight > leftViewport.clientHeight) {{
            // Smooth 60fps linear interpolation: glides 8% of distance each frame
            currentScrollLeft += (targetScrollLeft - currentScrollLeft) * 0.08;
            leftViewport.scrollTop = currentScrollLeft;
          }}
          const rightCard = iframe.contentDocument.querySelector('.visual-card');
          if (rightCard && rightCard.scrollHeight > rightCard.clientHeight) {{
            currentScrollRight += (targetScrollRight - currentScrollRight) * 0.08;
            rightCard.scrollTop = currentScrollRight;
          }}
        }}
      }} catch(e) {{}}

      animLoopId = requestAnimationFrame(renderSmoothLoop);
    }}

    function syncSlideContent(norm) {{
      try {{
        const iframe = document.getElementById('slideIframe');
        if (!iframe || !iframe.contentDocument) return;
        const doc = iframe.contentDocument;

        const leftViewport = doc.querySelector('.left-screenshot-viewport');
        const rightCard = doc.querySelector('.visual-card');
        const zoneBadge = doc.getElementById('lblScreenshotZone');
        const bullets = doc.querySelectorAll('.bullet-item');
        const s = slides[currentIndex];

        const numBullets = (s.bullets && s.bullets.length > 0) ? s.bullets.length : 3;
        let activeIdx = Math.floor(norm * numBullets);
        if (activeIdx >= numBullets) activeIdx = numBullets - 1;
        if (activeIdx < 0) activeIdx = 0;

        // Highlight active bullet item (sight & text sync)
        bullets.forEach((b, idx) => {{
          if (idx === activeIdx) {{
            b.style.background = '#e0f2fe';
            b.style.borderLeft = '4px solid #0284c7';
            b.style.color = '#0369a1';
            b.style.fontWeight = '700';
            b.style.paddingLeft = '10px';
          }} else {{
            b.style.background = 'transparent';
            b.style.borderLeft = '4px solid transparent';
            b.style.color = '#1e293b';
            b.style.fontWeight = 'normal';
            b.style.paddingLeft = '8px';
          }}
        }});

        // Dynamic Zone Badge Header
        if (zoneBadge) {{
          if (s.bullets && s.bullets[activeIdx]) {{
            const bTitle = s.bullets[activeIdx][0].toUpperCase();
            zoneBadge.innerText = `📍 FOCUS: ${{bTitle.length > 28 ? bTitle.substring(0, 26) + '...' : bTitle}}`;
          }} else {{
            zoneBadge.innerText = `📍 FOCUS: SECTION ${{activeIdx + 1}}`;
          }}
          if (activeIdx === 0) zoneBadge.style.background = '#0284c7';
          else if (activeIdx === 1) zoneBadge.style.background = '#0369a1';
          else zoneBadge.style.background = '#0f766e';
        }}

        // Monotonic Top-to-Bottom Smooth Target: glides steadily without jumping up and down
        // Plateau Scroll & Pause Timing Engine:
        // Holds 100% steady during the topic talk, glides monotonically between topics, never jumps up
        if (leftViewport && leftViewport.scrollHeight > leftViewport.clientHeight) {{
          const maxLeftScroll = leftViewport.scrollHeight - leftViewport.clientHeight;
          if (maxLeftScroll > 0) {{
            const focusPoints = (s.step_focus && s.step_focus.length > 0) 
              ? s.step_focus 
              : [{{scroll_pct: 0.0}}, {{scroll_pct: 0.40}}, {{scroll_pct: 0.85}}];
            
            const n = focusPoints.length;
            let targetPct = focusPoints[0].scroll_pct;
            
            for (let i = 0; i < n; i++) {{
              const segStart = i / n;
              const segEnd = (i + 1) / n;
              // Hold/Pause steady during 75% of this topic duration!
              const pauseUntil = segStart + (segEnd - segStart) * 0.75;
              
              if (norm < pauseUntil) {{
                targetPct = focusPoints[i].scroll_pct;
                break;
              }} else if (norm < segEnd) {{
                if (i < n - 1) {{
                  const t = (norm - pauseUntil) / (segEnd - pauseUntil);
                  const ease = 0.5 - 0.5 * Math.cos(t * Math.PI);
                  targetPct = focusPoints[i].scroll_pct + (focusPoints[i+1].scroll_pct - focusPoints[i].scroll_pct) * ease;
                }} else {{
                  targetPct = focusPoints[i].scroll_pct;
                }}
                break;
              }} else {{
                targetPct = focusPoints[i].scroll_pct;
              }}
            }}
            targetScrollLeft = targetPct * maxLeftScroll;
          }}
        }}

        if (rightCard && rightCard.scrollHeight > rightCard.clientHeight) {{
          const maxScroll = rightCard.scrollHeight - rightCard.clientHeight;
          targetScrollRight = norm * maxScroll;
        }}
      }} catch(e) {{}}
    }}

    function speakNarration(text) {{
      if (synth) synth.cancel();
      clearTimeout(slideTimer);

      if (!isPlaying) return;

      const s = slides[currentIndex];
      const audioEl = document.getElementById('audioPlayer');
      audioEl.src = `audio/slide_${{s.num}}.mp3`;
      audioEl.playbackRate = playbackSpeed;
      audioEl.volume = 1.0;
      audioEl.muted = false;

      const p = audioEl.play();
      if (p !== undefined) {{
        p.then(() => {{
          audioEl.onended = () => {{
            if (isPlaying) nextSlide();
          }};
        }}).catch((err) => {{
          console.warn("Audio play error:", err);
        }});
      }}
    }}

    function togglePlayPause() {{
      isPlaying = !isPlaying;
      updatePlayPauseUI();
      if (isPlaying) {{
        const s = slides[currentIndex];
        speakNarration(s.narration);
      }} else {{
        if (synth) synth.cancel();
        const audioEl = document.getElementById('audioPlayer');
        if (audioEl) audioEl.pause();
        clearTimeout(slideTimer);
      }}
    }}

    function nextSlide() {{
      if (currentIndex < slides.length - 1) {{
        loadSlide(currentIndex + 1);
        if (isPlaying) {{
          const s = slides[currentIndex];
          speakNarration(s.narration);
        }}
      }} else {{
        isPlaying = false;
        updatePlayPauseUI();
      }}
    }}

    function prevSlide() {{
      if (currentIndex > 0) {{
        loadSlide(currentIndex - 1);
        if (isPlaying) {{
          const s = slides[currentIndex];
          speakNarration(s.narration);
        }}
      }}
    }}

    function jumpToSlide(idx) {{
      loadSlide(idx);
      if (isPlaying) {{
        const s = slides[currentIndex];
        speakNarration(s.narration);
      }}
    }}

    function toggleTranscript() {{
      const d = document.getElementById('transcriptDrawer');
      const btn = document.getElementById('btnToggleTranscript');
      if (d.style.display === 'block') {{
        d.style.display = 'none';
        btn.classList.remove('active');
      }} else {{
        d.style.display = 'block';
        btn.classList.add('active');
      }}
    }}

    function cycleSpeed() {{
      const speeds = [1.0, 1.25, 1.5, 0.8];
      let curIdx = speeds.indexOf(playbackSpeed);
      playbackSpeed = speeds[(curIdx + 1) % speeds.length];
      document.getElementById('btnSpeed').innerText = `${{playbackSpeed}}x`;
      if (isPlaying) {{
        const s = slides[currentIndex];
        speakNarration(s.narration);
      }}
    }}

    function onScrub(e) {{
      const rect = e.currentTarget.getBoundingClientRect();
      const pct = (e.clientX - rect.left) / rect.width;
      const targetIdx = Math.floor(pct * slides.length);
      loadSlide(targetIdx);
      if (isPlaying) {{
        const s = slides[currentIndex];
        speakNarration(s.narration);
      }}
    }}

    function toggleFullscreen() {{
      if (!document.fullscreenElement) {{
        document.documentElement.requestFullscreen();
      }} else {{
        if (document.exitFullscreen) document.exitFullscreen();
      }}
    }}

    function handleGlobalKeyDown(e) {{
      if (e.code === 'Space' || e.key === ' ' || e.keyCode === 32) {{
        e.preventDefault();
        togglePlayPause();
      }} else if (e.code === 'ArrowRight' || e.key === 'ArrowRight') {{
        nextSlide();
      }} else if (e.code === 'ArrowLeft' || e.key === 'ArrowLeft') {{
        prevSlide();
      }} else if (e.key === 'f' || e.key === 'F') {{
        toggleFullscreen();
      }}
    }}

    window.addEventListener('keydown', handleGlobalKeyDown);

    // Start on Slide 1 in RUN standby state, start render loop
    window.onload = () => {{
      loadSlide(0);
      updatePlayPauseUI();
      renderSmoothLoop();
    }};
  </script>
</body>
</html>
"""

preview_path = os.path.join(pres_dir, "preview.html")
with open(preview_path, "w", encoding="utf-8") as f:
    f.write(preview_html_content)

# Also copy preview.html to root D:\veepoo\preview.html
root_preview_path = r"D:\veepoo\preview.html"
with open(root_preview_path, "w", encoding="utf-8") as f:
    f.write(preview_html_content)

print("Generated preview.html in presentation/ and D:\\veepoo\\preview.html with Australian Female Voice!")

# -----------------------------------------------------------------------------
# COMPILE C# WINDOWS 11 LAUNCHER (Preview_Presentation.exe)
# -----------------------------------------------------------------------------
launcher_cs = r"""using System;
using System.Diagnostics;
using System.IO;

class Program
{
    [STAThread]
    static void Main()
    {
        string baseDir = AppDomain.CurrentDomain.BaseDirectory;
        string localFile = Path.Combine(baseDir, "preview.html");
        if (!File.Exists(localFile))
        {
            localFile = Path.Combine(baseDir, "presentation", "preview.html");
        }

        string target = File.Exists(localFile)
            ? localFile
            : "http://localhost:8899/presentation/preview.html";

        string targetUri = new Uri(Path.GetFullPath(target)).AbsoluteUri;

        // Paths to Edge and Chrome on Windows 11
        string[] browserPaths = new string[]
        {
            @"C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe",
            @"C:\Program Files\Microsoft\Edge\Application\msedge.exe",
            @"C:\Program Files\Google\Chrome\Application\chrome.exe",
            @"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"
        };

        foreach (string browserPath in browserPaths)
        {
            if (File.Exists(browserPath))
            {
                try
                {
                    // Launch with unmuted autoplay policy flag
                    ProcessStartInfo psi = new ProcessStartInfo
                    {
                        FileName = browserPath,
                        Arguments = "--autoplay-policy=no-user-gesture-required \"" + targetUri + "\"",
                        UseShellExecute = false
                    };
                    Process.Start(psi);
                    return;
                }
                catch {}
            }
        }

        // Fallback to default system shell handler
        try
        {
            Process.Start(new ProcessStartInfo(targetUri) { UseShellExecute = true });
        }
        catch
        {
            Process.Start("cmd.exe", "/c start \"\" \"" + targetUri + "\"");
        }
    }
}
"""

cs_path = os.path.join(pres_dir, "SimpleLauncher.cs")
with open(cs_path, "w", encoding="utf-8") as f:
    f.write(launcher_cs)

csc_path = r"C:\Windows\Microsoft.NET\Framework64\v4.0.30319\csc.exe"
exe_path_1 = os.path.join(pres_dir, "Preview_Presentation.exe")
exe_path_2 = r"D:\veepoo\Preview_Presentation.exe"

if os.path.exists(csc_path):
    cmd = [csc_path, "/nologo", "/t:winexe", "/optimize+", f"/out:{exe_path_1}", cs_path]
    print("Compiling Windows 11 executable launcher:", " ".join(cmd))
    res = subprocess.run(cmd, capture_output=True, text=True)
    print("Compilation Output Code:", res.returncode)
    if os.path.exists(exe_path_1):
        shutil.copy2(exe_path_1, exe_path_2)
        print("Copied Preview_Presentation.exe to D:\\veepoo\\Preview_Presentation.exe successfully!")
else:
    print("Warning: csc.exe not found at standard path.")

print(f"All {total_slides_count} slides, female narration transcript, preview player, and W11 launcher built successfully!")

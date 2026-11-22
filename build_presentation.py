# build_presentation.py - Master {len(slides)}-Slide Clinical Audio-Visual Walkthrough
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
    f.write("# LCARS 5010 Clinical Ambulatory Telemetry Onboarding - Complete Voice Transcript\n\n")
    f.write("Voice: **Australian Female Voice (`en-AU-NatashaNeural`)**\n\n")
    f.write("---\n\n")
    for s in slides_data:
        f.write(f"### Slide {s['num']}: {s['title']}\n")
        f.write(f"**Module:** {s['module']}  \n")
        f.write(f"**Card Title:** {s['card_title']}  \n\n")
        f.write(f"> {s['narration']}\n\n")
        if s.get("bullets"):
            f.write("**Key Points:**\n")
            for b in s["bullets"]:
                f.write(f"- **{b[0]}:** {b[1]}\n")
            f.write("\n")
        f.write(f"**Key Takeaway:** {s.get('takeaway', '')}\n\n")
        f.write("---\n\n")

print(f"Created TRANSCRIPT.md with all {len(slides_data)} slides!")

# -----------------------------------------------------------------------------
# TAB MAP DEFINITIONS
# -----------------------------------------------------------------------------
TAB_MAP = [
    (1, "01-FULL SCAN", "images/lcars_tab01_fullscan.png"),
    (2, "02-MEAL PLANNER", "images/lcars_tab02_meal_planner.png"),
    (3, "03-CARDIO", "images/lcars_tab03_cardio.png"),
    (4, "04-REPORTS", "images/lcars_tab04_reports.png"),
    (5, "05-EXPORT", "images/lcars_tab05_export.png"),
    (6, "06-LEARNING", "images/lcars_tab06_learning.png"),
    (7, "07-HELP & FAQ", "images/lcars_tab07_help.png"),
    (8, "08-MEDICATION", "images/lcars_tab08_medication.png"),
    (9, "09-CLINICAL GOALS", "images/lcars_tab09_goals.png"),
    (10, "10-CONFIG", "images/lcars_tab10_config.png"),
    (11, "11-OPERATIONS", "images/lcars_tab11_operations.png"),
    (12, "12-DATA LOG", "images/lcars_tab12_datalog.png"),
    (13, "13-WATCH FACES", "images/lcars_tab13_watchfaces.png")
]

def get_active_tab_num(s, tab_img):
    if s.get("tab_num"):
        return s["tab_num"]
    base = os.path.basename(tab_img or "").lower()
    if "fullscan" in base or "tab01" in base:
        return 1
    if "diet" in base or "meal" in base or "tab02" in base:
        return 2
    if "cardio" in base or "vital" in base or "tab03" in base:
        return 3
    if "chart_" in base or "report" in base or "tall" in base or "tab04" in base or "lorenz" in base or "habit" in base:
        return 4
    if "export" in base or "tab05" in base:
        return 5
    if "learning" in base or "tab06" in base:
        return 6
    if "help" in base or "faq" in base or "tab07" in base:
        return 7
    if "medication" in base or "med_" in base or "tab08" in base or "titration" in base:
        return 8
    if "goal" in base or "tab09" in base:
        return 9
    if "setup" in base or "config" in base or "tab10" in base:
        return 10
    if "operation" in base or "tab11" in base:
        return 11
    if "log" in base or "datalog" in base or "tab12" in base:
        return 12
    if "watchface" in base or "dial" in base or "tab13" in base:
        return 13
    for t_num, t_name, t_path in TAB_MAP:
        if os.path.basename(t_path).lower() in base:
            return t_num
    return 1

def generate_left_panel(s):
    is_steady = s.get("no_scroll", False) or s.get("num") in ["03", "04", "05", "3", "4", "5"]
    
    bullets_html = ""
    for idx, (title, text) in enumerate(s.get("bullets", [])):
        li_padding = "2px 6px;" if is_steady else "4px 8px;"
        li_margin = "margin-bottom: 2px;" if is_steady else "margin-bottom: 5px;"
        bullets_html += f"""
          <li id="bullet-item-{idx}" class="bullet-item" data-idx="{idx}" style="{li_margin} line-height: 1.25; padding: {li_padding} border-radius: 4px; border-left: 4px solid transparent; transition: background 0.2s, border-color 0.2s;">
            <strong style="color: #0369a1;">{title}:</strong> {text}
          </li>
        """

    card_title = s.get("card_title", "CLINICAL OBJECTIVE & ARCHITECTURE")
    desc = s.get("desc", "")
    tab_img = s.get("tab_screenshot") or s.get("image") or ""
    tab_title = s.get("tab_title") or s.get("title") or "LCARS WORKSTATION CONSOLE"

    screenshot_html = ""
    if tab_img:
        if is_steady:
            screenshot_html = f"""
            <!-- Real LCARS Page Under Discussion (Steady Viewport) -->
            <div class="left-screenshot-card" style="margin-top: 4px; margin-bottom: 2px; border: 1.5px solid #cbd5e1; border-radius: 6px; overflow: hidden; background: #ffffff; box-shadow: 0 2px 6px rgba(0,0,0,0.06); display: flex; flex-direction: column; flex: 1; min-height: 100px; max-height: 160px;">
              <div class="left-screenshot-viewport" style="text-align: center; background: #ffffff; overflow: hidden; height: 100%; position: relative;">
                <img src="{tab_img}" onerror="this.src='../{tab_img}'" alt="{tab_title}" style="width: 100%; height: 100%; object-fit: contain; object-position: center;">
              </div>
            </div>
            """
        else:
            screenshot_html = f"""
            <!-- Real LCARS Page Under Discussion (Pure Authentic Light Theme Screenshot) -->
            <div class="left-screenshot-card" style="margin-top: 6px; margin-bottom: 6px; border: 2px solid #cbd5e1; border-radius: 8px; overflow: hidden; background: #ffffff; box-shadow: 0 4px 14px rgba(0,0,0,0.08); display: flex; flex-direction: column; flex: 1; min-height: 290px;">
              <!-- Continuous Monotonic Scroll Viewport (Clean Light Background) -->
              <div class="left-screenshot-viewport" style="text-align: center; background: #ffffff; overflow-y: auto; flex: 1; min-height: 250px; position: relative;">
                <img src="{tab_img}" onerror="this.src='../{tab_img}'" alt="{tab_title}" style="width: 100%; height: auto; display: block; object-fit: cover; object-position: top center;">
              </div>
            </div>
            """

    card_overflow = "overflow: hidden;" if is_steady else "overflow-y: auto;"
    card_padding = "padding: 8px 12px;" if is_steady else "padding: 10px 14px;"
    h3_margin = "margin-bottom: 2px; font-size: 0.95rem;" if is_steady else "margin-bottom: 5px; font-size: 1.05rem;"
    p_margin = "margin-bottom: 3px; font-size: 0.77rem; line-height: 1.25;" if is_steady else "margin-bottom: 5px; font-size: 0.85rem; line-height: 1.35;"
    ul_size = "font-size: 0.72rem;" if is_steady else "font-size: 0.80rem;"

    return f"""
      <!-- High-Contrast Dynamic Content Card -->
      <div class="content-card" style="background: #ffffff; color: #091428; border: 2px solid #cbd5e1; border-top: 6px solid #0284c7; {card_padding} border-radius: 8px; display: flex; flex-direction: column; height: 100%; min-height: 0; box-sizing: border-box; {card_overflow}">
        <h3 style="color: #0284c7; font-weight: 800; border-bottom: 2px solid #e2e8f0; padding-bottom: 4px; {h3_margin} text-transform: uppercase;">{card_title}</h3>
        <p style="color: #334155; font-weight: 500; {p_margin}">{desc}</p>
        <ul class="bullets-list" style="padding-left: 0; list-style-type: none; margin-bottom: 3px; color: #1e293b; {ul_size}">
          {bullets_html}
        </ul>
        {screenshot_html}
      </div>
    """

def generate_right_panel(s):
    is_steady = s.get("no_scroll", False) or s.get("num") in ["03", "04", "05", "3", "4", "5"]
    header = s.get("right_header", "LCARS 5010 • CLINICAL INTERFACE")
    badge = s.get("right_badge", "● ONLINE")
    badge_col = s.get("right_badge_col", "#15803d")
    html_content = s.get("right_html", "")
    takeaway = s.get("takeaway", "Continuous telemetry ensures patient safety and clinical insight.")

    card_overflow = "overflow: hidden;" if is_steady else "overflow-y: auto;"
    card_padding = "padding: 8px 12px;" if is_steady else "padding: 10px 14px;"

    return f"""
      <div class="visual-card" style="background: #ffffff; color: #091428; border: 2px solid #cbd5e1; border-top: 6px solid {badge_col}; {card_padding} border-radius: 8px; display: flex; flex-direction: column; height: 100%; min-height: 0; box-sizing: border-box; {card_overflow}">
        <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 2px solid #e2e8f0; padding-bottom: 4px; margin-bottom: 6px; flex-shrink: 0;">
          <span style="font-family: monospace; font-weight: 800; color: #0284c7; font-size: 0.84rem;">{header}</span>
          <span style="background: #f8fafc; color: {badge_col}; font-weight: 800; font-size: 0.68rem; padding: 2px 7px; border-radius: 4px; border: 1.5px solid {badge_col};">{badge}</span>
        </div>
        <div class="visual-content" style="flex: 1; display: flex; flex-direction: column;">
          {html_content}
        </div>
        <div style="background: #f0fdf4; border-left: 4px solid #16a34a; padding: 6px 10px; border-radius: 4px; font-size: 0.76rem; color: #14532d; margin-top: auto; flex-shrink: 0;">
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
      <div class="slide-badge">SLIDE {num} / {total_slides}</div>
    </div>

    <div class="slide-body">
      {left_panel}
      {right_panel}
    </div>

    <div class="slide-footer">
      <div>LCARS 5010 CLINICAL AMBULATORY TELEMETRY ONBOARDING • LIGHT THEME</div>
      <div>AUSTRALIAN FEMALE VOICE • 100% SCREEN SYNCHRONIZED</div>
    </div>
  </div>
</body>
</html>
"""

# Render individual slides
for s in slides_data:
    left_panel = generate_left_panel(s)
    right_panel = generate_right_panel(s)
    html_out = slide_template.format(
        num=s["num"],
        title=s["title"],
        module=s["module"],
        left_panel=left_panel,
        right_panel=right_panel,
        total_slides=len(slides_data)
    )
    
    # Save into presentation/slides/
    with open(os.path.join(slides_dir, f"slide_{s['num']}.html"), "w", encoding="utf-8") as f:
        f.write(html_out)
    # Save into root slides/
    with open(os.path.join(r"D:\veepoo\slides", f"slide_{s['num']}.html"), "w", encoding="utf-8") as f:
        f.write(html_out)
    # Also save into presentation/ for flat serving
    with open(os.path.join(pres_dir, f"slide_{s['num']}.html"), "w", encoding="utf-8") as f:
        f.write(html_out)

print(f"Generated all {len(slides_data)} synchronized slides in slides/, presentation/, and D:\\veepoo\\slides!")

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
      border-color: #0284c7;
    }}
    .scrubber-container {{
      flex: 1;
      height: 6px;
      background: #e2e8f0;
      border-radius: 3px;
      position: relative;
      cursor: pointer;
    }}
    .scrubber-fill {{
      height: 100%;
      background: #0284c7;
      border-radius: 3px;
      width: 0%;
      transition: width 0.1s linear;
    }}
    .transcript-drawer {{
      position: absolute;
      bottom: 42px;
      left: 0;
      right: 0;
      background: #ffffff;
      border-top: 2px solid #0284c7;
      box-shadow: 0 -4px 12px rgba(0,0,0,0.08);
      max-height: 180px;
      overflow-y: auto;
      padding: 12px 18px;
      display: none;
      z-index: 100;
    }}
    .transcript-drawer.open {{
      display: block;
    }}
  </style>
</head>
<body>

  <!-- Top Player Header -->
  <div class="player-header">
    <div class="player-title">
      <span>LCARS 5010 CLINICAL AMBULATORY TELEMETRY ONBOARDING</span>
      <span class="player-badge">WINDOWS 11 CONSOLE</span>
    </div>
    <div style="display: flex; align-items: center; gap: 12px;">
      <span id="lblScreenshotZone" style="font-family: monospace; font-size: 11px; background: #0284c7; color: #ffffff; padding: 2px 8px; border-radius: 3px; font-weight: bold;">📍 FOCUS: FULL OVERVIEW</span>
      <span style="font-size: 12px; color: #64748b; font-weight: 600;">VOICE: <strong style="color: #0284c7;">AUSTRALIAN FEMALE</strong></span>
      <span id="lblSlideCounter" style="font-family: monospace; font-size: 12px; font-weight: 800; color: #0f172a;">01 / {len(slides_data):02d}</span>
    </div>
  </div>

  <!-- Main Slide Iframe Viewport -->
  <div class="player-main">
    <iframe id="slideIframe" src="slides/slide_01.html"></iframe>
  </div>

  <!-- Transcript Drawer -->
  <div id="transcriptDrawer" class="transcript-drawer">
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
      <strong id="lblTranscriptTitle" style="color: #0284c7; font-size: 13px;">SLIDE 01 TRANSCRIPT</strong>
      <button onclick="toggleTranscript()" style="background: none; border: none; font-size: 14px; cursor: pointer; color: #64748b;">✕</button>
    </div>
    <p id="txtTranscript" style="font-size: 12.5px; line-height: 1.5; color: #334155; font-family: -apple-system, BlinkMacSystemFont, sans-serif;"></p>
  </div>

  <!-- Bottom Player Controls -->
  <div class="player-controls">
    <div style="display: flex; align-items: center; gap: 8px;">
      <button class="ctrl-btn" onclick="prevSlide()" title="Previous Slide [Left Arrow]">⏮ PREV</button>
      <button id="btnPlayPause" class="ctrl-btn active" onclick="togglePlayPause()" title="Play / Pause [Spacebar]">⏸ PAUSE</button>
      <button class="ctrl-btn" onclick="nextSlide()" title="Next Slide [Right Arrow]">NEXT ⏭</button>
    </div>

    <!-- Scrubber Progress Bar -->
    <div class="scrubber-container" onclick="seekSlide(event)">
      <div id="scrubberFill" class="scrubber-fill"></div>
    </div>

    <div style="display: flex; align-items: center; gap: 10px;">
      <button class="ctrl-btn" onclick="toggleTranscript()" title="Toggle Voice Script Drawer">📜 TRANSCRIPT</button>

      <!-- Slide Selector Dropdown -->
      <select id="selSlide" onchange="jumpToSlide(parseInt(this.value))" style="padding: 3px 8px; font-size: 11.5px; border-radius: 4px; border: 1.5px solid #cbd5e1; background: #f8fafc; color: #0f172a; font-weight: 600; cursor: pointer; outline: none;">
      </select>

      <!-- Speed Control -->
      <select id="selSpeed" onchange="setPlaybackSpeed(parseFloat(this.value))" style="padding: 3px 6px; font-size: 11.5px; border-radius: 4px; border: 1.5px solid #cbd5e1; background: #f8fafc; color: #0f172a; font-weight: 600; cursor: pointer; outline: none;">
        <option value="1.0" selected>1.0x</option>
        <option value="1.25">1.25x</option>
        <option value="1.5">1.5x</option>
      </select>
    </div>
  </div>

  <!-- Audio Player for Real Voice Narration -->
  <audio id="audioPlayer" preload="auto"></audio>

  <script>
    const slides = {slides_json_str};

    let currentIndex = 0;
    let isPlaying = true;
    let playbackSpeed = 1.0;
    let slideTimer = null;
    let synth = window.speechSynthesis;

    // Viewport Scroll Interpolation State
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
          const s = slides[currentIndex];
          const isSteady = s && (s.no_scroll === true || ['03', '04', '05', '3', '4', '5'].includes(s.num));

          if (isSteady) {{
            currentScrollLeft = 0;
            targetScrollLeft = 0;
            currentScrollRight = 0;
            targetScrollRight = 0;
            const leftViewport = iframe.contentDocument.querySelector('.left-screenshot-viewport');
            if (leftViewport && leftViewport.scrollTop !== 0) leftViewport.scrollTop = 0;
            const rightCard = iframe.contentDocument.querySelector('.visual-card');
            if (rightCard && rightCard.scrollTop !== 0) rightCard.scrollTop = 0;
            const contentCard = iframe.contentDocument.querySelector('.content-card');
            if (contentCard && contentCard.scrollTop !== 0) contentCard.scrollTop = 0;
          }} else {{
            const leftViewport = iframe.contentDocument.querySelector('.left-screenshot-viewport');
            if (leftViewport && leftViewport.scrollHeight > leftViewport.clientHeight) {{
              currentScrollLeft += (targetScrollLeft - currentScrollLeft) * 0.08;
              leftViewport.scrollTop = currentScrollLeft;
            }}
            const rightCard = iframe.contentDocument.querySelector('.visual-card');
            if (rightCard && rightCard.scrollHeight > rightCard.clientHeight) {{
              currentScrollRight += (targetScrollRight - currentScrollRight) * 0.08;
              rightCard.scrollTop = currentScrollRight;
            }}
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
        const zoneBadge = document.getElementById('lblScreenshotZone');
        const bullets = doc.querySelectorAll('.bullet-item');
        const s = slides[currentIndex];
        const isSteady = s && (s.no_scroll === true || ['03', '04', '05', '3', '4', '5'].includes(s.num));

        const numBullets = (s.bullets && s.bullets.length > 0) ? s.bullets.length : 3;
        let activeIdx = Math.floor(norm * numBullets);
        if (activeIdx >= numBullets) activeIdx = numBullets - 1;
        if (activeIdx < 0) activeIdx = 0;

        // Highlight active bullet item without layout/reflow shifting
        bullets.forEach((b, idx) => {{
          if (idx === activeIdx) {{
            b.style.background = '#e0f2fe';
            b.style.borderLeft = '4px solid #0284c7';
            b.style.color = '#0369a1';
          }} else {{
            b.style.background = 'transparent';
            b.style.borderLeft = '4px solid transparent';
            b.style.color = '#1e293b';
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

        // If steady slide, lock scroll completely to zero
        if (isSteady) {{
          targetScrollLeft = 0;
          targetScrollRight = 0;
          return;
        }}

        // Monotonic Top-to-Bottom Smooth Target: glides steadily without jumping up and down
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
          // Playing
        }}).catch((err) => {{
          console.warn("Audio file play error; falling back to SpeechSynthesis", err);
          if (synth) {{
            const utter = new SpeechSynthesisUtterance(text);
            utter.rate = playbackSpeed;
            utter.onend = () => {{
              if (isPlaying) {{
                slideTimer = setTimeout(() => {{
                  nextSlide();
                }}, 1500);
              }}
            }};
            synth.speak(utter);
          }}
        }});
      }}

      audioEl.onended = () => {{
        if (isPlaying) {{
          slideTimer = setTimeout(() => {{
            nextSlide();
          }}, 1200);
        }}
      }};
    }}

    function loadSlide(index) {{
      if (index < 0) index = 0;
      if (index >= slides.length) index = slides.length - 1;
      currentIndex = index;

      const s = slides[currentIndex];
      const isSteady = s && (s.no_scroll === true || ['03', '04', '05', '3', '4', '5'].includes(s.num));

      document.getElementById('slideIframe').src = `slides/slide_${{s.num}}.html`;
      document.getElementById('lblSlideCounter').innerText = `${{s.num}} / ${{slides.length}}`;
      document.getElementById('selSlide').value = currentIndex;
      document.getElementById('scrubberFill').style.width = `${{((currentIndex + 1) / slides.length) * 100}}%`;
      document.getElementById('lblTranscriptTitle').innerText = `SLIDE ${{s.num}}: ${{s.title}}`;
      document.getElementById('txtTranscript').innerText = s.narration;

      // Position immediately to initial target
      const initialPct = isSteady ? 0.0 : ((s.step_focus && s.step_focus.length > 0) ? s.step_focus[0].scroll_pct : 0.0);
      currentScrollLeft = isSteady ? 0 : initialPct * 1000;
      targetScrollLeft = isSteady ? 0 : initialPct * 1000;
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
              const initPos = (!isSteady && maxLeftScroll > 0) ? initialPct * maxLeftScroll : 0;
              leftViewport.scrollTop = initPos;
              currentScrollLeft = initPos;
              targetScrollLeft = initPos;
            }}
            const rightCard = doc.querySelector('.visual-card');
            if (rightCard && isSteady) rightCard.scrollTop = 0;
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
            const initPos = (!isSteady && maxLeftScroll > 0) ? initialPct * maxLeftScroll : 0;
            leftViewport.scrollTop = initPos;
            currentScrollLeft = initPos;
            targetScrollLeft = initPos;
          }}
          const rightCard = iframe.contentDocument.querySelector('.visual-card');
          if (rightCard && isSteady) rightCard.scrollTop = 0;
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

    function togglePlayPause() {{
      isPlaying = !isPlaying;
      const btn = document.getElementById('btnPlayPause');
      const audioEl = document.getElementById('audioPlayer');

      if (isPlaying) {{
        btn.innerHTML = '⏸ PAUSE';
        btn.classList.add('active');
        if (audioEl.src && audioEl.paused) {{
          audioEl.play();
        }} else {{
          speakNarration(slides[currentIndex].narration);
        }}
      }} else {{
        btn.innerHTML = '▶ RUN';
        btn.classList.remove('active');
        if (audioEl) audioEl.pause();
        if (synth) synth.cancel();
        clearTimeout(slideTimer);
      }}
    }}

    function nextSlide() {{
      if (currentIndex < slides.length - 1) {{
        loadSlide(currentIndex + 1);
      }} else {{
        togglePlayPause();
      }}
    }}

    function prevSlide() {{
      if (currentIndex > 0) {{
        loadSlide(currentIndex - 1);
      }}
    }}

    function jumpToSlide(idx) {{
      loadSlide(idx);
    }}

    function seekSlide(e) {{
      const container = e.currentTarget;
      const rect = container.getBoundingClientRect();
      const clickX = e.clientX - rect.left;
      const pct = clickX / rect.width;
      const targetIdx = Math.floor(pct * slides.length);
      loadSlide(targetIdx);
    }}

    function setPlaybackSpeed(speed) {{
      playbackSpeed = speed;
      const audioEl = document.getElementById('audioPlayer');
      if (audioEl) audioEl.playbackRate = speed;
    }}

    function toggleTranscript() {{
      const drawer = document.getElementById('transcriptDrawer');
      drawer.classList.toggle('open');
    }}

    function handleGlobalKeyDown(e) {{
      if (e.code === 'Space') {{
        e.preventDefault();
        togglePlayPause();
      }} else if (e.code === 'ArrowRight') {{
        e.preventDefault();
        nextSlide();
      }} else if (e.code === 'ArrowLeft') {{
        e.preventDefault();
        prevSlide();
      }}
    }}

    // Populate Slide Selector
    const selSlide = document.getElementById('selSlide');
    slides.forEach((s, idx) => {{
      const opt = document.createElement('option');
      opt.value = idx;
      opt.text = `${{s.num}}: ${{s.title}}`;
      selSlide.appendChild(opt);
    }});

    // Global Keybindings
    document.addEventListener('keydown', handleGlobalKeyDown);

    // Start 60fps render loop
    animLoopId = requestAnimationFrame(renderSmoothLoop);

    // Initial Load
    loadSlide(0);
  </script>
</body>
</html>
"""

# Save preview.html into presentation/ and root
with open(os.path.join(pres_dir, "preview.html"), "w", encoding="utf-8") as f:
    f.write(preview_html_content)
with open(os.path.join(r"D:\veepoo", "preview.html"), "w", encoding="utf-8") as f:
    f.write(preview_html_content)

print("Generated preview.html in presentation/ and D:\\veepoo\\preview.html with Australian Female Voice!")

# -----------------------------------------------------------------------------
# RECOMPILE SimpleLauncher.cs TO Preview_Presentation.exe
# -----------------------------------------------------------------------------
csc_path = r"C:\Windows\Microsoft.NET\Framework64\v4.0.30319\csc.exe"
launcher_src = os.path.join(pres_dir, "SimpleLauncher.cs")
launcher_out = os.path.join(pres_dir, "Preview_Presentation.exe")

if os.path.exists(csc_path) and os.path.exists(launcher_src):
    compile_cmd = [
        csc_path,
        "/nologo",
        "/t:winexe",
        "/optimize+",
        f"/out:{launcher_out}",
        launcher_src
    ]
    print(f"Compiling Windows 11 executable launcher: {' '.join(compile_cmd)}")
    result = subprocess.run(compile_cmd, capture_output=True, text=True)
    print(f"Compilation Output Code: {result.returncode}")
    if result.returncode == 0:
        root_exe = os.path.join(r"D:\veepoo", "Preview_Presentation.exe")
        shutil.copy2(launcher_out, root_exe)
        print("Copied Preview_Presentation.exe to D:\\veepoo\\Preview_Presentation.exe successfully!")
    else:
        print("Compilation stderr:", result.stderr)

print(f"All {len(slides_data)} slides, female narration transcript, preview player, and W11 launcher built successfully!")

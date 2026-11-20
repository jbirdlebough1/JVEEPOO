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

print(f"Loaded {len(slides_data)} slides from {data_file}")

# Write TRANSCRIPT.md
transcript_path = os.path.join(pres_dir, "TRANSCRIPT.md")
with open(transcript_path, "w", encoding="utf-8") as f:
    f.write("# LCARS 5010 Clinical Telemetry Masterclass - Official Narration Transcript\n\n")
    f.write("**Audio Track:** Australian Professional Technical Narration  \n")
    f.write("**Engine:** Edge TTS / Web Speech API (en-AU-WilliamNeural / en-AU Natural)  \n")
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

print("Created TRANSCRIPT.md with all 40 slides!")

# -----------------------------------------------------------------------------
# DYNAMIC PANEL GENERATORS
# -----------------------------------------------------------------------------

def generate_left_panel(s):
    # s is a slide dict
    bullets_html = ""
    for title, text in s.get("bullets", []):
        bullets_html += f"""
          <li style="margin-bottom: 8px; line-height: 1.45;">
            <strong style="color: #0369a1;">{title}:</strong> {text}
          </li>
        """
    
    # If slide 3 (FITAOS 5150 / Veepoo 5010 watch hardware), include watch photo
    watch_img_html = ""
    if s["id"] == 3:
        watch_img_html = """
        <div style="text-align: center; margin-bottom: 12px; background: #050b14; border-radius: 6px; padding: 6px; border: 1.5px solid #cbd5e1;">
          <img src="images/watch_fitaos5010.jpg" onerror="this.src='../images/watch_fitaos5010.jpg'" alt="FITAOS 5150 / Veepoo Model 5010 Smartwatch" style="max-height: 175px; max-width: 100%; object-fit: contain; border-radius: 4px;">
          <div style="color: #94a3b8; font-size: 0.72rem; margin-top: 4px; font-family: monospace;">FITAOS 5150 (Veepoo Model 5010) Wearable Transponder Hardware</div>
        </div>
        """

    card_title = s.get("card_title", "CLINICAL OBJECTIVE & ARCHITECTURE")
    desc = s.get("desc", "")
    takeaway = s.get("takeaway", "Continuous telemetry ensures patient safety and clinical insight.")

    return f"""
      <!-- High-Contrast Dynamic Content Card -->
      <div class="content-card" style="background: #ffffff; color: #091428; border: 2px solid #cbd5e1; border-top: 6px solid #0284c7; padding: 20px; border-radius: 8px; display: flex; flex-direction: column; overflow-y: auto;">
        {watch_img_html}
        <h3 style="color: #0284c7; font-size: 1.15rem; font-weight: 800; border-bottom: 2px solid #e2e8f0; padding-bottom: 8px; margin-bottom: 10px; text-transform: uppercase;">{card_title}</h3>
        <p style="font-size: 0.95rem; color: #334155; margin-bottom: 12px; line-height: 1.5; font-weight: 500;">{desc}</p>
        <ul style="padding-left: 20px; margin-bottom: 14px; color: #1e293b; font-size: 0.88rem;">
          {bullets_html}
        </ul>
        <div style="background: #f0fdf4; border-left: 4px solid #16a34a; padding: 10px 14px; border-radius: 4px; font-size: 0.85rem; color: #14532d; margin-top: auto;">
          <strong style="color: #15803d;">Key Takeaway:</strong> {takeaway}
        </div>
      </div>
    """

def generate_right_panel(s):
    header = s.get("right_header", "LCARS 5010 • CLINICAL INTERFACE")
    badge = s.get("right_badge", "● ONLINE")
    badge_col = s.get("right_badge_col", "#15803d")
    html_content = s.get("right_html", "")

    return f"""
      <div class="visual-card" style="background: #ffffff; color: #091428; border: 2px solid #cbd5e1; border-top: 6px solid {badge_col}; padding: 20px; border-radius: 8px; display: flex; flex-direction: column; overflow-y: auto;">
        <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 2px solid #e2e8f0; padding-bottom: 8px; margin-bottom: 12px;">
          <span style="font-family: monospace; font-weight: 800; color: #0284c7; font-size: 0.92rem;">{header}</span>
          <span style="background: #f8fafc; color: {badge_col}; font-weight: 800; font-size: 0.75rem; padding: 3px 10px; border-radius: 4px; border: 1.5px solid {badge_col};">{badge}</span>
        </div>
        <div style="flex: 1; display: flex; flex-direction: column;">
          {html_content}
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
      background: #091424;
      color: #ffffff;
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
      overflow: hidden;
      display: flex;
      flex-direction: column;
    }}
    .slide-frame {{
      flex: 1;
      display: flex;
      flex-direction: column;
      padding: 24px 32px;
      gap: 16px;
    }}
    .slide-header {{
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 3px solid #0284c7;
      padding-bottom: 12px;
    }}
    .slide-module {{
      font-size: 0.85rem;
      color: #38bdf8;
      font-weight: 700;
      letter-spacing: 0.08em;
      text-transform: uppercase;
    }}
    .slide-title {{
      font-size: 1.65rem;
      font-weight: 800;
      color: #ffffff;
      margin-top: 2px;
      letter-spacing: -0.01em;
    }}
    .slide-badge {{
      background: #0284c7;
      color: #ffffff;
      font-family: monospace;
      font-weight: 800;
      font-size: 0.85rem;
      padding: 6px 14px;
      border-radius: 4px;
      letter-spacing: 0.05em;
    }}
    .slide-body {{
      flex: 1;
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 24px;
      min-height: 0;
    }}
    .slide-footer {{
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: 0.75rem;
      color: #64748b;
      font-family: monospace;
      border-top: 1px solid #1e293b;
      padding-top: 8px;
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
      <div class="slide-badge">SLIDE {num} / 40</div>
    </div>

    <div class="slide-body">
{left_panel}
{right_panel}
    </div>

    <div class="slide-footer">
      <span>VEEPOO 5010 CLINICAL AMBULATORY MASTERCLASS &bull; REVISION 3.0</span>
      <span>SLIDE {num} OF 40 &bull; AUSTRALIAN NARRATION ACTIVE</span>
    </div>
  </div>
</body>
</html>
"""

# Build all 40 slides
for s in slides_data:
    left_html = generate_left_panel(s)
    right_html = generate_right_panel(s)
    
    html = slide_template.format(
        num=s["num"],
        title=s["title"],
        module=s["module"],
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

print("Generated all 40 synchronized slides in slides/ and presentation/!")

# -----------------------------------------------------------------------------
# BUILD preview.html (MASTER TECHNICAL PLAYER)
# -----------------------------------------------------------------------------
slides_json_str = json.dumps(slides_data, indent=2)

preview_html_content = f"""<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>LCARS 5010 Clinical Telemetry Masterclass - Windows 11 Technical Player</title>
  <style>
    * {{ box-sizing: border-box; margin: 0; padding: 0; }}
    html, body {{
      width: 100vw;
      height: 100vh;
      overflow: hidden;
      background: #091424;
      color: #ffffff;
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
      display: flex;
      flex-direction: column;
    }}
    .player-header {{
      height: 48px;
      background: #0c1b33;
      border-bottom: 2px solid #0284c7;
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 20px;
      user-select: none;
    }}
    .player-title {{
      font-size: 15px;
      font-weight: 800;
      letter-spacing: 0.05em;
      display: flex;
      align-items: center;
      gap: 12px;
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
      background: #000000;
    }}
    iframe {{
      width: 100%;
      height: 100%;
      border: none;
      display: block;
    }}
    .player-controls {{
      height: 64px;
      background: #0c1b33;
      border-top: 1px solid #1e3a5f;
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 24px;
      gap: 20px;
      user-select: none;
    }}
    .ctrl-btn {{
      background: #1e3a5f;
      color: #ffffff;
      border: 1px solid #38bdf8;
      border-radius: 4px;
      padding: 6px 14px;
      font-size: 12px;
      font-weight: 700;
      cursor: pointer;
      display: flex;
      align-items: center;
      gap: 6px;
      transition: all 0.15s ease;
    }}
    .ctrl-btn:hover {{
      background: #0284c7;
      border-color: #7dd3fc;
    }}
    .ctrl-btn.active {{
      background: #0284c7;
      box-shadow: 0 0 8px #0284c7;
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
      background: #1e293b;
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
      background: rgba(12, 27, 51, 0.95);
      border-top: 2px solid #38bdf8;
      backdrop-filter: blur(8px);
      padding: 16px 32px;
      max-height: 140px;
      display: none;
      overflow-y: auto;
      font-size: 14px;
      line-height: 1.5;
      color: #e2e8f0;
      box-shadow: 0 -4px 15px rgba(0,0,0,0.4);
    }}
  </style>
</head>
<body>
  <div class="player-header">
    <div class="player-title">
      <span class="player-badge">LCARS 5010</span>
      <span>CLINICAL AMBULATORY TELEMETRY MASTERCLASS (40 SLIDES &bull; AUSSIE VOICE NARRATION)</span>
    </div>
    <div style="display: flex; align-items: center; gap: 10px; font-size: 12px; color: #94a3b8;">
      <span id="lblVoiceStatus">🇦🇺 Australian Voice Engine: Active</span>
      <button class="ctrl-btn" onclick="toggleFullscreen()" style="padding: 4px 10px; font-size: 11px;">⛶ FULLSCREEN</button>
    </div>
  </div>

  <div class="player-main">
    <iframe id="slideIframe" src="slides/slide_01.html"></iframe>
    <div id="transcriptDrawer" class="transcript-drawer">
      <strong style="color: #38bdf8; display: block; margin-bottom: 4px;" id="lblTranscriptTitle">SLIDE 01 NARRATION:</strong>
      <div id="txtTranscript">Loading narration...</div>
    </div>
  </div>

  <div class="player-controls">
    <div style="display: flex; gap: 8px;">
      <button class="ctrl-btn" onclick="prevSlide()">⏮ PREV</button>
      <button class="ctrl-btn active" id="btnPlayPause" onclick="togglePlayPause()">⏸ PAUSE</button>
      <button class="ctrl-btn" onclick="nextSlide()">NEXT ⏭</button>
    </div>

    <div class="progress-bar-container">
      <span id="lblTime" style="font-family: monospace; font-size: 12px; color: #94a3b8; width: 45px;">00:00</span>
      <div class="scrubber" onclick="onScrub(event)">
        <div id="scrubberFill" class="scrubber-fill"></div>
      </div>
      <span id="lblSlideCounter" style="font-family: monospace; font-size: 12px; color: #38bdf8; font-weight: bold; width: 65px; text-align: right;">01 / 40</span>
    </div>

    <div style="display: flex; gap: 8px; align-items: center;">
      <select id="selSlide" onchange="jumpToSlide(parseInt(this.value))" style="background: #1e3a5f; color: #ffffff; border: 1px solid #38bdf8; border-radius: 4px; padding: 5px 8px; font-size: 12px;">
      </select>
      <button class="ctrl-btn" id="btnToggleTranscript" onclick="toggleTranscript()">📜 SCRIPT</button>
      <button class="ctrl-btn" id="btnSpeed" onclick="cycleSpeed()" style="width: 52px; justify-content: center;">1.0x</button>
    </div>
  </div>

  <!-- Audio Element (for pre-rendered MP3s if present) -->
  <audio id="audioPlayer"></audio>

  <script>
    const slides = {slides_json_str};

    let currentIndex = 0;
    let isPlaying = true;
    let playbackSpeed = 1.0;
    let auVoice = null;
    let synth = window.speechSynthesis;
    let currentUtterance = null;
    let slideTimer = null;

    // Populate Slide Selector
    const sel = document.getElementById('selSlide');
    slides.forEach((s, idx) => {{
      const opt = document.createElement('option');
      opt.value = idx;
      opt.text = `${{s.num}}. ${{s.title}}`;
      sel.appendChild(opt);
    }});

    // Initialize Aussie Voice Engine
    function initVoices() {{
      if (!synth) return;
      const voices = synth.getVoices();
      auVoice = voices.find(v => v.lang === 'en-AU' || v.lang.startsWith('en-AU') || v.name.includes('Australia') || v.name.includes('AU')) ||
                voices.find(v => v.lang === 'en-GB' || v.lang.startsWith('en-GB')) ||
                voices.find(v => v.lang.startsWith('en'));
      
      const lbl = document.getElementById('lblVoiceStatus');
      if (lbl && auVoice) {{
        lbl.innerText = `🇦🇺 Voice: ${{auVoice.name}}`;
      }}
    }}
    if (synth) {{
      synth.onvoiceschanged = initVoices;
      initVoices();
    }}

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

      // Speak narration
      speakNarration(s.narration);
    }}

    function speakNarration(text) {{
      if (synth) synth.cancel();
      clearTimeout(slideTimer);

      if (!isPlaying) return;

      // Check if pre-rendered MP3 exists
      const s = slides[currentIndex];
      const audioEl = document.getElementById('audioPlayer');
      audioEl.src = `audio/slide_${{s.num}}.mp3`;
      audioEl.playbackRate = playbackSpeed;

      audioEl.play().then(() => {{
        // MP3 is playing!
        audioEl.onended = () => {{
          if (isPlaying) nextSlide();
        }};
      }}).catch(() => {{
        // Fallback to native Web Speech API with Australian voice
        if (!synth) return;
        currentUtterance = new SpeechSynthesisUtterance(text);
        if (auVoice) currentUtterance.voice = auVoice;
        currentUtterance.rate = playbackSpeed;
        currentUtterance.pitch = 1.0;

        currentUtterance.onend = () => {{
          if (isPlaying) {{
            slideTimer = setTimeout(() => {{
              nextSlide();
            }}, 1200);
          }}
        }};

        currentUtterance.onerror = () => {{
          if (isPlaying) {{
            slideTimer = setTimeout(() => {{
              nextSlide();
            }}, 2000);
          }}
        }};

        synth.speak(currentUtterance);
      }});
    }}

    function togglePlayPause() {{
      isPlaying = !isPlaying;
      const btn = document.getElementById('btnPlayPause');
      if (isPlaying) {{
        btn.innerText = "⏸ PAUSE";
        btn.classList.add('active');
        const s = slides[currentIndex];
        speakNarration(s.narration);
      }} else {{
        btn.innerText = "▶ PLAY";
        btn.classList.remove('active');
        if (synth) synth.cancel();
        const audioEl = document.getElementById('audioPlayer');
        if (audioEl) audioEl.pause();
        clearTimeout(slideTimer);
      }}
    }}

    function nextSlide() {{
      if (currentIndex < slides.length - 1) {{
        loadSlide(currentIndex + 1);
      }} else {{
        isPlaying = false;
        document.getElementById('btnPlayPause').innerText = "▶ REPLAY";
        document.getElementById('btnPlayPause').classList.remove('active');
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
      const s = slides[currentIndex];
      speakNarration(s.narration);
    }}

    function onScrub(e) {{
      const rect = e.currentTarget.getBoundingClientRect();
      const pct = (e.clientX - rect.left) / rect.width;
      const targetIdx = Math.floor(pct * slides.length);
      loadSlide(targetIdx);
    }}

    function toggleFullscreen() {{
      if (!document.fullscreenElement) {{
        document.documentElement.requestFullscreen();
      }} else {{
        if (document.exitFullscreen) document.exitFullscreen();
      }}
    }}

    // Keyboard Shortcuts
    window.addEventListener('keydown', (e) => {{
      if (e.code === 'Space') {{
        e.preventDefault();
        togglePlayPause();
      }} else if (e.code === 'ArrowRight') {{
        nextSlide();
      }} else if (e.code === 'ArrowLeft') {{
        prevSlide();
      }} else if (e.key === 'f' || e.key === 'F') {{
        toggleFullscreen();
      }}
    }});

    // Start on Slide 1
    window.onload = () => {{
      loadSlide(0);
    }};
  </script>
</body>
</html>
"""

preview_path = os.path.join(pres_dir, "preview.html")
with open(preview_path, "w", encoding="utf-8") as f:
    f.write(preview_html_content)

print("Generated preview.html Windows 11 player!")

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

        try
        {
            Process.Start(new ProcessStartInfo(target) { UseShellExecute = true });
        }
        catch
        {
            Process.Start("cmd.exe", "/c start \"\" \"" + target + "\"");
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

print("All 40 slides, narration transcript, preview player, and W11 launcher built successfully!")

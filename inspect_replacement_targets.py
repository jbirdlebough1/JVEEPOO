with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

def show_loc(label, target, pre=100, post=400):
    idx = text.find(target)
    print(f"\n=== {label} (at {idx}) ===")
    if idx != -1:
        print(text[max(0, idx-pre):min(len(text), idx+post)])
    else:
        print("NOT FOUND")

show_loc("CSS .log-tx", "body.theme-light .log-tx", 50, 250)
show_loc("logLCARS descColor", 'if (type === "rx") descColor = "#15803d"', 50, 300)
show_loc("initializeVitalsAndCardioBaselines", "function initializeVitalsAndCardioBaselines()", 20, 800)
show_loc("render360HealthView", "function render360HealthView()", 20, 900)
show_loc("assembleAndParseSleepStream fallback", "Fallback to clinical sleep architecture baseline", 100, 700)
show_loc("handleIncomingTelemetryPacket 0xA8", "if (header === 0xA8", 50, 700)
show_loc("DOMContentLoaded", "window.addEventListener('DOMContentLoaded'", 50, 700)

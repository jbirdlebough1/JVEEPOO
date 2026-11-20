import json
import asyncio
import os
import edge_tts

async def render_slide(sem, s, audio_dirs):
    num = s['num']
    narration = s['narration']
    voice = "en-AU-NatashaNeural"
    
    # Check if files already match or need generation
    # We will generate for all to guarantee 100% accuracy and alignment
    async with sem:
        try:
            tts = edge_tts.Communicate(narration, voice)
            temp_file = f"D:/veepoo/audio/temp_slide_{num}.mp3"
            await tts.save(temp_file)
            
            # Copy to target directories
            for adir in audio_dirs:
                target_file = os.path.join(adir, f"slide_{num}.mp3")
                with open(temp_file, "rb") as src, open(target_file, "wb") as dst:
                    dst.write(src.read())
                    
            if os.path.exists(temp_file):
                os.remove(temp_file)
                
            print(f"Slide {num}: audio rendered successfully ({len(narration)} chars)")
        except Exception as e:
            print(f"Slide {num} ERROR: {e}")

async def main():
    with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
        slides = json.load(f)

    # Update Slide 1 narration to strictly focus on screen displayed topic
    s1 = slides[0]
    s1['narration'] = (
        "G'day mate! Welcome to the LCARS 5010 Clinical Ambulatory Telemetry Onboarding presentation. "
        "As a Type 2 diabetic, I created this open-source multidisciplinary toolkit to provide an objective, real-time window into human physiology. "
        "Displayed on your screen is our live 01-VITALS console running in pure light theme. "
        "In the telemetry matrix, you can see our four primary hemodynamic channels: "
        "BIO-01 optical glucose showing 156 milligrams per deciliter, "
        "BIO-02 dual thermal sensors tracking core and epidermal temperature at 96.1 degrees, "
        "BIO-03 arterial pressure acquiring 126 over 84 millimeters of mercury, "
        "and BIO-04 pulse oximetry at 98 percent oxygen saturation. "
        "Across the top ribbon, our secure transponder link and database vault remain strictly synchronized. "
        "On the right, this telemetry unites four vital care specialists: "
        "the Diabetic Pharmacist, Primary Care Physician, Endocrinologist, and Diet Specialist. "
        "Offered under GPL v3 copyleft, our heartfelt mission is 'That Others May Survive Successfully Diabetes,' "
        "and we warmly welcome clinicians, engineers, and community sponsors—including Google—to collaborate with us."
    )

    # Save updated all_slides_data.json
    with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
        json.dump(slides, f, indent=2, ensure_ascii=False)
        
    print("Updated all_slides_data.json with refined Slide 1 screen-focused narration.")

    audio_dirs = [r"D:\veepoo\audio", r"D:\veepoo\presentation\audio"]
    for adir in audio_dirs:
        os.makedirs(adir, exist_ok=True)

    # Render with concurrency of 5
    sem = asyncio.Semaphore(5)
    tasks = [render_slide(sem, s, audio_dirs) for s in slides]
    await asyncio.gather(*tasks)
    print("All audio renders complete!")

if __name__ == '__main__':
    asyncio.run(main())

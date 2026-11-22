with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('audioEnabled = (document.getElementById(\'cfgAudioEnabled\')')
print(html[pos:pos+1000])

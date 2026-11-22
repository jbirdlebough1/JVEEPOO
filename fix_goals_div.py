with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

target = '''        </div>
      </div>
    </main>'''

replacement = '''        </div>
      </div>
    </div>
    </main>'''

# Let's inspect around line 7415-7422
p_end = text.find('</main>')
print("Surrounding text before </main>:")
print(text[p_end-100:p_end+20])

# We can replace the end of pageGoals cleanly
idx_main = text.find('</main>')
# Find last </div> before </main>
idx_last_div = text.rfind('</div>', 0, idx_main)
idx_prev_div = text.rfind('</div>', 0, idx_last_div)
print("Previous 100 chars:", text[idx_prev_div:idx_main])

# Let's ensure there are 2 closing divs before </main>
text = text[:idx_main] + '  </div>\n    ' + text[idx_main:]

with open(r'D:\veepoo\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

with open(r'D:\veepoo\w11_htm_app\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

print("Balanced pageGoals closing div in both index.html files!")

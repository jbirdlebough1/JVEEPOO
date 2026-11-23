import os

def update_server():
    server_path = r"D:\veepoo\w11_htm_app\server.py"
    with open(server_path, "r", encoding="utf-8") as f:
        code = f.read()

    # 1. Add Wi-Fi IP and QR helpers
    wifi_helper_code = '''
def get_local_wifi_ip():
    import socket
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    try:
        s.connect(('10.255.255.255', 1))
        ip = s.getsockname()[0]
    except Exception:
        try:
            ip = socket.gethostbyname(socket.gethostname())
        except Exception:
            ip = "127.0.0.1"
    finally:
        s.close()
    return ip

def ensure_qr_svg():
    try:
        import qrcode
        import qrcode.image.svg
        ip = get_local_wifi_ip()
        url = f"http://{ip}:{PORT}/mobile_upload.html"
        factory = qrcode.image.svg.SvgPathImage
        img = qrcode.make(url, image_factory=factory)
        qr_path = os.path.join(BASE_DIR, "mobile_wifi_qr.svg")
        with open(qr_path, "wb") as f:
            img.save(f)
        print(f"[*] Generated mobile Wi-Fi QR SVG for {url}")
    except Exception as e:
        print(f"[!] Warning: could not generate QR SVG: {e}")
'''
    if 'def get_local_wifi_ip():' not in code:
        pos = code.find('PORT = 8899')
        if pos != -1:
            end_line = code.find('\n', pos)
            code = code[:end_line+1] + wifi_helper_code + code[end_line+1:]
            print("[✔] Injected get_local_wifi_ip and ensure_qr_svg into server.py")

    # 2. Add route /api/network/wifi_info in do_GET
    route_code = '''        elif self.path == "/api/network/wifi_info":
            wifi_ip = get_local_wifi_ip()
            mobile_url = f"http://{wifi_ip}:{PORT}/mobile_upload.html"
            self.send_json_response({
                "wifi_ip": wifi_ip,
                "port": PORT,
                "mobile_url": mobile_url,
                "qr_svg_url": f"http://{wifi_ip}:{PORT}/mobile_wifi_qr.svg",
                "status": "ONLINE"
            })
'''
    if '/api/network/wifi_info' not in code:
        pos_get = code.find('elif self.path == "/api/meal/recent":')
        if pos_get != -1:
            code = code[:pos_get] + route_code + '\n        ' + code[pos_get:]
            print("[✔] Injected /api/network/wifi_info route into do_GET")

    # 3. Call ensure_qr_svg() in run()
    if 'ensure_qr_svg()' not in code:
        pos_run = code.find('print(f"[*] HTTP Telemetry Server running on http://localhost:{PORT}")')
        if pos_run != -1:
            code = code[:pos_run] + 'ensure_qr_svg()\n    ' + code[pos_run:]
            print("[✔] Added ensure_qr_svg() call to run()")

    with open(server_path, "w", encoding="utf-8") as f:
        f.write(code)
    print(f"Saved {server_path}")

if __name__ == "__main__":
    update_server()

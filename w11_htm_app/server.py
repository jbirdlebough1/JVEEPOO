"""
Dedicated Veepoo Web Bluetooth Server & Live Packet Logger (Port 8899)
Serves static HTML files and logs all browser BLE console telemetry to packet_log.txt
"""

import http.server
import os
import socketserver

PORT = 8899
LOG_FILE = os.path.join(os.path.dirname(__file__), "packet_log.txt")


class VeepooHandler(http.server.SimpleHTTPRequestHandler):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, directory=os.path.dirname(__file__), **kwargs)

    def do_POST(self):
        if self.path == "/api/log":
            content_length = int(self.headers.get("Content-Length", 0))
            body = self.rfile.read(content_length).decode("utf-8", errors="ignore")
            
            with open(LOG_FILE, "a", encoding="utf-8") as f:
                f.write(body + "\n")
            
            self.send_response(200)
            self.send_header("Content-Type", "text/plain")
            self.send_header("Access-Control-Allow-Origin", "*")
            self.end_headers()
            self.wfile.write(b"OK")
        else:
            self.send_error(404, "Not Found")

    def do_OPTIONS(self):
        self.send_response(200)
        self.send_header("Access-Control-Allow-Origin", "*")
        self.send_header("Access-Control-Allow-Methods", "POST, GET, OPTIONS")
        self.send_header("Access-Control-Allow-Headers", "Content-Type")
        self.end_headers()


def run():
    # Initialize / clear previous session log
    with open(LOG_FILE, "a", encoding="utf-8") as f:
        f.write("\n=== Server Session Started ===\n")

    socketserver.TCPServer.allow_reuse_address = True
    with socketserver.TCPServer(("", PORT), VeepooHandler) as httpd:
        print(f"[*] Veepoo server running on http://localhost:{PORT}")
        print(f"[*] Streaming browser BLE logs to: {LOG_FILE}")
        httpd.serve_forever()


if __name__ == "__main__":
    run()

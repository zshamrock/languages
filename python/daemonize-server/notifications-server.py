import time

from daemon import runner


class App():
    def __init__(self):
        self.stdin_path = '/dev/null'
        self.stdout_path = '/dev/tty'
        self.stderr_path = '/dev/tty'
        self.pidfile_path = '/var/run/notifications-server.pid'
        self.pidfile_timeout = 5

    def run(self):
        while True:
            time.sleep(5)

app = App()
daemon_runner = runner.DaemonRunner(app)
daemon_runner.do_action()


import time

from daemonize import Daemonize

def run():
    while True:
        time.sleep(5)

daemon = Daemonize(app="notifications-server", pid="./notifications-server.pid", action=run)
daemon.start()


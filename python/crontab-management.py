#!/usr/bin/python

from crontab import CronTab

system_cron = CronTab()
job = system_cron.new(command='/usr/bin/echo')
job.hour.every(10)
system_cron.write()

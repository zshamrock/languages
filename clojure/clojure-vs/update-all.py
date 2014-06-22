#!/usr/bin/python

import os, subprocess

projects = os.walk('.').next()[1]
for project in projects:
    print 'update {0} project...'.format(project)
    os.chdir(project)
    subprocess.call(['git', 'pull'])
    print
    os.chdir('..')
    

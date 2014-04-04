## How to build uWSGI with ring support
1. update plugins/jvm/uwsgiplugin.py (vim plugins/jvm/uwsgiplugin.py +60) with the following code:

    JVM_INCPATH.append('-I"/usr/lib/jvm/jdk1.7.0_45/include/linux')

2. run `UWSGICONFIG_JVM_INCPATH=/usr/lib/jvm/jdk1.7.0_45/include UWSGICONFIG_JVM_LIBPATH=/usr/lib/jvm/jdk1.7.0_45/jre/lib/amd64/server python uwsgiconfig.py --build ring`
UWSGICONFIG_JVM_INCPATH contains jni.h and UWSGICONFIG_JVM_LIBPATH contains libjvm.so

This command builds uwsgi executable

3. run `UWSGICONFIG_JVM_INCPATH=/usr/lib/jvm/jdk1.7.0_45/include UWSGICONFIG_JVM_LIBPATH=/usr/lib/jvm/jdk1.7.0_45/jre/lib/amd64/server python uwsgiconfig.py --plugin plugins/jvm ring`
builds plugins/uwsgi.jar

4. When you try to run uwsgi you will get an error that libjvm.so can't be find (uwsgi was built with UWSGICONFIG_JVM_LIBPATH), but it is not available during the runtime, you should this path into `/etc/ld.so.conf.d/uwsgi.conf`

    cat /etc/ld.so.conf.d/uwsgi.conf
    /usr/lib/jvm/jdk1.7.0_45/jre/lib/amd64/server

5. And we are ready to run: uwsgi config.ini

6. Go to localhost:9090/?n=8 and see the result
    curl -i localhost:9090/?n=9

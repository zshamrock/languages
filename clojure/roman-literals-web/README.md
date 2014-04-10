### Work in progress
I know that there is a lein ring plugin, but better try to do everything how it is supposed to be, 
before using some high level plugin.

So, there are steps (based from https://github.com/ring-clojure/ring/wiki/Getting-Started) how to run the app:

    => (use 'ring.adapter.jetty)
    => (use 'roman-literals-web.core)
    => (run-jetty handler {:port 3000})

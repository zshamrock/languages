## Compare how to do the same task in clojure and different language of choice

Accomplished the same task using clojure and python. Python is about 4 secs faster, but I like the clojure way, unfortunately when the performance matters, probably I will still do the python instead. But, was surprised that clojure gives you a way to do even that kind of task (shell) relatively easy.

The way to run the single clojure file is to run: `java -cp $CLOJURE_JAR clojure.main $1` which I put in `clj` bin in my `$HOME/bin` directory (`$CLOJURE_JAR` points to the clojure.jar on my system).

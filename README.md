# Java Scan
### Motivation
I have very dificulties searching for classnames in various differents paths, and 
this was a great suffer for me: in actual tools you must remember ever command line, 
ever parameter, ever... No. No more pain! This tool is a command line. Will search
for ever jar and zip recursive in any directory that you'll enter with a simple "cd" 
command.

### Download
Will be pushed here soon.

### Source Code
Yes! We are free and open. Take it! :grin:

https://github.com/valtoni/javascan 

### Mini documentation

_Change directory_
```
cd /home/valtoni/myproject
```

_Change directory_
```
cd /home/valtoni/javascan
SCAN> cd ..
Available Files at /home/valtoni/javascan/target:
- javascan-1.0.0-SNAPSHOT.jar
- staging/original-javascan-1.0.0-SNAPSHOT.jar
```

_Search for classes and/or packages having 'main'_

```
SCAN> search main
javascan-1.0.0-SNAPSHOT.jar: org/pushingpixels/lafwidget/ant/LafMainClassAugmenter$AugmentClassAdapter.class
javascan-1.0.0-SNAPSHOT.jar: org/pushingpixels/lafwidget/ant/LafMainClassAugmenter.class
javascan-1.0.0-SNAPSHOT.jar: org/pushingpixels/substance/internal/contrib/randelshofer/quaqua/colorchooser/ColorChooserMainPanel$1.class
javascan-1.0.0-SNAPSHOT.jar: org/pushingpixels/substance/internal/contrib/randelshofer/quaqua/colorchooser/ColorChooserMainPanel.class
staging/original-javascan-1.0.0-SNAPSHOT.jar: info/boaventura/scan/core/MainSetting.class
javascan-1.0.0-SNAPSHOT.jar: org/pushingpixels/lafwidget/ant/AugmentMainTask.class
javascan-1.0.0-SNAPSHOT.jar: com/izforge/izpack/util/os/WinSetupAPIMain.class
javascan-1.0.0-SNAPSHOT.jar: com/izforge/izpack/util/os/WinSetupAPIMain$1.class
staging/original-javascan-1.0.0-SNAPSHOT.jar: info/boaventura/scan/Main.class

The search search for 'main' has found 9 matches
SCAN>
```

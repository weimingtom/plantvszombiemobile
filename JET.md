本文介绍了JET技术，其是一种基于MIDI的交互式的音乐技术。

# 简介 #

JET是Sonic Network公司所提供的基于MIDI的交互式（interactive）音乐技术。通过Sonic Network公司提供的SONiVOX JET Creator，我们可以制作.jet格式的文件。jet文件只支持MIDI格式的音乐。jet文件通过JET引擎解析后，能够在SONiVOX's Embedded Audio Synthesizer (EAS)中播放。EAS是Sonic Network所提供的MIDI合成器。JET和EAS引擎都集成到android中了，并通过JetPlayer类对外提供接口。

所谓交互式音乐，这个新概念主要是为了与那种只能进行进度、音量控制的音乐播放技术区分开来。在交互式音乐中，用户能够精细地控制各个声道（track），声段（segment）。而且，在音乐文件中，也可以定义各种事件（event），反过来触发应用程序的相关逻辑。现实中的跳舞机、电子钢琴游戏等，必须采用交互式的音乐技术，否则很难判断音乐自身的事件。


# MIDI的基础知识 #
我们都知道矢量图和栅格图的区别，前者存储的是几何信息，而后者存储的是点阵。以图像作类比，MIDI就是音乐中的“矢量”音乐，其只存储了一些标准的音高、音色等数字指令序列，这些信息有待真正的合成器解释和渲染。而MP3和WAV等都是“栅格”音乐，其存储的是波形数据。

严格说来，MIDI(Musical Instrument Digital Interface)是一种音乐接口标准，是20世纪80年代初为解决不同电声乐器、电脑和电子设备之间的通信和同步而提出的。MIDI传输的不是声音信号, 而是音符、控制参数等指令, 它指示MIDI设备要做什么,怎么做, 如演奏哪个音符、多大音量等。它们被统一表示成MIDI消息(MIDI Message) 。

MIDI文件是基于MIDI标准的一种音乐文件格式。MIDI文件相对MP3等波形音乐文件而言，占用空间非常小，这是MIDI的天生优势。MIDI文件在电脑中播放时，必须经过合成器（synthesizer）的合成。合成器从波表中查询每个MIDI指令应该发什么音，就如图形系统根据文字编码查询字体文件然后显示一样。

在MIDI音乐中，一些重要的概念解释如下：

声道（channel）：声道是一个物理概念，MIDI协议同时最多支持16个声道。多个声轨可以共用一个声道。可以认为，同一个声道的音乐会在同样一个物理设备被播放。

声轨（track）：声轨是一个逻辑概念，表示不同的并行音乐流。比如，一个音乐的伴唱和背景音如果独立成了两个不同的声轨，那么，在音乐播放中就可以单独控制每路音轨是否播放。

节/拍/点（M/B/T，measure,beat,tick)：MIDI音乐的计时方法，可以转换为时分秒。

消息（message）：MIDI消息即音乐符号或指令。下面的链接显示了所有的MIDI消息编码：

http://www.midi.org/techspecs/midimessages.php


# JET的基础知识 #
JET是在MIDI基础之上实施的一种交互式音乐技术。JET技术规定了特有的jet文件格式，实现了相应的JET引擎来驱动jet文件。JET引擎与合成器（synchesizer）协同工作进行音乐渲染。JET技术允许用户在jet文件中定义事件（event）、剪辑（clip）和声段（segment）。在应用程序中，用户可以控制声段（segment）、声道（track）、剪辑（clip），而事件（event）会通过回调的方式触发程序逻辑。


segment:segment是一个播放的单元，可以看作是一个抽象的midi文件。一个jet文件中可以定义多个segment。

clip:clip指track中的一小段，其主要是为了同步不同的track。

event:用户在jet文件中定义的音频事件。当音乐回放时，遇到了该事件标示，引擎会通过回调的方式触发程序逻辑。通过这种方式能够实现应用与声音的同步。

JET的clip和event都是利用MIDI的control Change message实现的。JET对CC的利用如下：
  1. ontrollers 80-83： Reserved for use by application
  1. ontroller 102： JET event marker（此编号在JET中专用来标示end of segment）
  1. ontroller 103： JET clip marker
  1. ontrollers 104-119： Reserved for future use

用户在定制jet文件过程中，可以使用编号80-83的cc消息，自定义音乐事件。官方文档对此说明如下：

The application may use controllers in this range for its own purposes. When a controller in this range is encountered, the event is entered into an event queue that can be queried by the application. Some possible uses include synchronizing video events with audio and marking a point in a MIDI segment to queue up the next segment. The range of controllers monitored by the application can be modified by the application during initialization.

对于clip，文档中有这样的说明：

JET can also synchronize the muting and un-muting of tracks to events in the music. For example, in the FPS game, it would probably be desirable to place the musical events relating to bonuses and damage as close to the actual game event as possible. However, simply un-muting a track at the moment the game event occurs might result in a music clip starting in the middle. Alternatively, a clip could be started from the beginning, but then it wouldn't be synchronized with the other music tracks.

JET使用编号为103的MIDI Control Change Message来实现clip marker的功能。标准文档描述如下：

Controller 103 is reserved for marking clips in a MIDI track that can be triggered by the JET\_TriggerClip API call. The clip ID is encoded in the low 6 bits of the controller value. Bit 6 is set to one to indicate the start of a clip, and set to zero to indicate the end of a clip.

For example, to identify a clip with a clip ID of 1, the author inserts a MIDI controller event with controller=103 and value=65 at the start of the clip and another event with controller=103 and value=1 at the end of the clip. When the JET\_TriggerClip() function is called with a clip ID of 1, the track will be un-muted when the controller value 65 is encountered and muted again when the controller value 1 is encountered.

![http://androidappdocs.appspot.com/images/jet/clip_marker.png](http://androidappdocs.appspot.com/images/jet/clip_marker.png)


Figure 5: Synchronized Clip

In the figure above, if the JET\_TriggerClip() function is called prior to the first controller event, Track 3 will be un-muted when the first controller event occurs, the first clip will play, and the track will be muted when the second controller event occurs. If the JET\_TriggerClip() function is called after the first controller event has occurred, Track 3 will be un-muted when the third controller event occurs, the second clip will play, and the track will be muted again when the fourth controller event occurs.

# 制作JET文件 #
JET文件是由MIDI文件组成的。所以，首先需要一个MIDI文件的编辑软件，制作满足需要的MIDI文件；其次需要使用SONiVOX JET Creator根据MIDI文件，制作需要的JET文件。

MIDI文件的编辑软件有很多，下面地址给出了一个列表：

http://en.wikipedia.org/wiki/List_of_MIDI_editors_and_sequencers

SONiVOX JET Creator软件可以由MIDI文件定制声段（segment）、clip和event。该软件是用python语言编写的，在Android SDK tools中。运行JET Creator需要安装如下的包：

（1）python运行时。http://www.python.org/download/releases/2.5.4/

（2）wxPython库。http://www.wxpython.org/download.php

JET Creator经过测试的兼容版本如下：

Python Version 2.5.4

wxPython Version 2.8.7.1

# 在程序中使用JetPlayer #
如果定制好了jet文件，就可以在程序中使用了。
示例代码如下：
```
JetPlayer myJet = JetPlayer.getJetPlayer();
myJet.loadJetFile("/sdcard/level1.jet");
byte segmentId = 0;

// queue segment 5, repeat once, use General MIDI, transpose by -1 octave
myJet.queueJetSegment(5, -1, 1, -1, 0, segmentId++);
// queue segment 2
myJet.queueJetSegment(2, -1, 0, 0, 0, segmentId++);

myJet.play();
```

  * 使用setEventListener可以接收引擎所触发的音频事件。
  * 使用setMuteFlag可以对声轨（track）进行控制。
  * 使用triggerClip可以触发jet文件中定义的剪辑。


# 参考资料 #
http://androidappdocs.appspot.com/reference/android/media/JetPlayer.html

http://androidappdocs.appspot.com/guide/topics/media/index.html#jet

http://androidappdocs.appspot.com/guide/topics/media/jet/jetcreator_manual.html

http://www.midi.org/techspecs/midimessages.php
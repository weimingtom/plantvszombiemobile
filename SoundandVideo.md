介绍如何在android中播放声音和视频、录制声音和视频。

# Introduction #
在android中，可以使用MediaPlayer来播放声音和视频，使用MediaRecorder来录制声音和视频。此外，JetPlayer类可以进行Jet音频的播放。

# MediaPlayer #
## 播放程序中的内嵌资源 ##
将待播放的文件资源如sound.mp3放在工程目录res\raw下。然后，可以使用R.raw.sound引用该资源，进行播放了。
```
MediaPlayer mp = MediaPlayer.create(context, R.raw.sound);
mp.start();
mp.stop();
```
注意以下事项：
  1. 如果stop后需要再次重新播放，必须在再次调用start之前调用reset和prepare。
  1. 如果不再使用mediaplayer，应该立即调用release释放资源。
  1. 如果要循环播放音乐，使用setLooping(true)。

更多内容，请参考[MediaPlayer](http://androidappdocs.appspot.com/reference/android/media/MediaPlayer.html)类库说明。

## 播放文件系统或者流 ##
```
MediaPlayer mp = new MediaPlayer();
mp.setDataSource(PATH_TO_FILE);//PATH_TO_FILE可为文件路径或者URL
mp.prepare();
mp.start();
```

# MediaRecorder #
示例代码如下：
```
    recorder = new MediaRecorder();
    ContentValues values = new ContentValues(3);

    values.put(MediaStore.MediaColumns.TITLE, SOME_NAME_HERE);
    values.put(MediaStore.MediaColumns.TIMESTAMP, System.currentTimeMillis());
    values.put(MediaStore.MediaColumns.MIME_TYPE, recorder.getMimeContentType());
    
    ContentResolver contentResolver = new ContentResolver();
    
    Uri base = MediaStore.Audio.INTERNAL_CONTENT_URI;
    Uri newUri = contentResolver.insert(base, values);
    
    if (newUri == null) {
        // need to handle exception here - we were not able to create a new
        // content entry
    }
    
    String path = contentResolver.getDataFilePath(newUri);

    // could use setPreviewDisplay() to display a preview to suitable View here
    
    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    recorder.setOutputFile(path);
    
    recorder.prepare();
    recorder.start();
```

停止录制
```
    recorder.stop();
    recorder.release();
```

# JetPlayer #
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


# 参考资料 #
http://androidappdocs.appspot.com/guide/topics/media/index.html<br />
http://androidappdocs.appspot.com/reference/android/media/MediaPlayer.html<br />
http://androidappdocs.appspot.com/reference/android/media/MediaRecorder.html<br />
http://androidappdocs.appspot.com/reference/android/media/JetPlayer.html
# Introduction #

在android中使用动画的技术有两种：Tween Animation和Frame Animation。

# Tween Animation #
Tween Animation能够实现对动画对象的移动、旋转、放缩、透明化等操作。更多内容可参考下面链接：<br />
http://androidappdocs.appspot.com/guide/topics/graphics/2d-graphics.html#tween-animation

# Frame Animation #
帧动画是一种传统的动画技术，其基本原理是顺序播放不同的图片，看起来就如流畅的电影动作一样。<br />
<br />
在android中，使用AnimationDrawable类来控制帧动画的播放，动画的各帧图片通过xml文件在资源中定义。示例xml文件如下：<br />
```
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="true">
    <item android:drawable="@drawable/rocket_thrust1" android:duration="200" />
    <item android:drawable="@drawable/rocket_thrust2" android:duration="200" />
    <item android:drawable="@drawable/rocket_thrust3" android:duration="200" />
</animation-list>
```

上面的xml文件定义了一个动画，该动画由三帧构成，每帧播放时间为200毫秒。oneshot="true"告诉播放器不要循环播放此动画。下面的代码显示了如何使用动画资源进行播放。<br />

```
AnimationDrawable rocketAnimation;

public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);

  ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
  rocketImage.setBackgroundResource(R.drawable.rocket_thrust);
  rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
}

public boolean onTouchEvent(MotionEvent event) {
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    rocketAnimation.start();
    return true;
  }
  return super.onTouchEvent(event);
}
```

更多详情，请参考下面的链接：<br />
http://androidappdocs.appspot.com/guide/topics/graphics/2d-graphics.html#frame-animation
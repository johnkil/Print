Print
=====

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Print-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1016)

A lightweight Android library for use iconic fonts.

![image](https://raw.githubusercontent.com/johnkil/Print/master/art/print.jpg)

<a href="https://play.google.com/store/apps/details?id=com.github.johnkil.print.sample">
  <img alt="Get it on Google Play"
       src="http://www.android.com/images/brand/get_it_on_play_logo_small.png" />
</a>

Download
--------

Gradle:

```groovy
compile 'com.github.johnkil.print:print:1.2.1'
```

Maven:

```xml
<dependency>
    <groupId>com.github.johnkil.print</groupId>
    <artifactId>print</artifactId>
    <version>1.2.1</version>
    <type>aar</type>
</dependency>
```

Usage
-----

First, you need to initialize the default iconic font in [Application.onCreate()][1] method. If
the font is not specified, then the font is used by default.

```java
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();                
        PrintConfig.initDefault(getAssets(), "fonts/iconic-font.ttf");
    }

}
```

#### PrintView

Use `PrintView` as single icon in your layout.

```xml
<com.github.johnkil.print.PrintView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        print:iconColor="@color/icon_color"
        print:iconSize="@dimen/icon_size"
        print:iconFont="fonts/iconic-font.ttf"
        print:iconText="@string/ic_android"
        android:contentDescription="@string/ic_android_description"/>
```

#### PrintButton

Use `PrintButton` to create a button with an icon.

```xml
<com.github.johnkil.print.PrintButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        print:iconColor="@color/icon_color"
        print:iconSize="@dimen/icon_size"
        print:iconFont="fonts/iconic-font.ttf"
        print:iconText="@string/ic_android"
        android:contentDescription="@string/ic_android_description"/>
```

#### PrintDrawable

If you need an icon in `ImageView` or in `ActionBar`, then you should use `PrintDrawable`.

```java
ImageView imageView = (ImageView) findViewById(R.id.image);
// Set an icon in the ImageView
imageView.setImageDrawable(
    new PrintDrawable.Builder(context)
            .iconText(R.string.ic_info)
            .iconColor(R.color.icon_color)
            .iconSize(R.dimen.icon_size)
            .iconFont("fonts/iconic-font.ttf")
            .build()
);
```

```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    // Set an icon in the ActionBar
    menu.findItem(R.id.action_info).setIcon(
            new PrintDrawable.Builder(context)
                    .iconText(R.string.ic_info)
                    .iconColor(R.color.ab_icon_color)
                    .iconSize(R.dimen.ab_icon_size)
                    .iconFont("fonts/iconic-font.ttf")
                    .build()
    );
    return true;
}
```

Links
-----

* [Android-Icon-Fonts][2] - Material and Holo iconic fonts.


License
-------

    Copyright 2014 Evgeny Shishkin
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    

[1]: http://developer.android.com/reference/android/app/Application.html#onCreate%28%29
[2]: https://github.com/johnkil/Android-Icon-Fonts

[0]: https://github.com/shamanland/fonticon
[0]: https://github.com/atermenji/IconicDroid
[0]: https://github.com/JoanZapata/android-iconify
[0]: https://github.com/chrisjenx/Calligraphy

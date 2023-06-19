# Android Ruler Picker

[![Build Status](https://travis-ci.org/kevalpatel2106/android-ruler-picker.svg?branch=master)](https://travis-ci.org/kevalpatel2106/android-ruler-picker) [![API](https://img.shields.io/badge/API-21%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=14)

#### Android view that uses ruler Picker.
| | |
|:---:|:---:|
|![screenShot_1](/screenshot/Screenshot_20220506_040914.png)|![screenShot_2](/screenshot/Screenshot_20220506_035907.png)

# Installation
Add the following dependency to your `build.gradle` file:

```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and
```
dependencies {
    implementation 'com.github.elsunhoty:Ruler-picker:1.0'
}
```

 

## Implementation:

You can specify the attributes from a layout XML like:
```xml
 <com.elsunhoty.rulerpicker.lib.RulerView
        android:id="@+id/viewRuler"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.13"
        app:ruler_hash_mark_distance="10dp"
        app:ruler_hash_mark_interval="8"
        app:ruler_indicator_color="#73FF7474"
        app:ruler_indicator_height="60dp"
        app:ruler_indicator_width="3dp"
        app:ruler_hash_mark_gravity="center"
        app:ruler_long_hash_mark_color="#FFFFFF"
        app:ruler_long_hash_mark_height="40dp"
        app:ruler_long_hash_mark_text_visible="true"
        app:ruler_long_hash_mark_text_color="#BFBFBF"
        app:ruler_long_hash_mark_text_margin_top="14dp"
        app:ruler_long_hash_mark_text_size="12sp"
        app:ruler_long_hash_mark_width="2dp"
        app:ruler_max_value="300"
        app:ruler_initial_value="152"
        app:ruler_small_hash_mark_color="#CFCFCF"
        app:ruler_small_hash_mark_height="30dp"
        app:ruler_small_hash_mark_width="1dp" />
```

- Library provides XML attributes to customize

|              Attribute               |  Type   |Java/Kotlin|              Description              |
|:------------------------------------:|:-------:|:---:|:-------------------------------------:|
|           ruler_max_value            |   int   |`NOT YET`|      Change max value for ruler.      |
|           ruler_init_value           |   int   |`NOT YET`|                                       |
|       ruler_hash_mark_interval       |   int   |`NOT YET`| interval between two long hash mark.  |
|       ruler_hash_mark_distance       |   dim   |`NOT YET`| distance between every two hash mark. |
|      ruler_long_hash_mark_width      |   dim   |`NOT YET`|                   .                   |
|     ruler_long_hash_mark_height      |   dim   |`NOT YET`|                   .                   |
|    ruler_long_hash_mark_text_size    |   dim   |`NOT YET`|                   .                   |
|   ruler_long_hash_mark_text_color    |  color  |`NOT YET`|                   .                   |
|      ruler_long_hash_mark_color      |  color  |`NOT YET`|                   .                   |
| ruler_long_hash_mark_text_margin_top |   dim   |`NOT YET`|                   .                   |
|     ruler_small_hash_mark_width      |   dim   |`NOT YET`|                   .                   |
|     ruler_small_hash_mark_height     |   dim   |`NOT YET`|                   .                   |
|     ruler_small_hash_mark_color      |  color  |`NOT YET`|                   .                   |
|        ruler_indicator_height        |   dim   |`NOT YET`|                   .                   |
|        ruler_indicator_width         |   dim   |`NOT YET`|                   .                   |
|        ruler_indicator_color         |   dim   |`NOT YET`|                   .                   |
|       ruler_hash_mark_gravity        |  enum   |`NOT YET`|                   .                   |
|  ruler_long_hash_mark_text_visible   | boolean |`NOT YET`|                   .                   |


## Want to contribute?
Every small or large contribution to this project is appreciated..


## License
Copyright 2020 Ahmed Hamed

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

<div align="center">
<img src="https://cloud.githubusercontent.com/assets/370176/26526332/03bb8ac2-432c-11e7-89aa-da3cd1c0e9cb.png">
</div>

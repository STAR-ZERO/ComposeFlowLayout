# Compose FlowLayout

[![Download](https://img.shields.io/maven-central/v/com.star-zero/compose-flowlayout)](https://search.maven.org/artifact/com.star-zero/compose-flowlayout)

A FlowLyout for Jetpack Compose.

## Screenshot

<img src="docs/images/screenshot.png" width="320" />

## Usage

```kt
FlowLayout(
    verticalSpacing = 8.dp,
    horizontalSpacing = 8.dp,
    modifier = Modifier.padding(16.dp)
) {
    Item(/** ... */)
    // ...
}
```

## Download

```
repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation "com.star-zero:compose-flowlayout:$version"
}
```

## License

```
Copyright 2021 Kenji Abe

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
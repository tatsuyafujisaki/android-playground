# Build variant
product flavor + build type (normally debug or release)

# Build type
* debug (automatically defined)
* release (automatically defined)
* myCustomBuildType

# Flavor dimension
* is a set of product flavors.

# Source set
* are directories under the `src` directory.

## Default source set
```shell
src/main
```

## How to list all the source sets
```shell
./gradlew sourceSets
```

## How to add Kotlin directories to source sets
```gradle
android {
    sourceSets.each {
        it.java.srcDirs += "src/$it.name/kotlin"
    }
}
```

## List of source sets in order of priority
1. Build variant source set (e.g. `src/HigherDimensionFravorLowerDimensionFlavorDebug`)
1. Combination of higher-priority individual product flavor and build type source set (e.g. `src/HigherDimensionFravorDebug`)
1. Combination of lower-priority individual product flavor and build type source set (e.g. `src/LowerDimensionFravorDebug`)
1. Build type source set (e.g. `src/Debug`)
1. Higher-prirority individual product flavor source set (e.g. `src/HigherDimensionFlavor`)
1. Lower-prirority individual product flavor source set (e.g. `src/LowerDimensionFlavor`)
1. Main/Default source set (`src/main`)

### Note
* The more specific, the more prioritized.
* Apart from `AndroidManifest.xml`, Gradle does not merge files from different source sets. Gradle uses only a single file with the highest priority among all the source sets.

## How to declare a dependency for a specific source set
```gradle
dependencies {
    // For example
    higherDimensionFlavorLowerDimensionFlavorDebugImplementation
    higherDimensionFlavorLowerDimensionFlavorReleaseImplementation

    higherDimensionFlavorDebugImplementation
    higherDimensionFlavorReleaseImplementation

    lowerDimensionFlavorDebugImplementation
    lowerDimensionFlavorReleaseImplementation

    debugImplementation
    releaseImplementation

    higherDimensionFlavorImplementation
    higherDimensionFlavorImplementation

    lowerDimensionFlavorImplementation
    lowerDimensionFlavorImplementation
}
```

# Example
```gradle
// Lefter dimensions have higher priorities
flavorDimensions "color", "fruit"

productFlavors {
    black {
        dimension "color"
        applicationIdSuffix ".black"
        versionNameSuffix "-black"
    }
    white {
        dimension "color"
        applicationIdSuffix ".white"
        versionNameSuffix "-white"
    }
    apple {
        dimension "fruit"
        applicationIdSuffix ".apple"
        versionNameSuffix "-apple"
    }
    orange {
        dimension "fruit"
        applicationIdSuffix ".orange"
        versionNameSuffix "-orange"
    }
}
```

## Generated build variants
```
[black, white][Apple, Orange][Debug, Release]
```

## Generated source sets in order of priority
1. `src/[black, white][Apple, Orange][Debug, Release]` *(build variant source set)*
1. `src/[black, white][Debug, Release]` *(higher-priority individual product flavor and build type source set)*
1. `src/[apple, orange][Debug, Release]` *(lower-priority individual product flavor and build type source set)*
1. `src/[debug, release]` *(build type source set)*
1. `src/[black, white]` *(higher-prirority individual product flavor source set)*
1. `src/[apple, orange]` *(lower-prirority individual product flavor source set)*
1. `src/main` *(main/default source set)*

## Generated APKs
```
app-[black, white]-[apple, orange]-[debug, release].apk
```

# References
https://developer.android.com/studio/build/build-variants
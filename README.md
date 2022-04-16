# UiText

### The problem: 
How to store strings / texts in android View Model? The good approach is to store only Ids. But what about more complex cases when we need not only simple string by id, but something formatted or even sometimes just plain string?

### The solution:
Use UiText wrappers.  
Example for typical ViewModel:  
```
_field01.value = UiText.Str("Just string")

_field02.value = UiText.Res(R.string.plain_string)

_field03.value = UiText.Format(R.string.formatted_string, "first", 2, 3f)

_field04.value = UiText.Plural(R.plurals.plural_plain_string, 1)

_field05.value = UiText.PluralFormat(R.plurals.plural_format_string, 3,  1, "2", "3")
```

In view layer:  
```
textView01.setUiText(it)
```

# Setup:  

[![](https://jitpack.io/v/Andrew0000/UiText.svg)](https://jitpack.io/#Andrew0000/UiText)

1. Add `maven { url 'https://jitpack.io' }` to the `allprojects` or `dependencyResolutionManagement` section in top-level `build.gradle` or `settings.gradle`.  
For example (`settings.gradle`):
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven { url "https://jitpack.io" }
    }
}
```
2. Add `implementation 'com.github.Andrew0000:UiText:$latest_version'` to the module-level `build.gradle`  

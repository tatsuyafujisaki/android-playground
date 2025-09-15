# ComposeView
```xml
<androidx.compose.ui.platform.ComposeView
    android:id="@+id/compose_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

## How to programmatically update constraints
```kotlin
myContentLayout.updateLayoutParams<ConstraintLayout.LayoutParams> {
    height = ConstraintSet.WRAP_CONTENT
    height = ConstraintSet.MATCH_CONSTRAINT // equivalent to 0dp
    height = ViewGroup.LayoutParams.MATCH_PARENT

    topToTop  = ConstraintSet.UNSET
    topToTop = ConstraintSet.PARENT_ID
    topToBottom = myToolbar.id // safer than R.id.my_toolbar

    bottomMargin = 0
}
```

# LinearLayout
```xml
<LinearLayout
    android:id="@+id/linear_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:showDividers="middle"
    android:dividerPadding="16dp"
    android:divider="?android:listDivider" or "?android:dividerHorizontal"
    android:orientation="vertical">
```

# RecyclerView
```xml
<!-- android:orientation is unnecessary if you want it vertical. -->
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recycler_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:clipToPadding="false"
    android:orientation="horizontal"
    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    tools:listitem="@layout/item" />
```

```kotlin
with(recyclerView) {
    adapter = myAdapter.apply {
        registerAdapterDataObserver(
            object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    scrollToPosition(0) // RecyclerView#scrollToPosition(0)
                }
            }
        )
    }
    addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
    setHasFixedSize(true) // only if the size is fixed.
    itemAnimator = null
    myLiveData.observe(viewLifecycleOwner) {
        with(adapter as MyAdapter) {
            submitList(it)
            // notifyDataSetChanged() // Uncomment this only if new data is not reflected on UI after submitList(...).
        }
    }
}
```

# TextView
```xml
<!-- "android:gravity" is unnecessary if you don't use app:drawableStartCompat and so on. -->
<TextView
    android:id="@+id/text_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:ellipsize="end"
    android:gravity="center"
    android:singleLine="true"
    tools:drawableEndCompat="@tools:sample/avatars"
    tools:drawableStartCompat="@tools:sample/avatars"
    tools:text="@tools:sample/lorem/random" />
```

# ImageView
```xml
<ImageView
    android:id="@+id/image_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:contentDescription="@null"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    tools:src="@tools:sample/avatars" />
```

# Divider
```xml
<View
    android:id="@+id/divider"
    android:layout_width="0dp"
    android:layout_height="1dp"
    android:background="@android:color/darker_gray"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent" />
```
Instead, use [MaterialDivider](https://material.io/components/dividers/android) when it becomes available.

# EditText
`android:hint`, `android:importantForAutofill`, and `android:inputType` are to suppress a warning.
```xml
<EditText
    android:id="@+id/edit_text"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:hint="@null"
    android:importantForAutofill="no"
    android:inputType="text"
    android:text="Hello, World!" />
```

# HorizontalScrollView and ChipGroup
```xml
<HorizontalScrollView
    android:id="@+id/horizontal_scroll_view"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:scrollbars="none"
    android:requiresFadingEdge="horizontal"
    android:fadingEdgeLength="80dp">

    <com.google.android.material.chip.ChipGroup
        android:id="@+id/chip_group"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:singleSelection="true"
        app:chipSpacingHorizontal="0dp" />
</HorizontalScrollView>
```
* `app:singleSelection="true"` makes [choice chips](https://material.io/components/chips#choice-chips) and enables [`ChipGroup.setOnCheckedChangeListener()`](https://developer.android.com/reference/com/google/android/material/chip/ChipGroup#setoncheckedchangelistener).
* If `app:singleSelection="true"` is not set, you have to add an `View.OnClickListener` on each chip.
* `ChipGroup.setOnCheckedChangeListener()` returns -1 if the currently selected chip is re-selected.

```kotlin
chipGroup.setOnCheckedChangeListener { group, checkedId ->
    group.children.filterIsInstance<Chip>().firstOrNull { it.id == checkedId }?.run {
        /*
         * Prevents re-selecting the selected chip because it will unselect the selected chip and leave all the chips unselected.
         * app:selectionRequired="true" in Material Components 1.2.0-alpha02 or higher dispenses with this workaround.
         * https://github.com/material-components/material-components-android/issues/651
         */
        isClickable = false
        group.children.filterIsInstance<Chip>().filterNot { it.id == id }.forEach {
            it.isClickable = true
        }
    }
}
```

# CoordinatorLayout
shares scrolling information between its children.


# ViewModel and LiveData
```kotlin
class MyViewModel : ViewModel() {
    private val _something = MutableLiveData<Something>()
    val something: LiveData<Something> = _something

    fun setSomething(something: Something) {
        _something.value = something
    }
}
```

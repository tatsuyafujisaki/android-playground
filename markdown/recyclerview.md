# RecyclerView
* `android:clipToPadding="false"` removes the top margin of the screen disappears as you scroll down the screen.
  * Search https://developer.android.com/codelabs/basic-android-kotlin-training-internet-images#4 for `android:clipToPadding="false"` to find a good visual explanation.
* `findViewHolderForItemId(id: Long)` returns a ViewHolder of the given ID.
  * This method is available only if `RecyclerView.Adapter.hasStableIds()` is `true`.
* `findViewHolderForAdapterPosition(position: Int)` returns a ViewHolder at the given adapter position.
* `findViewHolderForLayoutPosition(position: Int)` returns a ViewHolder at the given layout position.
* `setHasFixedSize(true)`
  * should be set if a vertical RecyclerView has `android:layout_height="match_parent"`.
  * should NOT be set if a vertical RecyclerView has `android:layout_height="wrap_content"`.
  * `setHasFixedSize(true)` refers to the entire size of RecyclerView, not each child item.
* `setItemAnimator(null)` removes animation.
* `smoothScrollToPosition(position: Int)` smooth-scrolls to the given adapter position.
* `addOnItemTouchListener(...)` intercepts touch events.
* `addOnScrollListener(...)` intercepts scroll events.
  * You may want to pass `SimpleOnItemTouchListener` instead of `OnItemTouchListener` if you don't want to override all the methods of the latter.

# Position
## Layout position
* is the position of a ViewHolder in the latest layout calculation, which is updated every 16 milliseconds.
* is the position of a ViewHolder the user sees.
## Adapter position
* is the position of a ViewHolder, which is updated when the adapter content is updated.
* is the position of a ViewHolder, which may not be reflected to UI yet.
* should be used in the click listener of a ViewHolder.

## Layout position and adapter position are inconsistent only during the following short period.
1. The adapter content is updated.
1. The Adapter.notify*() is called.
1. The next frame is rendered, which will occur in  16 milliseconds.

## How to scroll to top after adapter.submitList(...)
```kotlin
adapter.registerAdapterDataObserver(
    object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            binding.recyclerView.scrollToPosition(0)
        }
    }
)
```

# RecyclerView.Adapter
* adapts the data so that it can be displayed in a ViewHolder.
* `onCreateViewHolder(parent: ViewGroup, viewType: Int)`
  * takes a layout file and creates a ViewHolder.
  * ViewHolder(s) are created only as many as necessary for screen.
* `onBindViewHolder(holder: ViewHolder, position: Int)`
  * binds an item to a ViewHolder.
  * whose analogy is to replace a person in a moving vehicle in Disneyland attractions.
  * `position` should NOT be used in a click listener because `position` is only the snapshot of a position when binding occurs. Use `getBindingAdapterPosition()` instead.
  * It is recommended to call `ViewDataBinding.executePendingBindings()` at the end of `onBindViewHolder()` if data binding is used. Otherwise the size of the ViewHolder may not be correctly measured before it is rendered, so re-rendering will occur.
    * https://developer.android.com/topic/libraries/data-binding/generated-binding#dynamic_variables
* `notifyDataSetChanged()` should be called after `ListAdapter.submitList(...)` or UI does not change.
* `getItemId(position: Int)` returns the ID of an item at a given position.
  * This method is available only if `RecyclerView.Adapter.hasStableIds()` is `true`.
* `setHasStableIds(hasStableIds: Boolean)` takes `true` when each item has an ID.

# RecyclerView.ViewHolder
* is created by `RecyclerView.Adapter.onCreateViewHolder(...)` only as many as necessary for screen.
* contains an item by `RecyclerView.Adapter.onBindViewHolder(...)`.
* As you scroll, a ViewHolder that goes off screen will be moved to the opposite end of the screen to display a new item.
* `getBindingAdapterPosition()`
  * returns an adapter position.
  * returns `RecyclerView.NO_POSITION` between `notifyDataSetChanged()` and the new frame has not been rendered. FYI, frames are rendered every 16 milliseconds.
* `getItemId()` returns the ID of an item bound to the ViewHolder.
* `getAbsoluteAdapterPosition()` returns a MergeAdapter position.
* `getLayoutPosition()` returns a layout position.

# How to save and store RecyclerView's state during a configuration change or a system-initiated process death
```kotlin
class MyFragment : Fragment() {
    private lateinit var binding: MyFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedInstanceState?.getParcelable<Parcelable>(LAYOUT_MANAGER_STATE)?.let {
            binding.recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(LAYOUT_MANAGER_STATE, binding.recyclerView.layoutManager?.onSaveInstanceState())
    }

    companion object {
        private const val LAYOUT_MANAGER_STATE = "layout_manager_state"
    }
}
```

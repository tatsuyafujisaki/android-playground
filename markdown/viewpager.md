# ViewPager 1
* `PagerAdapter.getCount(...)` is automatically called after `viewPager.adapter = viewPagerAdapter`.
* If `PagerAdapter.getCount(...)` returns more than 0, which is the number of pages, `PagerAdapter.instantiateItem(...)` is automatically called for each page.
* The following error occurs if you don't call `PagerAdapter.notifyDataSetChanged()` after changing the adapter's contents.
```
java.lang.IllegalStateException: The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged!
```
* If you call `PagerAdapter.notifyDataSetChanged()`, you should override `PagerAdapter.getItemPosition()`. Otherwise, `PagerAdapter.instantiateItem(...)` is not automatically called after `PagerAdapter.notifyDataSetChanged()`. It means that your pages will not redraw using the changed data.
```kotlin
class MyViewPager : ViewPager {
    override fun getItemPosition(obj: Any): Int {
        return POSITION_NONE
    }
}
```
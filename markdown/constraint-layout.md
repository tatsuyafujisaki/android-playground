# ConstraintLayout
* should be nested when listing CardView.
  * https://stackoverflow.com/questions/37938767/designing-cardview-while-its-parent-is-constraintlayout
* If a view has an attribute related to ConstraintLayout, the innermost ViewGroup that contains the view must be ConstraintLayout to interpret the attribute. For example, If the ViewGroup is a CardView, the attribute will not be understood.

# `<MotionScene>`
* must contain at least one `<Transition>`.
* When using `MotionLayout`, all the constraints of animated views must be specified in `<MotionScene>`. The constraints of non-animated views can be specified in `<MotionLayout>`.

# `<Transition>`
* is one animation.
* contains the following attributes
  * `constraintSetStart`
  * `constraintSetEnd`
  * `duration`
  * Optionally, `<KeyFrameSet>`

# `<KeyFrameSet>`
* contains at least one `<KeyPosition>`.
> At `framePosition` modify the path of `motionTarget` by moving it by `percentX` or `percentY` according to the coordinates determined by `keyPositionType`.
* https://codelabs.developers.google.com/codelabs/motion-layout/index.html?index=..%2F..advanced-android-kotlin-training#4

# `<KeyPosition>`
* never affects start and end constraints.
* never affects other views that are constained to the view associated with `<KeyPosition>`.

# `<ConstraintSet>`
* is the start and the end constraints of a motion.
* contains `<Constraint>`(s).
* contains only constraints or layout information such as width, height, alpha, or visibility, but not views.
* whose constraints override constraints in a layout file.

# RelativeLayout vs. ConstraintLayout
RelativeLayout|ConstraintLayout
--|--
android:layout_centerInParent="true"|app:layout_constraintBottom_toBottomOf="parent"<br>app:layout_constraintStart_toStartOf="parent"<br>app:layout_constraintEnd_toEndOf="parent"<br>app:layout_constraintTop_toTopOf=""parent"<br>
android:layout_centerVertical="true"|app:layout_constraintBottom_toBottomOf="parent"<br>app:layout_constraintTop_toTopOf="parent"
android:layout_centerHorizontal="true""|app:layout_constraintStart_toStartOf="parent"<br>app:layout_constraintEnd_toEndOf="parent"
android:layout_alignParentTop="true"|app:layout_constraintTop_toTopOf="parent"
android:layout_alignParentBottom="true"|app:layout_constraintBottom_toBottomOf="parent"
android:layout_alignParentStart="true"|app:layout_constraintStart_toStartOf="parent"
android:layout_alignParentEnd="true"|app:layout_constraintEnd_toEndOf="parent"
android:layout_alignTop="@id/view"|app:layout_constraintTop_toTopOf="@id/view"
android:layout_alignBottom="@id/view"|app:layout_constraintBottom_toBottomOf="@id/view"
android:layout_alignStart="@id/view"|app:layout_constraintStart_toStartOf="@id/view"
android:layout_alignEnd="@id/view"|app:layout_constraintEnd_toEndOf="@id/view"
android:layout_alignBaseline="@id/view"|app:layout_constraintBaseline_toBaselineOf="@id/view"
android:layout_above="@id/view"|app:layout_constraintBottom_toTopOf="@id/view"
android:layout_below="@id/view"|app:layout_constraintTop_toBottomOf="@id/view"
android:layout_toStartOf="@id/view"|app:layout_constraintEnd_toStartOf="@id/view"
android:layout_toEndOf="@id/view"|app:layout_constraintStart_toEndOf="@id/view"
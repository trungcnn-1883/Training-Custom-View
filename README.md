# Training Custom View

## I. Các khái niệm

### 1. View

### a. Khái niệm

Những gì ta thấy trên màn hình thiết bị Android thì được gọi là View

<img src="http://eitguide.net/wp-content/uploads/2016/07/layout-android-0.png" width=600>

Điểm khác biệt là có các thuộc tính mới để đáp ứng nhu cầu của View đó\

Các thuộc tính có sẵn :

- Thuộc tính: setText, setTextColor, setBackGroundResource, setTextAllCap, ...

- Focus: xử lý focus dựa vào input của người dùng

- Xử lý sự kiện: như onTouch, onClick, onScoll, onDrag, ...

- Visibility: cho gone, invisible, visible, ...

Còn nhiều thuộc tính khác như Id, padding, margin, ...

### b. Lợi ích / Bất lợi

Lợi ích

- Tái sử dụng được view, được code

- Custom view sẽ có những tính năng, thuộc tính ta tự tạo ra

Bất lợi

- Tốn thời gian, công sức

- Phải chỉnh sửa, custom nhiều thứ

### c. View lifecycle

<img src="https://viblo.asia/uploads/be38a028-69c2-4249-a258-89eba0b6acb7.png">

### Giải thích

### Constructor

- Hàm khởi đầu, cung cấp các tham số mặc định, giá trị tính toán

- Ta sử dụng AttributeSet để tạo các **custom atribute**

<img src="https://viblo.asia/uploads/4948f601-ec56-4353-9dbc-cd0e66fbd702.png">

Lấy ra sử dụng

```
public PageIndicatorView(Context context, AttributeSet attrs) {
    super(context, attrs);
    TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PageIndicatorView);
    int count = typedArray.getInt(R.styleable.PageIndicatorView_piv_count,0);
    typedArray.recycle();
}
```

recycle(): giải phóng bộ nhớ, những thành phần liên kết với tài nguyên, ko cần tới lúc GC chạy. Dùng khi không cần sử dụng TypedArray này nữa

```

    public void recycle() {
        if (mRecycled) {
            throw new RuntimeException(toString() + " recycled twice!");
        }

        mRecycled = true;

        // These may have been set by the client.
        mXml = null;
        mTheme = null;
        mAssets = null;

        mResources.mTypedArrayPool.release(this);
    }

```

https://developer.android.com/reference/android/graphics/Bitmap.html#recycle()


### onAttachedToWindow

- Gọi sau khi view cha đã gọi addView(View), view con đã được attach vào window

- View con sẽ biết vị trí các view xung quanh nó

- Có thể findViewbyId từ giờ

### onMeasure - để tính toán lại, ước lượng kích thước cho View

Kích thước của view có thể đã được xác định trong file xml hoặc trong code. Nhưng trong TH ta muốn thay đổi nó tùy theo 1 điều kiện nào đó thì ta có thể làm ở đây

Có 3 mode kích thước:

- **MeasureSpec.EXACTLY**: 


```

// Kích thước cụ thể
layout_width= “100dp”

// Match parent 
layout_width=”match_parent”

// Chiếm hết không gian
layout_weight=”1"

```
- **MeasureSpec.AT_MOST**: 

View có thể có max width hoặc height của nó, hoặc được để wrap_content

- **MeasureSpec.UNSPECIFIED**: 
không xác định, layout con kích thước thế nào cũng được. Sử dụng trong ScrollView, ListView.

### onLayout

Chỉ định kích thước và vị trí các view con bên trong custom view

### dispatchDraw - vẽ các view con

### onDraw

Đây là nơi ta sẽ vẽ đối tượng của mình, sử dụng hai đối tượng

- Canvas: đối tượng chứa các phương thức để vẽ text, hình vuông, tròn, ...

- Paint: chứa các phương thức để quy định màu, font, style, ... cho đối tượng tạo ra bởi Canvas


onDraw sẽ được gọi rất nhiều lần, khi có bất kì sự thay đổi nào, vuốt màn hình, kéo, chạm

### invalidate() vs requestLayout()

- **invalidate()** sử dụng được vẽ lại các view đơn giản, trạng thái của view
 Ví dụ khi bạn update lại text, color hay tương tác chạm điểm. Có nghĩa là view chỉ cần đơn giản gọi onDraw() để update lại trạng thái của view. 
Còn có postInvalidate() để vẽ view không phải trên main thread

- **requestLayout()**: vẽ lại view, tính toán lại kích thước. Như ta thấy trong sơ đồ lifecycle thì method này sẽ gọi lại view update từ onMeasure().

### 2. Canvas và Paint

### a. Canvas

Là một bề mặt 2D mà ta có thể vẽ lên bất cứ thứ gì lên đó. Với canvas ta có thể vẽ các đối tượng như sau:

- Các đối tượng hình học cơ bản (point, line, oval, circle, rectangle, ...)

- Vẽ hình ảnh (bitmap, drawable)

- Vẽ Path (tập hợp điểm)

- Vẽ Text (sử dụng TextPaint)

Canvas còn có một số tính năng nâng cao như translate, scale, rolate

- Translate: 

```

// Dịch chuyển gốc tọa độ để vẽ một đoạn dx, dy

translate(float dx, float dy)

```


- Scale: 

```

// Thay đổi kích thước của vật thể, scale lên sx, sy lần

scale(float sx, float sy)

```

- Rotate:

```

// Xoay đối tượng theo 1 góc xác định

rotate(float degrees)

```

- Save: lưu lại trạng thái canvas hiện tại

- Restore: khôi phục lại trạng thái đã save

### b. Paint

Paint dùng để định nghĩa size, color, kiểu nét vẽ mà chúng ta sẽ sử dụng để vẽ bởi canvas (truyền vào method cavas.draw… trong phương thức onDraw của View).

Paint cung cấp cho chúng ta nhiều method:

- setColor: set màu cho nét vẽ

- setStrokeWidth: độ rộng của nét vẽ

- setStyle: style cho nét vẽ

- setStrokeCap: style vẽ ở những điểm kết thúc của đường thẳng

... 

### 3. Interpolator cho animation

Sử dụng đối tượng ValueAnimator: animation cho các thay đổi đơn giản như về kích thước, màu sắc, xoay, ...

Mỗi lần phát ra một giá trị là gọi invalidate() -> onDraw để vẽ lại hình 

```
        val animator = ValueAnimator.ofInt(0, 500)
        animator.duration = 3000
        animator.interpolator = AccelerateInterpolator()
        animator.addUpdateListener { animation ->
            mWidth = animation.animatedValue as Int
            invalidate()
        }
        animator.start()
```

<img src="img/cu1.gif" width="290">

Có nhiều loại Interpolatar để sử dụng

- Accelerate interpolator

- Decelerate interpolator 

- Accelerate decelerate interpolator

- Anticipate interpolator

- Overshoot interpolator 

- Bounce interpolator 

Có thể thêm Interpolator trong file class hoặc trong xml file

Tạo file /res/animator/value_animator_ex.xml

```
<?xml version="1.0" encoding="utf-8"?>
<animator xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="2000"
    android:interpolator="@android:anim/accelerate_decelerate_interpolator"
    android:valueFrom="0f"
    android:valueTo="500f"
    android:valueType="floatType" />

```

```
 private void setUpAnimation(){
        final TextView textAnimation = (TextView) findViewById(R.id.text_animation);
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(
                this, R.animator.value_animator_ex);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                textAnimation.setTranslationY(progress);
            }
        });
        valueAnimator.start();
    }

```






# Training Custom View

## I. Các khái niệm

### 1. View

### a. Khái niệm

Những gì ta thấy trên màn hình thiết bị Android thì được gọi là View

<img src="http://eitguide.net/wp-content/uploads/2016/07/layout-android-0.png" width=280>

Điểm khác biệt là có các thuộc tính mới để đáp ứng nhu cầu của View đó

Các thuộc tính có sẵn:

- Thuộc tính: setText, setTextColor, setBackGroundResource, setTextAllCap, ...

- Focus: xử lý focus dựa vào input của người dùng

- Xử lý sự kiện: như onTouch, onClick, onScoll, onDrag, ...

- Visibility: cho gone, invisible, visible, ...

Còn nhiều thuộc tính khác như Id, padding, margin, ...

### b. View lifecycle

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

https://developer.android.com/reference/android/graphics/Bitmap.html#recycle()

### onAttachedToWindow

- Gọi sau khi view cha đã gọi addView(View), view con đã được attach vào window

- View con sẽ biết vị trí các view xung quanh nó

- Có thể findViewbyId từ giờ

### onMeasure - để tính toán lại, ước lượng kích thước cho View

Kích thước của view có thể đã được xác định trong file xml hoặc trong code. Nhưng trong TH ta muốn thay đổi nó tùy theo 1 điều kiện nào đó thì ta có thể làm ở đây

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





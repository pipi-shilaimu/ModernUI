package flaciber;

import icyllis.modernui.annotation.NonNull;
import icyllis.modernui.annotation.Nullable;
import icyllis.modernui.fragment.Fragment;
import icyllis.modernui.util.DataSet;
import icyllis.modernui.view.*;
import icyllis.modernui.widget.Button;
import icyllis.modernui.widget.FrameLayout;
import icyllis.modernui.widget.TextView;
import icyllis.modernui.graphics.Paint;
import icyllis.modernui.graphics.drawable.ShapeDrawable;
import icyllis.modernui.graphics.Color;

public class MyFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable DataSet savedInstanceState) {

        FrameLayout rootLayout = new FrameLayout(requireContext());
        rootLayout.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        ));


        ShapeDrawable border = new ShapeDrawable();
// 1. 设置形状为矩形 (默认为 RECTANGLE，这行其实可以省)
        border.setShape(ShapeDrawable.RECTANGLE);
// 2. 调用我们在源码第 543 行找到的方法！
// 宽度 5，颜色红色
        border.setStroke(5, Color.DKGRAY);
// 3. (可选) 如果你想加圆角，源码第 429 行告诉我们要用 setCornerRadius
        border.setCornerRadius(10);
// 4. (重要) 默认背景色可能是透明的，但为了保险或者如果你想设个底色
// 源码第 485 行: setColor
        rootLayout.setBackground(border);

        TextView textView = new TextView(requireContext());
        textView.setText("Hello, ModernUI!");
        textView.setTextSize(24);
        textView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER
        ));
        Button button = new Button(requireContext());
        button.setText("Click Me");
        button.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM
        ));
        button.setOnClickListener(v -> textView.setText("Button Clicked!"));
        rootLayout.addView(button);
        rootLayout.addView(textView);
        return rootLayout;
    }
}

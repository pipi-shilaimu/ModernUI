package flaciber;

import icyllis.modernui.ModernUI;
import icyllis.modernui.TestFragment;

public class MyApplication {
    public static void main(String[] args) {
        try (ModernUI app = new ModernUI()) {
            //app.getTheme().applyStyle(R.style.ThemeOverlay_Material3_Dark_Rust, true);
            app.run(new MyFragment());
        }
    }
}

package www.almondsolutions.in.rotatelistview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Neeraj on 09-11-2015.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        init(null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView);
            String fontName = a.getString(R.styleable.MyTextView_fontName);

            if (fontName != null) {
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(typeface);
            }
            a.recycle();
        }
    }
}

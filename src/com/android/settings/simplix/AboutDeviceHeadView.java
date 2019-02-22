package com.android.settings.simplix;

import android.content.Context;
import android.os.SystemProperties;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.settings.R;

public class AboutDeviceHeadView extends RelativeLayout {

    TextView device_name;
    TextView feature_pack;

    public AboutDeviceHeadView(Context context) {
        super(context);
        init(context, null);
    }

    public AboutDeviceHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(final Context context, @Nullable AttributeSet attrs) {
        View base = inflate(context, R.layout.device_about_top, this);
        device_name = base.findViewById(R.id.header_device);
        device_name.setText(SystemProperties.get("ro.product.device"));
	feature_pack = base.findViewById(R.id.header_update);
	feature_pack.setText(SystemProperties.get("com.simplixone.feature_pack"));
    }

}

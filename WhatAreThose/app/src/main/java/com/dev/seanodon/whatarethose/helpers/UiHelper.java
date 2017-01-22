package com.dev.seanodon.whatarethose.helpers;

import android.content.Context;
import android.widget.Toast;

public class UiHelper {
    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}

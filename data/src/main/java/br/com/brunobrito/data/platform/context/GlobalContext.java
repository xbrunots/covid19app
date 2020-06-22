package br.com.brunobrito.data.platform.context;

import android.annotation.SuppressLint;
import android.app.Application;

public class GlobalContext {
    @SuppressLint("PrivateApi")
    public static Application context() throws Exception {
        return (Application) Class.forName("android.app.AppGlobals")
                .getMethod("getInitialApplication").invoke(null, (Object[]) null);
    }
}

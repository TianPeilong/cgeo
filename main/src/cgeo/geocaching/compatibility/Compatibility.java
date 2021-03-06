package cgeo.geocaching.compatibility;

import org.eclipse.jdt.annotation.NonNull;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.widget.TextView;

public final class Compatibility {

    private static final int SDK_VERSION = Build.VERSION.SDK_INT;

    private static final AndroidLevel11Interface LEVEL_11;
    private static final AndroidLevel13Interface LEVEL_13;
    private static final AndroidLevel19Interface LEVEL_19;

    static {
        LEVEL_11 = SDK_VERSION >= 11 ? new AndroidLevel11() : new AndroidLevel11Emulation();
        LEVEL_13 = SDK_VERSION >= 13 ? new AndroidLevel13() : new AndroidLevel13Emulation();
        LEVEL_19 = SDK_VERSION >= 19 ? new AndroidLevel19() : new AndroidLevel19Emulation();
    }

    private Compatibility() {
        // utility class
    }

    public static Point getDisplaySize() {
        return LEVEL_13.getDisplaySize();
    }

    public static void importGpxFromStorageAccessFramework(final @NonNull Activity activity, final int requestCodeImportGpx) {
        LEVEL_19.importGpxFromStorageAccessFramework(activity, requestCodeImportGpx);
    }

    public static boolean isStorageAccessFrameworkAvailable() {
        return SDK_VERSION >= 19;
    }

    public static void setTextIsSelectable(final TextView textView, final boolean selectable) {
        LEVEL_11.setTextIsSelectable(textView, selectable);
    }
}

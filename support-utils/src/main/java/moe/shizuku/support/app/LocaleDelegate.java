package moe.shizuku.support.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.Locale;
import java.util.Objects;


public class LocaleDelegate {

    /** current locale **/
    private static Locale sDefaultLocale = Locale.getDefault();

    /** system locale **/
    private static Locale sSystemLocale = Locale.getDefault();

    /** locale of this instance **/
    private Locale mLocale = Locale.getDefault();

    /**
     * Get default locale stored in this class.
     *
     * @return default locale
     */
    public static Locale getDefaultLocale() {
        return sDefaultLocale;
    }

    /**
     * Set default locale, this will not call {@link Locale#setDefault(Locale)}.
     *
     * @param newLocale new locale
     */
    public static void setDefaultLocale(Locale newLocale) {
        sDefaultLocale = newLocale;
    }

    /**
     * Get system locale stored in this class.
     *
     * @return system locale
     */
    public static Locale getSystemLocale() {
        return sSystemLocale;
    }

    /**
     * Set system locale.
     *
     * @param systemLocale new locale
     */
    public static void setSystemLocale(Locale systemLocale) {
        sSystemLocale = systemLocale;
    }

    /**
     * Return if current locale is different from default.
     * <p>Call this in {@link Activity#onResume()} and if true you should recreate activity.
     *
     * @return locale changed
     */
    public boolean isLocaleChanged() {
        return !Objects.equals(sDefaultLocale, mLocale);
    }

    /**
     * Update locale of given configuration, call in {@link Activity#attachBaseContext(Context)}.
     *
     * @param configuration Configuration
     */
    public void updateConfiguration(Configuration configuration) {
        mLocale = sDefaultLocale;

        configuration.setLocale(mLocale);
    }

    /**
     * A dirty fix for wrong layout direction after switching locale between LTR and RLT language,
     * call in {@link Activity#onCreate(Bundle)}.
     *
     * @param activity Activity
     */
    public void onCreate(Activity activity) {
        activity.getWindow().getDecorView().setLayoutDirection(TextUtils.getLayoutDirectionFromLocale(mLocale));
    }
}

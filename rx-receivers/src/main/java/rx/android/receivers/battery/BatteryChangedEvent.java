package rx.android.receivers.battery;

import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.BatteryManager;
import com.google.auto.value.AutoValue;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import rx.functions.Func1;
import rx.android.receivers.battery.annotation.Status;
import rx.android.receivers.battery.annotation.Health;
import rx.android.receivers.battery.annotation.Plugged;

@AutoValue
public abstract class BatteryChangedEvent {

    @CheckResult @NonNull //
    static Builder builder() {
        return new AutoValue_BatteryChangedEvent.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder health(@NonNull @Health int health);
        abstract Builder smallIcon(@NonNull @DrawableRes int smallIcon);
        abstract Builder level(@NonNull int level);
        abstract Builder plugged(@NonNull @Plugged int plugged);
        abstract Builder present(@NonNull boolean present);
        abstract Builder scale(@NonNull int scale);
        abstract Builder status(@NonNull @Status int status);
        abstract Builder technology(@Nullable String technology);
        abstract Builder temperature(@NonNull int temperature);
        abstract Builder voltage(@NonNull int voltage);

        abstract BatteryChangedEvent build();
    }

    public abstract @NonNull @Health int health();
    public abstract @NonNull @DrawableRes int smallIcon();
    public abstract @NonNull int level();
    public abstract @NonNull @Plugged int plugged();
    public abstract @NonNull boolean present();
    public abstract @NonNull int scale();
    public abstract @NonNull @Status int status();
    public abstract @Nullable String technology();
    public abstract @NonNull int temperature();
    public abstract @NonNull int voltage();

}

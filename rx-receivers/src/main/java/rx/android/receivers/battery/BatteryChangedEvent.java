package rx.android.receivers.battery;

import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;
import rx.android.receivers.battery.annotation.BatteryStatus;
import rx.android.receivers.battery.annotation.BatteryHealth;
import rx.android.receivers.battery.annotation.BatteryPlugged;

@AutoValue
public abstract class BatteryChangedEvent {

    @CheckResult @NonNull
    public static Builder builder() {
        return new AutoValue_BatteryChangedEvent.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder health(@NonNull @BatteryHealth int health);
        public abstract Builder smallIcon(@NonNull @DrawableRes int smallIcon);
        public abstract Builder level(@NonNull int level);
        public abstract Builder plugged(@NonNull @BatteryPlugged int plugged);
        public abstract Builder present(@NonNull boolean present);
        public abstract Builder scale(@NonNull int scale);
        public abstract Builder status(@NonNull @BatteryStatus int status);
        public abstract Builder technology(@Nullable String technology);
        public abstract Builder temperature(@NonNull int temperature);
        public abstract Builder voltage(@NonNull int voltage);

        public abstract BatteryChangedEvent build();
    }

    public abstract @NonNull @BatteryHealth int health();
    public abstract @NonNull @DrawableRes int smallIcon();
    public abstract @NonNull int level();
    public abstract @NonNull @BatteryPlugged int plugged();
    public abstract @NonNull boolean present();
    public abstract @NonNull int scale();
    public abstract @NonNull @BatteryStatus int status();
    public abstract @Nullable String technology();
    public abstract @NonNull int temperature();
    public abstract @NonNull int voltage();

}

package rx.android.receivers.battery.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import android.support.annotation.IntDef;
import android.os.BatteryManager;

@IntDef(value = {
  BatteryManager.BATTERY_STATUS_UNKNOWN,
  BatteryManager.BATTERY_STATUS_CHARGING,
  BatteryManager.BATTERY_STATUS_DISCHARGING,
  BatteryManager.BATTERY_STATUS_NOT_CHARGING,
  BatteryManager.BATTERY_STATUS_FULL
})
@Retention(RetentionPolicy.SOURCE)
public @interface Status {}

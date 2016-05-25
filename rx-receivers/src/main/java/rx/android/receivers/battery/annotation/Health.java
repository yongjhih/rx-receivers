package rx.android.receivers.battery.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import android.support.annotation.IntDef;
import android.os.BatteryManager;

@IntDef(value = {
  BatteryManager.BATTERY_HEALTH_UNKNOWN,
  BatteryManager.BATTERY_HEALTH_GOOD,
  BatteryManager.BATTERY_HEALTH_OVERHEAT,
  BatteryManager.BATTERY_HEALTH_DEAD,
  BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE,
  BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE,
  BatteryManager.BATTERY_HEALTH_COLD
})
@Retention(RetentionPolicy.SOURCE)
public @interface Health {}

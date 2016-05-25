package rx.android.receivers.battery.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import android.support.annotation.IntDef;
import android.os.BatteryManager;

@IntDef(flag = true, value = {
  BatteryManager.BATTERY_PLUGGED_AC,
  BatteryManager.BATTERY_PLUGGED_USB,
  BatteryManager.BATTERY_PLUGGED_WIRELESS
})
@Retention(RetentionPolicy.SOURCE)
public @interface BatteryPlugged {}

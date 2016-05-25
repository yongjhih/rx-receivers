package rx.android.receivers.battery.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import android.support.annotation.StringDef;
import android.os.BatteryManager;

@StringDef(value = {
  BatteryManager.EXTRA_ICON_SMALL,
  BatteryManager.EXTRA_LEVEL,
  BatteryManager.EXTRA_PLUGGED,
  BatteryManager.EXTRA_PRESENT,
  BatteryManager.EXTRA_SCALE,
  BatteryManager.EXTRA_TECHNOLOGY,
  BatteryManager.EXTRA_TEMPERATURE,
  BatteryManager.EXTRA_VOLTAGE,
  BatteryManager.EXTRA_HEALTH,
  BatteryManager.EXTRA_STATUS
})
@Retention(RetentionPolicy.SOURCE)
public @interface BatteryChangedExtra {
}

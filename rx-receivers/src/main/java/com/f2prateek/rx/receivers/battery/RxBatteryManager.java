package com.f2prateek.rx.receivers.battery;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import com.f2prateek.rx.receivers.RxBroadcastReceiver;
import rx.functions.Func1;
import rx.Observable;
import static com.f2prateek.rx.receivers.internal.Preconditions.checkNotNull;

public class RxBatteryManager {
  private RxBatteryManager() {
    throw new AssertionError("no instances");
  }

  @CheckResult @NonNull //
  public static Observable<Integer> changed(@NonNull final Context context, @NonNull final String key) {
    checkNotNull(context, "context == null");
    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    return RxBroadcastReceiver.create(context, filter).map(new Func1<Intent, Integer>() {
      @Override public Integer call(Intent intent) {
        return intent.getIntExtra(key, -1);
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> health(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_HEALTH);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> iconSmall(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_ICON_SMALL);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> level(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_LEVEL);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> plugged(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_PLUGGED);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> present(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_PRESENT);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> scale(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_SCALE);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> status(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_STATUS);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> technology(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_TECHNOLOGY);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> temperature(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_TEMPERATURE);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> voltage(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_VOLTAGE);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> discharging(@NonNull final Context context) {
    return status(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_STATUS_DISCHARGING;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> unknown(@NonNull final Context context) {
    return status(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_STATUS_UNKNOWN;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> charging(@NonNull final Context context) {
    return status(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_STATUS_CHARGING;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> notCharging(@NonNull final Context context) {
    return status(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_STATUS_NOT_CHARGING;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> full(@NonNull final Context context) {
    return status(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_STATUS_FULL;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> unknownHealth(@NonNull final Context context) {
    return health(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_HEALTH_UNKNOWN;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> good(@NonNull final Context context) {
    return health(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_HEALTH_GOOD;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> overheat(@NonNull final Context context) {
    return health(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_HEALTH_OVERHEAT;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> dead(@NonNull final Context context) {
    return health(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_HEALTH_DEAD;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> overVoltage(@NonNull final Context context) {
    return health(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> unspecifiedFailure(@NonNull final Context context) {
    return health(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> cold(@NonNull final Context context) {
    return health(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_HEALTH_COLD;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> wireless(@NonNull final Context context) {
    return plugged(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_PLUGGED_WIRELESS;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> ac(@NonNull final Context context) {
    return plugged(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_PLUGGED_WIRELESS;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> usb(@NonNull final Context context) {
    return plugged(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(Integer i) {
        return i == BatteryManager.BATTERY_PLUGGED_USB;
      }
    });
  }
}

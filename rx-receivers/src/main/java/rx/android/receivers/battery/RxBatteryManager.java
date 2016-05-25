package rx.android.receivers.battery;

import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import rx.android.receivers.RxBroadcastReceiver;
import rx.functions.Func1;
import rx.Observable;
import static rx.android.receivers.internal.Preconditions.checkNotNull;
import rx.android.receivers.battery.annotation.BatteryStatus;
import rx.android.receivers.battery.annotation.BatteryPlugged;
import rx.android.receivers.battery.annotation.BatteryChangedExtra;

public class RxBatteryManager {
  private RxBatteryManager() {
    throw new AssertionError("no instances");
  }

  @CheckResult @NonNull //
  public static Observable<Intent> changed(@NonNull final Context context) {
    checkNotNull(context, "context == null");
    return RxBroadcastReceiver.create(context, Intent.ACTION_BATTERY_CHANGED);
  }

  @CheckResult @NonNull //
  public static Observable<BatteryChangedEvent> changes(@NonNull final Context context) {
    checkNotNull(context, "context == null");
    return changed(context).map(new Func1<Intent, BatteryChangedEvent>() {
      @Override public BatteryChangedEvent call(Intent intent) {
          return BatteryChangedEvent.builder()
              .health(intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN))
              .smallIcon(intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, -1))
              .level(intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1))
              .plugged(intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1))
              .present(intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false))
              .scale(intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1))
              .status(intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN))
              .technology(intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY))
              .temperature(intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1))
              .voltage(intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1))
              .build();
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> changed(@NonNull final Context context,
      @NonNull @BatteryChangedExtra final String extra,
      @NonNull final int defValue) {
    checkNotNull(context, "context == null");
    checkNotNull(extra, "extra == null");
    checkNotNull(defValue, "defValue == null");
    return RxBroadcastReceiver.create(context, Intent.ACTION_BATTERY_CHANGED, extra, defValue);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Boolean> changed(@NonNull final Context context,
      @NonNull @BatteryChangedExtra final String extra,
      @NonNull final boolean defValue) {
    checkNotNull(context, "context == null");
    checkNotNull(extra, "extra == null");
    checkNotNull(defValue, "defValue == null");
    return RxBroadcastReceiver.create(context, Intent.ACTION_BATTERY_CHANGED, extra, defValue);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<String> changedString(@NonNull final Context context,
      @NonNull @BatteryChangedExtra final String extra) {
    checkNotNull(context, "context == null");
    checkNotNull(extra, "extra == null");
    return RxBroadcastReceiver.createString(context, Intent.ACTION_BATTERY_CHANGED, extra);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> health(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> smallIcon(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_ICON_SMALL, -1);
  }

  /**
   * Determine the Current Battery Level.
   *
   * You can't easily continually monitor the battery state, but you don't need to.
   * Generally speaking, the impact of constantly monitoring the battery level has a greater impact on the battery
   * than your app's normal behavior, so it's good practice to only monitor significant changes
   * in battery level—specifically when the device enters or exits a low battery state.
   *
   * The manifest snippet below is extracted from the intent filter element within a broadcast receiver.
   * The receiver is triggered whenever the device battery becomes low or
   * exits the low condition by listening for
   *
   *     &lt;action android:name="android.intent.action.ACTION_BATTERY_LOW" /&gt;
   *     &lt;action android:name="android.intent.action.ACTION_BATTERY_OKAY" /&gt;
   *
   * It is generally good practice to disable all your background updates when the battery is critically low.
   * It doesn't matter how fresh your data is if the phone turns itself off before you can make use of it.
   * In many cases, the act of charging a device is coincident with putting it into a dock.
   * The next lesson shows you how to determine the current dock state and monitor for changes in device docking.
   */
  @CheckResult @NonNull
  public static Observable<Integer> level(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_LEVEL, -1);
  }

  /**
   * TODO: docs.
   *
   *     &lt;action android:name="android.intent.action.ACTION_POWER_CONNECTED" /&gt;
   *     &lt;action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" /&gt;
   *
   */
  @CheckResult @NonNull
  public static Observable<Integer> plugged(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_PLUGGED, -1);
  }

  /**
   * TODO: docs.
   *
   *     &lt;action android:name="android.intent.action.ACTION_POWER_CONNECTED" /&gt;
   *     &lt;action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" /&gt;
   *
   */
  @CheckResult @NonNull
  public static Observable<Integer> ac(@NonNull final Context context) {
    return plugged(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(@BatteryPlugged Integer i) {
          return BatteryManager.BATTERY_PLUGGED_AC == i;
      }
    });
  }

  /**
   * TODO: docs.
   *
   *     &lt;action android:name="android.intent.action.ACTION_POWER_CONNECTED" /&gt;
   *     &lt;action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" /&gt;
   *
   */
  @CheckResult @NonNull
  public static Observable<Integer> usb(@NonNull final Context context) {
    return plugged(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(@BatteryPlugged Integer i) {
          return BatteryManager.BATTERY_PLUGGED_USB == i;
      }
    });
  }

  /**
   * TODO: docs.
   *
   *     &lt;action android:name="android.intent.action.ACTION_POWER_CONNECTED" /&gt;
   *     &lt;action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" /&gt;
   *
   */
  @CheckResult @NonNull
  public static Observable<Integer> wireless(@NonNull final Context context) {
    return plugged(context).filter(new Func1<Integer, Boolean>() {
      @Override public Boolean call(@BatteryPlugged Integer i) {
          return BatteryManager.BATTERY_PLUGGED_WIRELESS == i;
      }
    });
  }

  /**
   * TODO: docs.
   *
   *     &lt;action android:name="android.intent.action.ACTION_POWER_CONNECTED" /&gt;
   *     &lt;action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" /&gt;
   *
   */
  @CheckResult @NonNull
  public static Observable<Boolean> charging(@NonNull final Context context) {
    return status(context).exists(new Func1<Integer, Boolean>() {
      @Override public Boolean call(@BatteryStatus Integer i) {
          return BatteryManager.BATTERY_STATUS_CHARGING == i || BatteryManager.BATTERY_STATUS_FULL == i;
      }
    });
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Boolean> present(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_PRESENT, false);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> scale(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_SCALE, -1);
  }

  /**
   * TODO: docs.
   *
   *     &lt;action android:name="android.intent.action.ACTION_POWER_CONNECTED" /&gt;
   *     &lt;action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" /&gt;
   *
   */
  @CheckResult @NonNull
  public static Observable<Integer> status(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<String> technology(@NonNull final Context context) {
    return changedString(context, BatteryManager.EXTRA_TECHNOLOGY);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> temperature(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_TEMPERATURE, -1);
  }

  /** TODO: docs. */
  @CheckResult @NonNull //
  public static Observable<Integer> voltage(@NonNull final Context context) {
    return changed(context, BatteryManager.EXTRA_VOLTAGE, -1);
  }
}

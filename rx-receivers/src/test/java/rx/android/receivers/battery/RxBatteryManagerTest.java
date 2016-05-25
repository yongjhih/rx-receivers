package rx.android.receivers.battery;

import android.app.Application;
import android.content.Intent;
import android.os.BatteryManager;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import rx.functions.Func1;
import rx.functions.Action0;
import static rx.assertions.RxAssertions.assertThat;

@RunWith(RobolectricTestRunner.class) //
public class RxBatteryManagerTest {
  @Test public void changed() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changed(application).map(new Func1<Intent, Integer>() {
      @Override public Integer call(Intent intent) {
        return intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_AC));
      }
    }).expectedValues(BatteryManager.BATTERY_PLUGGED_AC).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_USB));
      }
    }).expectedValues(BatteryManager.BATTERY_PLUGGED_AC, BatteryManager.BATTERY_PLUGGED_USB);
  }

  @Test public void health() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changes(application).map(new Func1<BatteryChangedEvent, Integer>() {
      @Override public Integer call(BatteryChangedEvent changes) {
        return changes.health();
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN));
      }
    }).expectedValues(BatteryManager.BATTERY_HEALTH_UNKNOWN).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_COLD));
      }
    }).expectedValues(BatteryManager.BATTERY_HEALTH_UNKNOWN, BatteryManager.BATTERY_HEALTH_COLD);
  }

  @Test public void healthSimple() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.health(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN));
      }
    }).expectedValues(BatteryManager.BATTERY_HEALTH_UNKNOWN).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_COLD));
      }
    }).expectedValues(BatteryManager.BATTERY_HEALTH_UNKNOWN, BatteryManager.BATTERY_HEALTH_COLD);
  }

  @Test public void smallIcon() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changes(application).map(new Func1<BatteryChangedEvent, Integer>() {
      @Override public Integer call(BatteryChangedEvent changes) {
        return changes.smallIcon();
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_ICON_SMALL, 0));
      }
    }).expectedValues(0).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_ICON_SMALL, 1));
      }
    }).expectedValues(0, 1);
  }

  @Test public void smallIconSimple() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.smallIcon(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_ICON_SMALL, 0));
      }
    }).expectedValues(0).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_ICON_SMALL, 1));
      }
    }).expectedValues(0, 1);
  }

  @Test public void level() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changes(application).map(new Func1<BatteryChangedEvent, Integer>() {
      @Override public Integer call(BatteryChangedEvent changes) {
        return changes.level();
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_LEVEL, 0));
      }
    }).expectedValues(0).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_LEVEL, 1));
      }
    }).expectedValues(0, 1);
  }

  @Test public void levelSimple() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.level(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_LEVEL, 0));
      }
    }).expectedValues(0).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_LEVEL, 1));
      }
    }).expectedValues(0, 1);
  }

  @Test public void plugged() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changes(application).map(new Func1<BatteryChangedEvent, Integer>() {
      @Override public Integer call(BatteryChangedEvent changes) {
        return changes.plugged();
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_AC));
      }
    }).expectedValues(BatteryManager.BATTERY_PLUGGED_AC).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_USB));
      }
    }).expectedValues(BatteryManager.BATTERY_PLUGGED_AC, BatteryManager.BATTERY_PLUGGED_USB);
  }

  @Test public void pluggedSimple() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.plugged(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_AC));
      }
    }).expectedValues(BatteryManager.BATTERY_PLUGGED_AC).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_USB));
      }
    }).expectedValues(BatteryManager.BATTERY_PLUGGED_AC, BatteryManager.BATTERY_PLUGGED_USB);
  }

  @Test public void ac() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.ac(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_AC));
      }
    }).expectedValues(BatteryManager.BATTERY_PLUGGED_AC).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_USB));
      }
    }).expectedValues(BatteryManager.BATTERY_PLUGGED_AC);
  }

  @Test public void usb() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.usb(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_AC));
      }
    }).expectedValues().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_USB));
      }
    }).expectedValues(BatteryManager.BATTERY_PLUGGED_USB);
  }

  @Test public void wireless() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.wireless(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_AC));
      }
    }).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_WIRELESS));
      }
    }).expectedValues(BatteryManager.BATTERY_PLUGGED_WIRELESS);
  }

  @Test public void present() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changes(application).map(new Func1<BatteryChangedEvent, Boolean>() {
      @Override public Boolean call(BatteryChangedEvent changes) {
        return changes.present();
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PRESENT, false));
      }
    }).expectedValues(false).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PRESENT, true));
      }
    }).expectedValues(false, true);
  }

  @Test public void presentSimple() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.present(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PRESENT, false));
      }
    }).expectedValues(false).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_PRESENT, true));
      }
    }).expectedValues(false, true);
  }

  @Test public void scale() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changes(application).map(new Func1<BatteryChangedEvent, Integer>() {
      @Override public Integer call(BatteryChangedEvent changes) {
        return changes.scale();
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_SCALE, 0));
      }
    }).expectedValues(0).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_SCALE, 1));
      }
    }).expectedValues(0, 1);
  }

  @Test public void scaleSimple() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.scale(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_SCALE, 0));
      }
    }).expectedValues(0).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_SCALE, 1));
      }
    }).expectedValues(0, 1);
  }

  @Test public void status() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changes(application).map(new Func1<BatteryChangedEvent, Integer>() {
      @Override public Integer call(BatteryChangedEvent changes) {
        return changes.status();
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN));
      }
    }).expectedValues(BatteryManager.BATTERY_STATUS_UNKNOWN).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_CHARGING));
      }
    }).expectedValues(BatteryManager.BATTERY_STATUS_UNKNOWN,
        BatteryManager.BATTERY_STATUS_CHARGING);
  }

  @Test public void statusSimple() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.status(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN));
      }
    }).expectedValues(BatteryManager.BATTERY_STATUS_UNKNOWN).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_CHARGING));
      }
    }).expectedValues(BatteryManager.BATTERY_STATUS_UNKNOWN,
        BatteryManager.BATTERY_STATUS_CHARGING);
  }

  @Test public void charging() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.charging(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN));
      }
    }).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_CHARGING));
      }
    }).expectedValues(true);
  }

  @Test public void technology() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changes(application).map(new Func1<BatteryChangedEvent, String>() {
      @Override public String call(BatteryChangedEvent changes) {
        return changes.technology();
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_TECHNOLOGY, ""));
      }
    }).expectedValues("").then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_TECHNOLOGY, "Li-ion"));
      }
    }).expectedValues("", "Li-ion");
  }

  @Test public void technologySimple() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.technology(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_TECHNOLOGY, ""));
      }
    }).expectedValues("").then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_TECHNOLOGY, "Li-ion"));
      }
    }).expectedValues("", "Li-ion");
  }

  @Test public void temperature() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changes(application).map(new Func1<BatteryChangedEvent, Integer>() {
      @Override public Integer call(BatteryChangedEvent changes) {
        return changes.temperature();
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_TEMPERATURE, 0));
      }
    }).expectedValues(0).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_TEMPERATURE, 1));
      }
    }).expectedValues(0, 1);
  }

  @Test public void temperatureSimple() {

    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.temperature(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_TEMPERATURE, 0));
      }
    }).expectedValues(0).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_TEMPERATURE, 1));
      }
    }).expectedValues(0, 1);
  }

  @Test public void voltage() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.changes(application).map(new Func1<BatteryChangedEvent, Integer>() {
      @Override public Integer call(BatteryChangedEvent changes) {
        return changes.voltage();
      }
    })).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_VOLTAGE, 0));
      }
    }).expectedValues(0).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_VOLTAGE, 1));
      }
    }).expectedValues(0, 1);
  }

  @Test public void voltageSimple() {
    final Application application = RuntimeEnvironment.application;

    assertThat(RxBatteryManager.voltage(application)).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_VOLTAGE, 0));
      }
    }).expectedValues(0).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(new Intent(Intent.ACTION_BATTERY_CHANGED)
            .putExtra(BatteryManager.EXTRA_VOLTAGE, 1));
      }
    }).expectedValues(0, 1);
  }
}

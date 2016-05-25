package rx.android.receivers;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import rx.android.receivers.RxBroadcastReceiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import rx.Subscription;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import rx.assertions.RxAssertions;
import rx.functions.Action0;

@RunWith(RobolectricTestRunner.class) //
public class RxBroadcastReceiverTest {
  @Test(expected=IllegalArgumentException.class)
  public void createWithNullThrows() {
      RxBroadcastReceiver.create(null, (IntentFilter) null);
  }

  @Test public void subscribe() {
    IntentFilter intentFilter = new IntentFilter("test_action");
    Application application = RuntimeEnvironment.application;

    TestSubscriber<Intent> o = new TestSubscriber<>();
    Subscription subscription = RxBroadcastReceiver.create(application, intentFilter).subscribe(o);
    o.assertValues();

    Intent intent1 = new Intent("test_action").putExtra("foo", "bar");
    application.sendBroadcast(intent1);
    o.assertValues(intent1);

    Intent intent2 = new Intent("test_action").putExtra("bar", "baz");
    application.sendBroadcast(intent2);
    o.assertValues(intent1, intent2);

    Intent intent3 = new Intent("test_action_ignored");
    application.sendBroadcast(intent3);
    o.assertValues(intent1, intent2);

    Intent intent4 = new Intent("test_action").putExtra("bar", "baz");
    subscription.unsubscribe();
    application.sendBroadcast(intent4);
    o.assertValues(intent1, intent2);
  }

  @Test public void subscribeRxAssertions() {
    final Application application = RuntimeEnvironment.application;
    final Intent intent1 = new Intent("test_action").putExtra("foo", "bar");
    final Intent intent2 = new Intent("test_action").putExtra("bar", "baz");
    final Intent intent3 = new Intent("test_action_ignored");
    final Intent intent4 = new Intent("test_action").putExtra("bar", "baz");
    RxAssertions.assertThat(RxBroadcastReceiver.create(application, new IntentFilter("test_action"))).emitsNothing().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(intent1);
      }
    }).expectedValues(intent1).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(intent2);
      }
    }).expectedValues(intent1, intent2).then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(intent3);
      }
    }).expectedValues(intent1, intent2).unsubscribe().then(new Action0() {
      @Override public void call() {
        application.sendBroadcast(intent4);
      }
    }).expectedValues(intent1, intent2);
  }
}

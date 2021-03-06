package rx.android.receivers.internal;

public final class Preconditions {
  public static void checkNotNull(Object o, String failureMessage) {
    if (o == null) {
      throw new IllegalArgumentException(failureMessage);
    }
  }

  private Preconditions() {
    throw new AssertionError("no instances");
  }
}

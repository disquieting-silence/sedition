package dsq.sedition.util;

public class None<A> implements Option<A> {
    @Override
    public boolean isSet() {
        return false;
    }

    @Override
    public A getOrDie() {
        throw new RuntimeException("Option is not set");
    }

    @Override
    public A getOr(final A a) {
        return a;
    }
}

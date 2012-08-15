package dsq.sedition.util;

public class Some<A> implements Option<A> {
    
    private final A value;

    public Some(final A value) {
        this.value = value;
    }

    @Override
    public boolean isSet() {
        return true;
    }

    @Override
    public A getOrDie() {
        return value;
    }

    @Override
    public A getOr(final A a) {
        return value;
    }
}

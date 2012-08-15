package dsq.sedition.util;

// FIX 15/08/12 This isn't a functional option ... just the bare minimum to not return null
public interface Option<A> {
    boolean isSet();
    A getOrDie();
    A getOr(A a);
}

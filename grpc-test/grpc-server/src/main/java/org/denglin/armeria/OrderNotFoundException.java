package org.denglin.armeria;

public final class OrderNotFoundException extends IllegalStateException {

    OrderNotFoundException(String s) {
        super(s);
    }
}

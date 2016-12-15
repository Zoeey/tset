package model.gameInfo.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Alpi
 * @since 31.10.16
 */
public class SequentialIDGenerator {
    private final AtomicInteger current = new AtomicInteger(0);
    public int next() {
        return current.getAndIncrement();
    }
}

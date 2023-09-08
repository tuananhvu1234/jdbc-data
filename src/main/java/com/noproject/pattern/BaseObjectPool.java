/*
 * 
 */
package com.noproject.pattern;

import com.noproject.jdbc.connection.AbstractObjectPool;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author No
 * @param <T>
 */
public abstract class BaseObjectPool<T> {

    private Map<T, Long> available, using;
    private int countObject = 1;
    private long expirationTime = 5000;
    private long waitingTime = 1000;

    public BaseObjectPool(int count) {
        available = Collections.synchronizedMap(new HashMap<>());
        using = Collections.synchronizedMap(new HashMap<>());
        if (count > 0) {
            this.countObject = count;
        }
    }

    public BaseObjectPool(int count, long expTime) {
        this(count);
        if (expTime > 0) {
            this.expirationTime = expTime;
        }
    }

    public BaseObjectPool(int count, long expTime, long waitTime) {
        this(count, expTime);
        if (waitTime > 0) {
            this.waitingTime = waitTime;
        }
    }

    protected abstract T create();

    public abstract boolean isStillUsable(T o);

    public abstract void actionWhenExpire(T o);

    public abstract void actionWhileWaiting();

    public synchronized final T get() {
        if (getCountUsingObjects() == countObject) {
            actionWhileWaiting();
            waitUntilUsing();
            return get();
        }
        long now = System.currentTimeMillis();
        T t;
        if (getCountAvailableObjects() > 0) {
            t = available.keySet().iterator().next();
            if (objectCanUse(t) == false) {
                available.remove(t);
                return get();
            }
            available.remove(t);
            using.put(t, now);
            return t;
        }
        t = create();
        using.put(t, now);
        return t;
    }

    public synchronized final void release(T t) {
        using.remove(t);
        if (objectCanUse(t) == true) {
            available.put(t, System.currentTimeMillis());
        } else {
            actionWhenExpire(t);
        }
    }

    public synchronized final boolean shutdown() {
        available.clear();
        using.clear();
        using = available = null;
        return using == null && available == null;
    }

    public final int getCountUsingObjects() {
        return using.size();
    }

    public final int getCountAvailableObjects() {
        return available.size();
    }

    private boolean objectCanUse(T t) {
        if (isStillUsable(t) == false) {
            if (isExpired(available, t) == true
                    || isExpired(using, t) == true) {
                return false;
            }
        }
        return true;
    }

    private boolean isExpired(Map<T, Long> map, T t) {
        final long now = System.currentTimeMillis();
        if (map.isEmpty() == false) {
            if ((now - map.get(t)) > this.expirationTime) {
                return true;
            }
        }
        return false;
    }

    private void waitUntilUsing() {
        try {
            TimeUnit.MILLISECONDS.sleep(this.waitingTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractObjectPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

package com.noproject.jdbc.connection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 * @param <T>
 */
public abstract class AbstractObjectPool<T> {

    private final Map<T, Long> available, using;
    private long expirationTime = 5000;// 5s

    private int countObject = 0;
    private long waitingTime = 1000;// 1s

    public AbstractObjectPool() {
        available = Collections.synchronizedMap(new HashMap<>());
        using = Collections.synchronizedMap(new HashMap<>());
    }

    public AbstractObjectPool(long expirationTime) {
        available = Collections.synchronizedMap(new HashMap<>());
        using = Collections.synchronizedMap(new HashMap<>());
        this.expirationTime = expirationTime;
    }

    /**
     * Tạo ra đối tượng cần sử dụng.
     *
     * @return
     */
    protected abstract T create();

    /**
     * Kiểm tra xem đối tượng còn khả năng sử dụng hay không.
     *
     * @param o
     * @return
     */
    public abstract boolean validate(T o);

    /**
     * Hành động cần làm khi đối tượng không còn thời gian tồn tại.
     *
     * @param o
     */
    public abstract void expire(T o);

    /**
     * Các hành động muốn làm trong khi chờ.
     */
    public abstract void actionWhileWaiting();

    /**
     * Lấy ra hoặc tạo mới đối tượng và sử dụng.
     *
     * @return
     */
    public final synchronized T getAndUsing() {
        long now = System.currentTimeMillis();
        T t;
        if (countObject > 0
                && getNumberUsingObject() == countObject) {
            actionWhileWaiting();
            waitUntilUsing();
            return getAndUsing();
        }
        if (getNumberFreeObject() > 0) {
            t = available.keySet().iterator().next();
            available.remove(t);
            using.put(t, now);
            return t;
        }
        t = create();
        using.put(t, now);
        return t;
    }

    /**
     * Hoàn trả đối tượng về đợi tái sử dụng.
     *
     * @param t
     */
    public final synchronized void release(T t) {
        using.remove(t);
        available.put(t, System.currentTimeMillis());
    }

    /**
     * Lấy ra số lượng đối tượng chưa được sử dụng.
     *
     * @return Số lượng đối tượng chưa được sử dụng.
     */
    public final int getNumberFreeObject() {
        for (T t : available.keySet()) {
            if (canUse(t) == false) {
                expire(t);
                available.remove(t);
            }
        }
        return available.size();
    }

    /**
     * Lấy ra số lượng đối tượng đang được sử dụng.
     *
     * @return Số lượng đối tượng đang được sử dụng.
     */
    public final int getNumberUsingObject() {
        return using.size();
    }

    // Kiểm tra đối tượng còn khả năng sử dụng lại không.
    private boolean canUse(T t) {
        long now = System.currentTimeMillis();
        if (((now - available.get(t)) > expirationTime)
                || (validate(t) == false)) {
            return false;
        }
        return true;
    }

    // Giới hạn số lượng đối tượng có thể tạo ra.
    protected final void limitCountObject(int total) {
        this.countObject = total;
    }

    // Thời gian chờ tái sử dụng đối tượng.
    protected final void waiting(long waitingTime) {
        this.waitingTime = waitingTime;
    }

    private void waitUntilUsing() {
        try {
            TimeUnit.MILLISECONDS.sleep(this.waitingTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(AbstractObjectPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

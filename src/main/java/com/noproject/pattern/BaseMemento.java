/*
 *
 */
package com.noproject.pattern;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author No
 * @param <E>
 */
public class BaseMemento<E> {

    private E currentObject;
    private final List<E> canUseObjects;
    private final List<E> removedObjects;
    private boolean wasUndo = false;

    public BaseMemento(E first) {
        canUseObjects = Collections.synchronizedList(new LinkedList<>());
        removedObjects = Collections.synchronizedList(new LinkedList<>());
        currentObject = first;
        addElement(canUseObjects, currentObject);
    }

    private boolean addElement(List<E> list, E e) {
        if (e != null) {
            list.add(0, e);
            return list.get(0).equals(e);
        }
        return false;
    }

    public final boolean save(E e) {
        if (e.equals(canUseObjects.get(0)) == false) {
            if (wasUndo == true) {
                removedObjects.clear();
            }
            currentObject = e;
            return addElement(canUseObjects, currentObject);
        }
        return false;
    }

    public final E rollback() {
        E beforeRollback = currentObject;
        currentObject = undo();
        save(currentObject);
        if (removedObjects.get(0).equals(beforeRollback)) {
            removedObjects.remove(0);
        }
        return beforeRollback;
    }

    public final E restore(E e) {
        if (canUseObjects.contains(e) == true) {
            currentObject = e;
            save(currentObject);
        }
        return currentObject;
    }

    public final E undo() {
        if (canUseObjects.size() > 1) {
            if (addElement(removedObjects, currentObject) == true
                    && canUseObjects.remove(0).equals(currentObject) == true) {
                currentObject = canUseObjects.get(0);
            }
        }
        wasUndo = true;
        return currentObject;
    }

    public final E redo() {
        if (removedObjects.isEmpty() == false) {
            addElement(canUseObjects, removedObjects.remove(0));
            currentObject = canUseObjects.get(0);
        }
        return currentObject;
    }

    public final E getCurrentObject() {
        return currentObject;
    }

}

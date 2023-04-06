/*
 *
 */
package com.noproject.pattern;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
save :
    +) Thêm đối tượng vào một list chứa các đối tượng đã từng được lưu lại.
    +) Không thể xóa đối tượng trong list saved 
restore :
    +) Đưa đối tượng hiện tại về một phiên bản đã từng tồn tại trước đó.
    +) Sau khi restore thì có thể dùng undo để đưa đối tượng hiện tại về đối tượng trước khi restore.
undo :
    +) Quay về phiên bản trước khi thay đổi của đối tượng.
    +) Xóa bỏ hành động được thực hiện trước đó.
    +) Không thể xóa bỏ save.
redo :
    +) Thêm lại hành động đã bị undo trước đó.

 */
/**
 * Insert, Read. Not UPDATE, NOT DELETE.
 *
 * @author DELL
 * @param <E>
 */
public class BaseMemento<E> {

    // Tên các hành động có thể thực hiện.
    private static enum ActionType {
        SAVE, RESTORE, UNDO, REDO
    }

    private static class ActionHandle<E> {

        private final List<ActionType> listTakenActionsKey;
        private final List<E> listTakenActionsValue;
        private final List<String> listTakenActionsTime;

        public ActionHandle() {
            listTakenActionsKey = new LinkedList<>();
            listTakenActionsValue = new LinkedList<>();
            listTakenActionsTime = new LinkedList<>();
        }

        private boolean addAction(ActionType action, E value) {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
            if (listTakenActionsKey.add(action) == true
                    && listTakenActionsValue.add(value) == true
                    && listTakenActionsTime.add(dateTime.format(format)) == true) {
                return true;
            }
            return false;
        }

        private List<String> getAllActions() {
            List<String> result = new LinkedList<>();
            for (int i = 0; i < listTakenActionsKey.size(); i++) {
                listTakenActionsKey.get(i);
                listTakenActionsValue.get(i);
                listTakenActionsTime.get(i);
                result.add("{Action=" + listTakenActionsKey.get(i)
                        + ", Object=" + listTakenActionsValue.get(i)
                        + ", At=" + listTakenActionsTime.get(i) + "}");
            }
            return result;
        }

        private Object[] getLastAction() {
            ActionType lastKey = null;
            E lastValue = null;
            String lastTime = "";
            if (listTakenActionsKey.isEmpty() == false
                    && listTakenActionsValue.isEmpty() == false
                    && listTakenActionsTime.isEmpty() == false) {
                int lastIndex;
                if (listTakenActionsKey.size() > 1) {
                    lastIndex = listTakenActionsKey.size() - 1;
                } else {
                    lastIndex = 0;
                }
                lastKey = listTakenActionsKey.get(lastIndex);
                lastValue = listTakenActionsValue.get(lastIndex);
                lastTime = listTakenActionsTime.get(lastIndex);
            }
            return new Object[]{lastKey, lastValue, lastTime};
        }
    }
    private E currentObject; // Đối tượng hiện tại đang được sử dụng.
    private final List<E> listSavedObjects; // Danh sách các đối tượng đã được lưu lại.
    private final List<E> listObjectsBeforeChange; // Danh sách lưu lại đối tượng hiện tại trước khi thay đổi.

    ActionHandle<E> actionHandle = new ActionHandle<>();

    public BaseMemento() {
        listSavedObjects = new LinkedList<>();
        listObjectsBeforeChange = new LinkedList<>();
        currentObject = null;
    }

    /**
     * Lưu lại object.
     *
     * @param e
     * @return
     */
    // Nếu chỉ save bình thường thì listOld sẽ add giá trị cũ của current
    // Nếu đã restore rồi save thì list old không cần add vào.
    // Nếu 
    public final boolean save(E e) {
        if (actionHandle.getLastAction()[0] != ActionType.RESTORE
                && actionHandle.getLastAction()[0] != null) {
            listObjectsBeforeChange.add(0, currentObject);
        }
        currentObject = e;
        listSavedObjects.add(currentObject);
        return actionHandle.addAction(ActionType.SAVE, currentObject);
    }

    //rollback : Hoàn tác về object phía trước object hiện tại và xóa đi object hiện tại.
    /**
     * Hoàn tác về object chỉ định.
     *
     *
     * @param e
     * @return
     */
    public final E restore(E e) {
        if (listSavedObjects.contains(e) == true) {
            listObjectsBeforeChange.add(0, currentObject);
            currentObject = e;
            if (actionHandle.addAction(ActionType.RESTORE, currentObject) == true) {
                return currentObject;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * Hoàn tác về thao tác trước đó. Vẫn lưu lại thao tác hiện tại.
     *
     * @return
     */
    public final E undo() {
        E beforeUndo = currentObject;
        currentObject = listObjectsBeforeChange.get(0);
        listObjectsBeforeChange.remove(0);
        if (actionHandle.addAction(ActionType.UNDO, beforeUndo) == true) {
            return currentObject;
        }
        return null;
    }

    /**
     * Hoàn tác thao tác trước đó. Chỉ chạy nếu đã từng undo.
     *
     * @return
     */
    public final E redo() {
        Object[] lastAction = actionHandle.getLastAction();
        if (lastAction[0] == ActionType.UNDO) {
            E beforeRedo = currentObject;
            listObjectsBeforeChange.add(0, beforeRedo);
            currentObject = (E) lastAction[1];
            if (actionHandle.addAction(ActionType.REDO, beforeRedo) == true) {
                return currentObject;
            }
        }
        return null;
    }

    /**
     * Lấy ra vị trí của object cuối cùng.
     *
     * @return
     */
    public final int getLastIndex() {
//        return listSavedObject.size() - 1;
        return 0;
    }

    /**
     * Lấy ra tất cả object đã lưu.
     *
     * @return
     */
    public final List<E> getAllSavedObject() {
        System.out.println("list before change = " + listObjectsBeforeChange);
        System.out.println("list action = " + Arrays.toString(actionHandle.getLastAction()));
        return listSavedObjects;
    }

    /**
     * Lấy ra object được lưu đầu tiên.
     *
     * @return
     */
    public final E getFirstObject() {
//        return getLastIndex() < 0
//                ? null : listSavedObject.get(0);
        return null;
    }

    /**
     * Lấy ra object được lưu cuối cùng.
     *
     * @return
     */
    public final E getLastObject() {
//        return getLastIndex() < 0
//                ? null : listSavedObject.get(getLastIndex());
        return null;
    }

    /**
     * Lấy ra object tại vị trí chỉ định.
     *
     * @param index
     * @return
     */
    public final E getObjectAt(int index) {
        if (index < 0) {
            return getFirstObject();
        } else if (index > getLastIndex()) {
            return getLastObject();
        } else {
//            return listSavedObject.get(index);
            return null;
        }
    }

    /**
     * Lấy ra object đang sử dụng.
     *
     * @return
     */
    public final E getCurrentObject() {
        return currentObject;
    }

}

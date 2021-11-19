package com.ab.gray.property;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: DynamicGrayProperty
 */
public class DynamicGrayProperty<T> implements GrayProperty<T> {

    protected Set<PropertyListener<T>> listeners = Collections.synchronizedSet(new HashSet());
    private T value = null;

    public DynamicGrayProperty() {
    }

    public DynamicGrayProperty(T value) {
        this.value = value;
    }


    public void addListener(PropertyListener listener) {
        this.listeners.add(listener);
        listener.configLoad(this.value);
    }


    public void removeListener(PropertyListener listener) {
        this.listeners.remove(listener);
    }


    public boolean updateValue(T newValue) {
        if (isEqual(this.value, newValue)) {
            return false;
        }
        this.value = newValue;
        Iterator iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            PropertyListener<T> listener = (PropertyListener) iterator.next();
            listener.configUpdate(newValue);
        }
        return true;
    }

    private boolean isEqual(T oldValue, T newValue) {
        if (oldValue == null && newValue == null) {
            return true;
        } else {
            return oldValue == null ? false : oldValue.equals(newValue);
        }
    }

    public void close() {
        this.listeners.clear();
    }
}
package com.kinglong.mango.common.warpper;

/**
 * Created by chenjinlong on 16/2/24.
 */
public class DefaultValueWrapper implements ValueWrapper {
    private static final long serialVersionUID = -7561268448577587446L;

    private Class<?> type;

    private Object value;

    private DefaultValueWrapper() {}

    public Class<?> getType() {
        return this.type;
    }

    public <T> T getValue() {
        return (T)this.value;
    }
    public static <T> DefaultValueWrapper wrap(T value) {
        DefaultValueWrapper obj = new DefaultValueWrapper();
        obj.type = value.getClass();
        obj.value = value;
        return obj;
    }
}

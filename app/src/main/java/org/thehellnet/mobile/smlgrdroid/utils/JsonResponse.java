package org.thehellnet.mobile.smlgrdroid.utils;

import java.io.Serializable;

/**
 * Created by sardylan on 14/05/15.
 */
public final class JsonResponse implements Serializable {
    private boolean success;
    private Object data;

    private JsonResponse(boolean success) {
        this.setSuccess(success);
    }

    public static JsonResponse createInstance(boolean success) {
        return new JsonResponse(success);
    }

    public static JsonResponse createInstance(boolean success, Object data) {
        JsonResponse jsonResonse = new JsonResponse(success);
        jsonResonse.setData(data);
        return jsonResonse;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

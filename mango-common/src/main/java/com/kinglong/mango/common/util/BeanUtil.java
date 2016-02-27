package com.kinglong.mango.common.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjinlong on 16/2/24.
 */
public class BeanUtil {
    public static <FO, TO> TO objTran(FO request, Class<TO> cls) {
        if (null == request) return null;
        TO result;
        try {
            result = cls.newInstance();
            BeanUtils.copyProperties(request, result);
        } catch (Exception e) {
            throw new IllegalArgumentException("对象copy失败，请检查相关module", e);
        }
        return result;
    }

    public static <FO, TO> List<TO> objTran4List(List<FO> request, Class<TO> cls) {
        if (request == null) {
            return null;
        }
        List<TO> result = new ArrayList<TO>();
        for (FO obj : request) {
            result.add(objTran(obj, cls));
        }
        return result;
    }
}

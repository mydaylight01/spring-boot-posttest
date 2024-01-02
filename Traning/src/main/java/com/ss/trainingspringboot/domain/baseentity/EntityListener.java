package com.ss.trainingspringboot.domain.baseentity;

import com.ss.trainingspringboot.util.StandardUtil;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.util.Date;;

@Component
public class EntityListener {
    @PrePersist
    public void prePersistFunction(Object o) {
        try {
            String userName = "admin";
            String pgm = "TRAIN";
            Date now = new Date();
            assignValueToCommonFields(o,userName,now,pgm,"CREATE");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @PreUpdate
    public void preUpdateFunction(Object o) {
        try {
            String userName = "admin";
            String pgm = "TRAIN";
            Date now = new Date();
            assignValueToCommonFields(o,userName,now,pgm,"UPDATE");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void assignValueToCommonFields(Object arg, String user, Date currentDate,String program, String status) throws IllegalAccessException, InvocationTargetException {
        BeanUtils.setProperty(arg,"lastUpdatedBy", user);
        BeanUtils.setProperty(arg,"lastUpdatedDate", currentDate);
        BeanUtils.setProperty(arg,"lastUpdatePgm", program);
        if ("CREATE".equalsIgnoreCase(status)) {
            BeanUtils.setProperty(arg,"createdBy", user);
            BeanUtils.setProperty(arg,"createdDate", currentDate);
            BeanUtils.setProperty(arg,"lastUpdatePgm", program);
        }
        Class cls = arg.getClass();
        Object target = arg;
        for (Field field : cls.getDeclaredFields()) {
            Field strField = ReflectionUtils.findField(cls, field.getName());
            if (strField.getType().equals(String.class)) {
                strField.setAccessible(true);
                Object value = ReflectionUtils.getField(strField, target);
                if (!StandardUtil.isNull(value) && StandardUtil.isEmpty(value.toString())) {
                    ReflectionUtils.makeAccessible(strField);
                    ReflectionUtils.setField(strField, target, null);
                }
            }
        }
    }
}

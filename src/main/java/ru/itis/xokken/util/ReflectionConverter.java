package ru.itis.xokken.util;

import org.springframework.stereotype.Component;
import ru.itis.xokken.exception.ConvertToOtherObjectException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Component
public class ReflectionConverter {

    public <From, To> To convertToOtherObject(From from, Class<To> toClass)
            throws ConvertToOtherObjectException {
        Field[] fields = toClass.getDeclaredFields();
        To object;
        try {
            object = toClass.getConstructor().newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                Field fieldObject;
                try {
                    fieldObject = from.getClass().getDeclaredField(field.getName());
                    fieldObject.setAccessible(true);
                } catch (NoSuchFieldException e) {
                    continue;
                }
                field.setAccessible(true);
                field.set(object, fieldObject.get(from));
            }
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException
                | IllegalAccessException e) {
            throw new ConvertToOtherObjectException(e);
        }
        return object;
    }
}

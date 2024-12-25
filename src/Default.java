import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE}) // Цель - тип или поле
@Retention(RetentionPolicy.RUNTIME) // Доступна во время исполнения
public @interface Default {
    Class<?> value();
}

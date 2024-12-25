import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE}) // Цель - тип или аннотация
@Retention(RetentionPolicy.RUNTIME) // Доступна во время исполнения
public @interface Validate {
    Class<?>[] value(); // Массив классов
}

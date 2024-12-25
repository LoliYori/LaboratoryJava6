import java.lang.annotation.*;

@Target(ElementType.TYPE) // Цель - тип
@Retention(RetentionPolicy.RUNTIME) // Доступна во время исполнения
public @interface Cache {
    String[] value() default {}; // Пустой массив
}

import java.lang.annotation.*;

@Target(ElementType.METHOD) // Цель - метод
@Retention(RetentionPolicy.RUNTIME) // Доступна во время исполнения
public @interface Invoke {
}

import java.lang.annotation.*;

@Target(ElementType.TYPE) // Цель - тип
@Retention(RetentionPolicy.RUNTIME) // Доступна во время исполнения
public @interface Two {
    String first(); // Обязательное свойство String
    int second();   // Обязательное свойство int
}

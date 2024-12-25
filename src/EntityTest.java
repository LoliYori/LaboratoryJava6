import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    /**
     * Тест проверяет, что метод `toString` корректно обрабатывает поля класса A.
     * Ожидается, что поле `s` включено в строковое представление, так как оно не исключено аннотацией.
     * Поле `x` должно быть исключено из строкового представления, так как оно помечено аннотацией `@ToString(NO)`.
     */
    @Test
    public void testToStringForClassA() {
        A a = new A();
        String expected = "A{s=hello}";  // Ожидаемое строковое представление, где только поле s включено
        // Проверяем, что строковое представление объекта a совпадает с ожидаемым
        assertEquals(expected, a.toString(), "Метод toString() должен исключать поле x из класса A.");
    }

    /**
     * Тест проверяет, что метод `toString` корректно работает для наследника класса A (класс B).
     * Поле `text` из класса B должно быть включено, поле `x` из суперкласса A должно быть исключено,
     * а поле `s` из суперкласса должно быть включено в строковое представление.
     */
    @Test
    public void testToStringForClassB() {
        B b = new B();
        String expected = "B{text=B, s=hello}";  // Ожидаемое строковое представление, включающее поля text и s
        // Проверяем, что строковое представление объекта b совпадает с ожидаемым
        assertEquals(expected, b.toString(), "Метод toString() должен корректно обрабатывать наследование в классе B.");
    }

    /**
     * Тест проверяет поведение метода `toString` при отсутствии аннотации @ToString.
     * В этом случае, все поля должны быть включены в строковое представление объекта.
     */
    @Test
    public void testToStringWithoutAnnotation() {
        class C extends Entity {
            String field1 = "value1";
            int field2 = 100;
        }
        C c = new C();
        String expected = "C{field1=value1, field2=100}";  // Ожидаемое строковое представление с обоими полями
        // Проверяем, что строковое представление объекта c совпадает с ожидаемым
        assertEquals(expected, c.toString(), "Метод toString() должен включать все поля, если аннотация @ToString не указана.");
    }
}

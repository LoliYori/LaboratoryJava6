import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        // Задача 1.1 и 3.1: Проверка вывода классов A и B
        A a = new A();
        B b = new B();

        System.out.println(a);  // Вывод: A{s=hello}
        System.out.println(b);  // Вывод: B{text=B, s=hello, x=42}

        // Задача 2.1: Проверка аннотации @Invoke
        System.out.println("\n--- Проверка аннотации @Invoke ---");
        // Создаем объект TestInvoke с аннотацией @Invoke
        TestInvoke testInvoke = new TestInvoke();

        // Получаем все методы
        Method[] methods = TestInvoke.class.getDeclaredMethods();
        // Проверка каждого метода, если есть аннтоция
        for (Method method : methods) {
            if (method.isAnnotationPresent(Invoke.class)) {
                // Метод аннотирован, вывод
                System.out.println("Метод " + method.getName() + " аннотирован @Invoke.");
                method.invoke(testInvoke); // Вызываем метод
            }
        }

        // Задача 2.2: Проверка аннотации @Default
        System.out.println("\n--- Проверка аннотации @Default ---");
        TestDefault testDefault = new TestDefault();

        // Получаем все поля из класса TestDefault
        Field[] fieldsDefault = TestDefault.class.getDeclaredFields();
        // Для каждого поля проверяем, имеет ли оно аннотацию @Default
        for (Field field : fieldsDefault) {
            field.setAccessible(true);  // Доступ к приватным полям

            // Если поле аннотировано @Default, выводим его имя и тип значения по умолчанию
            if (field.isAnnotationPresent(Default.class)) {
                Default defaultAnnotation = field.getAnnotation(Default.class);
                System.out.println("Поле " + field.getName() + " имеет аннотацию @Default со значением: " + defaultAnnotation.value().getSimpleName());
            }
        }

        // Задача 2.3: Проверка аннотации @ToString
        System.out.println("\n--- Проверка аннотации @ToString ---");
        TestToString testToString = new TestToString();

        // Получаем все поля из класса TestToString
        Field[] fieldsToString = TestToString.class.getDeclaredFields();

        // Для каждого поля проверяем, если оно аннотировано @ToString
        for (Field field : fieldsToString) {
            field.setAccessible(true);  // Доступ к приватным полям

            // Если поле аннотировано @ToString, выводим его имя и значение аннотации
            if (field.isAnnotationPresent(ToString.class)) {
                ToString toStringAnnotation = field.getAnnotation(ToString.class);
                System.out.println("Поле " + field.getName() + " имеет аннотацию @ToString со значением: " + toStringAnnotation.value());
            }
        }

        // Задача 2.4: Проверка аннотации @Validate
        System.out.println("\n--- Проверка аннотации @Validate ---");

        // Получаем аннотацию @Validate для класса TestValidate
        Validate validateAnnotation = TestValidate.class.getAnnotation(Validate.class);

        // Если аннотация присутствует, выводим классы, указанные в массиве value
        if (validateAnnotation != null) {
            System.out.println("Класс TestValidate аннотирован @Validate с значениями: ");
            for (Class<?> clazz : validateAnnotation.value()) {
                System.out.println("- " + clazz.getSimpleName());
            }
        }

        // Задача 2.5: Проверка аннотации @Two
        System.out.println("\n--- Проверка аннотации @Two ---");

        // Получаем аннотацию @Two для класса TestTwo
        Two twoAnnotation = TestTwo.class.getAnnotation(Two.class);

        // Если аннотация присутствует, выводим параметры first и second
        if (twoAnnotation != null) {
            System.out.println("Класс TestTwo аннотирован @Two с параметрами: first = " + twoAnnotation.first() + ", second = " + twoAnnotation.second());
        }

        // Задача 2.6: Проверка аннотации @Cache
        System.out.println("\n--- Проверка аннотации @Cache ---");

        // Получаем аннотацию @Cache для класса TestCache
        Cache cacheAnnotation = TestCache.class.getAnnotation(Cache.class);

        // Если аннотация присутствует, выводим все значения, указанные в массиве value
        if (cacheAnnotation != null) {
            System.out.println("Класс TestCache аннотирован @Cache с значениями: ");
            for (String value : cacheAnnotation.value()) {
                System.out.println("- " + value);
            }
        }
    }
}

import java.lang.reflect.Field;

abstract class Entity {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("{"); // Добавляем название класса

        Class<?> clazz = this.getClass();
        boolean first = true;

        // Обрабатываем поля в классе и его суперклассах
        while (clazz != null) {
            // Проверяем аннотацию @ToString на уровне класса
            boolean includeAllFields = true;
            if (clazz.isAnnotationPresent(ToString.class)) {
                ToString classAnnotation = clazz.getAnnotation(ToString.class);
                if ("NO".equals(classAnnotation.value())) {
                    includeAllFields = false; // Исключаем все поля, кроме явно разрешенных
                }
            }

            // Получаем все поля текущего класса
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // Даем доступ к приватным полям

                // Обрабатываем аннотацию @ToString на уровне полей
                if (field.isAnnotationPresent(ToString.class)) {
                    ToString fieldAnnotation = field.getAnnotation(ToString.class);
                    if ("NO".equals(fieldAnnotation.value())) {
                        continue; // Пропускаем поле, помеченное @ToString(NO)
                    }
                } else if (!includeAllFields) {
                    continue; // Исключаем поле, если класс помечен @ToString(NO)
                }

                // Формируем строковое представление для поля
                try {
                    if (!first) sb.append(", "); // Добавляем запятую, если это не первое поле
                    sb.append(field.getName()).append("=").append(field.get(this)); // Добавляем имя поля и его значение
                    first = false;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            clazz = clazz.getSuperclass(); // Переходим к суперклассу
        }

        sb.append("}"); // Завершение
        return sb.toString();
    }
}


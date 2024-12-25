public class TestInvoke {

    @Invoke
    public void annotatedMethod() {
        System.out.println("Аннотация @Invoke сработала");
    }

    public void nonAnnotatedMethod() {
        System.out.println("Этот метод не аннотирован.");
    }
}
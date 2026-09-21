package cf.carefulhuo.thread.reflection;

public class TargetClass {

    private String value;

    public TargetClass() {
    }

    public TargetClass(String value) {
        this.value = value;
    }

    public void publicMethod(String s){
        System.out.println("My Love "+ s);
    }

    private void privateMethod(){
        System.out.println("value is "+ value);
    }
}

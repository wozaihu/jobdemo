package lyx.mvp.javatest;

/**
 * @Author LYX
 * @Date 2022/8/27 13:49
 */
public class JavaMain {

    private static Student student2;

    public static void main(String[] args) {
        Teacher teacher = new Teacher("叶老师");
        Student student = new Student("李明");
        student.teacher = teacher;
    }


}

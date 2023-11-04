package lyx.mvp.javatest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LYX
 * @Date 2022/8/27 13:49
 */
public class JavaMain {

    private static Student student2;

    public static void main(String[] args) {
        List<String> firstList = new ArrayList<>();
        List<String> secondList = new ArrayList<>();

        // 第一个集合
        for (int i = 0; i <30 ; i++) {
            firstList.add(i + 1 + "");
        }

        // 第二个集合
        for (int i = 0; i < 3; i++) {
            secondList.add("第" + i + "个");
        }

        int secondIndex = 0;
        for (int i = 5; i <= firstList.size(); i += 11) {
            if (secondIndex < secondList.size()) {
                firstList.add(i, secondList.get(secondIndex));
                secondIndex++;
            }
        }

        // 输出结果
        System.out.println("第一个集合插入元素后的结果：");
        for (String num : firstList) {
            System.out.print(num + "，");
        }
    }
}

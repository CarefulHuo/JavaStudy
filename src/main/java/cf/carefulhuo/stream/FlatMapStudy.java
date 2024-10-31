package cf.carefulhuo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 适用于 List<List<Object>> 转换为 List<Object> 的场景
 * 即将嵌套列表转换单个列表
 */
public class FlatMapStudy {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);
        List<List<Integer>> list = Arrays.asList(list1, list2, list3);

        // 使用 flatMap 将嵌套列表转换单个列表
        // flatmap 将单个元素转换一个全新的 steam 流返回
        List<Integer> result = list.stream().flatMap(e->e.stream()).filter(e->e > 5).collect(Collectors.toList());
        result.stream().forEach(e->System.out.println(e));

    }
}

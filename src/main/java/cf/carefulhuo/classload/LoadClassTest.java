package cf.carefulhuo.classload;

import java.util.*;

public class LoadClassTest {

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        List<Demo> list = new ArrayList<>();

        map.put("a","a");
        map.put("b","b");
        map.put("c",null);

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Object> entry = (Map.Entry<String, Object>) iterator.next();
            Demo demo = new Demo();
            demo.setName(entry.getKey());
            demo.setDesc((String) entry.getValue());
            list.add(demo);
        }

        Demo demo1 = new Demo();
        demo1.setName((String) map.get("r"));
        list.add(demo1);

        for (int i = 0; i < list.size(); i++) {
            if ("a".equals(list.get(i).getDesc())){
                System.out.println(i);
            }
        }

        list.stream().forEach(demo -> System.out.println(demo.getName()+"--"+demo.getDesc()));

    }

    public static class Demo{
        private String name;

        private String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}

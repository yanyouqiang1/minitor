/**
 * Created by Administrator on 2017/6/6 0006.
 */
public class test2 {
    public static void main(String[] args){
        String str = "http://172.18.218.252:8080/v2-beta/projects/1a5/stacks/1st9";
        String[] strings = str.split("projects");
        StringBuilder stringBuilder = new StringBuilder(strings[0]);
        stringBuilder.append("projects/");

        String[] strings1 = strings[1].split("/");

        stringBuilder.append(strings1[1]);

        stringBuilder.append("/services/");

        System.out.println(stringBuilder);
    }
}

package com.dreams.rj.learn.test1;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

    @Test
    public void testMatches() {
        String reg = "\\d{3,5}";
        String content = "test regex patter 123, 22, 3o458";

        //查找文本内容中是否存在某一个特殊模式的字符(Note: 只有字符串以表达式匹配的字符串开始才返回true)
        boolean matches = Pattern.matches(reg, content);  //false
        if (matches) {
            System.out.println("find key word in content");
        } else {
            System.out.println("not found");
        }

        //查找文本内容中是否存在某一个特殊模式的字符
        boolean matches1 = content.matches(reg); //false
        if (matches1) {
            System.out.println("find key word in content");
        } else {
            System.out.println("not found");
        }
    }


    @Test
    public void testDucpWrods() {
        String content = "Paris in the the spring, The the theme the the of this article is the Java's regex package";
        String reg = "\\b(\\w+)\\s+\\1\\b";
        //Pattern.CASE_INSENSITIVE ： 大小写不敏感
        Pattern compile = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compile.matcher(content);
        if (matcher.find()) {
            System.out.println("匹配到的数量:" + matcher.groupCount());

            for (int i = 0; i < matcher.groupCount(); i++) {
                // matcher.start()
                System.out.println(matcher.group(i));
//                matcher.group();
            }
        } else {
            System.out.println("没有匹配到数据");
        }

        content.split("");
    }

    @Test
    public void testGroup() {
        String content = "ABCD";
        String reg = "A(B(C))D";  //三个group : ABCD, BC, C
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(content);
        if (matcher.find()) {
            System.out.println("group count : " + matcher.groupCount());
            //groupCount() 方法返回的数量不包含group_0， 所以循环的时候需要使用<=
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.print("group " + i + " : " + matcher.group(i));
                //start(), end() 返回group_0 匹配的开始，结束索引， 含头不含尾
                System.out.println("start at : " + matcher.start() + " , end at : " + matcher.end());
            }
        }
    }

    @Test
    public void testStartEnd() {
        String content = "ABCEF";
        String reg = "Aa(B(CDD))D";  //三个group : ABCD, BC, C
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(content);
        if (matcher.find()) {
            System.out.println("group count : " + matcher.groupCount());
            //groupCount() 方法返回的数量不包含group_0， 所以循环的时候需要使用<=
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.print("group " + i + " : " + matcher.group(i));
                //start(), end() 返回group_0 匹配的开始，结束索引， 含头不含尾
                System.out.println("start at : " + matcher.start() + " , end at : " + matcher.end());
            }
        } else {
            //如果没有匹配的数据，调用start，end将会抛出错误 java.lang.IllegalStateException: No match available
            System.out.println("start at : " + matcher.start() + " , end at : " + matcher.end());
        }
    }

    @Test
    public void testFind() {
        String content = "Java has regular expressions in 1.4";
        String reg = "Java.*";

//        String content = "regular expressions now expressing in Java";
//        String reg = "Java.*";
        Pattern compile = Pattern.compile(reg);
        Matcher matcher = compile.matcher(content);

        while (matcher.find()) {
            System.out.println("m1.find() '" + matcher.group() + "' start = " + matcher.start() + " end = " + matcher.end());
        }

        //只有在字符串和表达式在开始位置批匹配才返回true
        if (matcher.lookingAt()) // No reset() necessary
            System.out.println("m1.lookingAt() start = " + matcher.start() + " end = " + matcher.end());

        //只有在字符串和表达式在开始位置批匹配才返回true
        if (matcher.matches()) // No reset() necessary
            System.out.println("m1.matches() start = " + matcher.start() + " end = " + matcher.end());
    }


//    public static void main(String[] args) {
//        String[] input = new String[]{
//                "Java has regular expressions in 1.4",
//                "regular expressions now expressing in Java",
//                "Java represses oracular expressions"
//        };
//        Pattern
//                p1 = Pattern.compile("re//w*"),
//                p2 = Pattern.compile("Java.*");
//        for (int i = 0; i < input.length; i++) {
//            System.out.println("input " + i + ": " + input[i]);
//            Matcher
//                    m1 = p1.matcher(input[i]),
//                    m2 = p2.matcher(input[i]);
//            while (m1.find())
//                System.out.println("m1.find() '" + m1.group() +
//                        "' start = " + m1.start() + " end = " + m1.end());
//            while (m2.find())
//                System.out.println("m2.find() '" + m2.group() +
//                        "' start = " + m2.start() + " end = " + m2.end());
//            if (m1.lookingAt()) // No reset() necessary
//                System.out.println("m1.lookingAt() start = "
//                        + m1.start() + " end = " + m1.end());
//            if (m2.lookingAt())
//                System.out.println("m2.lookingAt() start = "
//                        + m2.start() + " end = " + m2.end());
//            if (m1.matches()) // No reset() necessary
//                System.out.println("m1.matches() start = "
//                        + m1.start() + " end = " + m1.end());
//            if (m2.matches())
//                System.out.println("m2.matches() start = "
//                        + m2.start() + " end = " + m2.end());
//        }
//        monitor.expect(new String[]{
//                "input 0: Java has regular expressions in 1.4",
//                "m1.find() 'regular' start = 9 end = 16",
//                "m1.find() 'ressions' start = 20 end = 28",
//                "m2.find() 'Java has regular expressions in 1.4'" +
//                        " start = 0 end = 35",
//                "m2.lookingAt() start = 0 end = 35",
//                "m2.matches() start = 0 end = 35",
//                "input 1: regular expressions now " +
//                        "expressing in Java",
//                "m1.find() 'regular' start = 0 end = 7",
//                "m1.find() 'ressions' start = 11 end = 19",
//                "m1.find() 'ressing' start = 27 end = 34",
//                "m2.find() 'Java' start = 38 end = 42",
//                "m1.lookingAt() start = 0 end = 7",
//                "input 2: Java represses oracular expressions",
//                "m1.find() 'represses' start = 5 end = 14",
//                "m1.find() 'ressions' start = 27 end = 35",
//                "m2.find() 'Java represses oracular expressions' " +
//                        "start = 0 end = 35",
//                "m2.lookingAt() start = 0 end = 35",
//                "m2.matches() start = 0 end = 35"
//        });
//    }
//}
}

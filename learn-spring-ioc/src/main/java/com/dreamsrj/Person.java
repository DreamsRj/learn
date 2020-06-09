package com.dreamsrj;

import java.util.List;
import java.util.Map;

public class Person {
    private String name;
    private int age;
    private String[] books;
    private Map<String, String> numberMap;
    private List<String> friends;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public Map<String, String> getNumberMap() {
        return numberMap;
    }

    public void setNumberMap(Map<String, String> numberMap) {
        this.numberMap = numberMap;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}

package org.tasks;

public class Main {
    public static void main(String[] args) {
        Mathematician mathematician = new Mathematician();
        System.out.println(mathematician.findNumber(65));
        System.out.println(mathematician.findNumber(-65));
    }
}

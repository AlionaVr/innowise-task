package org.tasks;

public class Mathematician {
    protected int findNumber(int m) {
        if (m < 1) throw new IllegalArgumentException("Number m must be positive!");
        return m == 1 ? 0 : (int) Math.floor(Math.log(m) / Math.log(4));
    }
}

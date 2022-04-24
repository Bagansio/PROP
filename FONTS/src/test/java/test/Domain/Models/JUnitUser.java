package test.Domain.Models;

import org.junit.runner.JUnitCore;


/**
 * @author Alex
 */
public class JUnitUser {
    public static void main(String[] args) {
        JUnitCore.main(new DriverUserUnitary().getClass().getName());
    }
}

package app;

import com.sun.deploy.security.WinDeployNTLMAuthCallback;

public enum AttackType {
    FIRE (1, 2, 3),
    WIND (2, 3, 1),
    WATER(3, 1, 2);

    private final double mass;   // in kilograms
    private final double radius; // in meters
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
}

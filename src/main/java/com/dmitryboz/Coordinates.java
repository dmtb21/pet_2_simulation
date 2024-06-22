package com.dmitryboz;

public final class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 997 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSibling(Coordinates otherCoord) {
        return isSibling(otherCoord, 1);
    }

    public boolean isSibling(Coordinates otherCoord, int maxDistance) {
        int absX = Math.abs(otherCoord.x - this.x);
        int absY = Math.abs(otherCoord.y - this.y);
        return (absX <= maxDistance && absY == 0 || absX == 0 && absY <= maxDistance);
    }
}

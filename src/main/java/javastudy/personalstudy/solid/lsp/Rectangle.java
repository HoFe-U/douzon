package javastudy.personalstudy.solid.lsp;

// 잘못된 예제
class ARectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int calculateArea() {
        return width * height;
    }
}

class ASquare extends ARectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}

// 올바른 예제
abstract class Shape {
    public abstract int calculateArea();
}

class Rectangle extends Shape {
    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int calculateArea() {
        return width * height;
    }
}

class Square extends Shape {
    protected int side;

    public void setSide(int side) {
        this.side = side;
    }

    public int calculateArea() {
        return side * side;
    }
}

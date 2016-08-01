package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fisbii on 16-8-1.
 */
public class Test {

    public static void drawAll(List<? extends Shape> shapes){
        for(Shape s : shapes){
            s.draw();
        }
    }

    public static void drawAll2(List<? super Circle> shapes){

    }

    public static void main(String args[]){
        List<Circle> circleList = new ArrayList<>();
        circleList.add(new Circle());
        drawAll(circleList);
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Shape() {
            @Override
            public void draw() {
                System.out.println("shape");
            }
        });
        drawAll(shapes);
        List<Shape> s1 = new ArrayList<>();
        s1.add(new Circle());
        List<Object> s2 = new ArrayList<>();
        s2.add(new Circle1());
        drawAll2(s1);
        drawAll2(s2);
    }
}

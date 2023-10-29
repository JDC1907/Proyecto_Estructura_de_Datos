package ggm;

import java.util.Comparator;
import java.util.Iterator;
import tda.ArrayList;
import tda.LinkedList;
import tda.List;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {

        List<Student> l1 = new LinkedList<>();
        l1.addLast(new Student("Maria", "Panchana", 19));
        l1.addLast(new Student("Pepe", "Ortega", 21));
        l1.addLast(new Student("Luis", "Tomala", 16));
        l1.addLast(new Student("Carlos", "Tomala", 34));
        l1.addLast(new Student("Juan", "Tomala", 67));

        List<Student> l2 = new LinkedList<>();
        l2.addLast(new Student("Maria", "Panchana", 23));
        l2.addLast(new Student("Maria", "Quijije", 34));
        l2.addLast(new Student("Andres", "Vaca", 38));

        // con humildad
//        Comparator<Student> cmp = new Comparator<Student>() {
//            @Override
//            public int compare(Student s1, Student s2) {
//                
//                // usando el orden natural de los strings
////                return s1.getName().compareTo(s2.getName());
//                return s1.getAge() - s2.getAge();
//                
//            }
//        };

        // utilizando una expresión lambda
        Comparator<Student> cmp = (Student s1, Student s2) -> {
            return s1.getAge() - s2.getAge();
        };
        
        int comparacion = cmp.compare(l1.get(0), l2.get(2));
        
        System.out.println("comparacion: " + comparacion);
        
        
        

        List<Student> intersection = l1.findIntersection(l2, cmp);

        System.out.println("intersection.isEmpty(): " + intersection.isEmpty());

        System.out.println("intersection.size(): " + intersection.size());

        System.out.println(intersection);

        Comparator<Student> cmp2 = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getName().equals(o2.getName()) && o1.getLastName().equals(o2.getLastName())) {
                    return 0;
                }
                return 734;
            }
        };

        List<Student> intersection2 = l1.findIntersection(l2, cmp2);

        System.out.println("intersection2.isEmpty(): " + intersection2.isEmpty());

        System.out.println("intersection2.size(): " + intersection2.size());

        System.out.println(intersection2);

    }
}

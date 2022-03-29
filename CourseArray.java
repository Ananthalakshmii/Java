package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CourseArray {
    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "MicroServices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        //1. join all strings separated by space
        //Spring Spring Boot API MicroServices AWS PCF Azure Docker Kubernetes
        System.out.print(courses.stream()
                .collect(Collectors.joining(" ")));

        //2. join all strings separated by comma
        //Spring,Spring Boot,API,MicroServices,AWS,PCF,Azure,Docker,Kubernetes
        System.out.println(courses.stream()
                .collect(Collectors.joining(",")));

        //3.Take all characters from course
        //[S, p, r, i, n, g, S, p, r, i, n, g,  , B, o, o, t, A, P, I, M, i, c, r, o, S, e, r, v, i, c, e, s, A, W, S, P, C, F, A, z, u, r, e, D, o, c, k, e, r, K, u, b,e, r, n, e, t, e, s]
        System.out.println(courses.stream()
                .map(course->course.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList()));

        //4.Printing distinct characters
        //[S, p, r, i, n, g,  , B, o, t, A, P, I, M, c, e, v, s, W, C, F, z, u, D, k, K, b]
        System.out.println(courses.stream()
                .map(course->course.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList()));

        //5. Tuples with same length
        //step1 - duplicate course
        List courses2= List.of("Spring", "Spring Boot", "API", "MicroServices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
        //Step2 - compare course with course2
        System.out.println(courses.stream()
                .flatMap(course->courses2.stream()
                        .map(course2->List.of(course,course2)))
                .collect(Collectors.toList()));
        //Step3-Eliminate same course in course, course2
        //get not applicable for objects
       /*System.out.println(courses.stream()
                .flatMap(course->courses2.stream()
                        .map(course2->List.of(course,course2)))
               .filter( list-> !list.get(0).equals(list.get(1)));*/
        
        //Step4- Print courses with same length
        //length and get not applicable on object
        /*System.out.println(courses.stream().flatMap(course -> courses2.stream()
                .filter(course2 -> course2.length()==course.length())
                .map(course2 -> List.of(course,course2)))
                .filter(list -> !list.get(0).equals(list.get(1)))
                .collect(Collectors.toList()));*/


    }
}

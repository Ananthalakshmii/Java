package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Courses {
    private String name;
    private String Category;
    private int reviewScore;
    private int noOfStudents;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "name='" + name + '\'' +
                ", Category='" + Category + '\'' +
                ", reviewScore=" + reviewScore +
                ", noOfStudents=" + noOfStudents +
                '}';
    }

    public Courses(String name, String category, int reviewScore, int noOfStudents) {
        this.name = name;
        Category = category;
        this.reviewScore = reviewScore;
        this.noOfStudents = noOfStudents;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }



    public static void main(String[] args) {
        //String c[]=new String[] {"Spring","Spring Boot","API","MicroServices","AWS","PCF","Azure","Docker","Kubernetes"};
        List<Courses> courses=
        Arrays.asList(
                new Courses("Spring","Framework",98,20000),
                new Courses("Spring Boot","Framework",95,18000),
                new Courses("API","MicroServices",97,22000),
                new Courses("MicroServices","MicroServices",96,25000),
                new Courses("AWS","FullStack",91,14000),
                new Courses("PCF","Cloud",92,21000),
                new Courses("Azure","Cloud",99,21000),
                new Courses("Docker","Cloud",92,20000),
                new Courses("Kubernetes","Cloud",91,20000)
        );


        Predicate<Courses> reviewGT95 = course -> course.getReviewScore()>95;
        Predicate<Courses> reviewGT90 = course -> course.getReviewScore()>90;
        Predicate<Courses> reviewLT90 = course -> course.getReviewScore()<90;

        //1-4 allMatch(),noneMatch(),anyMatch()
        System.out.println(courses.stream().allMatch(reviewGT95)); //false
        System.out.println(courses.stream().noneMatch(reviewLT90)); //true
        System.out.println(courses.stream().anyMatch(reviewGT90)); //true
        System.out.println(courses.stream().anyMatch(reviewLT90)); //false

        Comparator<Courses> noOfStudentsInc = Comparator.comparing(Courses::getNoOfStudents);
        Comparator<Courses> noOfStudentsDec = Comparator.comparing(Courses::getNoOfStudents).reversed();
        Comparator<Courses> noOfStudentsAndReviews = Comparator.comparing(Courses::getNoOfStudents)
                .thenComparing(Courses::getReviewScore).reversed();

        //5. Sort courses based on number of students decreasing and collect it
        courses.stream()
                .sorted(noOfStudentsDec)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //6. Sort courses based on no of students and reviews and collect it
        courses.stream()
                .sorted(noOfStudentsAndReviews)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //7,8,9 skip,limit,takeWhile, dropWhile
        //7. Sort with no of students dec skip first 3 elements and limit only 5 elements

        courses.stream()
                .sorted(noOfStudentsDec)
                .skip(3)
                .limit(5)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //8,9 -takewhile, dropwhile
        courses.stream()
                .takeWhile(reviewGT90)
                .collect(Collectors.toList());

        courses.stream()
                .dropWhile(reviewGT90)
                .collect(Collectors.toList());

        //10. find the top course which has the greatest no of students and reviews
        //max- last element return, min - first element return
        System.out.println(courses.stream()
                .max(noOfStudentsAndReviews));

        System.out.println(courses.stream()
                .min(noOfStudentsAndReviews));

        //11. Display last(min) course based on noofStudentsandreviews where review greater than 90
        System.out.println(courses.stream()
                .filter(reviewGT90)
                .min(noOfStudentsAndReviews)
                .orElse(new Courses("Kuber","FW",94,20000)));//If not found, return this

        //12. find first course with review > 90
        System.out.println(courses.stream()
                .filter(reviewGT90)
                .findFirst());

        //13.find any course with review > 90
        System.out.println(courses.stream()
                .filter(reviewGT90)
                .findAny());

        //14. find the total number of students whose review >90
        System.out.println(courses.stream()
                .filter(reviewGT90)
                .mapToInt(Courses::getNoOfStudents)
                .sum());

        //15. find the average number of students whose review > 90
        System.out.println(courses.stream()
                .filter(reviewGT90)
                .mapToInt(Courses::getNoOfStudents)
                .average());

        //16. count num of courses whose review > 90
        System.out.println(courses.stream()
                .filter(reviewGT90)
                .count());

        //17. Group courses by category
        //{MicroServices=[Courses{name='API', Category='MicroServices', reviewScore=97, noOfStudents=22000}, Courses{name='MicroServices', Category='MicroServices', reviewScore=96, noOfStudents=25000}],
        // Cloud=[Courses{name='PCF', Category='Cloud', reviewScore=92, noOfStudents=21000}, Courses{name='Azure', Category='Cloud', reviewScore=99, noOfStudents=21000}, Courses{name='Docker', Category='Cloud', reviewScore=92, noOfStudents=20000},
        // Courses{name='Kubernetes', Category='Cloud', reviewScore=91, noOfStudents=20000}],
        // FullStack=[Courses{name='AWS', Category='FullStack', reviewScore=91, noOfStudents=14000}],
        // Framework=[Courses{name='Spring', Category='Framework', reviewScore=98, noOfStudents=20000}, Courses{name='Spring Boot', Category='Framework', reviewScore=95, noOfStudents=18000}]}

        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Courses::getCategory)));

        //18. Group courses by category along with number of courses in each category
        //{MicroServices=2, Cloud=4, FullStack=1, Framework=2}
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Courses::getCategory,Collectors.counting())));

        //19. Max review score course in each category
        //{MicroServices=Optional[Courses{name='API', Category='MicroServices', reviewScore=97, noOfStudents=22000}],
        // Cloud=Optional[Courses{name='Azure', Category='Cloud', reviewScore=99, noOfStudents=21000}],
        // FullStack=Optional[Courses{name='AWS', Category='FullStack', reviewScore=91, noOfStudents=14000}],
        // Framework=Optional[Courses{name='Spring', Category='Framework', reviewScore=98, noOfStudents=20000}]}

        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Courses::getCategory,
                        Collectors.maxBy(Comparator.comparing(Courses::getReviewScore)))));

        //20. Courses in each category
        //{MicroServices=[API, MicroServices],
        // Cloud=[PCF, Azure, Docker, Kubernetes],
        // FullStack=[AWS],
        // Framework=[Spring, Spring Boot]}
        System.out.println(courses.stream()
                .collect(Collectors.groupingBy(Courses::getCategory,
                        Collectors.mapping(Courses::getName,Collectors.toList()))));

    }
}

package com.company;

//--------Intermediate Stream operations------
//filter- Accepts 1 arg and returns boolean - Predicate<Integer> - test()
//sorted
//distinct
//map - Accepts 1 arg and returns 1 arg - Function<Integer,Integer> - apply()
//--------Terminal Stream operations------
//forEach - Accepts 1 arg and returns nothing -Consumer<Integer> - accept()
//reduce
//collect
//Supplier- No input,return something
//BiPredicate- test()
//BiFunction - apply()
//BiConsumer - accept()
//UnaryOperator- Accepts 1 arg and returns 1 arg - apply()
//BinaryOperator-Accepts 2 arg and returns 1 value -apply()

//IntPredicate, IntFunction, IntConsumer, IntSupplier, IntUnaryoperator, IntBinaryOperator
//IntToDoubleFunction, IntToLongFunction

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public class Functional_Programing {

    private static void filterAndPrint(List<Integer> numbers, Predicate<Integer> predicate){
        numbers.stream()
                .filter(predicate)
                .forEach(System.out::println);
    }

    private static List<Integer> mapAndCreateNewList(List<Integer> numbers, Function<Integer,Integer> function){
        return numbers.stream()
                .map(function)
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //1. Print square of every even number
        //4 16 36 64 100
        list.stream()
                .filter(x->x%2==0)
                .map(x->x*x)
                .forEach(System.out::println);

        //2. Behaviour Parameterization -- Create Predicate to filter and print the value
        filterAndPrint(list,x->x%2==0);
        filterAndPrint(list,x->x%2!=0);
        filterAndPrint(list,x->x%3==0);

        //3. Behaviour Parameterization -- Create Function to Map and print the value
        System.out.println(mapAndCreateNewList(list,x->x*x));
        System.out.println(mapAndCreateNewList(list,x->x*x*x));
        System.out.println(mapAndCreateNewList(list,x->x+x));

        //4.Perform basic operation using stream
        int[] numberArray = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(Arrays.stream(numberArray).sum());
        System.out.println(Arrays.stream(numberArray).average());
        System.out.println(Arrays.stream(numberArray).min());
        System.out.println(Arrays.stream(numberArray).max());

        //5.Find sum of first 10 integers
        System.out.println(IntStream.range(1,10).sum());
        System.out.println(IntStream.rangeClosed(1,10).sum());

        //6.Find sum of odd numbers
        System.out.println(IntStream.iterate(1,e->e+2).limit(10).sum());//100
        //7. Find sum of even numbers
        System.out.println(IntStream.iterate(2,e->e+2).limit(10).sum());//110
        //8. find sum of powers of 2
        IntStream.iterate(1,e->e*2).limit(10).peek(System.out::println).sum();//1 2 4 8 16 32 64 128 256 512

        //9.Find factorial of 50
        System.out.println(IntStream.rangeClosed(1,50)
                .reduce(1,(x,y)->x*y));//0
        System.out.println(LongStream.rangeClosed(1,50)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE,BigInteger::multiply));
        //30414093201713378043612608166064768844377641568960512000000000000








    }


}

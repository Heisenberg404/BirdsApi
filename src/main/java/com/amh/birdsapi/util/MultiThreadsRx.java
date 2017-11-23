package com.amh.birdsapi.util;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


public class MultiThreadsRx {
	
	

	/*public static void main(String[] args) {
	    Observable.fromCallable(thatReturnsNumberOne())     // the Observable
	            .map(numberToString())                      // the Operator
	            .subscribe(printResult());                  // the Subscriber
	}
	
    Output of the above program:
    ---------------------------
    Observable thread: main
    Operator thread: main
    Subscriber thread: main
    Result: 1
*/

        
private static Callable<Integer> thatReturnsNumberOne() {
    return () -> {
        System.out.println("Observable thread: " + Thread.currentThread().getName());
        return 1;
    };
}

private static Func1<Integer, String> numberToString() {
    return number -> {
        System.out.println("Operator thread: " + Thread.currentThread().getName());
        return String.valueOf(number);
    };
}

private static Action1<String> printResult() {
    return result -> {
        System.out.println("Subscriber thread: " + Thread.currentThread().getName());
        System.out.println("Result: " + result);
		};
	}
}

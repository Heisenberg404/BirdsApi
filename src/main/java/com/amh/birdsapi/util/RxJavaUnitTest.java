package com.amh.birdsapi.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaUnitTest {

	public static void main(String[] args) {

//		process6();
//		finalize1();
		 process7();
	}

	static void process() {
		Observable<String> o = Observable.from(Arrays.asList("hola", "que", "tal"));

		o.subscribe(new Action1<String>() {

			public void call(String arg0) {
				System.out.println(arg0);

			}
		});

	}

	static void process1() {
		Observable<Long> observable1 = Observable.interval(500, TimeUnit.MILLISECONDS).take(20);
		Observable<Long> observable2 = Observable.interval(200, TimeUnit.MILLISECONDS).take(20);

		Observable.merge(observable1, observable2).subscribe(new Action1<Long>() {

			@Override
			public void call(Long arg0) {
				System.out.println(arg0);
			}
		});

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	static void process3() {

		List<String> list1 = new ArrayList<>();
		list1.add("lista 1 - el 1");
		list1.add("lista 1 - el 2");
		list1.add("lista 1 - el 3");
		list1.add("lista 1 - el 4");
		list1.add("lista 1 - el 5");

		List<String> list2 = new ArrayList<>();
		list1.add("lista 2 - el 1");
		list1.add("lista 2 - el 2");
		list1.add("lista 2 - el 3");
		list1.add("lista 2 - el 4");
		list1.add("lista 2 - el 5");

		Observable<String> observable1 = Observable.from(list1);
		Observable<String> observable2 = Observable.from(list2);

		Observable.merge(observable1, observable2).subscribe(new Action1<String>() {

			@Override
			public void call(String arg0) {
				System.out.println(arg0);
			}
		});

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	static void process4() {

		List<String> list1 = new ArrayList<>();
		list1.add("lista 1 - el 1");
		list1.add("lista 1 - el 2");
		list1.add("lista 1 - el 3");
		list1.add("lista 1 - el 4");
		list1.add("lista 1 - el 5");

		List<String> list2 = new ArrayList<>();
		list1.add("lista 2 - el 1");
		list1.add("lista 2 - el 2");
		list1.add("lista 2 - el 3");
		list1.add("lista 2 - el 4");
		list1.add("lista 2 - el 5");

		Observable<String> observable1 = Observable.from(list1);
		Observable<String> observable2 = Observable.from(list2);

		observable1.subscribe(new Action1<String>() {

			@Override
			public void call(String arg0) {
				System.out.println(arg0);
			}
		});

		observable2.subscribe(new Action1<String>() {

			@Override
			public void call(String arg0) {
				System.out.println(arg0);
			}
		});

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	static void process5() {
		List<String> list1 = new ArrayList<>();
		list1.add("lista 1 - el 1");
		list1.add("lista 1 - el 2");
		list1.add("lista 1 - el 3");
		list1.add("lista 1 - el 4");
		list1.add("lista 1 - el 5");

		List<String> list2 = new ArrayList<>();
		list1.add("lista 2 - el 1");
		list1.add("lista 2 - el 2");
		list1.add("lista 2 - el 3");
		list1.add("lista 2 - el 4");
		list1.add("lista 2 - el 5");

		Observable<String> observable1 = Observable.from(list1);
		Observable<String> observable2 = Observable.from(list2);

		Observable.merge(observable1, observable2).flatMap(new Func1<String, Observable<String>>() {

			@Override
			public Observable<String> call(String pArg0) {

				return Observable.just(pArg0);
			}

		}).subscribeOn(Schedulers.newThread()).map(new Func1<String, String>() {

			@Override
			public String call(String pArg0) {
				imprimirEle(pArg0);
				try {
					Thread.sleep(1000 - (10 * 100));
					System.out.println(pArg0 + "  ccc   " + Thread.currentThread().getName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				return pArg0;
			}

			private void imprimirEle(String pArg0) {
				System.out.println(pArg0);
			}

		}).subscribe();
	}

	static List<Integer> listaN;

	static void process6() {
		
		listaN = new ArrayList<>();
		Observable<Long> observable1 = Observable.interval(300, TimeUnit.MILLISECONDS).take(20);
		Observable<Long> observable2 = Observable.interval(100, TimeUnit.MILLISECONDS).take(20);

		new Thread(() -> procesingOtherThread()).start();

		Observable.merge(observable1, observable2).subscribe(new Action1<Long>() {
			@Override
			public void call(Long arg0) {
				System.out.println(arg0);
				System.out.println("Hilo:" + Thread.currentThread().getName());
				listaN.add(Math.toIntExact(arg0));

			}
		});

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	static void finalize1() {

		Collections.sort(listaN);
		System.out.println("values add to list are:");
		for (Integer integer : listaN) {
			System.out.println(integer);
		}

	}

	static void procesingOtherThread() {
		for (int i = 0; i < 15; i++) {
			System.out.println("Hilo:" + Thread.currentThread().getName());
			System.out.println("proceso # " + i);
			
		}
	}

	@SuppressWarnings("unchecked")
	static void process7() {

		listaN = new ArrayList<>();
		Observable<Integer> observable1 = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Observable<Integer> observable2 = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		new Thread(() -> procesingOtherThread()).start();

		observable1.subscribeOn(Schedulers.newThread()).subscribe(new Subscriber() {

			@Override
			public void onCompleted() {
				System.out.println("Complete!");
				finalize1();
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Object value) {
				System.out.println("atendido en el hilo:" + Thread.currentThread().getName());
				listaN.add( (Integer) value);
				System.out.println("onNext: " + value);
				
			}
		});
		observable2.subscribeOn(Schedulers.newThread()).subscribe(new Subscriber() {

			@Override
			public void onCompleted() {
				System.out.println("Complete!");
				finalize1();
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());
			}

			@Override
			public void onNext(Object value) {
				System.out.println("atendido en el hilo:" + Thread.currentThread().getName());
				listaN.add( (Integer) value);
				System.out.println("onNext: " + value);
				
			}
		});
		
	}
}
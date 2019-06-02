package com.search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchMain {
	
	static int[] arr = {3,66,13,5,32,22,664};
	
	static ExecutorService pool = Executors.newFixedThreadPool(3);
	static final int Thread_Num = 2;
	static AtomicInteger result = new AtomicInteger(-1);

	public static int search(int searchValue , int beginPos, int endPos) {
		int i=0;
		System.out.println("start search Thread="+ Thread.currentThread().getId());
		for( i = beginPos;i<endPos; i++) {
			if(result.get() >= 0) {
				return result.get();
			}
			if(arr[i] == searchValue) {
				//如果设置失败，则表示其他线程已经先找到
				if(!result.compareAndSet(-1, i)) {
					return result.get();
				}
				return i;
			}
		}
		return -1;
	}
	
	public static class SearchTask implements Callable<Integer>{
		int begin,end,searchValue;
		public SearchTask(int searchValue, int begin, int end) {
			this.begin = begin;
			this.end = end;
			this.searchValue = searchValue;
		}
		public Integer call() throws Exception {
			int re = search(searchValue, begin, end);
			return re;
		}
	}
	
	public static int pSearch(int searchValue) throws InterruptedException, ExecutionException {
		int subArrSize = arr.length / Thread_Num + 1;
		List<Future<Integer>> re = new ArrayList<Future<Integer>>();
		for(int i=0;i< arr.length; i = i + subArrSize ) {
			int end = i + subArrSize;
			if(end >= arr.length ) end = arr.length;
			re.add(pool.submit(new SearchTask(searchValue, i ,end)));
		}
		for(Future<Integer> fu : re) {
			if(fu.get() >= 0) {
				System.out.println(" search seccuess");
				return fu.get();
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		try {
			int pos = pSearch(22);
			System.out.println("数组的第"+pos+"个"+"  arr["+pos+"]="+ arr[pos]);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}

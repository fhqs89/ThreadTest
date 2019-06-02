package com.blockqueue;

/**
 * 消息队列中的数据模型
 * @author zhanghao
 *
 */
public class PCData {
	
	private final int intData;
	public PCData(int d) {
		intData = d;
	}
	
	public int getData() {
		return intData;
	}

	public String toString() {
		return "data:" + intData; 
	}
}

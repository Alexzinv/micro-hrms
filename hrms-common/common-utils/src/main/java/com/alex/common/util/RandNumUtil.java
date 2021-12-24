package com.alex.common.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @Author _
 * @Date 2021/9/16
 * @Description 随机数工具类
 */
public class RandNumUtil {

	private static final Random RANDOM = new Random();

	private static final DecimalFormat FOUR_CODE = new DecimalFormat("0000");

	private static final DecimalFormat SIX_CODE = new DecimalFormat("000000");

	public static String getFourNumRandom() {
		return FOUR_CODE.format(RANDOM.nextInt(10000));
	}

	public static String getSixNumRandom() {
		return SIX_CODE.format(RANDOM.nextInt(1000000));
	}

	/**
	 * 给定列表，抽取n个数据
	 * @param list 数据
	 * @param n 个数
	 * @return 生成的随机数
	 */
	public static ArrayList<Integer> getRandom(List<Integer> list, int n) {

		Random random = new Random();
		HashMap<Object, Object> hashMap = new HashMap<>(16);

		// 生成随机数字并存入HashMap
		for (int i = 0; i < list.size(); ) {
			int number = random.nextInt(100) + 1;
			hashMap.put(number, i++);
		}

		// 从HashMap导入数组
		Object[] objs = hashMap.values().toArray();
		ArrayList<Integer> r = new ArrayList<>();

		// 遍历数组并打印数据
		for (int i = 0; i < n; i++) {
			r.add(list.get((int) objs[i]));
			System.out.print(list.get((int) objs[i]) + "\t");
		}
		System.out.print("\n");
		return r;
	}
}

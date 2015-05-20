package org.adastraeducation.sort;

import org.adastraeducation.util.Generate_random_number;
import org.adastraeducation.util.SetArray;

import processing.core.PApplet;

public class MergeSort {
	public int[] data;
	public ArrayDisplayer x;
	public static int arrayLength = 10;
	
	public MergeSort(ArrayDisplayer a) {
		this.x = a; 
	}

	public static void main(String[] args) {
		//int[] data = new int[] { 5, 3, 6, 2, 1, 9, 4, 8, 7 };
		SetArray randomArray = new SetArray(arrayLength); 
		MergeSort a = new MergeSort(new TextArrayDisplayer(randomArray.data));
		a.sort(0, arrayLength-1);
	}
	
	public void sort(int left, int right) {
		if (left >= right)
			return;
		// 找出中间索引
		int center = (left + right) / 2;
		// 对左边数组进行递归
		x.setHighlightVertex(left, center, false);
		//display(left, center);
		sort(left, center);
		x.setHighlightVertex(center+1, right, false);
		// 对右边数组进行递归
		//display(center+1, right);
		sort(center + 1, right);
		
		// 合并
		merge(x, left, center, right);
		x.setHighlightVertex(left, right, true);
		//displayC(left, right);
		print(x.data);
		//print(data);
	}

	public void merge(int[] data, int left, int center, int right) {
		// 临时数组
		int[] tmpArr = new int[data.length];
		// 右数组第一个元素索引
		int mid = center + 1;
		// third 记录临时数组的索引
		int third = left;
		// 缓存左数组第一个元素的索引
		int tmp = left;
		while (left <= center && mid <= right) {
			// 从两个数组中取出最小的放入临时数组
			if (data[left] <= data[mid]) {
				tmpArr[third++] = data[left++];
			} else {
				tmpArr[third++] = data[mid++];
			}
		}
		// 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
		while (mid <= right) {
			tmpArr[third++] = data[mid++];
		}
		while (left <= center) {
			tmpArr[third++] = data[left++];
		}
		// 将临时数组中的内容拷贝回原数组中
		// （原left-right范围的内容被复制回原数组）
		while (tmp <= right) {
			data[tmp] = tmpArr[tmp++];
		}
	}
	
	public void merge(ArrayDisplayer x, int left, int center, int right) {
		// 临时数组
		int[] tmpArr = new int[x.data.length];
		// 右数组第一个元素索引
		int mid = center + 1;
		// third 记录临时数组的索引
		int third = left;
		// 缓存左数组第一个元素的索引
		int tmp = left;
		while (left <= center && mid <= right) {
			// 从两个数组中取出最小的放入临时数组
			if (x.data[left] <= x.data[mid]) {
				tmpArr[third++] = x.data[left++];
			} else {
				tmpArr[third++] = x.data[mid++];
			}
		}
		// 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
		while (mid <= right) {
			tmpArr[third++] = x.data[mid++];
		}
		while (left <= center) {
			tmpArr[third++] = x.data[left++];
		}
		// 将临时数组中的内容拷贝回原数组中
		// （原left-right范围的内容被复制回原数组）
		while (tmp <= right) {
			x.data[tmp] = tmpArr[tmp++];
		}
	}

	public void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}

}

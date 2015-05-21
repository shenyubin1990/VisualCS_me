package org.adastraeducation.sort.quicksort;

public abstract class SortDisplayer implements SortObserver {

	protected QuickSort qs;
	protected int[] highlightvertex;

	public SortDisplayer(QuickSort qs) {
		this.qs = qs;
		int num = qs.getNum();

		highlightvertex = new int[num];

		for (int i = 0; i < highlightvertex.length; i++) {
			highlightvertex[i] = 0;
		}
		display();

	}

	public int getHighlightVertex(int v) {
		return highlightvertex[v];
	}

}

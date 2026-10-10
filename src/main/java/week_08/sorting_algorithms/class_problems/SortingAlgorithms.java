package week_08.sorting_algorithms.class_problems;

import java.util.Arrays;

/** Implementations aligned with the Week 8 sorting handout. */
public final class SortingAlgorithms {
    private SortingAlgorithms() { }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static void bubbleSort(int[] a) {
        for (int pass = 0; pass < a.length - 1; pass++) {
            boolean swapped = false;
            for (int j = 0; j < a.length - 1 - pass; j++) {
                if (a[j] > a[j + 1]) { swap(a, j, j + 1); swapped = true; }
            }
            if (!swapped) break;
        }
    }

    public static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) if (a[j] < a[min]) min = j;
            if (min != i) swap(a, i, min);
        }
    }

    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i], j = i - 1;
            while (j >= 0 && a[j] > key) { a[j + 1] = a[j]; j--; }
            a[j + 1] = key;
        }
    }

    public static void mergeSort(int[] a) {
        if (a.length < 2) return;
        mergeSort(a, new int[a.length], 0, a.length - 1);
    }
    private static void mergeSort(int[] a, int[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(a, temp, left, mid);
        mergeSort(a, temp, mid + 1, right);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) temp[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        while (i <= mid) temp[k++] = a[i++];
        while (j <= right) temp[k++] = a[j++];
        System.arraycopy(temp, left, a, left, right - left + 1);
    }

    public static void quickSort(int[] a) { quickSort(a, 0, a.length - 1); }
    private static void quickSort(int[] a, int low, int high) {
        if (low >= high) return;
        int pivot = a[low + (high - low) / 2], i = low, j = high;
        while (i <= j) {
            while (a[i] < pivot) i++;
            while (a[j] > pivot) j--;
            if (i <= j) { swap(a, i++, j--); }
        }
        if (low < j) quickSort(a, low, j);
        if (i < high) quickSort(a, i, high);
    }

    public static void heapSort(int[] a) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--) siftDown(a, n, i);
        for (int end = n - 1; end > 0; end--) {
            swap(a, 0, end);
            siftDown(a, end, 0);
        }
    }
    private static void siftDown(int[] a, int size, int root) {
        while (true) {
            int largest = root, left = 2 * root + 1, right = left + 1;
            if (left < size && a[left] > a[largest]) largest = left;
            if (right < size && a[right] > a[largest]) largest = right;
            if (largest == root) return;
            swap(a, root, largest);
            root = largest;
        }
    }

    /** Stable counting sort for integer values in a reasonably small range. */
    public static void countingSort(int[] a) {
        if (a.length < 2) return;
        int min = a[0], max = a[0];
        for (int value : a) { min = Math.min(min, value); max = Math.max(max, value); }
        long rangeLong = (long) max - min + 1;
        if (rangeLong > 1_000_000L) throw new IllegalArgumentException("Value range is too large for counting sort");
        int[] counts = new int[(int) rangeLong];
        for (int value : a) counts[value - min]++;
        for (int i = 1; i < counts.length; i++) counts[i] += counts[i - 1];
        int[] output = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) output[--counts[a[i] - min]] = a[i];
        System.arraycopy(output, 0, a, 0, a.length);
    }

    public static void main(String[] args) {
        int[] sample = {7, 3, 9, 1, 5};
        int[] copy;
        copy = sample.clone(); bubbleSort(copy); System.out.println("Bubble:    " + Arrays.toString(copy));
        copy = sample.clone(); selectionSort(copy); System.out.println("Selection: " + Arrays.toString(copy));
        copy = sample.clone(); insertionSort(copy); System.out.println("Insertion: " + Arrays.toString(copy));
        copy = sample.clone(); mergeSort(copy); System.out.println("Merge:     " + Arrays.toString(copy));
        copy = sample.clone(); quickSort(copy); System.out.println("Quick:     " + Arrays.toString(copy));
        copy = sample.clone(); heapSort(copy); System.out.println("Heap:      " + Arrays.toString(copy));
        copy = sample.clone(); countingSort(copy); System.out.println("Counting:  " + Arrays.toString(copy));
    }
}
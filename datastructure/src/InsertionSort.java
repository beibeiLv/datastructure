

public class InsertionSort {
	public static void sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int position = i;
			for (int j = i - 1; j >= 0; j--) {
				if (array[j] > temp) {
					array[j + 1] = array[j];
					 position -= 1;  
				} else {  
                    break;  
                }  
			}
			array[position] = temp;
		}
	}

	public static void main(String[] args) {
		int[] array = {3, -1, 0, -8, 2, 1 };

		sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}

	}
}

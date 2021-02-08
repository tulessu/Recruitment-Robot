class QuickSortStudent {

	int partition(Student arr[], int low, int high, Student inputStudent) {
		// pivot element to compare
		Student pivot = arr[high];
		double euclidPivot = pivot.getEuclid(inputStudent);
		int i = (low - 1); // index of smaller element

		for (int j = low; j < high; j++) {
			// compare euclid distances
			if (arr[j].getEuclid(inputStudent) < euclidPivot) {
				i++;

				// swap elements
				Student temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// swap next element and the pivot
		Student temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		// returns next partition
		return i + 1;
	}

	private void sort(Student arr[], int low, int high, Student inputStudent) {
		if (low < high) {

			int partitionIndex = partition(arr, low, high, inputStudent);

			// sort each part of the array according to PartitionIndex
			// left is smaller than partition, right is larger
			sort(arr, low, partitionIndex - 1, inputStudent);
			sort(arr, partitionIndex + 1, high, inputStudent);
		}
	}

	Student[] sortStudents(Student arr[], Student inputStudent) {

		// clone the original array to keep it unsorted.
		Student[] newArray = arr.clone();
		this.sort(newArray, 0, newArray.length - 1, inputStudent);
		return newArray;
	}

	

}
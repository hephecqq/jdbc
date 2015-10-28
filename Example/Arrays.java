import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Arrays {
	
		public static void main(String args[]) {
			
		System.out.println("Enter  array size");
			Scanner arraySize = new Scanner(System.in);	
		int[] arr =  new int[arraySize.nextInt()];
		ArrayList list=new ArrayList();
		for(int i = 0; i < arr.length; i++){
			int j = i+1;
		System.out.println("Enter value "+ j +" to enter in array");
		Scanner value = new Scanner(System.in);
		arr[i] = value.nextInt();
		}
		System.out.println("Array elements are");
		for(int i = 0; i < arr.length; i++){
		System.out.println(arr[i]);
		list.add(arr[i]);
		}
		
		List<Integer> arrayList = new ArrayList<Integer>();
		Arrays obj = new Arrays();
		obj.validations(list);
		
		System.out.println("The final elements in the ArrayList are:");
		
	for(int i = 0; i < list.size(); i++){
		System.out.println(list.get(i));
	}
	
	
	
	}
	
public  void  validations(List<Integer> arrayList){
	System.out.println("Choose option to do an ArrayList");
	System.out.println("1. Add   2. Remove   3. Stop");
	Scanner option = new Scanner(System.in);
	
	int j = option.nextInt();
	
	if(j == 1){
	System.out.println("Enter value to add  to the ArrayList");	
	Scanner add = new Scanner(System.in);	
	arrayList.add(add.nextInt());
	validations(arrayList);
	}
	else if(j == 2){
		System.out.println("Enter value to remove from the ArrayList");	
		Scanner remove = new Scanner(System.in);
		int value= remove.nextInt();
		if(arrayList.contains(value)){
		int index =	arrayList.indexOf(value);
		arrayList.remove(index);
		validations(arrayList);	
		}
		else{
		System.out.println("List is empty or given value doesnot exist");
		validations(arrayList);
		}
		
		}
	else if(j == 3){
		
		}
	
	else{
		System.out.println("Please enter correct value");
		validations(arrayList);
	}
	}
}

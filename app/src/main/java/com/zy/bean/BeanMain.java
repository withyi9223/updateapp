package com.zy.bean;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * <p>
 * Created by yi on 2019/12/23.
 */
public class BeanMain {

    public static void main(String[] args) {

        /*MyLinkedList linkedList = new MyLinkedList();
        linkedList.addData(1);
        linkedList.addData(2);
        linkedList.addData(3);
        linkedList.addData(4);
        linkedList.addData(5);
        
        linkedList.traverse(linkedList.getHead());*/

        int[] temp = new int[]{1, 8, 5, 6, 2, 0};
        /*QuickSort sort = new QuickSort(temp);
        sort.print();
        sort.quickSort(temp, 0, temp.length - 1);
        sort.print();*/
        //selectSort(temp);
        insertSort(temp);
    }


    public static void selectSort(int[] a) {
        int minInde = 0;
        int temp = 0;
        for (int i = 0; i < a.length; i++) {
            minInde = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minInde]) {
                    minInde = j;
                }
            }
            temp = a[i];
            a[i] = a[minInde];
            a[minInde] = temp;
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else break;
            }
        }
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

}

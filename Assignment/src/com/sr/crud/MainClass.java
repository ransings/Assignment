package com.sr.crud;

import java.util.Scanner;

public class MainClass
{
    public static void main(final String[] args) {
        CrudOperations co = new CrudOperations();
        boolean fl = true;
        Scanner sc = new Scanner(System.in);
        
        while (fl) {
            System.out.println("\nSelect One Option from following:");
            System.out.println("1.Insert Student Record\n2.Update Student Record\n3.Delete Student Record\n4.Display All students\n5.Get Individual Student Information\n6.Close");
            int key = sc.nextInt();
            switch (key) {
                case 1: {
                    co.insert();
                    continue;
                }
                case 2: {
                    co.update();
                    continue;
                }
                case 3: {
                    co.delete();
                    continue;
                }
                case 4: {
                    co.display();
                    continue;
                }
                case 5: {
                    co.perticular();
                    continue;
                }
                case 6: {
                    fl = false;
                    continue;
                }
                default: {
                    System.out.println("Invalid Selection..");
                    continue;
                }
            }
        }
        sc.close();
    }
}

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
                    break;
                }
                case 2: {
                    co.update();
                    break;
                }
                case 3: {
                    co.delete();
                    break;
                }
                case 4: {
                    co.display();
                    break;
                }
                case 5: {
                    co.perticular();
                    break;
                }
                case 6: {
                    fl = false;
                    break;
                }
                default: {
                    System.out.println("Invalid Selection..");
                    break;
                }
                
            }
        }
        sc.close();
    }
}

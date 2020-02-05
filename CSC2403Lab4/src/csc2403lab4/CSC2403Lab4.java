/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc2403lab4;

/**
 *
 * @author phil
 */
public class CSC2403Lab4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        testPalindrome();

        testPostfix();
    }
    
    private static void testPalindrome()
    {
        Palindrome p1 = new Palindrome("racecar");
        Palindrome p2 = new Palindrome("otto");
        Palindrome p3 = new Palindrome("reviver");       
        Palindrome p4 = new Palindrome("neveroddoreven");       
        Palindrome p5 = new Palindrome("a");       
        Palindrome p6 = new Palindrome("aa");       
        Palindrome p7 = new Palindrome("ava");
        
        System.out.println("Palindrome Tests");
        System.out.println("\np1: " + p1);
        if (!p1.isPalindrome()) System.out.println("**p1 Fail");
        else System.out.println("p1 pass");
        System.out.println("\np2: " + p2);
        if (!p2.isPalindrome()) System.out.println("**p2 Fail");
        else System.out.println("p2 pass");
        System.out.println("\np3: " + p3);
        if (!p3.isPalindrome()) System.out.println("**p3 Fail");
        else System.out.println("p3 pass");
        System.out.println("\np4: " + p4);
        if (!p4.isPalindrome()) System.out.println("**p4 Fail");
        else System.out.println("p4 pass");
        System.out.println("\np5: " + p5);
        if (!p5.isPalindrome()) System.out.println("**p5 Fail");
        else System.out.println("p5 pass");
        System.out.println("\np6: " + p6);
        if (!p6.isPalindrome()) System.out.println("**p6 Fail");
        else System.out.println("p6 pass");
        System.out.println("\np7: " + p7);
        if (!p7.isPalindrome()) System.out.println("**p7 Fail");
        else System.out.println("p7 pass");

        Palindrome n1 = new Palindrome("racecars");
        Palindrome n2 = new Palindrome("ab");
        Palindrome n3 = new Palindrome("otta");
        Palindrome n4 = new Palindrome("ralecar");
        Palindrome n5 = new Palindrome("neverjkreven");
        Palindrome n6 = new Palindrome("football");
        Palindrome n7 = new Palindrome("aaaaaaaaaabaaaaaaaaa");

        System.out.println("\nNon-palindrome tests:");
        System.out.println("\nn1: " + n1);
        if (n1.isPalindrome()) System.out.println("**n1 Fail");
        else System.out.println("n1 pass");
        System.out.println("\nn2: " + n2);
        if (n2.isPalindrome()) System.out.println("**n2 Fail");
        else System.out.println("n2 pass");
        System.out.println("\nn3: " + n3);
        if (n3.isPalindrome()) System.out.println("**n3 Fail");
        else System.out.println("n3 pass");
        System.out.println("\nn4: " + n4);
        if (n4.isPalindrome()) System.out.println("**n4 Fail");
        else System.out.println("n4 pass");
        System.out.println("\nn5: " + n5);
        if (n5.isPalindrome()) System.out.println("**n5 Fail");
        else System.out.println("n5 pass");
        System.out.println("\nn6: " + n6);
        if (n6.isPalindrome()) System.out.println("**n6 Fail");
        else System.out.println("n6 pass");
        System.out.println("\nn7: " + n7);
        if (n7.isPalindrome()) System.out.println("**n7 Fail");
        else System.out.println("n7 pass");
  
    }
    
    private static void testPostfix() {
        Postfix p1 = new Postfix("45+");
        Postfix p2 = new Postfix("54-");
        Postfix p3 = new Postfix("23*");
        Postfix p4 = new Postfix("84/");
        Postfix p5 = new Postfix("23-5+");
        Postfix p6 = new Postfix("12345****");
        Postfix p7 = new Postfix("62/42-42*++");
        Postfix p8 = new Postfix("952--");
        Postfix p9 = new Postfix("04/");
        Postfix p10 = new Postfix("12345++++");
        Postfix p11 = new Postfix("25^");
        Postfix p12 = new Postfix("52^3+");

        System.out.println("\n\nPostfix tests");
        System.out.println("p1: " + p1);
        if (p1.solve() != 9) System.out.println("**p1 fail\n");
        else System.out.println("p1 pass\n");
        System.out.println("p2: " + p2);
        if (p2.solve() != 1) System.out.println("**p2 fail\n");
        else System.out.println("p2 pass\n");
        System.out.println("p3: " + p3);
        if (p3.solve() != 6) System.out.println("**p3 fail\n");
        else System.out.println("p3 pass\n");
        System.out.println("p4: " + p4);
        if (p4.solve() != 2) System.out.println("**p4 fail\n");
        else System.out.println("p4 pass\n");
        System.out.println("p5: " + p5);
        if (p5.solve() != 4) System.out.println("**p5 fail\n");
        else System.out.println("p5 pass\n");
        System.out.println("p6: " + p6);
        if (p6.solve() != 120) System.out.println("**p6 fail\n");
        else System.out.println("p6 pass\n");
        System.out.println("p7: " + p7);
        if (p7.solve() != 13) System.out.println("**p7 fail\n");
        else System.out.println("p7 pass\n");
        System.out.println("p8: " + p8);
        if (p8.solve() != 6) System.out.println("**p8 fail\n");
        else System.out.println("p8 pass\n");
        System.out.println("p9: " + p9);
        if (p9.solve() != 0) System.out.println("**p9 fail\n");
        else System.out.println("p9 pass\n");
        System.out.println("p10: " + p10);
        if (p10.solve() != 15) System.out.println("**p10 fail\n");
        else System.out.println("p10 pass\n");
        System.out.println("p11: " + p11);
        if (p11.solve() != 32) System.out.println("**p11 fail\n");
        else System.out.println("p11 pass\n");
        System.out.println("p12: " + p12);
        if (p12.solve() != 28) System.out.println("**p12 fail\n");
        else System.out.println("p12 pass\n");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc2403lab4;
import java.util.Stack;
        
/**
 *
 * @author Dustin
 */
public class Palindrome {
    public String word;
    
    public Palindrome(String x) { word = x; }
    
    public String toString() { return word; }
    
    public boolean isPalindrome() {
        Stack<Character> my_stack = new Stack();
        int letter= 0;
        if (word.length() == 1 || word.length() == 0){
            return true;
        }
        if (word.length() % 2 == 0){
            for (int i = 0; i <= word.length()/2; i++){
                my_stack.push(word.charAt(i));
                letter++;
            }
            while(!my_stack.isEmpty()){
                for (int i = 0; i <= word.length()/2; i++){
                    if (my_stack.pop().equals(word.charAt(i))){
                       // letter--;
                        return true;
                    }
                    else
                        return false;
               }
               
            }
        }
        if (word.length() % 2 != 0){
            for (int i = 0; i <= word.length()/2; i++){
                my_stack.push(word.charAt(i));
                letter++;
            }
            while(!my_stack.isEmpty()){
                for (int i = 0; i <= word.length()/2; i++){
                    if (my_stack.pop().equals(word.charAt(i))){
                        //letter--;
                        return true;
                    }
                    else
                        return false;
                }
            }
        }
        // Your code here.  return true if word is a palindrome
        // false otherwise.
        // Push each char the first half of the word on the stack.
        // Then look at each letter in the second half of the
        // word and compare to the values you are popping off.
        
        return false;
    }
}
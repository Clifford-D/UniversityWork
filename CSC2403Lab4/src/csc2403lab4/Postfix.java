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
public class Postfix {
    String expression;
    
    public Postfix(String x) { expression = x; }
    
    public String toString() { return expression; }
    
    public int solve() {
        Stack<Integer> my_stack = new Stack();
        Integer opTwo;
        Integer opOne;
        Integer results;
        for(int i=0; i<=expression.length(); i++)
        {
        // Your code goes here
            char input= expression.charAt(i) ;
            if (input == ' ')
                continue;
            if (input > '0' && input < '9') {
                Integer r = Character.getNumericValue(input);
                my_stack.push(r);
                //System.out.println(my_stack.peek());      // testing
                break;
            }
        else 
            switch(input)
            {
               /* case '9' : 
                    int r = Character.getNumericValue(input);
                    my_stack.push(r);
                    //System.out.println(my_stack.peek());      //testing
                break;*/
                case '+' :   
                    opOne = my_stack.pop();
                    opTwo = my_stack.pop();
                    results = opTwo + opOne;
                    my_stack.push(results);
                    break;
                case '-' :
                    opOne = my_stack.pop();
                    opTwo = my_stack.pop();
                    results = opTwo - opOne;
                    my_stack.push(results);
                    break;
                case '*' : 
                    opOne = my_stack.pop();
                    opTwo = my_stack.pop();
                    results = opTwo * opOne;
                    my_stack.push(results);
                    break;
                case '/' :    
                    opOne = my_stack.pop();
                    opTwo = my_stack.pop();
                    results = opTwo / opOne;
                    my_stack.push(results);
                    break;
                case '^' :
                    opOne = my_stack.pop();
                    opTwo = my_stack.pop();
                    results = (int)Math.pow(opTwo, opOne);
                    my_stack.push(results);
                    break;
                default:
                    break;
            }
        }
        return my_stack.peek(); // this should be the last statement of your
                                   // completed function
            
    }
}
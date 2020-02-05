/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: Dustin
 *
 * Created on March 2, 2017, 8:47 AM
 */

#include <cstdlib>
#include <iostream>
#include <cctype>

using namespace std;

/*
 * 
 */
string encrypt (string x, int s)
{ for (int i = 0; i < x.length(); i++)
    {
        if (isalpha(x[i])){
            if (isupper(x[i])){
                if ((x[i] + s) <= 90){
                x[i] = (x[i] + s);
                }
                else 
                    x[i] = (x[i] +s) - 26;
        }
            if (islower(x[i])){
                if ((x[i]+ s) <= 122){
                x[i] = (x[i] + s);
                }
            else
                x[i]= (x[i] +s) - 26;
        }
        }
    }
    return x;
}

string decrypt (string x, int s)
{ for (int i = 0; i < x.length(); i++)
    {
        if (isalpha(x[i])){
            if (isupper(x[i])){
                if ((x[i] - s) >= 65){
                x[i] = (x[i] - s);
                }
                else 
                    x[i] = (x[i] - s) + 26;
        }
            if (islower(x[i])){
            if ((x[i]- s) >= 97){
                x[i] = (x[i] - s);
                }
            else
                x[i] = (x[i] - s) + 26;
            }
        }
    }
    return x;
}

int main() {
    string in;
    int sh;
    char ch;
    cout << "Do you wish to encrypt or decrypt? (e for encrypt and d for decrypt) What is the shift value?";
    cin >> ch >> sh;
    if (sh > 26 || sh < 0){
        cout << "Shift is out of Range.";
        return 0;
    }
    switch (ch){
        case 'e' :
        {
            cout << "What do you want to encrypt?";
            cin.ignore();
            getline(cin,in);
            cout << encrypt(in, sh);
            break;            
        }
        case 'd' :
        {
            cout << "What do you want to decrypt?";
            cin.ignore();
            getline(cin,in);
            cout << decrypt(in, sh);
            break;            
        }
        default :
        {
            cout << "Not a valid entry.";
        }

    }
    return 0;
}


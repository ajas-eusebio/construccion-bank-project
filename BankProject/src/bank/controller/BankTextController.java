/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.controller;

import bank.model.BankTextReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class BankTextController {

    public String[][] getTable(String Filename) {
        int i, j;
        BankTextReader bankBuilder = new BankTextReader();
        ArrayList<String> input;
        input = bankBuilder.bankReader(Filename);
        String[][] output = new String[input.size()][4];
        String array;
        String accounts;
        String[] split;
        for (i = 0; i < input.size(); i++) {
            array = input.get(i);
            split = array.split(",");
            accounts = split[3];
            for (j = 6; j < split.length; j++) {
                accounts = accounts + ", " + split[j];
                j = j + 2;
            }
            output[i][3] = accounts;
            for (j = 0; j < 3; j++) {
                output[i][j] = split[j];
            }
        }
        return output;
    }

    public String[] getHeader() {
        String[] header = {"ID", "First Name", "Last Name", "Account", "Balance"};
        return header;
    }

    //Recibe un hashTable y busca una key, si existe, regresa una tabla con
    //la informacion del resultado de la busqueda. Si no existe, regresa
    //un apuntador null
    final int ROWS = 3;
    final int COLUMNS = 5;
    final int COLUMN_BALANCE = 4;
    final int COLUMN_ACCOUNT = 3;
    public String[][] getHashTable(String key) {
        BankTextReader bankBuilder = new BankTextReader();
        Hashtable<String, String> input = bankBuilder.bankHashReader("Bank.txt");
        String[][] output = new String[ROWS][COLUMNS];
        //Si existe el elemento
        if (input.containsKey(key)) {
            String line;
            String accounts;
            String[] split;
            //Recibe la linea del .txt correspondiente
            line = input.get(key);
            //y lo divide en los elementos necesarios para mostrarlo en una tabla
            split = line.split(",");
            //colocandolos en la matriz output
            for (int j = 0; j < ROWS; j++) {
                output[0][j] = split[j];
                output[1][j] = split[j];
                output[2][j] = split[j];
            }

            int indexFirstAccount = 5;
            int indexFirstBalance = 4;
            int nextItem = 3;
            for (int currRow = 0; currRow < ROWS; currRow++) {
                output[currRow][COLUMN_BALANCE] = split[indexFirstBalance];
                output[currRow][COLUMN_ACCOUNT] = split[indexFirstAccount];
                indexFirstBalance += nextItem;
                indexFirstAccount += nextItem;
            }
        } //Si el elemento no existe, se devuelve null
        else {
            output = null;
        }
        return output;
    }
}

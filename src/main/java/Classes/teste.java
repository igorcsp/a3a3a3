/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.Timestamp;

/**
 *
 * @author Igor
 */
public class teste {
    public static void main(String[] args) {
        // Obtém a data e hora atual
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        // Exibe a data e hora no formato SQL
        System.out.println("Data e hora atual no formato SQL: " + timestamp);
    }
}

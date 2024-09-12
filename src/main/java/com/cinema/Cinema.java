package com.cinema;

import java.util.ArrayList;


public class Cinema {


    private Seat[][] seats;


    public Cinema(int[] rows) {
        seats = new Seat[rows.length][];
        initSeats(rows);
    }


    private void initSeats(int[] rows) {
        for (int i = 0; i < rows.length; i++) {
            seats[i] = new Seat[rows[i]];
        }
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }


    public int countAvailableSeats() {
        int count = 0;
        for (int i = 0; i < seats.length; i++){
            for (int j = 0; j < seats[i].length;j++){
                if (seats[i][j].isAvailable()){
                    count+= 1;
                }
            }
        }
        return count;
    }


    public Seat findFirstAvailableSeatInRow(int row) {
        if (row < 0 || row >= seats.length) {
            return null;

        };
        for (int j = 0; j < seats[row].length; j++){
            if (seats[row][j].isAvailable()){
                return seats[row][j];
            }

        }
        return null;

    }


    public Seat findFirstAvailableSeat() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].isAvailable()){
                    return seats[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Busca las N butacas libres consecutivas en una fila. Si no hay, retorna null.
     *
     * @param row    fila en la que buscará las butacas.
     * @param amount el número de butacas necesarias (N).
     * @return La primer butaca de la serie de N butacas, si no hay retorna null.
     */
    public Seat getAvailableSeatsInRow(int row, int amount) {
        int count = 0;
        if(amount <= 0 || amount > seats[row].length){
           return null;
        };
        for (int i = 0; i < seats[row].length; i++) {
            if (seats[row][i].isAvailable()){
                count += 1;
            }
            else{
                count = 0;
            }
            if (count == amount){
                return seats[row][i - amount + 1];
            }
        }
        return null;
    }

    /**
     * Busca en toda la sala N butacas libres consecutivas. Si las encuentra
     * retorna la primer butaca de la serie, si no retorna null.
     *
     * @param amount el número de butacas pedidas.
     */
    public Seat getAvailableSeats(int amount) {
       int count = 0;
       if (amount < 0 || amount > seats.length){
           return null;
       }
       for (int i = 0; i < seats.length; i++) {
           for (int j = 0; j < seats[i].length; j++) {
               if (seats[i][j].isAvailable()) {
                   count += 1;
               }
               else{
                   count = 0;
               }
               if (count == amount) {
                   return seats[i][j - amount + 1];
               }
            }
        }
        return null;
    }

    /**
     * Marca como ocupadas la cantidad de butacas empezando por la que se le pasa.
     *
     * @param seat   butaca inicial de la serie.
     * @param amount la cantidad de butacas a reservar.
     */
    public void takeSeats(Seat seat, int amount) {
        int row = seat.getRow();
        int number = seat.getSeatNumber();

        if (seats[row].length >= number + amount) {
            for (int i = number; i < (number + amount); i++) {
                seats[row][i].takeSeat();
            }
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Not enough seats");
        }

    }

    /**
     * Libera la cantidad de butacas consecutivas empezando por la que se le pasa.
     *
     * @param seat   butaca inicial de la serie.
     * @param amount la cantidad de butacas a liberar.
     */
    public void releaseSeats(Seat seat, int amount) {
        int row = seat.getRow();
        int number = seat.getSeatNumber();

        if (seats[row].length >= number + amount) {
            for (int i = number; i < (number + amount); i++) {
                seats[row][i].releaseSeat();
            }
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Not enough seats");
        }

    }


}
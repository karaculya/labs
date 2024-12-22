package org.example;

import org.example.db.OperationsDB;
import org.example.db.RequestConstants;

public class Main {

    public static void main(String[] args) {
        OperationsDB operationsDB = new OperationsDB();

        operationsDB.sendRequest(RequestConstants.DROP_TABLES);
//        operationsDB.sendRequest(RequestConstants.CREATE_TABLE_ARTISTS);
//        operationsDB.sendRequest(RequestConstants.CREATE_TABLE_ALBUMS);
//        operationsDB.sendRequest(RequestConstants.CREATE_TABLE_COMPOSITIONS);
//        operationsDB.sendRequest(RequestConstants.INSERT_INTO_ARTISTS);
//        operationsDB.sendRequest(RequestConstants.INSERT_INTO_ALBUMS);
//        operationsDB.sendRequest(RequestConstants.INSERT_INTO_COMPOSITIONS);

//        operationsDB.createArtist("ACDC");
//        operationsDB.updateArtist("CDCA", 6);
//        operationsDB.deleteArtist(3);

//        operationsDB.groupByHaving();
    }
}
package com.example.e.a0509;

public class Database {

    private int _id;
    private string _dataname;

    public Database(){}

    public Database(string productname) {
        this._dataname = productname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_dataname(string _productname) {
        this._dataname = _productname;
    }

    public int get_id() {
        return _id;
    }

    public string get_dataname() {
        return _dataname;
    }
}

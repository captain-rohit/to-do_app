package com.example.taskscheduler;

public class models {
    private int _id;
    private String _name;
    private int _done;

    public models(){

    }
    public models(String taskname){
        this._name = taskname;
        this._done = 0;
    }
    public void set_id(int _id){
        this._id = _id;
    }
    public void set_name(String _name){
        this._name = _name;
    }
    public void set_done(int _done){
        this._done = _done;
    }
    public int get_id(){
        return _id;
    }
    public String get_name(){
        return _name;
    }
    public int get_done(){
        return _done;
    }
}

package com.company;

public class State {
    private int cityNum;
    private int nextIndex;
    private boolean startPoint;
    private State prev;

    public int getCityNumb() {
        return cityNum;
    }

    public void setCityNum(int cityNum) {
        this.cityNum = cityNum;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int nextIndex) {
        this.nextIndex = nextIndex;
    }

    public boolean isStartPoint() {
        return startPoint;
    }

    public void setStartPoint(boolean startPoint) {
        this.startPoint = startPoint;
    }

    public State getPrev() {
        return prev;
    }

    public void setPrev(State prev) {
        this.prev = prev;
    }

    public State(State prev, int city){
        this.prev=prev;
        cityNum=city;
        startPoint=false;
    }
    public State(State prev,int city,boolean start){
        this.prev=prev;
        cityNum=city;
        startPoint=start;
    }

    public int calculateLength(Graph graph){
        State current=this;
        int sum=0;

        while(current.prev!=null){
            sum+=graph.getEdge(current.prev.cityNum,current.cityNum);
            current=current.prev;
        }

        return sum;
    }

    @Override
    public String toString(){
        if(prev==null)return Integer.toString(cityNum+1);
        else{
            return prev.toString()+" "+Integer.toString(cityNum+1);
        }
    }
}

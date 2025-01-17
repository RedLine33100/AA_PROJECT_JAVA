package fr.gimlbl.aa.utils;

class MatriceLink {
    int from;
    int to;
    Integer t1, t2;

    public MatriceLink(int from, int to) {
        this.from = from;
        this.to = to;
        this.t1 = null;
        this.t2 = null;
    }

    public Integer getCost(int to) {
        if(this.to == to)
            return this.t2;
        if(this.from == to)
            return this.t1;
        return null;
    }

    public void setCost(int to, Integer cost) {
        if(this.to == to)
            this.t2 = cost;
        if(this.from == to)
            this.t1 = cost;
    }

    public boolean isCut(){
        return this.t1 == null && this.t2 == null;
    }

    public int getOpposite(int point){
        if(point == this.to)
            return this.from;
        return this.to;
    }
}

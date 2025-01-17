package fr.gimlbl.aa.utils;

public class MatriceAdjacence{

    private final ListInstance<ListInstance<MatriceLink>> matrix = new ListInstance<>();
    private final int size;

    public MatriceAdjacence(int point) {
        this.size = point;

        for(int i = 1; i <= size; i++) {
            ListInstance<MatriceLink> listInstance = new ListInstance<>();
            for(int j = 1; j <= size; j++){
                listInstance.addToTail(null);
            }
            matrix.addToTail(listInstance);
        }
        for(int i = 1; i <= size; i++) {
            for(int j = i; j <= size; j++){
                MatriceLink matriceLink = new MatriceLink(i, j);
                matrix.getElementByPosition(i).updateElementByPosition(matriceLink, j);
                matrix.getElementByPosition(j).updateElementByPosition(matriceLink, i);
            }
        }
    }

    public Integer getCost(int from, int to){
        return matrix.getElementByPosition(from).getElementByPosition(to).getCost(to);
    }

    public boolean setCost(int from, int to, int cost){
        ListInstance<MatriceLink> listInstance = matrix.getElementByPosition(from);
        if(listInstance == null) {
            return false;
        }
        MatriceLink matriceLink = listInstance.getElementByPosition(to);
        if(matriceLink == null) {
            return false;
        }
        matriceLink.setCost(to, cost);
        return true;
    }

    public int getOutDegree(int from){
        ListInstance<MatriceLink> listInstance = matrix.getElementByPosition(from);
        if(listInstance == null) {
            return -1;
        }
        int outDegree = 0;
        int count = 1;
        ListElement<MatriceLink> outElement = listInstance.getHead();
        while(outElement != null) {
            if(outElement.getElement().getCost(count) != null) {
                outDegree++;
            }
            outElement = outElement.getNext();
            count++;
        }
        return outDegree;
    }

    public int getInDegree(int to){
        ListInstance<MatriceLink> listInstance = matrix.getElementByPosition(to);
        if(listInstance == null) {
            return -1;
        }
        int inDegree = 0;
        ListElement<MatriceLink> outElement = listInstance.getHead();
        while(outElement != null) {
            if(outElement.getElement().getCost(to) != null) {
                inDegree++;
            }
            outElement = outElement.getNext();
        }
        return inDegree;
    }

    public boolean isConnexe(){
        ListElement<ListInstance<MatriceLink>> listElement = matrix.getHead();
        while(listElement != null) {
            ListElement<MatriceLink> mE = listElement.getElement().getHead();
            while(mE != null) {
                if(mE.getElement().isCut())
                    return false;
                mE = mE.getNext();
            }
            listElement = listElement.getNext();
        }
        return true;
    }

    /**
     *
     * @return List of List of point in abr
     */
    public ListInstance<ListInstance<Integer>> getSubConnexeAbr(){
        ListInstance<Integer> remainCalcul = new ListInstance<>();
        ListInstance<ListInstance<Integer>> subAbrInstance = new ListInstance<>();

        for(int i = 1; i <= size; i++) {
            remainCalcul.addToTail(i);
        }

        while(remainCalcul.size() != 0) {

            ListInstance<Integer> abr = new ListInstance<>();
            abr.addToHead(remainCalcul.getHead().getElement());
            remainCalcul.removeElementByPosition(1);

            ListElement<Integer> abr_cur_calcul = abr.getHead();
            while(abr_cur_calcul != null) {

                // Ici on vas chercher les OUT et IN et on vas les ajouter en tail de abr et les retirer de remainCalcul

                ListElement<MatriceLink> check_out_instance = this.matrix.getElementByPosition(abr_cur_calcul.getElement()).getHead();
                while (check_out_instance != null) {
                    if(!check_out_instance.getElement().isCut()){
                        Integer oppPoint = check_out_instance.getElement().getOpposite(abr_cur_calcul.getElement());
                        if(!remainCalcul.hasValue(oppPoint)) {
                            abr.addToTail(oppPoint);
                        }
                    }
                    check_out_instance = check_out_instance.getNext();
                }

                abr_cur_calcul = abr_cur_calcul.getNext();
            }



            subAbrInstance.addToHead(abr);

        }

        return subAbrInstance;
    }

}

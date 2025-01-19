package fr.gimlbl.aa.adt;

import fr.gimlbl.aa.adt.graph.Edge;
import fr.gimlbl.aa.adt.graph.WeightedGraph;
import fr.gimlbl.aa.adt.list.List;
import fr.gimlbl.aa.adt.list.ListElement;
import fr.gimlbl.aa.adt.list.ListInstance;

public class AdjacencyMatrix implements WeightedGraph {

    private final List<ListInstance<Edge>> matrix = new ListInstance<>();
    private final List<Edge> edgeList = new ListInstance<>();

    public AdjacencyMatrix(int point) {

        for(int i = 1; i <= point; i++) {
            ListInstance<Edge> listInstance = new ListInstance<>();
            for(int j = 1; j <= point; j++){
                listInstance.addToTail(null);
            }
            matrix.addToTail(listInstance);
        }
        for(int i = 1; i <= point; i++) {
            for(int j = i; j <= point; j++){
                Edge edge = new Edge(i,j, -1);
                matrix.getElementByPosition(i).updateElementByPosition(edge, j);
                matrix.getElementByPosition(j).updateElementByPosition(edge, i);
                this.edgeList.addToTail(edge);
            }
        }
    }

    @Override
    public int vertexCount() {
        return matrix.size();
    }

    @Override
    public List<Edge> getEdges() {
        List<Edge> edges = new ListInstance<>();
        ListElement<Edge> curHead = this.edgeList.getHead();
        while (curHead != null) {
            if(curHead.getElement().getWeight() != -1)
                edges.addToTail(curHead.getElement());
            curHead = curHead.getNext();
        }
        return edges;
    }

    @Override
    public int edgeCount() {
        return getEdges().size();
    }

    @Override
    public int edgeWeight(int from, int to){
        return matrix.getElementByPosition(from).getElementByPosition(to).getWeight();
    }

    @Override
    public void addEdge(int from, int to, int cost){
        ListInstance<Edge> listInstance = matrix.getElementByPosition(from);
        if(listInstance == null) {
            return;
        }
        Edge matriceLink = listInstance.getElementByPosition(to);
        if(matriceLink == null) {
            return ;
        }
        matriceLink.setWeight(cost);
        return;
    }

    @Override
    public List<Edge> getIncidentEdges(int from){
        List<Edge> incidentEdges = new ListInstance<>();

        ListInstance<Edge> listInstance = matrix.getElementByPosition(from);
        if(listInstance == null) {
            return incidentEdges;
        }

        ListElement<Edge> outElement = listInstance.getHead();
        while(outElement != null) {
            if(outElement.getElement().getWeight() != -1) {
                incidentEdges.addToTail(outElement.getElement());
            }
            outElement = outElement.getNext();
        }
        return incidentEdges;
    }

    public boolean isConnexe(){
        ListElement<ListInstance<Edge>> listElement = matrix.getHead();
        while(listElement != null) {
            ListElement<Edge> mE = listElement.getElement().getHead();
            while(mE != null) {
                if(mE.getElement().getWeight() == -1)
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

        for(int i = 1; i <= this.matrix.size(); i++) {
            remainCalcul.addToTail((Integer) i);
        }

        while(remainCalcul.size() != 0) {

            ListInstance<Integer> abr = new ListInstance<>();
            abr.addToHead(remainCalcul.getHead().getElement());
            remainCalcul.removeElementByPosition(1);

            ListElement<Integer> abr_cur_calcul = abr.getHead();
            while(abr_cur_calcul != null) {

                // Ici on vas chercher les OUT et IN et on vas les ajouter en tail de abr et les retirer de remainCalcul

                ListElement<Edge> check_out_instance = this.matrix.getElementByPosition(abr_cur_calcul.getElement()).getHead();
                while (check_out_instance != null) {
                    if(check_out_instance.getElement().getWeight() != -1){
                            abr.addToTail(check_out_instance.getElement().getOppositeVertex(abr_cur_calcul.getElement()));
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

package util.Graph;

import org.junit.jupiter.api.Test;
import util.LinkedList;

class GraphTest {

    @Test
    void shortPath() {
        Graph<String> map = new Graph<>();
        String cartago = "Cartago";
        String paraiso = "Paraiso";
        String san_jose = "San Jose";
        String heredia = "Heredia";
        String tilaran = "Tilaran";
        String hojancha = "Hojancha";
        String turrialba = "Turrialba";

        map.addElement(cartago);
        map.addElement(turrialba);
        map.addElement(san_jose);
        map.addElement(heredia);
        map.addElement(tilaran);
        map.addElement(hojancha);
        map.addElement(paraiso);

        //cartago
        map.connect(cartago, san_jose, 22);
        map.connect(cartago, paraiso, 7);
        map.connect(cartago, heredia, 30);
        // san jose
        map.connect(san_jose, tilaran, 15);
        map.connect(san_jose, heredia, 9);
        //tilaran
        map.connect(tilaran, hojancha, 5);
        //turrialba
        map.connect(turrialba, hojancha, 180);
        //paraiso
        map.connect(paraiso, heredia, 38);
        map.connect(paraiso, turrialba, 50);
        //heredia
        map.connect(heredia, hojancha, 6);
        map.connect(heredia, tilaran, 11);

        //System.out.println(map);
        LinkedList< DijkstraHelper<Vertex<String>>> list  = map.dijkstraAlgorithm(cartago);
        for (int i = 0; i < list.len; i++) {
            System.out.print("Elemento: " + list.getElement(i).getNode().getData());
            try{
                System.out.print("  Predecesor: " + list.getElement(i).getPre().getData());
            }
            catch (Exception ignored){ }
            System.out.print("  Peso: " + list.getElement(i).weight + "\n");
        }
        System.out.println(map.shortestPath(cartago, hojancha));
     }
}
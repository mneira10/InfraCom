package interfaz;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class PanelLista extends JPanel {

    private InterfazCliente principal;
    private JList<String> lista;

    public PanelLista(InterfazCliente principal) {
        this.principal  = principal;
        setLayout(new BorderLayout());

        lista = new JList<>(new DefaultListModel<>());
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sp = new JScrollPane(lista);
        sp.setPreferredSize(new Dimension(400, 220));
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(sp, BorderLayout.CENTER);
    }

    public void actualizarLista(String[] archivos){
        lista.setListData(archivos);
        if(archivos.length > 0){
            lista.setSelectedIndex(0);
        }
    }

    public String darArchivoSeleccionado() {
        return lista.getSelectedValue();
    }

    public void limpiar() {
        lista.setListData(new String[0]);
    }
}

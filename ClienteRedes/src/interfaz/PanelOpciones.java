package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelOpciones extends JPanel implements ActionListener{
	
	private static final String TITULO = "Opciones";
	private static final String CONECTAR = "Conectar";
	private static final String LISTAR = "Listar";
	private static final String DESCARGAR = "Descargar";
	
	private final InterfazCliente frmPrincipal;
	
	private final JButton btnConectar;
	private final JButton btnListar;
	private final JButton btnVerLineas;
	
	public PanelOpciones(InterfazCliente principal){
		frmPrincipal = principal;
		GridLayout layout =new GridLayout(1, 3);
		layout.setHgap(20);
		setLayout(layout);
		setBorder(BorderFactory.createTitledBorder(TITULO));
		
		btnConectar = new JButton(CONECTAR);
		btnConectar.setActionCommand(CONECTAR);
		btnConectar.addActionListener(this);
		add(btnConectar);
		
		btnListar = new JButton(LISTAR);
		btnListar.setActionCommand(LISTAR);
		btnListar.addActionListener(this);
		add(btnListar);
		
		btnVerLineas = new JButton(DESCARGAR);
		btnVerLineas.setActionCommand(DESCARGAR);
		btnVerLineas.addActionListener(this);
		add(btnVerLineas);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		if(com.equals(CONECTAR)){
			frmPrincipal.conectar();
		}else if(com.equals(LISTAR)){
			frmPrincipal.listar();
		}else if(com.equals(DESCARGAR)){
			frmPrincipal.descargar();
		}
	}
}

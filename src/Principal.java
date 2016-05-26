import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton button;
	private JSlider sliderEdad;
	private JSlider sliderPeso;
	private JSlider sliderEstatura;
	private JLabel valorPeso;
	private JLabel valorEstatura;
	private JLabel valorEdad;
	private double edadNitida,glucosaNitida,aminoacidosNitido,imcNitido,nivMemCondicion,condicionNitido;
	private String edadDifusa,glucosaDifusa,aminoacidosDifuso,imcDifuso,condicionDifusa;
	private JTextPane textPane;
	private JTextArea textArea;
	private JSpinner spinnerGlucosa;
	private JSpinner spinnerAminoacidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Principal frame = new Principal();
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sliderEdad = new JSlider();
		sliderEdad.setMinimum(1);
		sliderEdad.setValue(0);
		sliderEdad.setBounds(83, 68, 200, 26);
		sliderEdad.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				valorEdad.setText(String.valueOf(sliderEdad.getValue()));
			}
		});
		contentPane.add(sliderEdad);
		
		JLabel label = new JLabel("Edad");
		label.setBounds(10, 72, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Glucosa");
		label_1.setBounds(10, 108, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Amino\u00E1cidos");
		label_2.setBounds(10, 142, 68, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Peso (Kg)");
		label_3.setBounds(321, 72, 68, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Estatura (cm)");
		label_4.setBounds(321, 108, 68, 14);
		contentPane.add(label_4);
		
		sliderPeso = new JSlider();
		sliderPeso.setMinimum(10);
		sliderPeso.setMaximum(140);
		sliderPeso.setValue(0);
		sliderPeso.setBounds(399, 68, 200, 26);
		sliderPeso.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				valorPeso.setText(String.valueOf(sliderPeso.getValue()));
			}
		});
		contentPane.add(sliderPeso);
		
		sliderEstatura = new JSlider();
		sliderEstatura.setMinimum(30);
		sliderEstatura.setMaximum(220);
		sliderEstatura.setValue(0);
		sliderEstatura.setBounds(399, 105, 200, 26);
		sliderEstatura.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				valorEstatura.setText(String.valueOf(sliderEstatura.getValue()));
			}
		});
		contentPane.add(sliderEstatura);
		
		button = new JButton("Obtener resultados");
		button.setBounds(485, 147, 145, 23);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean err;
				if(err=(sliderEstatura.getValue()==sliderEstatura.getMinimum() && 
						((float)spinnerAminoacidos.getValue())==30 &&
				   sliderEdad.getValue()==sliderEdad.getMinimum() && sliderPeso.getValue()==sliderPeso.getMinimum()
				   && ((float)spinnerGlucosa.getValue())==40)){	
				    JOptionPane.showMessageDialog(null, "Al parecer estos son los valores inciales de "
				    		+ "la aplicación.\nTe sugerimos adecuarlos a tus necesidades.\n"
				    		+ "Estos datos podrían brindar resultados inesperados.","Advertencia",JOptionPane.WARNING_MESSAGE);
				}
				StringBuilder str = new StringBuilder();
				condicionNitido=0.0;
				condicionDifusa="";
				edadDifusa=SistemaFuzzyDiabetes.fuzzificarEdad(edadNitida);
				glucosaDifusa=SistemaFuzzyDiabetes.fuzzificarGlucosa(glucosaNitida);
				aminoacidosDifuso=SistemaFuzzyDiabetes.fuzzificarAminoacidos(aminoacidosNitido);
				imcDifuso=SistemaFuzzyDiabetes.fuzzificarIMC(imcNitido);
				if(err) str.append("CONDICIONES INICIALES DETECTADAS\n\n");
				str.append("Para Edad="+edadNitida+" corresponde: "+edadDifusa+"\n");
				str.append("Para Glucosa="+glucosaNitida+" corresponde: "+glucosaDifusa+"\n");
				str.append("Para Aminoacidos="+aminoacidosNitido+" corresponde: "+aminoacidosDifuso+"\n");
				str.append("Para IMC="+imcNitido+" corresponde: "+imcDifuso+"\n");
				condicionDifusa=SistemaFuzzyDiabetes.inferirCondicionDifusaCualitativo(edadDifusa, glucosaDifusa, aminoacidosDifuso, imcDifuso);
				str.append("La condición potencial que tienes es: "+condicionDifusa);       
		        nivMemCondicion=SistemaFuzzyDiabetes.inferirCondicionDifusaCuantitativo(edadDifusa, glucosaDifusa, aminoacidosDifuso, imcDifuso);
		        str.append(", con una certeza de: "+nivMemCondicion+"\n"); 
		        SistemaFuzzyDiabetes.muestraNivMemMay("Membresías Condición",SistemaFuzzyDiabetes.getNivsMemCondicionDiabetico());
		        condicionNitido=SistemaFuzzyDiabetes.desfuzzificar(condicionDifusa,nivMemCondicion);
		        // Notificar en consola resultados de la DesfuzzificaciÃ³n
		        str.append("Desfuzzificación correspondiente: "+condicionNitido+"\n");
		        textArea.setText(str.toString());
		        textPane.setText("Evaluación terminada");
			}
		});
		contentPane.add(button);
		
		JButton button_1 = new JButton("Ingresar Datos");
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				float pow = (float)sliderEstatura.getValue()/100;
				pow*=pow;
				if(sliderEdad.getValue()!=0){
					edadNitida=sliderEdad.getValue();
					glucosaNitida=(float) spinnerGlucosa.getValue();       
					aminoacidosNitido=(float)spinnerAminoacidos.getValue();
					imcNitido=sliderPeso.getValue()/pow;
					textArea.setText("");
					textPane.setText("Datos cargados");
				}else{
					textPane.setText("Error cargando datos. La edad no puede ser 0");
				}
			}
		});
		button_1.setBounds(330, 148, 145, 23);
		contentPane.add(button_1);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(0, 359, 669, 54);
		contentPane.add(textPane);
		SistemaFuzzyDiabetes.inicializar();
		JLabel lblNewLabel = new JLabel("Sistema Fuzzy de Diagn\u00F3stico de Diabetes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 32, 325, 25);
		contentPane.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 669, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("A\u00F1adir de archivo");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Ayuda");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Acerca de");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Informaci\u00F3n");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 182, 620, 166);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(textArea);
		
		valorEdad = new JLabel(String.valueOf(sliderEdad.getValue()));
		valorEdad.setBounds(289, 72, 46, 14);
		contentPane.add(valorEdad);
		
		valorPeso = new JLabel(String.valueOf(sliderPeso.getValue()));
		valorPeso.setBounds(611, 72, 46, 14);
		contentPane.add(valorPeso);
		
		valorEstatura = new JLabel(String.valueOf(sliderEstatura.getValue()));
		valorEstatura.setBounds(611, 108, 46, 14);
		contentPane.add(valorEstatura);
		
		spinnerGlucosa = new JSpinner();
		spinnerGlucosa.setModel(new SpinnerNumberModel(new Float(40), new Float(40), new Float(200), new Float(.1)));
		spinnerGlucosa.setBounds(83, 105, 200, 20);
		contentPane.add(spinnerGlucosa);
		
		spinnerAminoacidos = new JSpinner();
		spinnerAminoacidos.setModel(new SpinnerNumberModel(new Float(30), new Float(30), new Float(230), new Float(.1)));
		spinnerAminoacidos.setBounds(83, 139, 200, 20);
		contentPane.add(spinnerAminoacidos);
				

		
	}
}

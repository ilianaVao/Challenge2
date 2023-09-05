package conversor;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.Font;
import java.awt.Color;

public class MiConversor {

	private JFrame frmConversorAlura;
	private JTextField txt;
	private JButton btn;
	private JComboBox<Moneda> cmb;
	private JLabel lbl;
	
	public enum Moneda{
		Pesos_dolar,
		Pesos_Euro,
		Pesos_Libra,
		Pesos_Yen,
		Pesos_Won,
		Dolar_Pesos,
		Euro_Pesos,
		Libra_Pesos,
		Yen_Pesos,
		Won_Pesos
	}
	
	public double dolar = 17.18;
	public double euro = 18.54;
	public double libra = 21.70;
	public double yen = 0.12;
	public double won = 0.013;
	
	public double valorInput = 0.00;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frmConversorAlura.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConversorAlura = new JFrame();
		frmConversorAlura.setTitle("Conversor Alura");
		frmConversorAlura.getContentPane().setBackground(new Color(70, 130, 180));
		frmConversorAlura.setBounds(100, 100, 450, 300);
		frmConversorAlura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConversorAlura.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		txt.setBounds(10, 11, 123, 20);
		frmConversorAlura.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(10, 59, 123, 22);
		frmConversorAlura.getContentPane().add(cmb);
		
		// evento boton
		btn = new JButton("Convertir");
		btn.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(161, 59, 123, 23);
		frmConversorAlura.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lbl.setBounds(161, 11, 89, 20);
		frmConversorAlura.getContentPane().add(lbl);
	}
	
	// funcion del boton
	public void Convertir() {
		// validacion de números
		if(Validar(txt.getText())) {
			Moneda moneda = (Moneda) cmb.getSelectedItem();
			
			switch (moneda) {
			
			case Pesos_dolar: 
				PesosAMoneda(dolar);
				break;
			case Pesos_Euro: 
				PesosAMoneda(euro);
				break;
			case Pesos_Libra: 
				PesosAMoneda(libra);
				break;
			case Pesos_Yen: 
				PesosAMoneda(yen);
				break;
			case Pesos_Won: 
				PesosAMoneda(won);
				break;
			case Dolar_Pesos: 
				MonedaAPesos(dolar);
				break;
			case Euro_Pesos: 
				MonedaAPesos(euro);
				break;
			case Libra_Pesos: 
				MonedaAPesos(libra);
				break;
			case Yen_Pesos: 
				MonedaAPesos(yen);
				break;
			case Won_Pesos: 
				MonedaAPesos(won);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			
			}		
		}
	}
	
	public void PesosAMoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
	}
	
	public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));
	}
	
	// redondear decimales
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	// validar input
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if(x > 0);
			valorInput = x;
			return true;
		}catch(NumberFormatException e) {
			lbl.setText("Solo se aceptan numeros");
			return false;
		}
	}
}

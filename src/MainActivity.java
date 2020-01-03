import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class MainActivity extends JFrame {

	private JPanel contentPane;
	static MainActivity framePrincipal = new MainActivity();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					framePrincipal.setUndecorated(true);
					framePrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainActivity() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainActivity.class.getResource("/Imagens/favicon.png")));
		setTitle("Mountain Battleship");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 370);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 351, 381);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDevelopedByMauricio = new JLabel("Developed by Mauricio Montanha Junior");
		lblDevelopedByMauricio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDevelopedByMauricio.setForeground(Color.DARK_GRAY);
		lblDevelopedByMauricio.setBounds(0, 319, 341, 84);
		panel.add(lblDevelopedByMauricio);
		
		JLabel lblTeste = new JLabel("TESTE HUNTERCO");
		lblTeste.setBounds(-21, 11, 388, 30);
		panel.add(lblTeste);
		lblTeste.setForeground(Color.BLACK);
		lblTeste.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeste.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainActivity.class.getResource("/Imagens/fundo.jpg")));
		label.setBounds(-189, -90, 582, 542);
		panel.add(label);
		
		// ---------------------Valores--------------------------------
		Button btnValores = new Button("Valores Faturados e Perdidos");
		btnValores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					ValuesActivity valuesActivity = new ValuesActivity();
					valuesActivity.setVisible(true);
					valuesActivity.setLocationRelativeTo(null);
					valuesActivity.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnValores.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnValores.setForeground(Color.WHITE);
		btnValores.setBackground(new Color(0, 139, 139));
		btnValores.setBounds(371, 59, 332, 50);
		contentPane.add(btnValores);
		
		// ---------------------Mais Regeitados-------------------------
		Button btnMaisReg = new Button("Candidatos Mais Rejeitados");
		btnMaisReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CandidateActivity candidateActivity = new CandidateActivity();
					candidateActivity.setVisible(true);
					candidateActivity.setLocationRelativeTo(null);
					candidateActivity.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} catch (Exception abs) {
					abs.printStackTrace();
				}
			}
		});
		btnMaisReg.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnMaisReg.setForeground(Color.WHITE);
		btnMaisReg.setBackground(new Color(0, 139, 139));
		btnMaisReg.setBounds(371, 131, 332, 50);
		contentPane.add(btnMaisReg);
				
		// -----------------Clientes q mais regeitaram--------------------
		Button btnCliMaisReg = new Button("Clientes que Mais Regeitaram");
		btnCliMaisReg.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCliMaisReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ClientActivity clientActivity = new ClientActivity();
					clientActivity.setVisible(true);
					clientActivity.setLocationRelativeTo(null);
					clientActivity.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} catch (Exception abs) {
					abs.printStackTrace();
				}
			}
		});
		btnCliMaisReg.setForeground(SystemColor.text);
		btnCliMaisReg.setBackground(new Color(0, 139, 139));
		btnCliMaisReg.setBounds(371, 200, 332, 50);
		contentPane.add(btnCliMaisReg);
		
		
		// ------------------Regeitaram todos---------------------------
		Button btnRegTodos = new Button("Clientes que Regeitaram Todos");
		btnRegTodos.setForeground(Color.WHITE);
		btnRegTodos.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRegTodos.setBackground(new Color(0, 139, 139));
		btnRegTodos.setBounds(371, 271, 332, 50);
		btnRegTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ClientAllActivity clientAllActivity = new ClientAllActivity();
					clientAllActivity.setVisible(true);
					clientAllActivity.setLocationRelativeTo(null);
					clientAllActivity.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} catch (Exception abs) {
					abs.printStackTrace();
				}
			}
		});
		contentPane.add(btnRegTodos);

		// ---------------------CLOSE--------------------------------
		JLabel lblClose = new JLabel("x");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblClose.setForeground(new Color(0, 139, 139));
		lblClose.setBounds(704, 0, 24, 25);
		contentPane.add(lblClose);
		
		// ---------------------Minimize--------------------------------
		JLabel lblMinimize = new JLabel("-");
		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		lblMinimize.setForeground(new Color(0, 139, 139));
		lblMinimize.setFont(new Font("Segoe Print", Font.BOLD, 20));
		lblMinimize.setBounds(679, 0, 13, 25);
		contentPane.add(lblMinimize);
	}
}

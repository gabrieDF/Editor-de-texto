package Edicao;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Editor extends JFrame {
	private JLabel label1;
	private JButton  btLimpar, btNegrito;
	private JTextField tfTexto;
	private TextArea taTexto;
	private FileDialog fdAbrir, fdSalvar;
	private String nome_do_arquivo;

	
	private JMenuBar mnBarra;
	private JMenu mnArquivo, mnFontes, mnCor, mnTamanho;
	private JMenuItem miAbrir, miSalvar, miSair;
	private JMenuItem miVerde, miRosa, miAmarelo;
	private JMenuItem miArial, miItalic, miSerif;
	private JMenuItem miVinte, mitrintadois, miquarentaquatro; 
	
	
	private String fonte="Verdana";

	private int tamanho = 12, n;
	
	public static void main(String args[]) {
		JFrame frame = new Editor();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public Editor() {
		inicializarComponentes();
		definirEventos();
	}

	public void inicializarComponentes() {

		setTitle("Editor de Texto");
		setLayout(null);
		setBounds(250, 50, 500, 300);
		setResizable(false);

		label1 = new JLabel("Digite seu texto:");
		label1.setBounds(5, 5, 200, 20);
		

		btNegrito = new JButton("Negrito");
		btNegrito.setBounds(15, 210, 80, 25);

		btLimpar = new JButton("Limpar");
		btLimpar.setBounds(390, 210, 80, 25);

		tfTexto = new JTextField();
		tfTexto.setBounds(50, 240, 430, 20);
		tfTexto.setEditable(false);
		taTexto = new TextArea();
		taTexto.setBounds(5, 25, 480, 180);
		fdAbrir = new FileDialog(this, "Abrir arquivo", FileDialog.LOAD);
		fdSalvar = new FileDialog(this, "Salvar arquivo", FileDialog.SAVE);

		add(label1);
		add(tfTexto);
		add(taTexto);
		add(btLimpar);
		add(btNegrito);


		mnBarra = new JMenuBar();

		mnArquivo = new JMenu("Arquivo");
		mnFontes = new JMenu("Fontes");
		mnCor = new JMenu("Cores");
		mnTamanho = new JMenu("Tamanho");

		miAbrir = new JMenuItem("Abrir");
		miSalvar = new JMenuItem("Salvar");
		miSair = new JMenuItem("Sair");

		miVerde = new JMenuItem("Verde");
		miRosa = new JMenuItem("Rosa");
		miAmarelo = new JMenuItem("Amarelo");

		miArial = new JMenuItem("Arial");
		miItalic = new JMenuItem("Italic");
		miSerif = new JMenuItem("Serif");
		
		miVinte = new JMenuItem("20");
		mitrintadois = new JMenuItem("32");
		miquarentaquatro = new JMenuItem("44");

		mnArquivo.add(miAbrir);
		mnArquivo.add(miSalvar);
		mnArquivo.add(miSair);

		mnCor.add(miVerde);
		mnCor.add(miRosa);
		mnCor.add(miAmarelo);

		mnFontes.add(miArial);
		mnFontes.add(miItalic);
		mnFontes.add(miSerif);

		mnBarra.add(mnArquivo);
		mnBarra.add(mnFontes);
		mnBarra.add(mnCor);
		mnBarra.add(mnTamanho);
		
		mnTamanho.add(miVinte);
		mnTamanho.add(mitrintadois);
		mnTamanho.add(miquarentaquatro);
		

		setJMenuBar(mnBarra);

	}

	public void definirEventos() {
		miAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fdAbrir.setVisible(true);
					if (fdAbrir.getFile() == null) {
						return;
					}
					nome_do_arquivo = fdAbrir.getDirectory() + fdAbrir.getFile();
					FileReader in = new FileReader(nome_do_arquivo);
					String s = "";
					int i = in.read();
					while (i != -1) {
						s = s + (char) i;
						i = in.read();
					}
					taTexto.setText(s);
					in.close();
					tfTexto.setText("Arquivo aberto com sucesso");
				} catch (IOException erro) {
					tfTexto.setText("Erro ao abrir o arquivo" + erro.toString());
				}

			}
		});
		miSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fdSalvar.setVisible(true);
					if (fdSalvar.getFile() == null) {
						return;
					}
					nome_do_arquivo = fdSalvar.getDirectory() + fdSalvar.getFile();
					FileWriter out = new FileWriter(nome_do_arquivo);
					out.write(taTexto.getText());
					out.close();
					tfTexto.setText("Arquivo gravado com sucesso");
				} catch (IOException erro) {
					tfTexto.setText("Erro ao gravar o arquivo" + erro.toString());
				}

			}
		});
		miSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTexto.setText("");
				tfTexto.setText("");
			}
		});
		miVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTexto.setForeground(Color.green);
			}
		});
		miRosa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTexto.setForeground(Color.pink);
			}
		});
		miAmarelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTexto.setForeground(Color.yellow);
			}
		});
		miArial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fonte="SansSerif";
				taTexto.setFont(new Font(fonte, n, tamanho));
			}
		});
		miItalic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fonte="Monospaced";
				taTexto.setFont(new Font(fonte, n, tamanho));
			}
		});
		miSerif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fonte="Serif";
				taTexto.setFont(new Font(fonte, n, tamanho));
			}
		});
		miVinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					tamanho = 20;
					taTexto.setFont(new Font(fonte, n, tamanho));
				}
			}
		);
		 mitrintadois.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					tamanho = 32;
					taTexto.setFont(new Font(fonte, n, tamanho));
				}
			}
		);
		miquarentaquatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					tamanho = 42;
					taTexto.setFont(new Font(fonte, n, tamanho));
				}
			}
		);
		btNegrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				n = 1;
				taTexto.setFont(new Font(fonte, n, tamanho));
			}
		});
	}
}

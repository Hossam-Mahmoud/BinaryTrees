import java.io.File;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Label;


public class GUI {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private BinTree tree;
	private Text text_3;
	private Text text_4;
	private Button btnSearch;
	private Text text_5;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GUI window = new GUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(594, 497);
		shell.setText("SWT Application");
		shell.setLayout(null);
		shell.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				  
			 
			}
		}) ;
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(new Shell(), SWT.OPEN);
				fd.setFilterNames(new String[]{"Text Files"});
				fd.setFilterExtensions(new String[]{"*.txt"});
				try {
					Scanner reader = new Scanner(new File(fd.open()));
					int head = reader.nextInt();
					tree = new BinTree(head);
					while(reader.hasNext()){
						tree.insert(reader.nextInt());
					}
					reader.close();
				} catch (Exception e1) {
					
				}
				
			}
		});
		btnNewButton.setBounds(242, 412, 99, 37);
		btnNewButton.setText("Read from file");
		
		Button btnPreorder = new Button(shell, SWT.NONE);
		btnPreorder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					text.setText(tree.traverse(1).replace(" ", " , "));
				}catch(Exception e1){
					
				}
			}
		});
		btnPreorder.setBounds(10, 10, 75, 25);
		btnPreorder.setText("Pre-order");
		
		Button btnInorder = new Button(shell, SWT.NONE);
		btnInorder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					text_1.setText(tree.traverse(2).replace(" ", " , "));
				}catch(Exception e1){
					
				}
			}
		});
		btnInorder.setBounds(10, 41, 75, 25);
		btnInorder.setText("In-order");
		
		Button btnPostorder = new Button(shell, SWT.NONE);
		btnPostorder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					text_2.setText(tree.traverse(3).replace(" ", " , "));
				}catch(Exception e1){
					
				}
			}
		});
		btnPostorder.setText("Post-order");
		btnPostorder.setBounds(10, 72, 75, 25);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(91, 14, 477, 21);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(91, 45, 477, 21);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(91, 76, 477, 21);
		
		Button btnInsert = new Button(shell, SWT.NONE);
		btnInsert.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					tree.insert(Integer.parseInt(text_3.getText()));
				}catch(Exception e1){
					text_3.setText("");
				}
			}
		});
		btnInsert.setBounds(10, 103, 75, 25);
		btnInsert.setText("Insert");
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(91, 105, 76, 21);
		
		Button btnRemove = new Button(shell, SWT.NONE);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					tree.remove(Integer.parseInt(text_4.getText()));
				}catch(Exception e1){
					text_4.setText("");
				}
			}
		});
		btnRemove.setText("Remove");
		btnRemove.setBounds(173, 103, 75, 25);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(254, 105, 76, 21);
		
		final Label label = new Label(shell, SWT.NONE);
		label.setBounds(499, 108, 69, 25);
		
		btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					boolean found = tree.searchFor(Integer.parseInt(text_5.getText()));
					label.setText(""+found);
				}catch(Exception e1){
					label.setText("");
				}
				
			}
		});
		btnSearch.setText("Search");
		btnSearch.setBounds(336, 103, 75, 25);
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setBounds(417, 105, 76, 21);
		
		
		

	}
}

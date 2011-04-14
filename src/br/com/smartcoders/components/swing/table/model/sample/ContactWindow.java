package br.com.smartcoders.components.swing.table.model.sample;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

@SuppressWarnings("serial")
public class ContactWindow extends JFrame {

	private JTable contacts;
	private ContactTableModel tableModel;

	private JButton add;
	private JButton remove;

	private ContactPanel contactPanel;

	public ContactWindow() {
		initialize();
		layoutComponents();
		linkListeners();
	}

	private void initialize() {
		contactPanel = new ContactPanel();

		add = new JButton("Add");
		remove = new JButton("Remove");

		tableModel = new ContactTableModel();
		contacts = new JTable(tableModel);

		contactPanel = new ContactPanel();

		setTitle("AnnotationTableModel Sample");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void layoutComponents() {
		String columns = "pref:grow, pref, 3dlu, pref";
		String lines = "pref:grow, 3dlu, pref:grow, 3dlu, pref";

		FormLayout layout = new FormLayout(columns, lines);

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		builder.add(new JScrollPane(contacts), CC.xyw(1, 1, 4));
		builder.add(contactPanel, CC.xyw(1, 3, 4));
		builder.add(add, CC.xy(2, 5));
		builder.add(remove, CC.xy(4, 5));

		setLayout(new BorderLayout());
		add(builder.getPanel(), BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
	}

	private void linkListeners() {
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addContact();
			}
		});
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteRows();
			}
		});
		contacts.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						showContact();
					}
				});
	}

	private void addContact() {
		Contact contact = contactPanel.getContact();
		tableModel.add(contact);
		contactPanel.clear();
		contacts.clearSelection();
	}

	private void deleteRows() {
		int[] selectedRows = contacts.getSelectedRows();
		List<Contact> toBeDeleted = new ArrayList<Contact>();

		for (int row : selectedRows) {
			toBeDeleted.add(tableModel.get(row));
		}

		tableModel.removeAll(toBeDeleted);
		contacts.clearSelection();
	}

	private void showContact() {
		int selectedRow = contacts.getSelectedRow();
		if (selectedRow != -1) {
			contactPanel.setContact(tableModel.get(selectedRow));
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new ContactWindow();
				frame.setVisible(true);
			}
		});
	}
}

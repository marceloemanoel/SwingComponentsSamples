package br.com.smartcoders.components.swing.table.model.sample;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.smartcoders.components.swing.mask.MaskFactory;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

@SuppressWarnings("serial")
public class ContactPanel extends JPanel {

	private JTextField name;
	private JTextField email;
	private JTextField phone;

	public ContactPanel() {
		initialize();
		layoutComponents();
	}

	private void initialize() {
		name = new JTextField();
		name.requestFocusInWindow();

		email = new JTextField();

		phone = new JFormattedTextField(
				MaskFactory.createMask("(##) ####-####"));
		setBorder(BorderFactory.createTitledBorder("Contact"));
	}

	private void layoutComponents() {
		String columns = "right:pref, 3dlu, pref:grow, 3dlu, right:pref, 3dlu, pref:grow";
		String lines = "pref, 3dlu, pref";

		FormLayout layout = new FormLayout(columns, lines);
		layout.addGroupedColumn(3);
		layout.addGroupedColumn(7);

		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		builder.addLabel("Name", CC.xy(1, 1));
		builder.add(name, CC.xy(3, 1));
		builder.addLabel("Email", CC.xy(5, 1));
		builder.add(email, CC.xy(7, 1));
		builder.addLabel("Phone", CC.xy(1, 3));
		builder.add(phone, CC.xy(3, 3));

		setLayout(new BorderLayout());
		add(builder.getPanel(), BorderLayout.CENTER);
	}

	public Contact getContact() {
		Contact contact = new Contact(name.getText());
		contact.setEmail(email.getText());
		contact.setPhoneNumber(phone.getText());
		contact.setBirthday(new Date());

		return contact;
	}

	public void setContact(Contact contact) {
		name.setText(contact.getName());
		email.setText(contact.getEmail());
		phone.setText(contact.getPhoneNumber());
	}

	public void clear() {
		final String BLANK = "";
		
		name.setText(BLANK);
		email.setText(BLANK);
		phone.setText(BLANK);
	}

}

package br.com.smartcoders.components.swing.table.model.sample;

import java.util.Date;

import br.com.smartcoders.components.swing.table.model.AnnotationTableModel;
import br.com.smartcoders.components.swing.table.model.mapping.annotations.Column;

/**
 * Simple table model with annotations.
 * Each method annotated with <code>@Column</code> represents a column with index and name.
 * To be annotated the method should follow a simple convention:
 * <code>
 *  @Column(index= columnIndex, name= columnName, editable=[true|false])
 * 	(private|public|protected)* ColumnType methodName(RowInstance)
 * </code>
 * 
 *  The number of rows and columns is automatically inferred by the TableModel, as well as 
 *  the column types.
 *  
 *  Each time the table model needs a value it will call the respective method annotated
 *  with the column index passing the row value.
 *  
 * @author marceloemanoel
 */
@SuppressWarnings({ "serial", "unused" })
public class ContactTableModel extends AnnotationTableModel<Contact> {

	@Column(index = 0, name = "Name")
	private String getName(Contact contact) {
		return contact.getName();
	}

	@Column(index = 1, name = "Email")
	private String getEmail(Contact contact) {
		return contact.getEmail();
	}
	
	//Not included in the table since it's not annotated with @Column
	private Date getBirhday(Contact contact) {
		return contact.getBirthday();
	}

	@Column(index = 2, name = "Phone")
	private String getPhoneNumber(Contact contact) {
		return contact.getPhoneNumber();
	}
}

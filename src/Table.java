import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Table extends AbstractTableModel {

	private ArrayList<String> columnNames = new ArrayList<>();
	private ArrayList<ArrayList<Object>> data = new ArrayList<>();
	private int noOfColumns = 0;

	public Table(Class objectClass) {
		int i = 0;
		for (Field field : objectClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(AnnotationColumns.class)) {
				for (Annotation it : field.getDeclaredAnnotations()) {
					if (it instanceof AnnotationColumns) {
						columnNames.add(((AnnotationColumns) it).columnName());
					}
				}
				i++;
			}
		}
		noOfColumns = i;
	}
	
	public void removeRow(int row){
		data.remove(row);
	}

	public ArrayList<Object> getRow(int row){
		return data.get(row);
	}
	public int getRowCount() {
		return data.size();
	}

	public int getColumnCount() {
		return noOfColumns;
	}

	public String getColumnName(int columnIndex) {
		return (String) columnNames.get(columnIndex);
	}

	public Object getValueAt(int row, int col) {
		return data.get(row).get(col);
	}

	public void setValueAt(Object value, int row, int col) {
		data.get(row).set(col, value);
	}

	public Class getColumnClass(int columnIndex) {
		return columnNames.get(columnIndex).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		if(row == data.size()-1)
			return true;
		return false;
	}

	public boolean isCellNull(int row, int column) {
		if (data.get(row).get(column).toString().isEmpty())
			return true;
		return false;
	}

	public void addRow() {
		ArrayList<Object> row = new ArrayList<>();
		for (int i = 0; i < noOfColumns; i++) {
			row.add("");
		}
		data.add(row);
	}
}

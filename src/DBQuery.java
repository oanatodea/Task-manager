import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

public class DBQuery 
{
	private DBConnect bdc = new DBConnect();
	private Statement selectStatement = null;
	private ResultSet rs = null;
	
	public boolean statement(String interogare)
	{
		try
		{
			bdc.conectare();
			selectStatement = (bdc.getConexiune()).createStatement();
			selectStatement.execute(interogare);
			rs = selectStatement.getResultSet();
		}
		catch(SQLException sqlex)
		{
			/**
			 * Mesaj de eroare.
			 */
			JOptionPane.showMessageDialog(null, "Verificarea datelor nu a reusit...\n"+sqlex.getMessage(),
										  "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void inchideStatement()
	{
		if (selectStatement != null) 
		{ 
            try 
            { 
            	bdc.deconectare();
                selectStatement.close();
                selectStatement = null;
            } 
            catch(SQLException e) 
            {
    			/**
    			 * Mesaj de eroare.
    			 */
    			JOptionPane.showMessageDialog(null, "Inchiderea conexiuni la baza de date nu a reusit...",
    										  "Error", JOptionPane.ERROR_MESSAGE);
            }    
         }
		if (rs != null) 
		{ 
            try 
            { 
                rs.close();
                rs = null; 
            } 
            catch(SQLException e) 
            {
    			/**
    			 * Mesaj de eroare.
    			 */
            	JOptionPane.showMessageDialog(null, "Inchiderea conexiuni la baza de date nu a reusit!...",
						  "Error", JOptionPane.ERROR_MESSAGE);
            }   
         }
	}
	
	/**
	 * @return ResultSet de la baza de date.
	 */
	public ResultSet getResultSet()
	{
		return rs;
	}
	
	/**
	 * @return true daca exista date in rs fals daca nu exista.
	 */
	public boolean isResultSet()
	{
		try
		{
			return rs.next();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Verificarea datelor nu a reusit!...",
					  "Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	public String getNextString(String col)
	{
		try
		{
			return rs.getString(col);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Verificarea datelor nu a reusit!...",
					  "Error", JOptionPane.ERROR_MESSAGE);
		}
		return "Eroare";
	}
	
	public Boolean getNextBoolean(String col)
	{
		try
		{
			return rs.getBoolean(col);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Verificarea datelor nu a reusit!...",
					  "Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	public Date getNextDate(String col)
	{
		try
		{
			return rs.getDate(col);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Verificarea datelor nu a reusit!...",
					  "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	public int getNextInt(String col)
	{
		try
		{
			return rs.getInt(col);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Verificarea datelor nu a reusit!...",
					  "Error", JOptionPane.ERROR_MESSAGE);
		}
		return 0;
	}
}

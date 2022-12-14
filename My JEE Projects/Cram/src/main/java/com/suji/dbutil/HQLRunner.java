package com.suji.dbutil;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;


public class HQLRunner extends JFrame implements ActionListener, ListSelectionListener{
	
	private SessionFactory sessionFactory = null;
	
	// all containers 
	private JPanel pnlTop = new JPanel(new BorderLayout());
	private JPanel pnlBottom = new JPanel(new BorderLayout());	
	private JSplitPane pnlSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlTop, pnlBottom);

	// all components
	private JTextArea txtHQL = new JTextArea(6,100);
	
	private JComboBox cmbLimit = new JComboBox(new String[]{"All", "100", "1000", "10000"});
	private JButton btnRunHQL = new JButton("Run HQL Query");
	private JButton btnRunSQL = new JButton("Run SQL Query");   
	
	private JTable tblResult = new JTable();
	private JTextArea txtGeneratedSQL = new JTextArea();
	private JList lstHistory = new JList();	

	// model holds all the previously executed queries...
	private List<String> lstQueryHistory = new ArrayList<String>();
	
	private HQLRunner(SessionFactory sessFact){
		
		sessionFactory = sessFact;
		
		setMinimumSize(new Dimension(800,600));
		setTitle("HQL Runner");
		
		pnlTop.setBorder(new javax.swing.border.TitledBorder("Enter your HQL below and hit 'Run'"));
		pnlTop.add(txtHQL, BorderLayout.CENTER);
		cmbLimit.setSelectedIndex(1); // limit 100 is selected by default...
		btnRunHQL.addActionListener(this);
		btnRunSQL.addActionListener(this);
		
		pnlTop.add( new JPanel() {{ 
							add(new JLabel(" Limit"));
							add(cmbLimit);
							add(new JLabel("Rows "));
							add(btnRunHQL);
							add(btnRunSQL);			
						}}
						, BorderLayout.SOUTH);
		
		pnlBottom.add( new JTabbedPane() {{
								addTab("Result", new JScrollPane(tblResult));
								addTab("Generated SQL", new JScrollPane(txtGeneratedSQL));
								addTab("History", lstHistory);
							}}, BorderLayout.CENTER);
		
		lstHistory.addListSelectionListener(this);
		lstHistory.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

		pnlSplit.setDividerLocation(0.4d);
		
		getContentPane().add(pnlSplit);
		
		setVisible(true);
	}

	public void runHQL(String strHQL){
		addToHistory(strHQL);
		txtGeneratedSQL.setText(toSql(strHQL));
		
		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();
		
		Query q = session.createQuery(strHQL);
		
		if (!"All".equals(cmbLimit.getSelectedItem())) {
			q.setMaxResults( Integer.parseInt((String)cmbLimit.getSelectedItem()) );
		}
		
		List lst = q.list();
		setResult(lst);
	}
	
	public void runSQL(String strHQL){
		addToHistory(strHQL);
		
		Session session = sessionFactory.openSession();
		Transaction trans = session.getTransaction();
		trans.begin();
		 NativeQuery query = session.createSQLQuery(strHQL);
		
		
		if (!"All".equals(cmbLimit.getSelectedItem())) {
			query.setMaxResults( Integer.parseInt((String)cmbLimit.getSelectedItem()) );
		}
		List lst = query.list();
		trans.commit();
		setResult(lst);
	}	
	
	private void addToHistory(String strHQL){
		
		lstQueryHistory.add(strHQL);
		lstHistory.setModel(new ListModel() {
			@Override public void removeListDataListener(ListDataListener arg0) {}
			@Override public int getSize() { return lstQueryHistory.size();}
			@Override public Object getElementAt(int index) { return lstQueryHistory.get(index);}
			@Override public void addListDataListener(ListDataListener arg0) {}
		});		
	}
	
	private void setResult(final List lst){
		
		tblResult.setModel(new TableModel() {
			
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
			
			@Override
			public void removeTableModelListener(TableModelListener l) {}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {   
				
				Object row = lst.get(rowIndex);
				if (row instanceof Object[]) {
					Object[] cols = (Object[])row;
					return cols[columnIndex];
				}else {
					try{
						BeanInfo testBeanInfo = Introspector.getBeanInfo(row.getClass());
						return testBeanInfo.getPropertyDescriptors()[columnIndex].getReadMethod().invoke(row);
					}catch(Exception ex){
						ex.printStackTrace();
						return row;
					}
				}
			}
			
			@Override
			public int getRowCount() { return lst.size();}
			
			@Override
			public String getColumnName(int columnIndex) {
				if (lst.size()>0){
					Object row = lst.get(0);
					if (row instanceof Object[]) {
						return ((Object[])row)[columnIndex].getClass().getName();
					}else {
						try {
							BeanInfo testBeanInfo = Introspector.getBeanInfo(row.getClass());
							return testBeanInfo.getPropertyDescriptors()[columnIndex].getName();							
						} catch (IntrospectionException e) {
							e.printStackTrace();
							return "????";
						}
					}
				}					
				return "";
			}
			
			@Override
			public int getColumnCount() {
				if (lst.size()>0){
					Object row = lst.get(0);
					if (row instanceof Object[]) {
						return ((Object[])row).length;
					}else {
						try {
							BeanInfo testBeanInfo = Introspector.getBeanInfo(row.getClass());
							return testBeanInfo.getPropertyDescriptors().length;
						} catch (IntrospectionException e) {
							e.printStackTrace();
							return 0;
						}
					}
				}
				return 0;
			}
			
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (lst.size()>0){
					Object row = lst.get(0);
					if (row instanceof Object[]) {
						return ((Object[])row)[columnIndex].getClass();
					}else return Object.class;
				}				
				return null;
			}
			
			@Override
			public void addTableModelListener(TableModelListener l) {
				
			}
		});		
	}

	// ref: http://narcanti.keyboardsamurais.de/hibernate-hql-to-sql-translation.html
	public String toSql(String hqlQueryText){

	    if (hqlQueryText!=null && hqlQueryText.trim().length()>0){
	      final QueryTranslatorFactory translatorFactory = new ASTQueryTranslatorFactory();
	      final SessionFactoryImplementor factory = (SessionFactoryImplementor) sessionFactory;
	      final QueryTranslator translator = translatorFactory.createQueryTranslator(hqlQueryText, hqlQueryText, java.util.Collections.EMPTY_MAP , factory, null);
	      translator.compile(java.util.Collections.EMPTY_MAP, false);
	      return translator.getSQLString();
	    }
	    return null;
	  }
	

	/****  Event-Handlers  ****/ 
	public void actionPerformed(ActionEvent e) {
		
		
		setResult(new ArrayList());
		
		
		
		txtGeneratedSQL.setText("");		
		if (e.getSource() == btnRunHQL) runHQL(txtHQL.getText());
		else if (e.getSource() == btnRunSQL) runSQL(txtHQL.getText());
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		txtHQL.setText(lstQueryHistory.get(lstHistory.getSelectedIndex()));
	}

	
	/****  Main Method  ****/
	public static void main(String[] args) {
		HQLRunner runner = new HQLRunner(HibernateUtil.getSessionFactory());
		runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

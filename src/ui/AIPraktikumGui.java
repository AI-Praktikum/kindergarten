/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Component;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import kindergarten.admin.Preisinfo;
import kindergarten.helper.DBGruppe;
import kindergarten.helper.DBKind;
import kindergarten.helper.DBWarteliste;
import kindergarten.helper.DBhelpers;
import kindergarten.model.Gruppe;
import kindergarten.model.Kind;
import kindergarten.model.Registrierung;
import kindergarten.model.Warteliste;

/**
 *
 * @author andy
 */
public class AIPraktikumGui extends javax.swing.JFrame {

    /**
     * Creates new form AIPraktikumGui
     */
    public AIPraktikumGui() {
        initComponents();
        Gruppe gr;
        if(jComboBoxGruppe.getSelectedItem() != null){
            gr = (Gruppe)jComboBoxGruppe.getSelectedItem();
            DefaultListModel lm = new DefaultListModel();

            List<Kind> kind = new ArrayList<Kind>();
            kind.addAll(gr.getKindCollection());

            for(Kind k : kind){
                lm.addElement(k);
            }
            jList1.setModel(lm);
        }
        Warteliste wl;
        if(jComboBox1.getSelectedItem() != null){
            wl = (Warteliste)jComboBox1.getSelectedItem();
            DefaultListModel lm = new DefaultListModel();

            List<Registrierung> kinder = new ArrayList<Registrierung>();
            kinder.addAll(wl.getRegistrierungCollection());

            for(Registrierung r : kinder){                
                lm.addElement(r);
            }
            jList2.setModel(lm);
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:Inf09PU").createEntityManager();
        gruppeQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT g FROM Gruppe g");
        gruppeList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : gruppeQuery.getResultList();
        kindQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT k FROM Kind k");
        kindList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : kindQuery.getResultList();
        wartelisteQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT w FROM Warteliste w");
        wartelisteList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : wartelisteQuery.getResultList();
        kindQuery1 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT k FROM Kind k");
        kindList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : kindQuery1.getResultList();
        kindQuery2 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT k FROM Kind k");
        kindList2 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : kindQuery2.getResultList();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxGruppe = new javax.swing.JComboBox();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        RBverschieben = new javax.swing.JRadioButton();
        jCverschieben = new javax.swing.JComboBox();
        BTverschieben = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        BTverschieben2 = new javax.swing.JButton();
        RBverschieben2 = new javax.swing.JRadioButton();
        jCverschieben2 = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));

        jInternalFrame1.setVisible(true);

        jLabel2.setText("Kind:");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, kindList, jComboBox2);
        bindingGroup.addBinding(jComboBoxBinding);

        jButton7.setText("Kind hinzufuegen...");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, 0, 573, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(0, 488, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(jButton7)
                .addContainerGap(355, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Kinder", jPanel1);

        jLabel4.setText("Gruppe:");

        jComboBoxGruppe.setRenderer((new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Gruppe) {
                    Gruppe g = (Gruppe)value;
                    setText(g.getBezeichnung());
                }
                return this;
            }
        }));

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, gruppeList, jComboBoxGruppe, "");
        bindingGroup.addBinding(jComboBoxBinding);
        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, gruppeList, org.jdesktop.beansbinding.ObjectProperty.create(), jComboBoxGruppe, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jComboBoxGruppe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxGruppeItemStateChanged(evt);
            }
        });
        jComboBoxGruppe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGruppeActionPerformed(evt);
            }
        });
        jComboBoxGruppe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxGruppePropertyChange(evt);
            }
        });

        jLabel1.setText("Kind:");

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${selectedItem.kindCollection}");
        org.jdesktop.swingbinding.JListBinding jListBinding = org.jdesktop.swingbinding.SwingBindings.createJListBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jComboBoxGruppe, eLProperty, jList1);
        jListBinding.setDetailBinding(org.jdesktop.beansbinding.ELProperty.create("${nachname}"));
        bindingGroup.addBinding(jListBinding);

        jScrollPane1.setViewportView(jList1);

        jLabel3.setText("Plaetze max:");

        jLabel5.setText("MAX");

        jLabel6.setText("Plaetze frei:");

        jLabel7.setText("FREI");

        jButton8.setText("Kind Details...");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        RBverschieben.setText("Kind verschieben in ...");
        RBverschieben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBverschiebenActionPerformed(evt);
            }
        });

        jCverschieben.setEnabled(false);

        BTverschieben.setText("Verschieben");
        BTverschieben.setEnabled(false);
        BTverschieben.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTverschiebenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton8)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3)
                                .addComponent(jLabel7))
                            .addComponent(jLabel6)
                            .addComponent(RBverschieben, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCverschieben, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BTverschieben, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(76, 76, 76)
                        .addComponent(RBverschieben)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCverschieben, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTverschieben))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setText("Neue Gruppe...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxGruppe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxGruppe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Gruppen", jPanel2);

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, wartelisteList, jComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel8.setText("Warteliste:");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${selectedItem.registrierungCollection}");
        jListBinding = org.jdesktop.swingbinding.SwingBindings.createJListBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jComboBox1, eLProperty, jList2);
        jListBinding.setDetailBinding(org.jdesktop.beansbinding.ELProperty.create("${kind.nachname}"));
        bindingGroup.addBinding(jListBinding);

        jScrollPane2.setViewportView(jList2);

        jLabel9.setText("Kind:");

        jButton9.setText("Kind Details...");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        BTverschieben2.setText("Verschieben");
        BTverschieben2.setEnabled(false);
        BTverschieben2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTverschieben2ActionPerformed(evt);
            }
        });

        RBverschieben2.setText("Kind verschieben in...");
        RBverschieben2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBverschieben2ActionPerformed(evt);
            }
        });

        jCverschieben2.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jButton9)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jCverschieben2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(RBverschieben2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(BTverschieben2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RBverschieben2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCverschieben2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTverschieben2)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Wartelisten", jPanel4);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jInternalFrame1.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        NewGrpDialog dia = new NewGrpDialog(this, true);
        dia.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        NewKindDialog dia = new NewKindDialog(this, true);
        dia.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBoxGruppeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGruppeActionPerformed
        
    }//GEN-LAST:event_jComboBoxGruppeActionPerformed

    private void jComboBoxGruppePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxGruppePropertyChange
        
    }//GEN-LAST:event_jComboBoxGruppePropertyChange

    private void jComboBoxGruppeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxGruppeItemStateChanged
        if (evt.getStateChange() == evt.SELECTED){
            Gruppe gr = (Gruppe)jComboBoxGruppe.getSelectedItem();

            DefaultListModel lm = new DefaultListModel();
        
            List<Kind> kind = new ArrayList<Kind>();
            kind.addAll(gr.getKindCollection());
            System.out.println(kind.size());
        
            for(Kind k : kind){
                lm.addElement(k);
            }
            jList1.setModel(lm);
            
            
            jLabel5.setText(gr.getGruppengroesse().toString());
            jLabel7.setText(gr.getGruppengroesse().subtract(new BigInteger(String.valueOf(kind.size()))).toString());
        }
    }//GEN-LAST:event_jComboBoxGruppeItemStateChanged

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(jList1.getSelectedValue() != null){
            KindInfoDialog kid = new KindInfoDialog(this, true);
            Kind k = (Kind)jList1.getSelectedValue();
            // preis berechnen
            int preis = Preisinfo.getPrice(k);
            kid.setTextFields(k.getNachname(), k.getVorname(), k.getGeburtsdatum(), k.getElternteilId().getName(), k.getElternteilId().getAdresse(), preis);
            kid.setVisible(true);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (evt.getStateChange() == evt.SELECTED){
            Warteliste wl;
            if(jComboBox1.getSelectedItem() != null){
                wl = (Warteliste)jComboBox1.getSelectedItem();
                DefaultListModel lm = new DefaultListModel();

                List<Registrierung> kinder = new ArrayList<Registrierung>();
                kinder.addAll(wl.getRegistrierungCollection());

                for(Registrierung k : kinder){
                    lm.addElement(k);
                }
                jList2.setModel(lm);
            }
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(jList2.getSelectedValue() != null){
            KindInfoDialog kid = new KindInfoDialog(this, true);
            Kind k = ((Registrierung)jList2.getSelectedValue()).getKind();
            // preis berechnen
            int preis = Preisinfo.getPrice(k);
            kid.setTextFields(k.getNachname(), k.getVorname(), k.getGeburtsdatum(), k.getElternteilId().getName(), k.getElternteilId().getAdresse(), preis);
            kid.setVisible(true);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void RBverschiebenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBverschiebenActionPerformed
        // TODO add your handling code here:
        if(RBverschieben.isSelected()){
            jCverschieben.removeAllItems();
            List<Gruppe> free = DBGruppe.getFreeGroups();
            List<Warteliste> wl = DBWarteliste.getAll();
            for(Gruppe g : free){
                jCverschieben.addItem(g);
            }  
            for(Warteliste w : wl){
                jCverschieben.addItem(w);
            }
            jCverschieben.setEnabled(true);
            BTverschieben.setEnabled(true);
        }else{
            jCverschieben.setEnabled(false);
            BTverschieben.setEnabled(false);
        }
    }//GEN-LAST:event_RBverschiebenActionPerformed

    private void BTverschiebenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTverschiebenActionPerformed
        // TODO add your handling code here:
        Kind kind = (Kind)jList1.getSelectedValue();
        Gruppe oldGroup = (Gruppe)jComboBoxGruppe.getSelectedItem();
        if(jCverschieben.getSelectedItem() instanceof Gruppe){
            Gruppe newGroup = (Gruppe)jCverschieben.getSelectedItem(); 
            DBKind.shift(kind,oldGroup,newGroup);
        }else{
            DBKind.shift(kind,oldGroup,(Warteliste)jCverschieben.getSelectedItem());
        }
      
    }//GEN-LAST:event_BTverschiebenActionPerformed

    private void RBverschieben2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBverschieben2ActionPerformed
       if(RBverschieben2.isSelected()){
            jCverschieben2.removeAllItems();
            List<Gruppe> free = DBGruppe.getFreeGroups();
            List<Warteliste> wl = DBWarteliste.getAll();
            for(Gruppe g : free){
                jCverschieben2.addItem(g);
            }  
            for(Warteliste w : wl){
                jCverschieben2.addItem(w);
            }
            jCverschieben2.setEnabled(true);
            BTverschieben2.setEnabled(true);
        }else{
            jCverschieben2.setEnabled(false);
            BTverschieben2.setEnabled(false);
        }
    }//GEN-LAST:event_RBverschieben2ActionPerformed

    private void BTverschieben2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTverschieben2ActionPerformed
        Registrierung r = (Registrierung)jList2.getSelectedValue();
        Warteliste source = (Warteliste)jComboBox1.getSelectedItem();
        if(!(r == null)){
            if(jCverschieben2.getSelectedItem() instanceof Gruppe){
                Gruppe newGroup = (Gruppe)jCverschieben2.getSelectedItem(); 
                DBKind.shift(r,source,newGroup);
            }else{
                DBKind.shift(r,source,(Warteliste)jCverschieben2.getSelectedItem());
            }
        }
    }//GEN-LAST:event_BTverschieben2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AIPraktikumGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AIPraktikumGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AIPraktikumGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AIPraktikumGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AIPraktikumGui().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTverschieben;
    private javax.swing.JButton BTverschieben2;
    private javax.swing.JRadioButton RBverschieben;
    private javax.swing.JRadioButton RBverschieben2;
    private javax.persistence.EntityManager entityManager;
    private java.util.List<kindergarten.model.Gruppe> gruppeList;
    private javax.persistence.Query gruppeQuery;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBoxGruppe;
    private javax.swing.JComboBox jCverschieben;
    private javax.swing.JComboBox jCverschieben2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private java.util.List<kindergarten.model.Kind> kindList;
    private java.util.List<kindergarten.model.Kind> kindList1;
    private java.util.List<kindergarten.model.Kind> kindList2;
    private javax.persistence.Query kindQuery;
    private javax.persistence.Query kindQuery1;
    private javax.persistence.Query kindQuery2;
    private java.util.List<kindergarten.model.Warteliste> wartelisteList;
    private javax.persistence.Query wartelisteQuery;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}

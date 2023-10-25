// FarmaciAPP en Java para NetBeans created by Flerkendroid - Miércoles 25 de Octubre año 2023.-

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Farmacia extends JFrame {

    private JTextField codigoField;
    private JTextField nombreField;
    private JTextField descripcionField;
    private JTextField precioVentaField;
    private JTextField stockField;
    private JTextField fechaIngresoField;
    private JTextField unidadesAComprarField;
    private JTextField totalCompraField;

    public Farmacia() {
        JLabel codigoLabel = new JLabel("Código:");
        codigoField = new JTextField(10);
        JLabel nombreLabel = new JLabel("Nombre del medicamento:");
        nombreField = new JTextField(20);
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionField = new JTextField(30);
        JLabel precioVentaLabel = new JLabel("Precio de venta:");
        precioVentaField = new JTextField(10);
        JLabel stockLabel = new JLabel("Stock:");
        stockField = new JTextField(10);
        JLabel fechaIngresoLabel = new JLabel("Fecha de ingreso:");
        fechaIngresoField = new JTextField(10);
        JLabel unidadesAComprarLabel = new JLabel("Unidades a comprar:");
        unidadesAComprarField = new JTextField(10);
        JLabel totalCompraLabel = new JLabel("Total de la compra:");
        totalCompraField = new JTextField(10);
        totalCompraField.setEditable(false); // No se puede modificar
        JButton realizarVentaButton = new JButton("Realizar Venta");
        realizarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarVenta();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(codigoLabel);
        panel.add(codigoField);
        
        panel.add(nombreLabel);
        panel.add(nombreField);
        
        panel.add(descripcionLabel);
        panel.add(descripcionField);
        
        panel.add(precioVentaLabel);
        panel.add(precioVentaField);
        
        panel.add(stockLabel);
        panel.add(stockField);
        
        panel.add(fechaIngresoLabel);
        panel.add(fechaIngresoField);
        
        panel.add(unidadesAComprarLabel);
        panel.add(unidadesAComprarField);
        
         panel.add(totalCompraLabel);
         panel.add(totalCompraField);

         panel.add(realizarVentaButton);

         this.add(panel);

    }

    public void realizarVenta() {
        

       Calculos calculos = new Calculos();
       
       String codigo = codigoField.getText();
       String nombre = nombreField.getText();
       String descripcion = descripcionField.getText();
       
       double precioVenta = Double.parseDouble(precioVentaField.getText());
       
       int stock = Integer.parseInt(stockField.getText());
       
       LocalDate fechaIngreso = LocalDate.parse(fechaIngresoField.getText());
       
       int unidadesAComprar = Integer.parseInt(unidadesAComprarField.getText());
       
      double totalCompra = 0.0;
      
      String detalleCompra = "";
      
      // Recorres los campos de texto relacionados con los medicamentos con el ciclo for
      for (int i = 0; i < 4; i++) {
          // Obtienes el código, el nombre, el precio y las unidades de cada medicamento de la Farmacia
          codigo = fields[i*2].getText();
          nombre = fields[i*2+1].getText();
          precioVenta = Double.parseDouble(fields[i*2+4].getText());
          unidadesAComprar = Integer.parseInt(fields[i*2+7].getText());
          
          double subtotal = calculos.calcularVenta(precioVenta, unidadesAComprar);

          totalCompra += subtotal;
          
          // Se actualiza el detalle de la compra con el código, el nombre, el precio y las unidades de cada medicamento
          detalleCompra += "Código: " + codigo + "\n";
          detalleCompra += "Nombre: " + nombre + "\n";
          detalleCompra += "Precio: " + precioVenta + "\n";
          detalleCompra += "Unidades: " + unidadesAComprar + "\n";
          detalleCompra += "Subtotal: " + subtotal + "\n";
          detalleCompra += "---------------------\n";
      }
      
      if (calculos.esLunes(fechaIngreso)) {
          totalCompra = calculos.aplicarDescuento(totalCompra, 0.15);
      }
      
      totalCompraField.setText(String.valueOf(totalCompra));
      
      JOptionPane.showMessageDialog(this, detalleCompra, "Detalle de la compra", JOptionPane.INFORMATION_MESSAGE);
      
      limpiarCampos();
   }
   
   public void limpiarCampos() {
       for (int i = 0; i < fields.length; i++) {
           fields[i].setText("");
       }
   }

   public static void main(String[] args) {
       Farmacia farmacia = new Farmacia();
       farmacia.setVisible(true);
   }
}

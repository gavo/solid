package soe.mdeis.m7.solid.service;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soe.mdeis.m7.solid.model.Venta;
import soe.mdeis.m7.solid.repository.VentaRepository;

@Service
public class VentaService {

      @Autowired
      VentaRepository repository;

      public Venta save(Venta venta) {
            venta.setFecha(LocalDateTime.now());

            BigDecimal subTotalDescuentoProducto = venta.getProductos().stream()
                        .map(producto -> producto.getDescuento()).reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal subTotalDescuentoServicio = venta.getServicios().stream()
                        .map(servicio -> servicio.getDescuento()).reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal subTotalProducto = venta.getProductos().stream()
                        .map(producto -> producto.getPrecio()
                                    .multiply(BigDecimal.valueOf(producto.getCantidad())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal subTotalServicio = venta.getServicios().stream()
                        .map(servicio -> servicio.getPrecio()).reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal subTotal = subTotalProducto.add(subTotalServicio);
            venta.setSubTotal(subTotal);

            BigDecimal subTotalDescuento = subTotalDescuentoProducto.add(subTotalDescuentoServicio);

            if (venta.getCliente() != null && venta.getCliente().getGrupoCliente() != null) {
                  BigDecimal descuentoCliente = venta.getCliente().getGrupoCliente().getDescuento()
                              .setScale(1, RoundingMode.HALF_UP);

                  BigDecimal descuento = subTotal.subtract(subTotalDescuento)
                              .multiply(descuentoCliente)
                              .divide(BigDecimal.valueOf(100), 1, RoundingMode.HALF_UP)
                              .add(subTotalDescuento);
                  venta.setDescuento(descuento);
            } else {
                  venta.setDescuento(subTotalDescuento);
            }

            venta.setTotal(venta.getSubTotal().subtract(venta.getDescuento()));

            if (venta.getFactura() != null) {
                  venta.getFactura().setTotal(venta.getTotal());
                  venta.getFactura().setFecha(LocalDateTime.now());
                  venta.getFactura().setCreditoFiscal(
                              venta.getTotal().multiply(BigDecimal.valueOf(0.15)).setScale(1, RoundingMode.HALF_UP));
            }

            return repository.save(venta);
      }

      public List<Venta> getAllVentas() {
            return repository.findAll();
      }

}

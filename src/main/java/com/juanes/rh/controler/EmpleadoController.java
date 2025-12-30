package com.juanes.rh.controler;

import com.juanes.rh.exception.ResourceNoFound;
import com.juanes.rh.modelos.Empleado;
import com.juanes.rh.service.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:5173")
public class EmpleadoController {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> getEmpleados() {
        var empleados = empleadoService.getAllEmpleados();
        empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado addEmpleado(@RequestBody Empleado empleado) {
        logger.info("Adding empleado: " + empleado.toString());
        return empleadoService.saveEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Integer id) {
        logger.info("Fetching empleado with id: " + id);
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if (empleado == null) {
            throw new ResourceNoFound("Empleado with id " + id + " not found.");
        }
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoDetails) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if (empleado != null) {
            empleado.setNombre(empleadoDetails.getNombre());
            empleado.setDepartamento(empleadoDetails.getDepartamento());
            empleado.setSalario(empleadoDetails.getSalario());
            logger.info("Updating empleado with id: " + id + " to new values: " + empleado.toString());
            empleadoService.saveEmpleado(empleado);
            return ResponseEntity.ok(empleado);
        } else {
            throw new ResourceNoFound("Empleado with id " + id + " not found.");
        }
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmpleado(@PathVariable Integer id) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if (empleado != null) {
            empleadoService.deleteEmpleado(id);
            logger.info("Deleted empleado with id: " + id);
            return ResponseEntity.ok(Map.of("deleted", Boolean.TRUE));
        } else {
            throw new ResourceNoFound("Empleado with id " + id + " not found.");
        }
    }
}

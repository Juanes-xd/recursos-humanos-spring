package com.juanes.rh.controler;

import com.juanes.rh.modelos.Empleado;
import com.juanes.rh.service.IEmpleadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Empleado getEmpleadoById(@PathVariable Integer id) {
        logger.info("Fetching empleado with id: " + id);
        return empleadoService.getEmpleadoById(id);
    }

    @PutMapping("/empleados/{id}")
    public Empleado updateEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoDetails) {
        Empleado empleado = empleadoService.getEmpleadoById(id);
        if (empleado != null) {
            empleado.setNombre(empleadoDetails.getNombre());
            empleado.setDepartamento(empleadoDetails.getDepartamento());
            empleado.setSalario(empleadoDetails.getSalario());
            logger.info("Updating empleado with id: " + id + " to new values: " + empleado.toString());
            return empleadoService.saveEmpleado(empleado);
        } else {
            logger.warn("Empleado with id " + id + " not found for update.");
            return null;
        }
    }

    @DeleteMapping("/empleados/{id}")
    public void deleteEmpleado(@PathVariable Integer id) {
        logger.info("Deleting empleado with id: " + id);
        empleadoService.deleteEmpleado(id);
    }
}

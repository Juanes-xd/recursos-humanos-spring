package com.juanes.rh.service;

import com.juanes.rh.modelos.Empleado;

import java.util.List;

public interface IEmpleadoService {
    public List<Empleado> getAllEmpleados();
    public Empleado getEmpleadoById(Integer id);
    public Empleado saveEmpleado(Empleado empleado);
    public void deleteEmpleado(Integer id);
}
